package com.footballscore.footballscore.net;

import android.content.Context;
import android.util.Log;

import com.footballscore.footballscore.model.Competitions;
import com.footballscore.footballscore.model.Seasons;
import com.footballscore.footballscore.net.responce.LeagueTableResponse;
import com.footballscore.footballscore.net.responce.PlayersResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ApiManager {

    private static final String TAG = ApiManager.class.getSimpleName();
    private Context mContext;
    private ApiService mApiService;

    @Inject
    public ApiManager(Context context, ApiService apiService) {
        mContext = context;
        mApiService = apiService;
    }

    private <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<List<Seasons>> getSoccerSeasons() {
        return mApiService.getSoccerSeasons()
                .compose(applySchedulers());
    }

    public Observable<LeagueTableResponse> getLeagueTable(int competitions) {
        return mApiService.getLeagueTable(competitions)
                .compose(applySchedulers());
    }

    public Observable<Seasons> getCompetitionById(int competitions) {
        return mApiService.getCompetition(competitions)
                .compose(applySchedulers());
    }

    public Observable<PlayersResponse> getPlayers(String teem) {
        return mApiService.getPlayers(teem)
                .compose(applySchedulers());
    }
}
