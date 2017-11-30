package com.footballscore.footballscore.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.footballscore.footballscore.App;
import com.footballscore.footballscore.injection.components.ActivityComponent;
import com.footballscore.footballscore.injection.components.AppComponent;
import com.footballscore.footballscore.injection.module.ActivityModule;
import com.footballscore.footballscore.mvp.presenters.MvpPresenter;
import com.footballscore.footballscore.mvp.views.MvpView;

import butterknife.ButterKnife;
import lombok.Getter;
import lombok.experimental.Accessors;

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity{

    @Getter
    @Accessors(prefix = "m")
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivityComponent = getApplicationComponent().providesActivityComponent(new ActivityModule(this));
        injectDependencies();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
    }


    public abstract void injectDependencies();

    public abstract int getLayoutRes();

    AppComponent getApplicationComponent() {
        return ((App) getApplication()).getComponent();
    }
}
