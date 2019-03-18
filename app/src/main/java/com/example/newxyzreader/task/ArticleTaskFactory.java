package com.example.newxyzreader.task;

import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;

import java.util.List;

import androidx.annotation.NonNull;

public class ArticleTaskFactory {

    private ArticleRepository repository;

    public ArticleTaskFactory(ArticleRepository repository) {
        this.repository = repository;
    }

    public GetRemoteArticleTask getRemoteArticleTask(@NonNull GetRemoteArticleTask.GetRemoteArticleListener listener){
        return new GetRemoteArticleTask(repository, listener);
    }

    public InsertArticlesTask getInsertArticlesTask(List<Article> articles){
        return new InsertArticlesTask(repository, articles);
    }

    public GetLocalArticlesTask getLocalArticlesTask(){
        return new GetLocalArticlesTask(repository);
    }

    public GetArticleTask getArticleTask(int articleId){
        return new GetArticleTask(repository, articleId);
    }
}
