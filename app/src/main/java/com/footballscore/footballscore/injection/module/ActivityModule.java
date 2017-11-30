package com.footballscore.footballscore.injection.module;



import com.footballscore.footballscore.ui.activity.BaseActivity;

import java.lang.ref.WeakReference;

import dagger.Module;

@Module
public class ActivityModule {
    private WeakReference<BaseActivity> mActivity;

    public ActivityModule(BaseActivity activity) {
        mActivity = new WeakReference<>(activity);
    }
}
