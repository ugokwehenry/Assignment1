package henry.ugokwe.test.security;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.henry.ugokwe.Security;
import com.henry.ugokwe.SecurityImpl;

/**
 * @author Henry.U Security Service test case
 */
public class SecurityTest {
	public Security securityClassTest;

	@Before
	public void setUp() throws Exception {
		securityClassTest = new SecurityImpl();

	}

	@Test
	public void testAuthenticateUser() {
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
