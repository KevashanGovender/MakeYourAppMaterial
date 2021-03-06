package com.example.newxyzreader.viewmodel;

import com.example.newxyzreader.model.Article;

import java.util.List;

public interface ArticleListView {
    void showLoader();

    void hideLoader();

    void showArticles(List<Article> data);

    void showError(String errorMessage);

    void launchArticle(Article article);
}
