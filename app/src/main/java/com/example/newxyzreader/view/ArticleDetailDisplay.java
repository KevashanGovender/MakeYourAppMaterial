package com.example.newxyzreader.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newxyzreader.R;
import com.example.newxyzreader.database.ArticleDatabse;
import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.repository.ArticleRepository;
import com.example.newxyzreader.service.ArticleService;
import com.example.newxyzreader.service.RetrofitInstance;
import com.example.newxyzreader.task.ArticleTaskFactory;
import com.example.newxyzreader.viewmodel.DisplayArticleView;
import com.example.newxyzreader.viewmodel.DisplayArticleViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.LiveData;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT;

public class ArticleDetailDisplay extends AppCompatActivity implements DisplayArticleView {

    private TextView bodyTv;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        bodyTv =  findViewById(R.id.article_detail);
        progressBar = findViewById(R.id.progress_circular);

        fab = findViewById(R.id.fab);

        Intent intent = getIntent();
        int articleId = intent.getIntExtra(ArticleListActivity.ARTICLE_ID, 0);

        ArticleService service = RetrofitInstance.getRetrofitInstance().create(ArticleService.class);
        ArticleTaskFactory taskFactory = new ArticleTaskFactory(new ArticleRepository(service,
                ArticleDatabse.getInstance(getApplicationContext()).getArticleDao()));
        DisplayArticleViewModel viewModel = new DisplayArticleViewModel(taskFactory, this);

        viewModel.getDisplayArticle(articleId);

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
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showArticle(LiveData<Article> article) {
        article.observe(this, a -> {
            toolbar.setTitle(a.getTitle());
            bodyTv.setText(Html.fromHtml(a.getBody(), FROM_HTML_MODE_COMPACT));

            fab.setOnClickListener(v -> {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText = a.getBody();
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, a.getTitle());
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(intent, "Choose sharing method"));
            });

        });
    }
}
