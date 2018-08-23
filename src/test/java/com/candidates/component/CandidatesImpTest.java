package com.candidates.component;

import com.candidates.factory.ElasticSearchFactory;
import com.candidates.model.Candidate;
import com.candidates.model.CandidateRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ElasticSearchFactory.class, CandidatesImp.class})
//@Ignore
public class CandidatesImpTest {

    private final String CANDIDATE_NAME = "Nathy Costa";
    private final String CANDIDATE_ID = "584";

    @Qualifier("elasticSearchFactory")
    @Autowired
    private ElasticSearchFactory factory;

    @Qualifier("candidatesImp")
    @Autowired
    private CandidatesImp candidatesImp;

    @Test
    public void searchCandidadeById_shouldReturnCandidateNameDefinedAsHardCode() throws IOException {
        Candidate candidateResult = candidatesImp.searchById(CANDIDATE_ID);
        Assert.assertEquals(CANDIDATE_NAME, candidateResult.getName());
    }

    @Test
    public void updateCandidadeById_shouldUpdateCandidateDefinedAsHardCode() throws IOException, URISyntaxException {
        Candidate candidateResult = candidatesImp.searchById(CANDIDATE_ID);

        candidateResult.setName(CANDIDATE_NAME);
        CandidateRequest candidateRequest = new CandidateRequest(candidateResult);
        candidateRequest.setId(Integer.parseInt(CANDIDATE_ID));

        candidatesImp.updateById(candidateRequest);
        candidateResult = candidatesImp.searchById(CANDIDATE_ID);

        Assert.assertEquals(CANDIDATE_NAME, candidateResult.getName());
    }
}
