package com.candidates.component;

import com.candidates.factory.ElasticSearchFactory;
import com.candidates.model.Candidate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ElasticSearchFactory.class, CandidatesImp.class})
//@Ignore
public class CandidatesImpTest {

    private final String CANDIDATE_NAME = "Angélica Fialho";
    private final String CANDIDATE_ID = "139";

    @Qualifier("elasticSearchFactory")
    @Autowired
    private ElasticSearchFactory factory;

    @Qualifier("candidatesImp")
    @Autowired
    private CandidatesImp candidatesImp;

    @Test
    public void searchCandidadeById_shouldReturnCandidateNameDefinedAsHardCode() throws IOException {
        Candidate candidateResult = candidatesImp.searchCandidateById(CANDIDATE_ID);
        Assert.assertEquals(CANDIDATE_NAME, candidateResult.getName());
    }
}
