import java.util.regex.Pattern;

public class Password {
    static final int LONGITUD_MINIMA_PASSWORD = 8;
    // Códigos de error
    //
    public static final String ERR_LONGITUD = "Longitud no válida";
    public static final String ERR_SIN_NUMEROS = "La contraseña debe tener al menos un número";
    public static final String ERR_SIN_MINUSCULAS = "La contraseña debe tener al menos una minúscula";
    public static final String ERR_SIN_MAYUSCULAS = "La contraseña debe tener al menos una mayúscula";
    //
    // Regex
    //
    static final String REGEX_DIGITO = "^.*\\d.*$";
    static final String REGEX_MINUSCULA = "^.*[a-z].*$";
    static final String REGEX_MAYUSCULA = "^.*[A-Z].*$";
    //
    public static void validar(String password) throws Exception {
        if (password.length() < LONGITUD_MINIMA_PASSWORD)
            throw new Exception(ERR_LONGITUD);
       if (!Pattern.matches(REGEX_DIGITO, password))
            throw new Exception(ERR_SIN_NUMEROS);
        if (!Pattern.matches(REGEX_MINUSCULA, password))
            throw new Exception(ERR_SIN_MINUSCULAS);
        if (!Pattern.matches(REGEX_MAYUSCULA, password))
            throw new Exception(ERR_SIN_MAYUSCULAS);
    }
}