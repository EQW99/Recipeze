package com.group2.recipeze.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ProfileViewModel.
 */
public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> meText;

    public ProfileViewModel() {
        meText = new MutableLiveData<>();
        meText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return meText;
    }
}
