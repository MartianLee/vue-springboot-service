package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.diaries.Diaries;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/diaries")
public class DiariesController {

    @GetMapping
    public ResponseVO<?> getDiaries() {
        ResponseVO<List<Diaries>> resp = new ResponseVO<>();
        
        List<Diaries> list = new ArrayList<>();

        resp.setResponse(list);
        return resp;
    }

    @GetMapping("{id}")
    public ResponseVO<?> getDiaries(@PathVariable int id) {
        ResponseVO<Diaries> resp = new ResponseVO<>();
        
        List<Diaries> list = new ArrayList<>();

        resp.setResponse(list.get(id));
        return resp;
    }
    
}
