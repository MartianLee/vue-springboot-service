package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.matches.Matches;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/matches")
public class MatchesController {

    @GetMapping
    public ResponseVO<?> getMatches() {
        ResponseVO<List<Matches>> resp = new ResponseVO<>();
        
        List<Matches> list = new ArrayList<>();

        resp.setResponse(list);
        return resp;
    }

    @GetMapping("{id}")
    public ResponseVO<?> getMatches(@PathVariable int id) {
        ResponseVO<Matches> resp = new ResponseVO<>();
        
        List<Matches> list = new ArrayList<>();

        resp.setResponse(list.get(id));
        return resp;
    }
    
}
