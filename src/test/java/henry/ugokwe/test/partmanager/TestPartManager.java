/**
 * 
 */
package henry.ugokwe.test.partmanager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.henry.ugokwe.DeliveryAddress;
import com.henry.ugokwe.PartManager;

/**
 * @author HENRY
 *
 */
public class TestPartManager {
	public PartManager partManagerClassTest;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		partManagerClassTest = new PartManager(){

			public PartResponse SubmitPartForManufactureAndDelivery(
					int parNtumber, int quantity,
					DeliveryAddress deliveryAddress) {
			int partNo = 2;
			int qty = 3;
				return null;
			}
			
		};
	}

	@Test
	public void testSubmitPartForManufactureAndDelivery() {
		int partNo = 222;
		int qty = 3333;

		
		//boolean val = securityClassTest.IsDealerAuthorized(delearId, delearAccessKey);
		//assertEquals(isAuthorized, val);
	}

}
