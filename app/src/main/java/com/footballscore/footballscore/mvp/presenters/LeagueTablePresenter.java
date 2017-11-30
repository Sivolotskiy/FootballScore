package com.footballscore.footballscore.mvp.presenters;

import android.util.Log;

import com.footballscore.footballscore.mvp.views.LeagueTableView;
import com.footballscore.footballscore.net.ApiManager;

import javax.inject.Inject;

public class LeagueTablePresenter extends MvpBasePresenter<LeagueTableView> {
    private static final String TAG = LeagueTablePresenter.class.getName();

    @Inject
    ApiManager mApiManager;

    @Inject
    public LeagueTablePresenter(
            ApiManager apiManager) {
        mApiManager = apiManager;
    }

    public void getLeagueTable(int competition) {
        mApiManager.getLeagueTable(competition)
                .subscribe(leagueTableResponse -> {
                    if (getView() != null) {
                        getView().hideLoading();
                        getView().setTable(leagueTableResponse.getTeamsStanding());
                    }
                }, throwable -> {
                    Log.e(TAG, "Error while load table: ", throwable);
                    if (getView() != null) {
                        getView().hideLoading();
                        getView().showError(throwable);
                    }
                });
    }

    public void getCompetitionData(int competition) {
        mApiManager.getCompetitionById(competition)
                .subscribe(competitions -> {
                            if (getView() != null) {
                                getView().hideLoading();
                                getView().setCompetitionData(competitions);
                            }
                        }, throwable ->
                                Log.e(TAG, "Error while get competition data: ", throwable)
                );
    }
}
