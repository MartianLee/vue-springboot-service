package com.greenhair.template;

import java.util.List;
import java.util.Optional;

import com.greenhair.template.domain.diaries.Diary;
import com.greenhair.template.domain.diaries.DiaryRepository;
import com.greenhair.template.domain.users.Users;
import com.greenhair.template.domain.users.UsersRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiaryRepositoryTest {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void saveDiaryTest() {
        // given

        diaryRepository.save(Diary.builder().title("Test Diary").content("I'm writting test Diary")
                .users(usersRepository.findByEmail("test1@gmail.com")).build());

        diaryRepository.save(Diary.builder().title("Test Diary2").content("I'm writting test Diary2")
                .users(usersRepository.findByEmail("test1@gmail.com")).build());

        // when
        Optional<Diary> testDiary = diaryRepository.findById((long) 1);
        Diary diary = testDiary.get();

        Users testUser = usersRepository.findByEmail("test1@gmail.com");
        List<Diary> testDiaries = diaryRepository.findByUsersIdOrderByModifiedDateDesc(testUser.getId());
        
        // then
        assertThat(diary.getTitle(), is("Test Diary"));
        assertThat(testDiaries.get(0).getTitle() , is("Test Diary"));
    }
}
