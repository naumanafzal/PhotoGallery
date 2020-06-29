package com.mna.photogallery.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mna.photogallery.R;
import com.mna.photogallery.databinding.ActivityMainBinding;
import com.mna.photogallery.model.Photo;
import com.mna.photogallery.viewmodel.PhotosViewModel;

import java.util.List;

/**
 * Main Activity of the Application
 * Shows the List of Photos
 */
public class MainActivity extends AppCompatActivity {
    private PhotosViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupViewCustom(activityBinding);
        viewModel = new ViewModelProvider(this).get(PhotosViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        activityBinding.setLifecycleOwner(this);
        setupListUpdate();

    }

    /**
     *
     * @param activityBinding
     */
    private void setupViewCustom(ActivityMainBinding activityBinding)
    {
        // Setup Divider
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_item_divid_space));
        activityBinding.listOfPhotos.addItemDecoration(itemDecorator);
    }

    /**
     *
     */
    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.getPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                viewModel.loading.set(View.GONE);
                if (photos.size() == 0) {
                    viewModel.showEmpty.set(View.VISIBLE);
                } else {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setPhotosInAdapter(photos);
                }
            }
        });
        viewModel.fetchList(getString(R.string.client_id));
        setupListClick();
    }

    /**
     *
     */
    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<Photo>() {
            @Override
            public void onChanged(Photo photo) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("Photo", photo);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
