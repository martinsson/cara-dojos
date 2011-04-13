package agilecup;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MazeFinder {

    public String getRawMaze(String uri) throws IOException, ClientProtocolException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = httpClient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public String[] mazeRows(String uri) throws IOException, ClientProtocolException {
        String rawMaze = getRawMaze(uri);
        String noApostrofs = rawMaze.substring(1, rawMaze.length()-1);
        String[] result = noApostrofs.split(".n");
        return result;
    }

}
