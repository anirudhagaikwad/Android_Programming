package com.anirudha.opendefaultappwithtabview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyPagerAdapter extends FragmentStateAdapter {

    public MyPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CameraFragment();
            case 1:
                return new MessageFragment();
            case 2:
                return new ContactsFragment();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Number of tabs
    }
}

