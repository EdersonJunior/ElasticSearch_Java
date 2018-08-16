package com.candidates.devs.component;

import com.candidates.devs.factory.ElasticSearchConnectionFactoryImp;
import com.candidates.devs.model.Dev;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("searchDevs")
public class SearchDevsImp {

    private static final String DOCUMENT = "/candidates/devs/";

    @Qualifier("elasticSearchFactory")
    @Autowired
    private ElasticSearchConnectionFactoryImp connectionFactory;

    @Qualifier("searchUtils")
    @Autowired
    private SearchDevsUtils utils;

    public Dev searchById(String id) throws IOException {
        connectionFactory.createClient();
        CloseableHttpResponse httpResponse = connectionFactory.searchDocumentById(DOCUMENT + id);
        String responseBody = EntityUtils.toString(httpResponse.getEntity());

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        responseBody = utils.removeSpecialCharactersFromJson(responseBody);

        return gson.fromJson(responseBody, Dev.class);
    }


}
