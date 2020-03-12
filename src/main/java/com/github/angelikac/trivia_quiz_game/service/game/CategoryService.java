package com.github.angelikac.trivia_quiz_game.service.game;

import com.github.angelikac.trivia_quiz_game.entity.game.CategoryEntity;
import com.github.angelikac.trivia_quiz_game.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> findAllCategoriesFromDB() {
       return categoryRepository.findAll();
    }
}