package com.example.newxyzreader.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.example.newxyzreader.R;
import com.example.newxyzreader.database.ArticleDatabse;
import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;
import com.example.newxyzreader.service.ArticleService;
import com.example.newxyzreader.service.RetrofitInstance;
import com.example.newxyzreader.task.ArticleTaskFactory;
import com.example.newxyzreader.viewmodel.ArticleListView;
import com.example.newxyzreader.viewmodel.GetArticlesViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleListActivity extends AppCompatActivity implements ArticleListView {

    private RecyclerView articleRv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progress_circular);

        articleRv = findViewById(R.id.article_rv);
        articleRv.setLayoutManager(new LinearLayoutManager(this));

        ArticleService service = RetrofitInstance.getRetrofitInstance().create(ArticleService.class);
        ArticleTaskFactory taskFactory = new ArticleTaskFactory(new ArticleRepository(service,
                ArticleDatabse.getInstance(getApplicationContext()).getArticleDao()));
        GetArticlesViewModel viewModel = new GetArticlesViewModel(taskFactory, this);

        viewModel.getArticlesFromRemote();
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showArticles(List<Article> data) {
        articleRv.setAdapter(new ArticleListAdapter(data, this));
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void launchArticle(Article article) {
        startActivity(new Intent(this, ScrollingActivity.class));
    }
}
