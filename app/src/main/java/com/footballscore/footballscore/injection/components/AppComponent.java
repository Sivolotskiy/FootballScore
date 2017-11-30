package com.footballscore.footballscore.injection.components;


import com.footballscore.footballscore.injection.module.ActivityModule;
import com.footballscore.footballscore.injection.module.ApiModule;
import com.footballscore.footballscore.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class})
@Singleton
public interface AppComponent {

    ActivityComponent providesActivityComponent(ActivityModule activityModule);

}
