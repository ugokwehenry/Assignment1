package henry.ugokwe.test.security;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.henry.ugokwe.Security;

/**
 * @author Henry.U Security Service test case
 */
public class SecurityTest {
	public Security securityClassTest;

	@Before
	public void setUp() throws Exception {
		securityClassTest = new Security() {
			
			public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey) {
				String dealerId = "XXX-1234-ABCD-1234";
				String accessKey = "kkklas8882kk23nllfjj88290";
				if (dealerid.trim().equals(dealerId)) {
					if (dealeraccesskey.trim().equals(accessKey)) {
						return true;
					}
				}
				return false;
			}
		};
	}

	@Test
	public void testAuthenticateDealer() {
		boolean isAuthorized = true;	 
		String delearId = "XXX-1234-ABCD-1234";
		String delearAccessKey = "kkklas8882kk23nllfjj88290";

		boolean val = securityClassTest.IsDealerAuthorized(delearId, delearAccessKey);
		assertEquals(isAuthorized, val);
	}
	
	@Test
	public void testValidateXML(){
		
		
	}

}
