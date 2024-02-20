package com.anirudha.notepad;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        return DocumentFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 5; // Set the number of tabs/documents you want
    }
}