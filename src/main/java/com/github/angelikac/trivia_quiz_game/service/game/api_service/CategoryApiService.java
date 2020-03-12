package com.github.angelikac.trivia_quiz_game.service.game.api_service;


import com.github.angelikac.trivia_quiz_game.entity.game.CategoryEntity;
import com.github.angelikac.trivia_quiz_game.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryApiService {

    private OpenDBApiService categoryApiService;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryApiService(OpenDBApiService categoryApiService, CategoryRepository categoryRepository) {
        this.categoryApiService = categoryApiService;
        this.categoryRepository = categoryRepository;
    }

    private List<CategoryEntity> findAllCategoriesFromApi() {
        return categoryApiService.getCategoriesListFromApi();
    }

    @Transactional
    public void saveAllCategoriesInDb(){
        for (final CategoryEntity categoryEntity : findAllCategoriesFromApi()) {
            final int totalQuestionCount = categoryApiService
                    .getCountOfQuestionFromApi(categoryEntity.getId())
                    .getTotalQuestionCount();
            if (totalQuestionCount >= 30){
                categoryRepository.save(categoryEntity);
            }
        }
    }
}