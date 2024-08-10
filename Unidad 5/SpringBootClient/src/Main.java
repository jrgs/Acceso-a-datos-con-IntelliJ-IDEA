import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Base64;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;

public class Main {
    public static void main(String[] args) {
        try {
            PostRequest();
            //GetRequest();
        }
        catch(JSONException jsonException) {
            System.out.println("Ocurrió un error.");
        }
    }

    public static SSLContext getSSLContext(String trustStorePath, String trustStorePassword) throws Exception {
        // Cargar el TrustStore
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(new FileInputStream(trustStorePath), trustStorePassword.toCharArray());

        // Crear un TrustManagerFactory con el TrustStore cargado
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);

        // Crear un SSLContext con el TrustManagerFactory configurado
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }

    private static SSLContext disableHostnameVerification() throws NoSuchAlgorithmException, KeyManagementException {
        // Crear un SSLContext que no valide el nombre del host
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        }}, null);

        // Configurar la fábrica de sockets para utilizar el SSLContext creado
        //HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Desactivar la validación del nombre del host
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        return sslContext;
    }

    public static void GetRequest() throws JSONException {
        HttpsURLConnection conn = null;

        try {
            URL url = new URL("https://192.168.224.125/api-rest/empleados/7499");
            String username = "admin";
            String password = "password";

            SSLContext sslContext = disableHostnameVerification();
            //SSLContext sslContext = getSSLContext("src/employees.jks", "spring");
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            // Codificar las credenciales en base64
            String credentials = username + ":" + password;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            // Agregar la cabecera de autorización con las credenciales codificadas en base64
            conn.setRequestProperty("Authorization", "Basic " + encodedCredentials);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();

                JSONObject jsonObject = new JSONObject(response);
//                System.out.println(jsonObject.get("ename") + "\n" + jsonObject.get("job"));
                System.out.println(jsonObject.get("nombre") + "\n" + jsonObject.get("puesto"));
                }
            else {
                System.out.println("Ha ocurrido un error");
            }
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
        finally {
            if ( conn != null )
                conn.disconnect();
        }
    }

    public static void PostRequest() throws JSONException {
        HttpsURLConnection conn = null;
/*        String jsonInputString = new JSONObject()
                .put("empno", 9479)
                .put("ename", "Smithers")
                .put("job", "SALESMAN")
//                .put("depno", 20)
//                .toString();
                .put("department", new JSONObject()
                        .put("deptno", 30)
                        .put("dname", "SALES")
                        .put("loc", "CHICAGO")
                        .toString()).toString();*/
        String jsonInputString = "{\"empno\":9779,\"nombre\":\"JOESEPP\",\"puesto\":\"TEACHER\",\"depno\":30}";

        try {
            URL url = new URL("https://192.168.224.125/api-rest/empleados");
            String username = "admin";
            String password = "password";

            SSLContext sslContext = disableHostnameVerification();
            //SSLContext sslContext = getSSLContext("src/employees.jks", "spring");
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            // Codificar las credenciales en base64
            String credentials = username + ":" + password;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            // Agregar la cabecera de autorización con las credenciales codificadas en base64
            conn.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            conn.setDoOutput(true);

            try ( OutputStream os = conn.getOutputStream() ) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200)
                System.out.println("Empleado insertado");
            else {
                Scanner scanner = new Scanner(conn.getErrorStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();

                JSONObject jsonObject = new JSONObject(response).getJSONArray("errors").getJSONObject(0);
                System.out.println(jsonObject.get("defaultMessage"));
            }
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
        finally {
            if ( conn != null )
                conn.disconnect();
        }
    }
}