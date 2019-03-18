package com.example.newxyzreader.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newxyzreader.R;
import com.example.newxyzreader.model.Article;
import com.example.newxyzreader.viewmodel.ArticleListView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder> {


    private List<Article> articles;
    private ArticleListView view;

    public ArticleListAdapter(List<Article> articles, ArticleListView view) {
        this.articles = articles;
        this.view = view;
    }

    @NonNull
    @Override
    public ArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View item = inflater.inflate(R.layout.item_article, parent, false);

        return new ArticleListViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListViewHolder holder, int position) {
        Article  article = articles.get(position);

        holder.setUpCard(article.getThumb(), article.getTitle(), article.getPublished_date(), article.getAuthor());

        holder.cardContainer.setOnClickListener(v -> {
            view.launchArticle(article);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ArticleListViewHolder extends RecyclerView.ViewHolder{

        private View cardContainer;
        private ImageView thumbIv;
        private TextView titleTv;
        private TextView dateTv;
        private TextView authorTv;

        public ArticleListViewHolder(@NonNull View itemView) {
            super(itemView);

            cardContainer = itemView.findViewById(R.id.card_container);
            thumbIv = itemView.findViewById(R.id.thumb_iv);
            titleTv = itemView.findViewById(R.id.article_title_tv);
            dateTv = itemView.findViewById(R.id.article_date_title);
            authorTv = itemView.findViewById(R.id.author_tv);
        }

        public void setUpCard(String imageUrl, String title, String datePub, String author){
            Picasso.get().load(imageUrl).into(thumbIv);
            titleTv.setText(title);
            dateTv.setText(formatDate(datePub));
            authorTv.setText(author);
        }

        private String formatDate(String dateToFormat){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                return dateFormat.format(dateFormat.parse(dateToFormat));
            } catch (ParseException e) {
                Log.e(this.getClass().getName(), "Could not format date", e);
                return dateToFormat;
            }
        }
    }

}
