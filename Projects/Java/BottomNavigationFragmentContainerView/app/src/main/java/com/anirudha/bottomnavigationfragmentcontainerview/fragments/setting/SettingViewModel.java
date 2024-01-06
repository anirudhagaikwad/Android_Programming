package com.anirudha.bottomnavigationfragmentcontainerview.fragments.setting;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
public class SettingViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public SettingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Setting fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}