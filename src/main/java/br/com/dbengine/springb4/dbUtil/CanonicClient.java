package br.com.dbengine.springb4.dbUtil;

import okhttp3.*;

import java.io.*;

public final class CanonicClient {

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


}
