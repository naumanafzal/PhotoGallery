package com.mna.photogallery.model;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.mna.photogallery.service.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Photos Class to Observe All Service Calls
 */

public class Photos extends BaseObservable {

    private int page=1;
    private List<Photo> photosList = new ArrayList<>();
    private MutableLiveData<List<Photo>> photos = new MutableLiveData<>();
    private Photo photoDetail = new Photo();
    private MutableLiveData<Photo> photo = new MutableLiveData<>();


    /**
     *
     * @param photo
     */
    public void addPhoto(Photo photo) {
        photosList.add(photo);
    }

    /**
     *
     * @return
     */
    public MutableLiveData<List<Photo>> getPhotos() {
        return photos;
    }

    /**
     *
     */
    public void fetchList(String clientId) {
        Callback<Photos> callback = new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                Photos body = response.body();
                if(body == null)
                {
                    photos.setValue(new ArrayList<Photo>());
                }
                else
                {
                    page++;
                    photos.setValue(body.photosList);
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };

        Api.getApi().getPhotos(clientId,""+page).enqueue(callback);
    }

    /**
     *
     * @param photoDetail
     */
    public void setPhotoDetail(Photo photoDetail) {
        this.photoDetail = photoDetail;
    }

    /**
     *
     * @return
     */
    public MutableLiveData<Photo> getPhotoDetail() {
        return photo;
    }

    /**
     *
     * @param clientId
     * @param photoId
     */
    public void fetchPhotoDetail(String clientId,String photoId) {
        Callback<Photos> callback = new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                Photos body = response.body();
                if(body == null)
                    photo.setValue(null);
                else
                    photo.setValue(body.photoDetail);
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
                photo.setValue(null);
            }
        };

        Api.getApi().getPhotoById(photoId,clientId).enqueue(callback);
    }
}
