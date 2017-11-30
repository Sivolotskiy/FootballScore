package com.footballscore.footballscore;

import android.app.Application;

import com.footballscore.footballscore.injection.components.AppComponent;
import com.footballscore.footballscore.injection.components.DaggerAppComponent;
import com.footballscore.footballscore.injection.module.AppModule;

public class App extends Application {

    private static AppComponent mComponent;

    public AppComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
