package com.footballscore.footballscore.injection.module;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private WeakReference<Fragment> mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = new WeakReference<>(fragment);
    }

    @Provides
    public FragmentManager provideFragmentManager() {
        return mFragment.get().getChildFragmentManager();
    }
}
