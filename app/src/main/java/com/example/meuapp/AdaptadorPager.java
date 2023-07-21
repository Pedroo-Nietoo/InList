package com.example.meuapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdaptadorPager extends FragmentStateAdapter {
    public AdaptadorPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public AdaptadorPager(@NonNull Fragment fragment) {
        super(fragment);
    }

    public AdaptadorPager(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 1 ? new Exibe() : new Cadastro();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
