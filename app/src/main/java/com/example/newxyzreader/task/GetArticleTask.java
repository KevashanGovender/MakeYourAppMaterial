package com.example.newxyzreader.task;

import android.os.AsyncTask;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;

import java.util.List;

import androidx.annotation.UiThread;
import androidx.lifecycle.LiveData;


public class GetArticleTask extends AsyncTask<Void, Void, LiveData<Article>> {

    private ArticleRepository repository;
    private int articleId;
    private GetArticleListener listener;

    public GetArticleTask(ArticleRepository repository, int articleId, GetArticleListener listener) {
        this.repository = repository;
        this.articleId = articleId;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskStarted();
    }

    @Override
    protected LiveData<Article> doInBackground(Void... voids) {
        return repository.getArticle(articleId);
    }

    @Override
    protected void onPostExecute(LiveData<Article> data) {
        super.onPostExecute(data);

        listener.onTaskFinished();

        if(data != null){
            listener.onTaskSuccess(data);
        } else {
            listener.onTaskError("Could not get article");
        }

    }

    @UiThread
    public interface GetArticleListener {
        void onTaskStarted();
        void onTaskFinished();
        void onTaskSuccess(LiveData<Article> data);
        void onTaskError(String errorMessage);
    }
}
