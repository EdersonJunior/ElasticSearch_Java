package com.candidates.component;

import com.candidates.factory.ElasticSearchFactory;
import com.candidates.model.*;
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
    private ElasticSearchFactory factory;

    public Candidate searchCandidateById(String id) throws IOException {
        factory.createClient();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return mapCandidateObject(gson, getHttpResponseBody(id));
    }

    private Candidate mapCandidateObject(Gson gson, String responseBody) {
        ElasticSearchHitResponse hit = gson.fromJson(responseBody, ElasticSearchHitResponse.class);
        String hitJson = gson.toJson(hit.get_source());
        return gson.fromJson(hitJson, Candidate.class);
    }

    private String getHttpResponseBody(String id) throws IOException {
        CloseableHttpResponse httpResponse = factory.searchDocumentById(CANDIDATES_DOCUMENT + id);
        return EntityUtils.toString(httpResponse.getEntity());
    }

}
