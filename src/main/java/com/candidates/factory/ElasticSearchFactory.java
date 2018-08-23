package com.candidates.factory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component("elasticSearchFactory")
public class ElasticSearchFactory {

    private static String wsUrl = "http://127.0.0.1:9200";

    private CloseableHttpClient client;

    public void createClient() throws IOException {
        if (client == null) {
            this.client = HttpClients.custom()
                    .setRetryHandler(new StandardHttpRequestRetryHandler()).build();
        }
    }

    public boolean isConnectionOK(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode() != HttpStatus.SC_OK;
    }

    public String executeHttpRequest(HttpGet httpGet) throws IOException {
        try {
            return EntityUtils.toString(client.execute(httpGet).getEntity());

        } catch (IOException exception) {
            throw exception;
        }
    }

    public String executeHttpRequest(HttpPut httpPut) throws IOException {
        try {
            return EntityUtils.toString(client.execute(httpPut).getEntity());

        } catch (IOException exception) {
            throw exception;
        }
    }


}
