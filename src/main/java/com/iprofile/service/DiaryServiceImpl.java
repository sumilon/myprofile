package com.iprofile.service;

import com.iprofile.model.Diary;
import com.iprofile.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Override
    public List<Diary> getDiaryByUserName(String username) {
        return diaryRepository.findByUserNameOrderByCreatedDateDesc(username);
    }

    @Override
    public Optional<Diary> getDiaryById(long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public void updateDiary(Diary diary) {
        diaryRepository.save(diary);
    }

    @Override
    public void deleteDiary(long id) {
        Optional<Diary> diary = diaryRepository.findById(id);
        diary.ifPresent(value -> diaryRepository.delete(value));
    }

    @Override
    public void saveDiary(Diary diary) {
        diaryRepository.save(diary);
    }

    @Override
    public List<Diary> fetchAllDiaryData() {
        return diaryRepository.findAll();
    }
}
