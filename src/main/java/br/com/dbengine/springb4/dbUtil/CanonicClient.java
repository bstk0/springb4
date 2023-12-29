package br.com.dbengine.springb4.dbUtil;

import okhttp3.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public final class CanonicClient {


    public String getList(String cObj) { //throws IOException {
        return this.getList(cObj,0);
    }
    public String getList(String cObj, int imovelId) { //throws IOException {
        String query = null;
        String METHOD = "GET";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = null; //RequestBody.create(mediaType,query);
        if (imovelId > 0) {
            METHOD = "POST";
            query = "{\"imovelId\" : " + imovelId + "}";
            //Sysout.s(query);
            body = RequestBody.create(mediaType,query);
        }

        //MediaType mediaType = MediaType.parse("text/plain");
        //RequestBody body = RequestBody.create(mediaType, "");
        //RequestBody body = RequestBody.create("");
        try {
        Request request = new Request.Builder()
                .url("https://can.canonic.dev/rep1-180hdf/api/" + cObj)
                .method(METHOD, body)   //null
                .addHeader("Authorization", "658c9538c6feb36c0d677bb4-1334c248-62e9-4c23-a0a6-93af5972ad7d")
                .build();
        Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            return e.getMessage();
            //throw new RuntimeException(e);
        }
    }
public String getPeopleList() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    //MediaType mediaType = MediaType.parse("text/plain");
    //RequestBody body = RequestBody.create(mediaType, "");
    //RequestBody body = RequestBody.create("");
    Request request = new Request.Builder()
            .url("https://can.canonic.dev/rep1-180hdf/api/people")
            .method("GET", null)
            .addHeader("Authorization", "658c9538c6feb36c0d677bb4-1334c248-62e9-4c23-a0a6-93af5972ad7d")
            .build();
    Response response = client.newCall(request).execute();
    try {
        return response.body().string();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

/*    public String getPeopleJSONList() {
        String resp = this.getPeopleList();

    }*/
    public String add(String sobj) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, sobj);
        Request request = new Request.Builder()
                .url("https://can.canonic.dev/rep1-180hdf/api/people")
                .method("POST", body)
                .addHeader("Authorization", "658c9538c6feb36c0d677bb4-1334c248-62e9-4c23-a0a6-93af5972ad7d")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.toString();
    }

    public JSONArray CanonicJSONList(String sjson) {
        JSONArray result = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) parser.parse(sjson);
            JSONObject jobj2 = (JSONObject) jobj.get("data");
            //System.out.print(jobj2.toJSONString());
            //System.out.println(jobj2.size());

            Iterator<?> iterator = jobj2.keySet().iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                jobj = (JSONObject) jobj2.get(key.toString());
                result.add(jobj);
            }
            //JSONArray sportsArray = (JSONArray) jobj.get("data");
            //Sysout.s(result.toJSONString());
        } catch (ParseException e) {
            //return e.getMessage();
            throw new RuntimeException(e);
        }
        return result;
    }

}
