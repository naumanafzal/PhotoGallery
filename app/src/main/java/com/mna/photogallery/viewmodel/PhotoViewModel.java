package com.mna.photogallery.viewmodel;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mna.photogallery.model.Photo;
import com.mna.photogallery.model.Photos;

/**
 * PhotoViewModel
 */

public class PhotoViewModel extends ViewModel {
    Photos photos;
    public MutableLiveData<Void> onEndLive = new MutableLiveData<>();
    private MutableLiveData<Photo> selected;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    public ObservableInt hideEmpty;

    /**
     * initializer
     */
    public void init() {
        photos = new Photos();
        selected = new MutableLiveData<Photo>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        hideEmpty = new ObservableInt(View.GONE);
    }

    public MutableLiveData<Photo> getFetchedPhoto() {
        return photos.getPhotoDetail();
    }

    public MutableLiveData<Photo> getSelected() {
        return selected;
    }

    /**
     *
     * @param photo
     */
    public void setPhoto(Photo photo) {
        selected.setValue(photo);
    }

    /**
     *
     * @param clientId
     * @param photoId
     */
    public void fetchPhotoDetail(String clientId,String photoId) {
        photos.fetchPhotoDetail(clientId,photoId);
    }

    public void onBackClick() {
        onEndLive.setValue(null);
    }
}
