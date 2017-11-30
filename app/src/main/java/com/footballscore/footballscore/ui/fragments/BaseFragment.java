package com.footballscore.footballscore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.footballscore.footballscore.injection.components.ActivityComponent;
import com.footballscore.footballscore.injection.components.FragmentComponent;
import com.footballscore.footballscore.injection.module.FragmentModule;
import com.footballscore.footballscore.mvp.presenters.MvpBasePresenter;
import com.footballscore.footballscore.mvp.views.MvpView;
import com.footballscore.footballscore.ui.activity.MainActivity;

import butterknife.ButterKnife;
import lombok.Getter;
import lombok.experimental.Accessors;

abstract class BaseFragment<V extends MvpView, P extends MvpBasePresenter<V>> extends Fragment {

    protected P mPresenter;
    protected V mView;

    @Getter
    @Accessors(prefix = "m")
    private FragmentComponent mFragmentComponent;

    abstract public int getLayoutRes();

    abstract void injectDependencies();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = getActivityComponent()
                .providesFragmentComponent(new FragmentModule(this));
        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected ActivityComponent getActivityComponent() {
        return ((MainActivity) getActivity()).getActivityComponent();
    }
}
