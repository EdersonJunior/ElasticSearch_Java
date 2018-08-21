package com.candidates.factory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public interface ElasticSearchFactory {

    public void createClient() throws IOException;

    public boolean isConnectionOK(CloseableHttpResponse response);

    public CloseableHttpResponse searchDocumentById(String document) throws IOException;

    public CloseableHttpResponse searchAll(String document) throws IOException;
}
