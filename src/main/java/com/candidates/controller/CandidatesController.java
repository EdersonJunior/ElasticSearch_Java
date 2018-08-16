package com.candidates.controller;

import com.candidates.component.CandidatesImp;
import com.candidates.model.Candidates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/candidates")
public class CandidatesController {

    @Qualifier("candidatesImp")
    @Autowired
    private CandidatesImp candidatesImp;

    @GetMapping
    public ResponseEntity<Candidates> searchById(@RequestParam String id) throws IOException {
        return new ResponseEntity<>(candidatesImp.searchById(id), HttpStatus.OK);
    }
}
