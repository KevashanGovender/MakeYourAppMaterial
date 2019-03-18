package com.example.newxyzreader.repository;


import android.util.Log;

import com.example.newxyzreader.database.ArticleDao;
import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.service.ArticleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class ArticleRepository {

    private ArticleService service;
    private ArticleDao dao;

    public ArticleRepository(ArticleService service, ArticleDao dao) {
        this.service = service;
        this.dao = dao;
    }

    public List<Article> getRemoteArticles() {
        try {
            return service.getArticles().execute().body();
        } catch (IOException e) {
            Log.e(ArticleRepository.class.getName(), "Could not get articles", e.fillInStackTrace());
            return new ArrayList<>();
        }

    }

    public void insertArticles(List<Article> articles){
        dao.insertAllArticles(articles);
    }

    public List<Article> getArticlesLocal(){
        return dao.getArticles();
    }

    public LiveData<Article> getArticle(int id){
        return dao.getArticleFromId(id);
    }
}
