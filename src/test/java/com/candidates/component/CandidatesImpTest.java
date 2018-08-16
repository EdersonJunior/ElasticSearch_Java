package com.candidates.component;

import com.candidates.factory.ElasticSearchConnectionFactoryImp;
import com.candidates.model.Candidates;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ElasticSearchConnectionFactoryImp.class, CandidatesUtils.class, CandidatesImp.class})
//@Ignore
public class CandidatesImpTest {

    private final String CANDIDATE_NAME = "Angélica Fialho";
    private final String CANDIDATE_ID = "139";

    @Qualifier("elasticSearchFactory")
    @Autowired
    private ElasticSearchConnectionFactoryImp connectionFactory;

    @Qualifier("candidatesImp")
    @Autowired
    private CandidatesImp candidatesImp;

    @Qualifier("candidatesUtils")
    @Autowired
    private CandidatesUtils utils;

    @Test
    public void testSearchByIdSuccessfully() throws IOException {
        Candidates candidateResult = candidatesImp.searchById(CANDIDATE_ID);
        Assert.assertEquals(CANDIDATE_NAME, candidateResult.getName());
    }
}
