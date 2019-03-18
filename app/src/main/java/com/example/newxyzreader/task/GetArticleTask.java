package com.example.newxyzreader.task;

import android.os.AsyncTask;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;

import androidx.lifecycle.LiveData;


public class GetArticleTask extends AsyncTask<Void, Void, LiveData<Article>> {

    private ArticleRepository repository;
    private int articleId;

    public GetArticleTask(ArticleRepository repository, int articleId) {
        this.repository = repository;
        this.articleId = articleId;
    }

    @Override
    protected LiveData<Article> doInBackground(Void... voids) {
        return repository.getArticle(articleId);
    }
}
