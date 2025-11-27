package br.com.dbengine.springb4.dbUtil;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.net.*;
import java.util.*;

public final class RestClient {

    private final String TAG = RestClient.class.getSimpleName();
    // You can find your API KEYS here : https://restdb.io/docs/apikeys-and-cors
    private String BASE_URI; //"https://rdb-examples.restdb.io/rest/"; // change to your BASE URI
    private String CONTENT_TYPE; // = "application/json";

    private List<String> AUTH_KEY;

    private final int TIMEOUT = 2000;// 2000ms = 2seconds

    //HTTP protocol
    private final String POST = "POST";
    private final String PATCH = "PATCH";
    private final String DELETE = "DELETE";
    private final String GET = "GET";
    private final String PUT = "PUT";

    private final String QUERY_COUNT = "?q={}&h={\"$aggregate\":[\"COUNT:\"]}";

//    public RestClient(String baseURI) {
//        BASE_URI = "https://bstk1-1e0a.restdb.io/rest/";
//    }

//    public void createConnection(String url, String contentType, List<String> authKey) {
//        this.BASE_URI = url;
//        this.CONTENT_TYPE = contentType;
//        this.AUTH_KEY = authKey;
//    }

    public RestClient(String BASE_URI, String CONTENT_TYPE, List<String> AUTH_KEY) {
        Sysout.s(" ** RestClient constructor ... ");
        this.BASE_URI = BASE_URI;
        this.CONTENT_TYPE = CONTENT_TYPE;
        this.AUTH_KEY = AUTH_KEY;
        Sysout.s("   >> this.BASE_URI : " +  this.BASE_URI);
        Sysout.s("   >> this.CONTENT_TYPE : " +  this.CONTENT_TYPE);
        Sysout.s("   >> this.AUTH_KEY : " +  this.AUTH_KEY.toString());
        Sysout.s(" ** RestClient constructor ... ");
    }

    public void setAUTH_KEY(List<String> AUTH_KEY) {
        this.AUTH_KEY = AUTH_KEY;
        Sysout.s(" >> setAUTH_KEY : " + this.AUTH_KEY.toString());
    }

    public void setCONTENT_TYPE(String CONTENT_TYPE) {
        this.CONTENT_TYPE = CONTENT_TYPE;
    }

    /**
     * Create connection to the server
     * @param requestMethod Method to the request (PUT,GET,DELETE,POST)
     * @param url url to call
     * @return connection
     * @throws IOException throw exception if wrong parameters or cant' open a connection
     */
    private  HttpURLConnection createConnection(final String requestMethod, final String url) throws IOException {
        Sysout.s(" ** HttpURLConnection ... ");
        Sysout.s(" >> url param : " + url);
        HttpURLConnection connection = null;
//        if (AUTH_KEY.isEmpty()) {
//            Sysout.s(" >> ERRO DE CONEXAO, AUSENCIA DE KEY <<");
//            return connection;
//        }
        Sysout.s(" >> BASE_URI: " + this.BASE_URI);
        URL finalUrl = new URL(BASE_URI + url);
        Sysout.s(" >> finalURL: " + finalUrl);
        Sysout.s(" >> Sysout.RESTDB_KEY: " + AUTH_KEY.get(0) + " / " + AUTH_KEY.get(1) );
        Sysout.s(" >> CONTENT_TYPE: " + CONTENT_TYPE);
        connection = (HttpURLConnection)finalUrl.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty(AUTH_KEY.get(0),AUTH_KEY.get(1));
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setConnectTimeout(TIMEOUT);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        return connection;
    }

    /**
     * Send Post request
     * @param connection current connection
     * @param parameters parameters to send to the POST request
     * @throws IOException throw exception if wrong parameters or cant' open a connection
     */
    private void sentPostRequest(final HttpURLConnection connection, final String parameters) throws IOException {
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();
    }


