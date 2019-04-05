package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.diaries.Diary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @GetMapping
    public ResponseVO<?> getDiary() {
        ResponseVO<List<Diary>> resp = new ResponseVO<>();
        
        List<Diary> list = new ArrayList<>();

        resp.setResponse(list);
        return resp;
    }

    @GetMapping("{id}")
    public ResponseVO<?> getDiary(@PathVariable int id) {
        ResponseVO<Diary> resp = new ResponseVO<>();
        
        List<Diary> list = new ArrayList<>();

        resp.setResponse(list.get(id));
        return resp;
    }
    
}
