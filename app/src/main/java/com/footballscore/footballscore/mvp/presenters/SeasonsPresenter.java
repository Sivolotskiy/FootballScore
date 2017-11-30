package com.footballscore.footballscore.mvp.presenters;


import android.util.Log;

import com.footballscore.footballscore.mvp.views.SeasonsView;
import com.footballscore.footballscore.net.ApiManager;


import javax.inject.Inject;

public class SeasonsPresenter extends MvpBasePresenter<SeasonsView> {
    private static final String TAG = SeasonsPresenter.class.getName();

    @Inject
    ApiManager mApiManager;

    @Inject
    public SeasonsPresenter(
            ApiManager apiManager) {
        mApiManager = apiManager;
    }

    public void getSeasons() {
        mApiManager.getSoccerSeasons().subscribe(conversationResponse -> {
            if (getView() != null) {
                getView().hideLoading();
                getView().setData(conversationResponse);
            }
        }, throwable -> {
            Log.e(TAG, "Error while get soccer seasons: ", throwable);
            if (getView() != null) {
                getView().hideLoading();
                getView().showError(throwable);
            }
        });
    }

}
