package com.iprofile.repository;

import com.iprofile.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findByUserNameOrderByCreatedDateDesc(String user);
}
