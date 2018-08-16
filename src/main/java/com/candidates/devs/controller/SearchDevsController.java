package com.candidates.devs.controller;

import com.candidates.devs.component.SearchDevsImp;
import com.candidates.devs.model.Dev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/devs")
public class SearchDevsController {

    @Qualifier("searchDevs")
    @Autowired
    private SearchDevsImp searchDevsImp;

    @GetMapping
    public ResponseEntity<Dev> searchById(@RequestParam String id) throws IOException {
        return new ResponseEntity<>(searchDevsImp.searchById(id), HttpStatus.OK);
    }
}
