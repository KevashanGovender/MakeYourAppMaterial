package com.example.newxyzreader.viewmodel;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.task.ArticleTaskFactory;
import com.example.newxyzreader.task.GetLocalArticlesTask;
import com.example.newxyzreader.task.GetRemoteArticleTask;

import java.util.List;

public class GetArticlesViewModel implements GetRemoteArticleTask.GetRemoteArticleListener {

    private ArticleTaskFactory taskFactory;
    private ArticleListView view;

    public GetArticlesViewModel(ArticleTaskFactory taskFactory, ArticleListView view) {
        this.taskFactory = taskFactory;
        this.view = view;
    }

    public void getArticlesFromRemote(){
        taskFactory.getRemoteArticleTask(this).execute();
    }

    private void insertArticles(List<Article> articles){
        taskFactory.getInsertArticlesTask(articles).execute();
    }

    @Override
    public void onTaskStarted() {
        view.showLoader();
    }

    @Override
    public void onTaskFinished() {
        view.hideLoader();
    }

    @Override
    public void onTaskSuccess(List<Article> data) {
        view.showArticles(data);
        insertArticles(data);
    }

    @Override
    public void onTaskError(String errorMessage) {
        view.showError(errorMessage);
    }
}
