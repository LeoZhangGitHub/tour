package com.example.administrator.tour;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/4/1/001.
 */

public class ArticleActivity extends Activity {
    private TextView articleTitle;
    private TextView articleContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        articleTitle = (TextView) findViewById(R.id.activity_article_title);
        articleContent = (TextView) findViewById(R.id.activity_article_content);
    }
}
