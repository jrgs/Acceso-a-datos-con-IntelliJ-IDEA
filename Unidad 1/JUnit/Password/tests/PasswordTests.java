import org.junit.Assert;
import org.junit.Test;

public class PasswordTests {
    @Test
    public void passwordCorrecto() throws Exception {
        String password = "MiPassword123";
        Password.validar(password);
    }

    @Test
    public void passwordSinNumeros() {
        String password = "MiPassword";
        try {
            Password.validar(password);
        }
        catch (Exception exception) {
            if (exception.getMessage().equals(Password.ERR_SIN_NUMEROS))
                    return;
        }
        Assert.fail();
    }

    @Test
    public void passwordSinMinusculas() {
        String password = "MIPASSWORD123";
        try {
            Password.validar(password);
        }
        catch (Exception exception) {
            if (exception.getMessage().equals(Password.ERR_SIN_MINUSCULAS))
                return;
        }
        Assert.fail();
    }

    @Test
    public void passwordSinMayusculas() {
        String password = "mipassword123";
        try {
            Password.validar(password);
        }
        catch (Exception exception) {
            if (exception.getMessage().equals(Password.ERR_SIN_MAYUSCULAS))
                return;
        }
        Assert.fail();
    }
}
