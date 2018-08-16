package com.candidates.component;

import com.candidates.factory.ElasticSearchConnectionFactoryImp;
import com.candidates.model.Candidates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("candidatesImp")
public class CandidatesImp {

    private static final String CANDIDATES_DOCUMENT = "/techwaves/candidates/";

    @Qualifier("elasticSearchFactory")
    @Autowired
    private ElasticSearchConnectionFactoryImp connectionFactory;

    @Qualifier("candidatesUtils")
    @Autowired
    private CandidatesUtils utils;

    public Candidates searchById(String id) throws IOException {
        connectionFactory.createClient();
        CloseableHttpResponse httpResponse = connectionFactory.searchDocumentById(CANDIDATES_DOCUMENT + id);
        String responseBody = EntityUtils.toString(httpResponse.getEntity());

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        responseBody = utils.removeSpecialCharactersFromJson(responseBody);

        return gson.fromJson(responseBody, Candidates.class);
    }


}
