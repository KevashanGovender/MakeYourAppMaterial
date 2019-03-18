package com.example.newxyzreader.task;

import android.os.AsyncTask;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

public class GetLocalArticlesTask extends AsyncTask<Void, Void, List<Article>> {

    private ArticleRepository repository;

    public GetLocalArticlesTask(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<Article> doInBackground(Void... voids) {
        return repository.getArticlesLocal();
    }

    @UiThread
    public interface GetLocalArticlesListener {
        void onTaskStarted();
        void onTaskSuccess(List<Article> articles);
        void onTaskFinished();
        void onTaskError(String no_articles_found);
    }
}
