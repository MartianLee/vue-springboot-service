package com.greenhair.template.domain.diaries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUsersIdOrderByModifiedDateDesc(long userId);
}