package com.candidates.factory;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("elasticSearchFactory")
public class ElasticSearchConnectionFactoryImp implements ElasticSearchFactory {

    private static String wsUrl = "http://127.0.0.1:9200";

    private CloseableHttpClient client;

    @Override
    public void createClient() throws IOException {
        this.client = HttpClients.custom()
                .setRetryHandler(new StandardHttpRequestRetryHandler()).build();
    }

    @Override
    public boolean isConnectionOK(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode() != HttpStatus.SC_OK;
    }

    @Override
    public CloseableHttpResponse searchDocumentById(String document) throws IOException {
        return getResponse(document);
    }

    @Override
    public CloseableHttpResponse searchAll(String document) throws IOException {
        return null;
    }

    private CloseableHttpResponse getResponse(String document) throws IOException {
        HttpGet method = new HttpGet(wsUrl + document);

        try {
            return client.execute(method);

        } catch (IOException exception) {
            throw exception;
        }
    }

}
