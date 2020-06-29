package com.mna.photogallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mna.photogallery.R;
import com.mna.photogallery.databinding.ActivityDetailBinding;
import com.mna.photogallery.model.Photo;
import com.mna.photogallery.viewmodel.PhotoViewModel;

/**
 * Detail Activity to show the Details of the Photos
 */
public class DetailActivity extends AppCompatActivity {
    private PhotoViewModel viewModel;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupBindings(savedInstanceState);
    }

    /**
     *
     * @param savedInstanceState
     */
    private void setupBindings(Bundle savedInstanceState) {
        ActivityDetailBinding activityBinding = DataBindingUtil.setContentView(DetailActivity.this, R.layout.activity_detail);
        viewModel = new ViewModelProvider(DetailActivity.this).get(PhotoViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setViewModel(viewModel);
        activityBinding.setLifecycleOwner(this);
        setupListUpdate();
    }

    /**
     * setupListUpdate
     */
    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra("Photo");
        //viewModel.setPhoto(photo);

        viewModel.getFetchedPhoto().observe(DetailActivity.this, new Observer<Photo>() {
            @Override
            public void onChanged(Photo photoNew) {
                viewModel.loading.set(View.GONE);
                if (photoNew != null) {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.hideEmpty.set(View.VISIBLE);
                    viewModel.setPhoto(photoNew);
                } else {
                    viewModel.showEmpty.set(View.VISIBLE);
                    viewModel.hideEmpty.set(View.GONE);
                }
            }
        });

        viewModel.onEndLive.observe(DetailActivity.this, new Observer<Void>() {
            @Override
            public void onChanged(Void i) {
                finish();
            }
        });
        viewModel.fetchPhotoDetail(getString(R.string.client_id),photo.getId());

    }

}
