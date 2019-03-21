package com.example.newxyzreader.viewmodel;

import com.example.newxyzreader.model.Article;

import androidx.lifecycle.LiveData;

public interface DisplayArticleView {

    void showLoader();

    void hideLoader();

    void showError(String errorMessage);

    void showArticle(LiveData<Article> article);

}
