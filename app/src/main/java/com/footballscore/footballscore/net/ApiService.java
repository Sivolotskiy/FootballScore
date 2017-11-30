package com.footballscore.footballscore.net;


import com.footballscore.footballscore.model.Seasons;
import com.footballscore.footballscore.net.responce.LeagueTableResponse;
import com.footballscore.footballscore.net.responce.PlayersResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {

    @GET("soccerseasons/")
    Observable<List<Seasons>> getSoccerSeasons();

    @GET("competitions/{teem}/leagueTable/")
    Observable<LeagueTableResponse> getLeagueTable(@Path("teem") int teem);

    @GET("competitions/{id}/")
    Observable<Seasons> getCompetition(@Path("id") int id);

    @GET("teams/{teem}/players/")
    Observable<PlayersResponse> getPlayers(@Path("teem") String teem);
}