package com.anirudha.watersupplier.supplier;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anirudha.watersupplier.R;

public class SupplierHomeFragment extends Fragment {

    private SupplierHomeViewModel mViewModel;

    public static SupplierHomeFragment newInstance() {
        return new SupplierHomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_supplier_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SupplierHomeViewModel.class);
        // TODO: Use the ViewModel
    }

}