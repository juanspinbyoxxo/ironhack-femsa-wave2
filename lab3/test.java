package lab3;
TEST DataProcessing
  DATA data = fetchData()
  TRY
    processData(data)
    ASSERT_TRUE(data.processedSuccessfully, "Data should be processed successfully")
  CATCH error
    ASSERT_EQUALS("Data processing error", error.message, "Should handle processing errors")
  END TRY
END TEST
/** 
 * se analiza que esta probando una funcion para validar la autentiacion de un usuario, tambien se visualiza que quiere probar 
 * los 2 escenarios en el mismo test 
 * 
 * 
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class AuthenticatorTest {
    @Test
    public void testAuthenticateWithValidCredentials() {
        Authenticator auth = new Authenticator();
        assertTrue("Should succeed with correct credentials", auth.authenticate("validUser", "validPass"));
    }

    @Test
    public void testAuthenticateWithInvalidCredentials() {
        Authenticator auth = new Authenticator();
        assertFalse("Should fail with wrong credentials", auth.authenticate("validUser", "wrongPass"));
    }
}
/**
 * ahora reescribimos el test creando dos test 1 para el escenario donde las credenciales son correctas y otro mas para validar el
 * escenario donde las credenciales son incorrectas 
 * 
 */