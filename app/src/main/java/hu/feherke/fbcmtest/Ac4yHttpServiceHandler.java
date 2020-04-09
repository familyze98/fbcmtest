package hu.feherke.fbcmtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class Ac4yHttpServiceHandler {
    public String getResponse(
            String aHost
            ,String aServiceName
            ,String aRequest
    ) {
        String responseInString = new String();
        String message = new String();
        try {
            // create HTTP Client
            HttpClient httpClient = HttpClientBuilder.create().build();
            // Create new getRequest with below mentioned URL
            HttpGet getRequest =
                    new HttpGet(
                            aHost
                                    + aServiceName
                                    + aRequest
                    );
            // Add additional header to getRequest which accepts application/xml data
            getRequest.addHeader("accept", "application/json");
            // Execute your request and catch response
            HttpResponse response = httpClient.execute(getRequest);
            // Check for HTTP response code: 200 = success
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }
            // Get-Capture Complete application/xml body response
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            StringBuffer buffer = new StringBuffer();
            while ((message = br.readLine()) != null) {
                //buffer.append(line);
                responseInString += message;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseInString;
    }

}
