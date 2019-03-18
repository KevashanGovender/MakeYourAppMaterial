package com.example.newxyzreader.database;

import com.example.newxyzreader.model.Article;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleDao {

    @Insert(onConflict = REPLACE)
    void insertAllArticles(List<Article> articles);

    @Query("SELECT * FROM article_table")
    List<Article> getArticles();

    @Query("SELECT * FROM article_table WHERE id = :id")
    LiveData<Article> getArticleFromId(int id);
}
