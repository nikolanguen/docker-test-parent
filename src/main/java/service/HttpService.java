package service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

public class HttpService {

    private static final String URL = "http:localhost:8080/test/result";
    private OkHttpClient httpClient;

    public HttpService() {
        this.httpClient = new OkHttpClient();
    }

    public void sendTestResult(int points, List<String> failedTestCases) throws IOException {
        JSONObject testsResult = buildJsonTestsResultObject(points, failedTestCases);
        RequestBody body = buildRequestBody(testsResult);
        Request request = buildRequest(URL, body);

        httpClient.newCall(request).execute();
    }

    private Request buildRequest(String url, RequestBody body) {
        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    private JSONObject buildJsonTestsResultObject(int points, List<String> failedTestCases) {
        JSONObject testsResult = new JSONObject();
        testsResult.put("points", points);
        JSONArray failedCases = new JSONArray();
        failedCases.addAll(failedTestCases);
        testsResult.put("failedTestCases", failedCases);
        return testsResult;
    }

    private RequestBody buildRequestBody(JSONObject testsResult) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(testsResult.toString(), json);
    }

}
