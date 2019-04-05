package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.matches.Match;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @GetMapping("/")
    public ResponseVO<?> getMatch() {
        ResponseVO<List<Match>> resp = new ResponseVO<>();
        
        List<Match> list = new ArrayList<>();

        resp.setResponse(list);
        return resp;
    }

    @GetMapping("{id}")
    public ResponseVO<?> getMatch(@PathVariable int id) {
        ResponseVO<Match> resp = new ResponseVO<>();
        
        List<Match> list = new ArrayList<>();

        resp.setResponse(list.get(id));
        return resp;
    }
    
}
