package catalin.coinnews.services;

import android.accounts.NetworkErrorException;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import catalin.coinnews.MainActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by catalin on 02/12/17.
 */

public abstract class Service {

    public static final int SUCCESS = 200;
    public static final int BAD_REQUEST_HTTP_CODE       = 400;
    public static final int UNAUTHORIZED_HTTP_CODE      = 401;
    public static final int RESERVED_HTTP_CODE          = 403;
    public static final int NOT_FOUND_HTTP_CODE         = 404;
    public static final int SYSTEM_BUSY_HTTP_CODE       = 408;
    public static final int RESTRICTED_HTTP_CODE        = 418; // I'm a teapot
    public static final int SERVER_ERROR_HTTP_CODE      = 500;

    String SERVER = "http://10.0.2.2:3000/rf/app"; // Localhost

    MediaType JSON = MediaType.parse("application/json");
    String TAG = MainActivity.class.getSimpleName();
    private static final int TIMEOUT = 30;

    public Response consumeService(String url, JSONObject obj, String requestType) throws NetworkErrorException, IOException, JSONException {
        Request request = this.buildRequest(url, obj, requestType);

        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
        Response response = client.newCall(request).execute();

        Log.v(TAG, "RESPONSE CODE: "+response.code());
        Log.v(TAG, "HEADERS: \n"+response.headers().toString());

        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));

        Log.v(TAG, "RESPONSE: \n"+responseBodyString);

        return response;
    }

    private Request buildRequest(String url, JSONObject obj, String requestType) throws NetworkErrorException {
        RequestBody body = RequestBody.create(JSON, String.valueOf(obj));
        Request.Builder builder = new Request.Builder();
        this.addHeaders(builder);
        if (requestType.equals("POST")) builder.post(body);
        Request request = builder
                .url(url)
                .build();

        return request;
    }

    private Request.Builder addHeaders(Request.Builder builder) throws NetworkErrorException {
//        if(!Util.isNetworkAvailable()) { throw new NetworkErrorException(); }

//        String authToken = Util.getAuthToken();
//        String phoneId = Util.getPhoneId();
//        String modelName = Util.getModelName();

        builder.header("Content-Type", "application/json");
//                .addHeader("Company-Reference", authToken)
//                .addHeader("Phone-Identifier", phoneId);
//                .addHeader("Model-Name", modelName);

        return builder;
    }

}
