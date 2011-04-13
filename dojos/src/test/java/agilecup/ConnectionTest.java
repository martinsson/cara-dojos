package agilecup;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class ConnectionTest {
    private static final String key = "vCYVFJtCTDkaLdlZqfUecw%3D%3D";
    public String uri = "http://beta.agilecup.org/problem/01?key="+key;
    public MazeFinder mazeFinder = new MazeFinder();
    
    @Test
    public void canConnectAndDownloadJson() throws Exception {
        String result = mazeFinder.getRawMaze(uri);
        assertThat(result.split("\"")[1], equalTo("#I#\\n#O#"));
    }
    
    @Test
    public void removeApostrofsAndSplitsAtNewLine() throws Exception {
        String[] result = mazeFinder.mazeRows(uri);
        assertThat(result, arrayContaining("#I#", "#O#"));
    }
    
    @Test
    public void canPostTheSolutionToMaze1() throws Exception {
        String responseUri = "http://beta.agilecup.org/solution/01";
        String solution = "S";
        String result = getRawSolutionResult(responseUri, solution);
        
        System.out.println(result);
        assertThat(result, startsWith("{\"success\":\"ok\""));
    }

    public String getRawSolutionResult(String responseUri, String solution) throws UnsupportedEncodingException, IOException, ClientProtocolException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(responseUri);
        HttpEntity requestEntity = new StringEntity("key="+key+"&solution="+solution);
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }
    
}
