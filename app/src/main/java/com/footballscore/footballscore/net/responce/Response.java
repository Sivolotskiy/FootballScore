package com.footballscore.footballscore.net.responce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class Response {

    @SerializedName("_links")
    LinksResponse links;
}