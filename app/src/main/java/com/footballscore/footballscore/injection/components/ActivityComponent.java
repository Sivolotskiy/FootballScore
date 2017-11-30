package com.footballscore.footballscore.injection.components;


import com.footballscore.footballscore.injection.module.ActivityModule;
import com.footballscore.footballscore.injection.module.FragmentModule;
import com.footballscore.footballscore.ui.activity.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    FragmentComponent providesFragmentComponent(FragmentModule fragmentModule);

    void inject(MainActivity mainActivity);
}
