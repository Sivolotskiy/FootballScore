package com.footballscore.footballscore.model;


import com.footballscore.footballscore.net.responce.Response;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Team  extends Response implements Serializable {

    @SerializedName("position")
    private int position;

    @SerializedName("teamName")
    private String teamName;

    @SerializedName("crestURI")
    private String crestURI;

    @SerializedName("playedGames")
    private int playedGames;

    @SerializedName("points")
    private int points;

    @SerializedName("goals")
    private int goals;

    @SerializedName("goalsAgainst")
    private int goalsAgainst;

    @SerializedName("goalDifference")
    private int goalDifference;

    @SerializedName("wins")
    private int wins;

    @SerializedName("draws")
    private int draws;

    @SerializedName("losses")
    private int losses;

    public String getTeamId(){
        String teamResponse = getLinks().getTeam().getHref();
        return teamResponse.substring(teamResponse.lastIndexOf("/") + 1);
    }

    public String getTypeCrestURI(){
        return crestURI.substring(crestURI.lastIndexOf(".") + 1);
    }
}
