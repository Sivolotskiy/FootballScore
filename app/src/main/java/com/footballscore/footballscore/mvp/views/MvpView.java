package com.footballscore.footballscore.mvp.views;

public interface MvpView {
    void showLoading();
    void hideLoading();
    void showError(Throwable error);
}
