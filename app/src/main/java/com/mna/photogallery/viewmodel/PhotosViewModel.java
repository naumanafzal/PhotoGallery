package com.mna.photogallery.viewmodel;

import android.view.View;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mna.photogallery.R;
import com.mna.photogallery.adapter.PhotosAdapter;
import com.mna.photogallery.model.Photo;
import com.mna.photogallery.model.Photos;

import java.util.List;

/**
 * PhotosViewModel
 */

public class PhotosViewModel extends ViewModel {

    private Photos photos;
    private PhotosAdapter adapter;
    public MutableLiveData<Photo> selected;
    public ObservableArrayMap<String, String> images;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    private String clientId;
    /**
     * initializer
     */
    public void init() {
        photos = new Photos();
        selected = new MutableLiveData<>();
        adapter = new PhotosAdapter(R.layout.view_photo, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    /**
     *
     * @param clientId
     */
    public void fetchList(String clientId) {
        clientId = clientId;
        photos.fetchList(clientId);
    }

    /**
     *
     * @return
     */
    public MutableLiveData<List<Photo>> getPhotos() {
        return photos.getPhotos();
    }

    public PhotosAdapter getAdapter() {
        return adapter;
    }

    /**
     *
     * @param photos
     */
    public void setPhotosInAdapter(List<Photo> photos) {
        this.adapter.setPhotos(photos);
        this.adapter.notifyDataSetChanged();
    }

    /**
     *
     * @return
     */
    public MutableLiveData<Photo> getSelected() {
        return selected;
    }

    /**
     *
     * @param index
     */
    public void onItemClick(Integer index) {
        Photo photo = getPhotoAt(index);
        selected.setValue(photo);
    }

    /**
     *
     * @param index
     * @return
     */

    public Photo getPhotoAt(Integer index) {
        if (photos.getPhotos().getValue() != null &&
                index != null &&
                photos.getPhotos().getValue().size() > index) {
            return photos.getPhotos().getValue().get(index);
        }
        return null;
    }

    public void retry() {
        fetchList(clientId);
    }
}
