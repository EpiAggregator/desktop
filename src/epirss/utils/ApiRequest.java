package epirss.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Consumer;


public class ApiRequest {

    private String base_url = "http://java.guillaume-fillon.com/v1/";

    private ApiRequest()
    {}

    private static ApiRequest INSTANCE = new ApiRequest();
    public  static String Token;

    public static ApiRequest getInstance() {
        return INSTANCE;
    }

    public void login(String email, String password, Consumer< String > task) {
        String str =  "{ \"email\": \"" + email + "\" , \"password\": \"" + password + "\"}";
        postRequest(base_url + "token", str, task);
    }

    public void createAccount(String email, String password, Consumer< String > task) {
        String str =  "{ \"email\": \"" + email + "\" , \"password\": \"" + password + "\"}";
        postRequest(base_url + "users", str, task);
    }

    private void postRequest(String urlTarget, String str, Consumer< String > task) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(urlTarget);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(str.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            byte[] outputInBytes = str.getBytes("UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write( outputInBytes );
            os.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            task.accept(response.toString());
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
