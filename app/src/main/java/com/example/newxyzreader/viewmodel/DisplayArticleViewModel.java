package com.example.newxyzreader.viewmodel;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.task.ArticleTaskFactory;
import com.example.newxyzreader.task.GetArticleTask;

import androidx.lifecycle.LiveData;

public class DisplayArticleViewModel implements GetArticleTask.GetArticleListener {

    private ArticleTaskFactory taskFactory;
    private DisplayArticleView view;

    public DisplayArticleViewModel(ArticleTaskFactory taskFactory, DisplayArticleView view) {
        this.taskFactory = taskFactory;
        this.view = view;
    }

    public void getDisplayArticle(int id){
        taskFactory.getArticleTask(id, this).execute();
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
    public void onTaskSuccess(LiveData<Article> data) {
        view.showArticle(data);
    }

    @Override
    public void onTaskError(String errorMessage) {
        view.showError(errorMessage);
    }
}
