package com.example.newxyzreader.database;

import android.content.Context;

import com.example.newxyzreader.model.Article;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class ArticleDatabse extends RoomDatabase {

    private static ArticleDatabse instance;

    public abstract ArticleDao getArticleDao();

    public static ArticleDatabse getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ArticleDatabse.class, "xyz_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
