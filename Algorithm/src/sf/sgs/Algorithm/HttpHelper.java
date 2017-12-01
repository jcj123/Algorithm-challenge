package sf.sgs.Algorithm;

import okhttp3.*;

import java.io.IOException;

public class HttpHelper {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static Response response;

    public static String post(String url,String json) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON, json))
                .build();
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String get(String url){
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
