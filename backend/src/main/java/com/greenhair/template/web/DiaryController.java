package com.greenhair.template.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.diaries.Diary;
import com.greenhair.template.domain.diaries.DiaryRepository;
import com.greenhair.template.domain.match.Match;
import com.greenhair.template.domain.match.MatchRepository;
import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;
import com.greenhair.template.dto.diary.DiaryDto;
import com.greenhair.template.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public List<Diary> getDiaryById(@RequestParam(required = false) Long id) {
        long loginUserId = id;
        List<Diary> diaries = diaryRepository.findByUsersIdOrderByModifiedDateDesc(loginUserId);
        return diaries;
    }

    @GetMapping("/{id}")
    public ResponseVO<?> getDiary(@PathVariable long id) {
        ResponseVO<Diary> resp = new ResponseVO<>();

        Optional<Diary> diary = diaryRepository.findById(id);

        resp.setResponse(diary.get());
        return resp;
    }

    @PostMapping
    public String create(@RequestBody DiaryDto diary) {
        try {
            long loginUserId = jwtService.getMemberId();
            Optional<Users> loginUsers = usersRepository.findById(loginUserId);
            Optional<Match> match = matchRepository.findById(diary.getMatchId());
            diaryRepository.save(Diary.builder().title(diary.getTitle()).content(diary.getContent())
                .users(loginUsers.get()).match(match.get()).build());
            return "success";
        } catch(Exception e) {
            return "falied";
        }
    }

    @PutMapping("/{id}")
    public String updateForm(@PathVariable long id, @RequestBody Diary diary) {
        try {
            long loginUserId = jwtService.getMemberId();
            Optional<Users> loginUsers = usersRepository.findById(loginUserId);
            Optional<Diary> pastDiary = diaryRepository.findById(id);
            if (!pastDiary.isPresent())
		        return "failed";
            diary.setUsers(loginUsers.get());
            diary.setId(id);
            diaryRepository.save(diary);
            return "success";
        } catch(Exception e) {
            return "falied";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        try {
            long loginUserId = jwtService.getMemberId();
            Optional<Users> loginUsers = usersRepository.findById(loginUserId);
            Optional<Diary> diary = diaryRepository.findById(id);
            if (!diary.isPresent() || diary.get().getUsers().getId() != loginUsers.get().getId())
		        return "failed";
            diaryRepository.deleteById(id);
            return "success";
        } catch(Exception e) {
            return "falied";
        }
    }
    
}
