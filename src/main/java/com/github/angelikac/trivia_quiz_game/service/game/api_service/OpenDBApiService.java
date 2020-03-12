package com.github.angelikac.trivia_quiz_game.service.game.api_service;

import com.github.angelikac.trivia_quiz_game.entity.game.CategoryEntity;
import com.github.angelikac.trivia_quiz_game.entity.game.api.ApiCount;
import com.github.angelikac.trivia_quiz_game.entity.game.api.CategoryApiResults;
import com.github.angelikac.trivia_quiz_game.entity.game.api.QuestionApi;
import com.github.angelikac.trivia_quiz_game.entity.game.api.QuestionApiResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenDBApiService {

    private final RestTemplate restTemplate;

    @Value("${api.triviaquiz.address.question}")
    private String currentApiAddress;
    @Value("${api.triviaquiz.address.category}")
    private String categoryApiAddress;
    @Value("${api.triviaquiz.address.checknumberofquestions}")
    private String countApiAddress;

    @Autowired
    public OpenDBApiService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    List<QuestionApi> getQuestionByCategory(Long category) {
        final String apiAddress = currentApiAddress.replace("{category}", category.toString());

        final QuestionApiResults questionApi = restTemplate.getForObject(apiAddress, QuestionApiResults.class);
        if (questionApi != null) {
            return questionApi.getResults();
        }
        throw new RuntimeException("Problem with reading question list");
    }

    List<CategoryEntity> getCategoriesListFromApi() {
        final CategoryApiResults category = getCategoryApiResults();
        if (category != null) {
            return category.getTriviaCategories();
        }
        throw new RuntimeException("Problem with reading categories list");
    }

    public CategoryApiResults getCategoryApiResults() {
            return restTemplate.getForObject(categoryApiAddress, CategoryApiResults.class);
    }

    ApiCount.CategoryQuestionCount getCountOfQuestionFromApi(Long category){
        final String apiAddress = countApiAddress.replace("{category}", category.toString());

        final ApiCount apiCount = restTemplate.getForObject(apiAddress, ApiCount.class);
        if (apiCount != null) {
            return apiCount.getCategoryQuestionCount();
        }
        throw new RuntimeException("Problem with reading count of questions");
    }
}