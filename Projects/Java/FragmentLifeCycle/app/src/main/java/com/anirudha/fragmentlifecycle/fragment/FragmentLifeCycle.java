package com.anirudha.fragmentlifecycle.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anirudha.fragmentlifecycle.R;

public class FragmentLifeCycle extends Fragment {
    private TextView textView;
    private FragmentLifeCycleViewModel viewModel;

     @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Perform any necessary initialization or setup related to the attached context.

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Perform any initialization that doesn't involve the UI.
        viewModel = new ViewModelProvider(this).get(FragmentLifeCycleViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment's layout and return the View.
        //return inflater.inflate(R.layout.life_cycle_fragment, container, false);
        View view = inflater.inflate(R.layout.life_cycle_fragment, container, false);
        textView = view.findViewById(R.id.textViewId);
        // Observe LiveData and update TextView when the value changes
        viewModel.getText().observe(getViewLifecycleOwner(), newText -> {
            textView.setText(newText);
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Perform UI-related initialization after the View has been created.
        showToast("onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Perform actions when the fragment becomes visible.
        showToast("onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        // Resume any operations or animations paused in onPause().
        showToast("onResume");
    }

    @Override
    public void onPause() {
        // Pause operations or animations that should be stopped when the fragment is not visible.
        super.onPause();
        showToast("onPause");
    }

    @Override
    public void onStop() {
        // Perform actions when the fragment is no longer visible.
        super.onStop();
        showToast("onStop");
    }

    @Override
    public void onDestroyView() {
        // Release resources associated with the UI.
        super.onDestroyView();
        showToast("onDestroyView");
    }

    @Override
    public void onDestroy() {
        // Perform any final cleanup before the fragment is destroyed.
        super.onDestroy();
        showToast("onDestroy");
    }

    @Override
    public void onDetach() {
        // Detach from the activity, perform any necessary cleanup.
        super.onDetach();
        showToast("onDetach");
    }
    private void showToast(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}