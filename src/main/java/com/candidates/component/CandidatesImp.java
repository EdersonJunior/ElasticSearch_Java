package com.candidates.component;

import com.candidates.factory.ElasticSearchFactory;
import com.candidates.model.Candidate;
import com.candidates.model.CandidateRequest;
import com.candidates.model.ElasticSearchHitResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component("candidatesImp")
public class CandidatesImp {

    private static final String CANDIDATES_DOCUMENT = "/techwaves/candidates/";

    private static String URL = "http://127.0.0.1:9200";

    private static String HOST = "127.0.0.1:9200";

    private static String SCHEME = "http";

    @Autowired
    @Qualifier("elasticSearchFactory")
    private ElasticSearchFactory elasticSearchFactory;


    public Candidate searchById(String id) throws IOException {
        elasticSearchFactory.createClient();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        HttpGet httpGet = new HttpGet(URL + CANDIDATES_DOCUMENT + id);
        String responseBody = elasticSearchFactory.executeHttpRequest(httpGet);

        ElasticSearchHitResponse hit = gson.fromJson(responseBody, ElasticSearchHitResponse.class);
        String hitJson = gson.toJson(hit.get_source());

        return gson.fromJson(hitJson, Candidate.class);
    }


    public void updateById(CandidateRequest candidateRequest) throws IOException, URISyntaxException {
        Candidate candidate = new Candidate(candidateRequest);

        elasticSearchFactory.createClient();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(SCHEME).setHost(HOST).setPath(CANDIDATES_DOCUMENT + candidateRequest.getId());
        URI uri = uriBuilder.build();
        HttpPut put = new HttpPut(uri);

        String candidateJson = gson.toJson(candidate);
        HttpEntity entity = new ByteArrayEntity(candidateJson.getBytes());
        put.setEntity(entity);
        elasticSearchFactory.executeHttpRequest(put);
    }


}
