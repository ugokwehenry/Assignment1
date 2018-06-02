package henry.ugokwe.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.henry.ugokwe.DatabaseImpl;

public class DatabaseTestCase {
private DatabaseImpl DatabaseClassTest;

	@Before
	public void setUp() throws Exception {
		DatabaseClassTest = new DatabaseImpl();
	}

	@Test
	public void testDatabase() {
		int partNumber = 1;
		int quantity = 2;
		//assertEquals(2, DatabaseClassTest.SubmitPartForManufacture(partNumber, quantity));
	}
	
}
