package com.anirudha.bottomnavigationfragmentcontainerview.fragments.meesage;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MessageViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public MessageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Message fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}