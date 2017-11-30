package com.footballscore.footballscore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Seasons implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("caption")
    private String caption;

    @SerializedName("league")
    private String league;

    @SerializedName("year")
    private String year;

    @SerializedName("currentMatchday")
    private int currentMatchday;

    @SerializedName("numberOfMatchdays")
    private int numberOfMatchdays;

    @SerializedName("numberOfTeams")
    private int numberOfTeams;

    @SerializedName("numberOfGames")
    private int numberOfGames;

    @SerializedName("lastUpdated")
    private String lastUpdated;
}
