package com.anirudha.fragmentlifecycle.fragment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FragmentLifeCycleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public FragmentLifeCycleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}