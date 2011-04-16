package agilecup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MazePublisher {
    protected String getRawSolutionResult(String responseUri, String solution) throws UnsupportedEncodingException, IOException, ClientProtocolException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(responseUri);
        HttpEntity requestEntity = new StringEntity("key="+ConnectionTest.key+"&solution="+solution);
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }
    public String expiryToken(String responseUri, String solution) throws UnsupportedEncodingException, ClientProtocolException, IOException {
        String result = getRawSolutionResult(responseUri, solution);
        Pattern expirypattern = Pattern.compile("expiry\":\"(.*)\"}");
        Matcher matcher = expirypattern.matcher(result);
        if (matcher.find()) 
            return matcher.group(1);
        else 
            return "";
    }
}