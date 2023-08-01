package br.com.dbengine.springb4.dbUtil;


import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaumeagis on 15/03/2016.
 */
public final class HarperDBClient {

    private final String TAG = HarperDBClient.class.getSimpleName();
    // You can find your API KEYS here : https://restdb.io/docs/apikeys-and-cors
    private final String BASE_URI = "https://cloud-dbe-bisterco.harperdbcloud.com"; // change to your BASE URI
    private final String AUTHOR = "Basic YmlzdGVyY286Q2FsbTA2MjNA";
    private final String CONTENT_TYPE = "application/json";
    private final int TIMEOUT = 2000;// 2000ms = 2seconds

    //HTTP protocol
    private final String POST = "POST";
    private final String DELETE = "DELETE";
    private final String GET = "GET";
    private final String PUT = "PUT";

    //private final String QUERY_COUNT = "?q={}&h={\"$aggregate\":[\"COUNT:\"]}";

    public String getList() {

        //JSONObject snuttgly = new JSONObject();
        //snuttgly.put("operation","sql");
        //snuttgly.put("sql","select * FROM rep1.imovel");
        //System.out.println(">> " + snuttgly.toJSONString());

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"operation\": \"sql\",\r\n  \"sql\": \"select * FROM rep1.imovel\"\r\n  }");
        //RequestBody body = RequestBody.create(snuttgly.toJSONString(), mediaType);
        Request request = new Request.Builder()
                .url("https://cloud-dbe-bisterco.harperdbcloud.com")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic YmlzdGVyY286Q2FsbTA2MjNA")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            System.out.println("Exception ...");
            throw new RuntimeException(e);
        }
    }

    public String getList(String strQuery) {

        //JSONObject snuttgly = new JSONObject();
        //snuttgly.put("operation","sql");
        //snuttgly.put("sql","select * FROM rep1.imovel");
        //System.out.println(">> " + snuttgly.toJSONString());

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{\"operation\": \"sql\",\r\n  \"sql\": \"" + strQuery + "\"\r\n  }");
        //RequestBody body = RequestBody.create(snuttgly.toJSONString(), mediaType);
        Request request = new Request.Builder()
                .url("https://cloud-dbe-bisterco.harperdbcloud.com")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic YmlzdGVyY286Q2FsbTA2MjNA")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            System.out.println("Exception ...");
            throw new RuntimeException(e);
        }
    }

    public String execOperation(String strOpp) {

        //JSONObject snuttgly = new JSONObject();
        //snuttgly.put("operation","sql");
        //snuttgly.put("sql","select * FROM rep1.imovel");
        //System.out.println(">> " + snuttgly.toJSONString());

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, strOpp);
                //"{\"operation\": \"sql\",\r\n  \"sql\": \"" + strQuery + "\"\r\n  }");
        //RequestBody body = RequestBody.create(snuttgly.toJSONString(), mediaType);
        Request request = new Request.Builder()
                .url("https://cloud-dbe-bisterco.harperdbcloud.com")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic YmlzdGVyY286Q2FsbTA2MjNA")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            System.out.println("Exception ...");
            throw new RuntimeException(e);
        }
    }

}

