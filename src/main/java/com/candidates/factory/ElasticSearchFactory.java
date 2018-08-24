package com.candidates.factory;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("elasticSearchFactory")
public class ElasticSearchFactory {

    private CloseableHttpClient client;

    public void createClient() {
        if (client == null) {
            this.client = HttpClients.custom()
                    .setRetryHandler(new StandardHttpRequestRetryHandler()).build();
        }
    }

    public String executeHttpRequest(HttpGet httpGet) throws IOException {
        try {
            return EntityUtils.toString(client.execute(httpGet).getEntity());

        } catch (IOException exception) {
            throw new IOException("Error at trying to execut HTTP request");
        }
    }

    public String executeHttpRequest(HttpPut httpPut) throws IOException {
        try {
            return EntityUtils.toString(client.execute(httpPut).getEntity());

        } catch (IOException exception) {
            throw new IOException("Error at trying to execut HTTP request");
        }
    }

    public String executeHttpRequest(HttpDelete httpDelete) throws IOException {
        try {
            return EntityUtils.toString(client.execute(httpDelete).getEntity());

        } catch (IOException exception) {
            throw new IOException("Error at trying to execut HTTP request");
        }
    }
}
