package com.example.newxyzreader.task;

import android.os.AsyncTask;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;

import java.util.List;

public class InsertArticlesTask extends AsyncTask<Void, Void, Void> {

    private ArticleRepository repository;
    private List<Article> articles;

    public InsertArticlesTask(ArticleRepository repository, List<Article> articles) {
        this.repository = repository;
        this.articles = articles;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        repository.insertArticles(articles);
        return null;
    }
}
