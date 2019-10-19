package edu.qc.seclass.glm;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
//azure parsing/score
public class GetSentiment {

    static String accessKey = "7e61f11c688540858ca0f08e2f4d9c1c";

    static String host = "https://eastus.api.cognitive.microsoft.com";

    static String path = "/text/analytics/v2.0/sentiment";

    public static String getTheSentiment (Documents documents) throws Exception {
        String text = new Gson().toJson(documents);
        byte[] encoded_text = text.getBytes("UTF-8");

        URL url = new URL(host+path);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/json");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", accessKey);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(encoded_text, 0, encoded_text.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();

        JsonObject json = parser.parse(json_text).getAsJsonObject();
        JsonElement documents = json.get("documents");
        boolean isarray = documents.isJsonArray();

        JsonArray array = (JsonArray) documents;

        JsonObject document = array.get(0).getAsJsonObject();
        JsonElement scorejson =  document.get("score");
        String stringscore = scorejson.toString();

        return stringscore;
    }

    public static String makeCall (String feeling) {
        String score = "Failed";
        try {
            Documents documents = new Documents ();
            documents.add ("1", "en", feeling);


            String response = getTheSentiment (documents);
            score = (prettify (response));
        }
        catch (Exception e) {
            System.out.println (e);
        }
        return score;
    }

}
