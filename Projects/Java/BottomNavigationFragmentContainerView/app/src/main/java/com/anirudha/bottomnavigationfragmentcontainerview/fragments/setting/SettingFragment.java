package com.anirudha.bottomnavigationfragmentcontainerview.fragments.setting;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anirudha.bottomnavigationfragmentcontainerview.R;

public class SettingFragment extends Fragment {
    private SettingViewModel viewModel;
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Perform any initialization that doesn't involve the UI.
        viewModel = new ViewModelProvider(this).get(SettingViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_camera, container, false);
        // Inflate the fragment's layout and return the View.
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        textView = view.findViewById(R.id.text_setting);
        // Observe LiveData and update TextView when the value changes
        viewModel.getText().observe(getViewLifecycleOwner(), newText -> {
            textView.setText(newText);
        });
        return view;
    }
}