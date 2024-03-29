package markus.wieland.defaultappelements.api;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest<T> extends AsyncTask<Void, Void, Void> {

    protected final RequestResultListener<T> requestResultListener;
    protected final Gson gson;
    protected final String url;
    protected final Class<T> type;

    public GetRequest(Class<T> type, String url, RequestResultListener<T> requestResultListener) {
        this.requestResultListener = requestResultListener;
        this.url = url;
        this.gson = new Gson();
        this.type = type;
    }

    protected T parse(String json) {
        return gson.fromJson(json, type);
    }

    protected String parseResult(HttpURLConnection connection) throws IOException {
        StringBuilder responseString = new StringBuilder();
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            responseString.append(responseLine);
        }
        Log.e("Result", responseString.toString());
        return responseString.toString();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            String result = parseResult(connection);
            requestResultListener.onLoad(parse(result));
        } catch (Exception e) {
            requestResultListener.onError(e);
        }
        return null;
    }


}
