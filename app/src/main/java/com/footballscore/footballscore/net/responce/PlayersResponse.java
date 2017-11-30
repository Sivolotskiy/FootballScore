package com.footballscore.footballscore.net.responce;

import com.footballscore.footballscore.model.Player;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class PlayersResponse {
    @SerializedName("players")
    List<Player> players;
    @SerializedName("count")
    int count;
}