    /**
     * Call the API without parameter
     * @param requestMethod HTTP protocol  (GET,DELETE)
     * @param url url to call
     * @return
     */
    private String executeHTTPRequest(final String requestMethod, final String url) {
        return executeHTTPRequest(requestMethod,url, null);
    }
    /**
     * Call the API, Only POST and PUT requests have parameters
     * @param requestMethod HTTP protocol  (PUT, GET,DELETE,POST)
     * @param url url to call
     * @param parameters parameters to send to the POST request. otherwise null
     * @return
     */
    private  String executeHTTPRequest(final String requestMethod,
                                       final String url,
                                       final String parameters)  {
        Sysout.s(" ** executeHTTPRequest ...");
        Sysout.s(" ** HTTPRequest : " + requestMethod);
        Sysout.s(" ** HTTPURL     : " + url);
        Sysout.s(" ** PARAM       : " + parameters);
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
        try {
            //Create connection
            connection = createConnection(requestMethod, url);

            if (POST.equals(requestMethod) || PUT.equals(requestMethod) || PATCH.equals(requestMethod))
                sentPostRequest(connection, parameters);

            // read stream
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = rd.readLine()) != null) {
                response.append(line); // append result
            }
            rd.close();
        } catch (IOException e) {
            //System.err.println("Cant connect to the server. Please try later on");
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            // close connection no matter what
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }

    /**
     * POST method
     * @param collection collection to call
     * @param objectToAdd object to add in the collection
     * @return result from the server
     */
    public  String post(final String collection, final String objectToAdd) {
        this.setCONTENT_TYPE("application/json");
        return executeHTTPRequest(POST, collection, objectToAdd);
    }

    /**
     * GET method, get the list of items in the given collection
     * @param collection collection to call
     * @return list of items in the collection
     */
    public  String get(final String collection) {
        return executeHTTPRequest(GET, collection);
    }

    /**
     * PUT method, update a given object by its ID
     * More info about the query here : https://restdb.io/docs/querying-with-the-api
     * @param collection collection to call, with the ID of the object to update
     * @return ID of the object updated
     */
    public  String put(final String collection, final String query) {
        this.setCONTENT_TYPE("application/json");
        return executeHTTPRequest(PUT, collection, query);
    }

    /**
     * PUT method, update a given object by its ID
     * More info about the query here : https://restdb.io/docs/querying-with-the-api
     * @param collection collection to call, with the ID of the object to update
     * @return ID of the object updated
     * ==== NAO FUNCIONOU PARA SUPABASE - PostREST ==========
     */
    public  String patch(final String collection, final String query) {
        this.setCONTENT_TYPE("application/json");
        return executeHTTPRequest(PATCH, collection, query);
    }

    /**
     * DELETE method, delete a given object by its ID
     * @param collection collection to call, with the ID of the object to delete
     * @return List of ID's ( the deleted objects )
     */
    public  String delete(final String collection) {
        return executeHTTPRequest(DELETE, collection);
    }


    /**
     * getCount - Bisterço
     */
    public String getCount(final String collection) {
        return executeHTTPRequest(GET, collection + QUERY_COUNT);
    }

    public JSONArray HasuraJSONList(String sjson, String tableName) {
        JSONArray result = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) parser.parse(sjson);
//            JSONObject jobj2 = (JSONObject) jobj.get(tableName);
//            Iterator<?> iterator = jobj2.keySet().iterator();
//            while (iterator.hasNext()) {
//                Object key = iterator.next();
//                jobj = (JSONObject) jobj2.get(key.toString());
//                result.add(jobj);
//            }
            Sysout.s( " >> HasuraJSONList - results : " + jobj);

            result = (JSONArray) jobj.get(tableName);
        } catch (ParseException e) {
            //return e.getMessage();
            //throw new RuntimeException(e);
            Sysout.s( "ERROR:" + e.getMessage());
        }
        return result;
    }

    public JSONObject HasuraJSONObject(String sjson, String tableName) {
        JSONObject result = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) parser.parse(sjson);
//            JSONObject jobj2 = (JSONObject) jobj.get(tableName);
//            Iterator<?> iterator = jobj2.keySet().iterator();
//            while (iterator.hasNext()) {
//                Object key = iterator.next();
//                jobj = (JSONObject) jobj2.get(key.toString());
//                result.add(jobj);
//            }
            Sysout.s( " >> HasuraJSONObject - results : " + jobj);

            result = (JSONObject) jobj.get(tableName);
        } catch (ParseException e) {
            //return e.getMessage();
            //throw new RuntimeException(e);
            Sysout.s( "ERROR:" + e.getMessage());
        }
        return result;
    }

    public JSONArray SupabaseJSONList(String sjson) {
        JSONArray result = new JSONArray();
        JSONParser parser = new JSONParser();
        //JSONObject jobj = null;
        try {
            //jobj = (JSONObject) parser.parse(sjson);
//            JSONObject jobj2 = (JSONObject) jobj.get(tableName);
//            Iterator<?> iterator = jobj2.keySet().iterator();
//            while (iterator.hasNext()) {
//                Object key = iterator.next();
//                jobj = (JSONObject) jobj2.get(key.toString());
//                result.add(jobj);
//            }
            Sysout.s( " >> SupabaseJSONList - results : " + sjson);

            result = (JSONArray) parser.parse(sjson);
        } catch (ParseException e) {
            //return e.getMessage();
            //throw new RuntimeException(e);
            Sysout.s( "ERROR:" + e.getMessage());
        }
        return result;
    }

}

