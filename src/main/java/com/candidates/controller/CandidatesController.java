package com.candidates.controller;

import com.candidates.component.CandidatesImp;
import com.candidates.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/candidates")
@CrossOrigin("*")
public class CandidatesController {

    @Qualifier("candidatesImp")
    @Autowired
    private CandidatesImp candidatesImp;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Candidate> searchById(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(candidatesImp.searchCandidateById(id), HttpStatus.OK);
    }

}
