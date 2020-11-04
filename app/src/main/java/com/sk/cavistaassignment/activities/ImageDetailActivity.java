package com.sk.cavistaassignment.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sk.cavistaassignment.R;
import com.sk.cavistaassignment.adapters.CommentListAdapter;
import com.sk.cavistaassignment.adapters.ImageDetailViewPagerAdapter;
import com.sk.cavistaassignment.model.Image;
import com.sk.cavistaassignment.model.ImageCommentEntity;
import com.sk.cavistaassignment.viewModels.ImageDetailViewModel;

import java.util.List;

public class ImageDetailActivity extends AppCompatActivity {

    private ImageDetailViewPagerAdapter imageDetailViewPagerAdapter;
    private CommentListAdapter commentListAdapter;
    private ViewPager2 imageViewPager;
    private EditText commentEditText;
    private FloatingActionButton addComment;
    private RecyclerView commentList;
    private List<Image> imageList;
    private TabLayout pageIndicator;
    private String dataID;
    private String title;
    private ImageDetailViewModel imageDetailViewModel;
    private TextView emptyCommentsText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        initializeView();

        imageDetailViewModel = new ViewModelProvider(this).get(ImageDetailViewModel.class);

        imageList = getIntent().getParcelableArrayListExtra("ImageList");
        dataID = getIntent().getStringExtra("dataID");
        title = getIntent().getStringExtra("title");

        setTitle(title);

        if (imageList == null || imageList.size() == 0) {
            onBackPressed();
        }

        setAdapters();
        setUpClickListeners();
        setUpViewModelObservers();
    }

    private void setUpViewModelObservers() {
        imageDetailViewModel.getAllImageComment(dataID);
        imageDetailViewModel.getImageCommentList().observe(this, imageCommentEntities -> {

            if (imageCommentEntities.isEmpty()) {
                emptyCommentsText.setVisibility(View.VISIBLE);
            } else {
                emptyCommentsText.setVisibility(View.GONE);
            }

            commentListAdapter.setListData(imageCommentEntities);
        });
    }

    private void setUpClickListeners() {
        addComment.setOnClickListener(view -> {
            commentEditText.setError(null);
            String comment = commentEditText.getText().toString();
            if (!comment.trim().isEmpty()) {
                ImageCommentEntity commentEntity = new ImageCommentEntity(0, comment, dataID, System.currentTimeMillis());
                imageDetailViewModel.insertComment(commentEntity);
                commentEditText.setText("");
            } else {
                commentEditText.setError(getString(R.string.add_comment_error));
            }
        });
    }

    private void setAdapters() {
        imageDetailViewPagerAdapter = new ImageDetailViewPagerAdapter(this);
        imageViewPager.setAdapter(imageDetailViewPagerAdapter);
        imageDetailViewPagerAdapter.setListData(imageList);

        commentListAdapter = new CommentListAdapter(this);
        commentList.setAdapter(commentListAdapter);
        commentList.addItemDecoration(new DividerItemDecoration(commentList.getContext(), DividerItemDecoration.VERTICAL));

        new TabLayoutMediator(pageIndicator, imageViewPager, (tab, position) -> {
        }).attach();

        if (imageList.size() > 1) {
            pageIndicator.setVisibility(View.VISIBLE);
        } else {
            pageIndicator.setVisibility(View.GONE);
        }
    }

    private void initializeView() {
        imageViewPager = findViewById(R.id.imageViewPager);
        commentEditText = findViewById(R.id.commentEditText);
        addComment = findViewById(R.id.addComment);
        commentList = findViewById(R.id.commentList);
        pageIndicator = findViewById(R.id.pageIndicator);
        emptyCommentsText = findViewById(R.id.emptyCommentsText);
    }
}
