package com.example.newxyzreader.task;

import android.os.AsyncTask;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

public class GetRemoteArticleTask extends AsyncTask<Void, Void, List<Article>> {

    private final GetRemoteArticleListener listener;
    private ArticleRepository repository;

    public GetRemoteArticleTask(ArticleRepository repository, @NonNull GetRemoteArticleListener listener) {
        this.repository = repository;
        this.listener = listener;
    }

    @Override
    protected List<Article> doInBackground(Void... voids) {
        return repository.getRemoteArticles();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskStarted();
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        super.onPostExecute(articles);
        listener.onTaskFinished();

        if(articles != null){
            listener.onTaskSuccess(articles);
        } else {
            listener.onTaskError("Could not get articles");
        }
    }



    @UiThread
    public interface GetRemoteArticleListener {
        void onTaskStarted();
        void onTaskFinished();
        void onTaskSuccess(List<Article> data);
        void onTaskError(String errorMessage);
    }
}
