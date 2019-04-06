package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.diaries.Diary;
import com.greenhair.template.domain.diaries.DiaryRepository;
import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;
import com.greenhair.template.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public List<Diary> getDiary() {
        long loginUserId = jwtService.getMemberId();
        List<Diary> diaries = diaryRepository.findByUsersId(loginUserId);
        return diaries;
    }

    @GetMapping("{id}")
    public ResponseVO<?> getDiary(@PathVariable int id) {
        ResponseVO<Diary> resp = new ResponseVO<>();

        List<Diary> list = new ArrayList<>();

        resp.setResponse(list.get(id));
        return resp;
    }

    @PostMapping(path = "/create")
    public String create(@RequestBody Diary diary) {
        try{
            long loginUserId = jwtService.getMemberId();
            Optional<Users> loginUsers = usersRepository.findById(loginUserId);
            diary.setUsers(loginUsers.get());
            System.out.println(loginUserId);
            System.out.println(loginUsers);
            System.out.println(diary);
            diaryRepository.save(diary);
            return "success";
        } catch(Exception e) {
            return "falied";
        }
    }
    
}
