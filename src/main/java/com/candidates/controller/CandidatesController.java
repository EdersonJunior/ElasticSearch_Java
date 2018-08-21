package com.candidates.controller;

import com.candidates.component.CandidatesImp;
import com.candidates.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController("/candidates")
public class CandidatesController {

    @Qualifier("candidatesImp")
    @Autowired
    private CandidatesImp candidatesImp;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Candidate> searchById(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(candidatesImp.searchById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> searchAll() throws IOException {
        return new ResponseEntity<>(candidatesImp.searchAll(), HttpStatus.OK);
    }
}
