package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.users.Users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @GetMapping
    public ResponseVO<?> getUser() {
        ResponseVO<List<Users>> resp = new ResponseVO<>();
        
        List<Users> list = new ArrayList<>();
        list.add(new Users("테스트", "010-1111-2222"));
        list.add(new Users("홍길동", "010-4444-5757"));
        list.add(new Users("김길동", "010-9999-4343"));

        resp.setResponse(list);
        return resp;
    }

    @GetMapping("{id}")
    public ResponseVO<?> getUsers(@PathVariable int id) {
        ResponseVO<Users> resp = new ResponseVO<>();
        
        List<Users> list = new ArrayList<>();
        list.add(new Users("테스트", "010-1111-2222"));
        list.add(new Users("홍길동", "010-4444-5757"));
        list.add(new Users("김길동", "010-9999-4343"));

        resp.setResponse(list.get(id));
        return resp;
    }
    
}
