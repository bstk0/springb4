package br.com.dbengine.springb4.dbUtil;


import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    private final String AUTHOR = Sysout.HARPER_KEY;
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
        //Sysout.s(">> " + snuttgly.toJSONString());

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"operation\": \"sql\",\r\n  \"sql\": \"select * FROM rep1.imovel\"\r\n  }");
        //RequestBody body = RequestBody.create(snuttgly.toJSONString(), mediaType);
        Request request = new Request.Builder()
                .url("https://cloud-dbe-bisterco.harperdbcloud.com")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Sysout.HARPER_KEY)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Sysout.s("Exception ...");
            throw new RuntimeException(e);
        }
    }

    public String getList(String strQuery) {

        //JSONObject snuttgly = new JSONObject();
        //snuttgly.put("operation","sql");
        //snuttgly.put("sql","select * FROM rep1.imovel");
        //Sysout.s(">> " + snuttgly.toJSONString());

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
                .addHeader("Authorization", Sysout.HARPER_KEY)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Sysout.s("Exception ...");
            throw new RuntimeException(e);
        }
    }

    public String execOperation(String strOpp) {
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
                .addHeader("Authorization", Sysout.HARPER_KEY)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Sysout.s("Exception ...");
            throw new RuntimeException(e);
        }
    }

    public JSONObject getJSONItem(String strQuery) {
        //String strQuery = "select * FROM rep1.imovelOcorrencia where id = '" + imovelOccId + "'";
        //strQuery += " order by __createdtime__ desc";
        Sysout.s("SQL: " + strQuery);
        JSONParser parser = new JSONParser();
        JSONArray results = null;
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = this.getList(strQuery);
            obj = parser.parse(resultGetAll);
            results = (JSONArray) (obj);
            Sysout.s("DAO-175: " + results.size());
            return ((JSONObject) results.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public JSONArray getJSONList(String strQuery) {
        //String strQuery = "select * FROM rep1.imovelOcorrencia where imovel_id = " + imovelId;
        //strQuery += " order by __createdtime__ desc";
        JSONParser parser = new JSONParser();
        JSONArray results = null;
        Object obj = null;
        String resultGetAll;
        try {
            resultGetAll = this.getList(strQuery);
            obj = parser.parse(resultGetAll);
            results = (JSONArray) (obj);
            return results;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

}

