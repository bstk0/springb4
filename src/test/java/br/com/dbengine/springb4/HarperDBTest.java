import java.io.*;
import okhttp3.*;
public class HarperDBTest {

    public static void main(String []args) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"operation\": \"sql\",\r\n  \"sql\": \"select * FROM rep1.imovel\"\r\n  }");
        Request request = new Request.Builder()
                .url("https://cloud-dbe-bisterco.harperdbcloud.com")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic YmlzdGVyY286Q2FsbTA2MjNA")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}