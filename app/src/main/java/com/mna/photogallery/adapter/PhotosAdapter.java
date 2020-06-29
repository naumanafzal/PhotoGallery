package com.mna.photogallery.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.mna.photogallery.BR;
import com.mna.photogallery.model.Photo;
import com.mna.photogallery.viewmodel.PhotosViewModel;

import java.util.List;

/**
 * PhotosAdapter
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.GenericViewHolder> {

    private int layoutId;
    private List<Photo> photos;
    private PhotosViewModel viewModel;

    /**
     *
     * @param layoutId
     * @param viewModel
     */
    public PhotosAdapter(@LayoutRes int layoutId, PhotosViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new GenericViewHolder(binding);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    /**
     *
     * @param photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     *
     */
    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PhotosViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}
