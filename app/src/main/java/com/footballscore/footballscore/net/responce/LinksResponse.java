package com.footballscore.footballscore.net.responce;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LinksResponse {
    @SerializedName("self")
    HrefResponse self;
    @SerializedName("competition")
    HrefResponse competition;
    @SerializedName("team")
    HrefResponse team;

    @Data
    public class HrefResponse {
        @SerializedName("href")
        String href;
    }
}
