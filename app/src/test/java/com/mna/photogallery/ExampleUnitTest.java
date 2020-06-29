package com.mna.photogallery;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.mna.photogallery.model.Photo;
import com.mna.photogallery.viewmodel.PhotosViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<Photo> observer;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_clickReturnCorrectObject() {
        List<Photo> photos = generatePhotos();
        PhotosViewModel viewModel = new PhotosViewModel();
        viewModel.init();

        viewModel.getSelected().observeForever(observer);
        // When
        viewModel.getPhotos().setValue(photos);
        viewModel.onItemClick(0);
        // then
        verify(observer).onChanged(photos.get(0));
    }

    private List<Photo> generatePhotos() {
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Photo db = new Photo();
            db.setDescription("Description " + i);
            photos.add(db);
        }
        return photos;
    }
}