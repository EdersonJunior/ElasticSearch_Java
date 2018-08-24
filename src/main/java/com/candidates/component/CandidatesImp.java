package com.candidates.component;

import com.candidates.factory.ElasticSearchFactory;
import com.candidates.model.Candidate;
import com.candidates.model.CandidateRequest;
import com.candidates.model.ElasticSearchHitResponse;
import com.candidates.model.ElasticSearchHitsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
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
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.trimToNull;

@Component("candidatesImp")
public class CandidatesImp {

    private static final String CANDIDATES_DOCUMENT = "/techwaves/candidates/";

    private static final String SEARCH_ALL_OPTION = "_search";

    private static final String URL = "http://127.0.0.1:9200";

    private static final String HOST = "127.0.0.1:9200";

    private static final String SCHEME = "http";

    private GsonBuilder builder;

    private Gson gson;

    @Autowired
    @Qualifier("elasticSearchFactory")
    private ElasticSearchFactory elasticSearchFactory;

    public List<Candidate> searchAll() throws IOException, NoSuchFieldException {
        elasticSearchFactory.createClient();
        builder = new GsonBuilder();
        gson = builder.create();

        HttpGet httpGet = new HttpGet(URL + CANDIDATES_DOCUMENT + SEARCH_ALL_OPTION);
        String responseBody = elasticSearchFactory.executeHttpRequest(httpGet);

        ElasticSearchHitsResponse hitsResponse = gson.fromJson(responseBody, ElasticSearchHitsResponse.class);
        return buildCandidatesListFromHitsResponse(hitsResponse);
    }

    private List<Candidate> buildCandidatesListFromHitsResponse(ElasticSearchHitsResponse hitsResponse) {
        List<Candidate> candidates = new ArrayList<>();
        List hitLinkedTreeMapObject = gson.fromJson(gson.toJson(hitsResponse.getHits().getHits()), List.class);
        for (Object hitMap : hitLinkedTreeMapObject) {
            LinkedTreeMap linkedTreeMapList = (LinkedTreeMap) hitMap;
            Object sourceObject = linkedTreeMapList.get("_source");
            LinkedTreeMap sourceMapList = (LinkedTreeMap) sourceObject;

            Object interests = sourceMapList.get("interests");
            ArrayList interestsList = (ArrayList) interests;
            Object[] objects = interestsList.toArray();
            String[] interestsArray = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                interestsArray[i] = objects[i].toString();
            }

            Candidate candidate = new Candidate();
            candidate.setName(trimToNull(sourceMapList.get("name").toString()));
            candidate.setInterests(interestsArray);
            candidate.setCountryOrigin(trimToNull(sourceMapList.get("countryOrigin").toString()));
            candidate.setCountryLiving(trimToNull(sourceMapList.get("countryLiving").toString()));
            candidate.setCity(trimToNull(sourceMapList.get("city").toString()));
            candidate.setPosition(trimToNull(sourceMapList.get("position").toString()));
            candidate.setSalary(Double.parseDouble(trimToNull(sourceMapList.get("salary").toString())));

            candidates.add(candidate);
        }

        return candidates;
    }

    public Candidate searchById(String id) throws IOException {
        elasticSearchFactory.createClient();
        builder = new GsonBuilder();
        gson = builder.create();

        HttpGet httpGet = new HttpGet(URL + CANDIDATES_DOCUMENT + id);
        String responseBody = elasticSearchFactory.executeHttpRequest(httpGet);

        ElasticSearchHitResponse hit = gson.fromJson(responseBody, ElasticSearchHitResponse.class);
        String hitJson = gson.toJson(hit.get_source());

        return gson.fromJson(hitJson, Candidate.class);
    }


    public void updateById(CandidateRequest candidateRequest) throws IOException, URISyntaxException {
        elasticSearchFactory.createClient();
        builder = new GsonBuilder();
        gson = builder.create();

        Candidate candidate = new Candidate(candidateRequest);

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
