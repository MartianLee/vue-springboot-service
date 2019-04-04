package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;
import com.greenhair.template.service.JwtService;
import com.greenhair.template.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseVO<?> getUser() {
        ResponseVO<List<Users>> resp = new ResponseVO<>();
        List<Users> list = userRepository.findAll();
        resp.setResponse(list);
        return resp;
    }

    @GetMapping("{id}")
    public ResponseVO<?> getUsers(@PathVariable int id) {
        ResponseVO<Users> resp = new ResponseVO<>();

        List<Users> list = new ArrayList<>();
        list.add(new Users("adsf@wfe.com", "awefawef", "홍길동"));
        list.add(new Users("gwrfgwe@wfeq.com", "qwerr", "김길동"));
        list.add(new Users("zveye@wfef.com", "hgfhej", "최길동"));

        resp.setResponse(list.get(id));
        return resp;
    }

    @PostMapping(path = "/create")
    public String create(@RequestBody Users user) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch(Exception e) {
            return "failed";
        }
        return "success";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id) {
        // Optional<Users> user = userRepository.findById(id);
        // model.addAttribute("user", user);
        return "/user/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Users updatedUser) {
        // Users user = userRepository.findById(id); // 기존의 아이디 정보를 조회
        // user.update(updatedUser);   // 아이디의 정보 변경
        // userRepository.save(user);  // 변경된 정보를 저장
        return "redirect:/users/list";
    }
    
    @PostMapping(path = "/login")
    public String login(@RequestBody Users user) {
        Users loginUsers;
        try {
            loginUsers = usersService.signin(user.getEmail(), user.getPassword());
            String token = jwtService.create("member", loginUsers, "user");
            return token;
        } catch(Exception e) {
            return "failed";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에 담기 user 를 제거 <-- 변경
        // session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        // System.out.println("logout success");
        return "redirect:/";
    }
    
}
