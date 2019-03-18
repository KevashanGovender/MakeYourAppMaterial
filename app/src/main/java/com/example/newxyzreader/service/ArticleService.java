package com.example.newxyzreader.service;

import com.example.newxyzreader.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleService {

    @GET("/xyz-reader-json")
    Call<List<Article>> getArticles();
}
