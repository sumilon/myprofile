package com.iprofile.service;

import com.iprofile.model.Diary;

import java.util.List;
import java.util.Optional;

public interface DiaryService {

    List<Diary> getDiaryByUserName(String username);

    Optional<Diary> getDiaryById(long id);

    void updateDiary(Diary diary);

    void deleteDiary(long id);

    void saveDiary(Diary diary);
}
