package com.candidates.factory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public interface ElasticSearchConnectionFactory {

    public void createClient() throws IOException;

    public boolean isConnectionOK(CloseableHttpResponse response);

    public CloseableHttpResponse searchDocumentById(String document) throws IOException;
}
