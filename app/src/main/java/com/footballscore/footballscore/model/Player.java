package com.footballscore.footballscore.model;


import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Player {
    @SerializedName("name")
    String name;

    @SerializedName("position")
    String position;

    @SerializedName("jerseyNumber")
    int jerseyNumber;

    @SerializedName("nationality")
    String nationality;
}
