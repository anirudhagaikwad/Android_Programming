package com.anirudha.bottomnavigationfragmentcontainerview.fragments.camera;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
public class CameraViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public CameraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Camera fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}