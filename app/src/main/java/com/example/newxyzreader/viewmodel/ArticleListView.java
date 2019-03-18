package com.example.newxyzreader.viewmodel;

import com.example.newxyzreader.model.Article;

import java.util.List;

public interface ArticleListView {
    public void showLoader();

    public void hideLoader();

    public void showArticles(List<Article> data);

    public void showError(String errorMessage);

    public void launchArticle(Article article);
}
