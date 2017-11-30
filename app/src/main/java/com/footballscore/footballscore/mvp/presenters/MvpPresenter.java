package com.footballscore.footballscore.mvp.presenters;


import com.footballscore.footballscore.mvp.views.MvpView;

public interface MvpPresenter<V extends MvpView> {

    public void attachView(V view);

    public void detachView(boolean retainInstance);
}
