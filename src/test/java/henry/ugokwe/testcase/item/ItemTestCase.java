package henry.ugokwe.testcase.item;

import static org.junit.Assert.*;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.henry.ugokwe.Item;

public class ItemTestCase {
	private Item itemUnderTest;

	static final String VALID_TEST_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<item>\n"
			+ "	<uniqueID>1234</uniqueID>\n"
			+ "	<quantity>2</quantity>\n" + "</item>";
	static final String INVALID_UNIQUE_ID = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<item>\n"
			+ "	<uniqueID>A</uniqueID>\n"
			+ "	<quantity>2</quantity>\n" + "</item>";
	static final String INVALID_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<item>\n"
			+ "	<uniqueID>1234</uniqueID>\n"
			+ "	<quantity>A</quantity>\n" + "</item>";
	static final String INVALID_QUANTITY_NEGATIVE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<item>\n"
			+ "	<uniqueID>1234</uniqueID>\n"
			+ "	<quantity>-1</quantity>\n" + "</item>";
	static final String INVALID_QUANTITY_ZERO = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<item>\n"
			+ "	<uniqueID>1234</uniqueID>\n"
			+ "	<quantity>0</quantity>\n" + "</item>";
	static final String MISSING_UNIQUE_ID = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<item>\n" + "	<quantity>2</quantity>\n" + "</item>";
	static final String MISSING_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<item>\n" + "	<uniqueID>1234</uniqueID>\n" + "</item>";

	@Before
	public void setUp() throws Exception {
		//itemUnderTest = new Item();
	}

	@Test
	public void testItem() {
		int partNo = 0;
		int quantity = 0;
		boolean validate = true;
		assertEquals(true, itemUnderTest.validate());
	}
	@Test
	void itemTest()
	{
		Item item = deserializeXMLToItemObject(VALID_TEST_XML);
		assertNotNull(item);
		assertEquals("1234", item.getPartNumber());
		assertEquals("2", item.getQuantity());
	}

	@Test
	void invalidUniqueIDTest()
	{
		Item item = deserializeXMLToItemObject(INVALID_UNIQUE_ID);
		assertNotNull(item);
		assertTrue(!item.validate());
	}

	@Test
	void invalidQuantityTest()
	{
		Item item = deserializeXMLToItemObject(INVALID_QUANTITY);
		assertNotNull(item);
		assertTrue(!item.validate());
	}

	@Test
	void invalidNegativeQuantity()
	{
		Item item = deserializeXMLToItemObject(INVALID_QUANTITY_NEGATIVE);
		assertNotNull(item);
		assertTrue(!item.validate());
	}

	@Test
	void invalidZeroQuantity()
	{
		Item item = deserializeXMLToItemObject(INVALID_QUANTITY_ZERO);
		assertNotNull(item);
		assertTrue(!item.validate());
	}

	@Test
	void invalidMissingUniqueID()
	{
		Item item = deserializeXMLToItemObject(MISSING_UNIQUE_ID);
		assertNotNull(item);
		assertTrue(!item.validate());
	}

	@Test
	void invalidMissingQuantity()
	{
		Item item = deserializeXMLToItemObject(MISSING_QUANTITY);
		assertNotNull(item);
		assertTrue(!item.validate());
	}
	
	// This is a helper method for the test to ensure I don't duplicate the XML deserialization
		// in every test.  Where else do you think this method should go?  There's a better place...
		// Remember this method when we learn about factories.
		private Item deserializeXMLToItemObject(String xml)
		{
			try
			{
				StringReader reader = new StringReader(xml);
				JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Item item = (Item) jaxbUnmarshaller.unmarshal(reader);
				return item;
			} 
			catch (JAXBException e)
			{
				e.printStackTrace();
			}
			return null;
		}
}
