package com.footballscore.footballscore.mvp.views;

import com.footballscore.footballscore.model.Player;
import com.footballscore.footballscore.model.Team;

import java.util.List;


public interface TeamView extends MvpView {
    void setTeam(Team list);
    void setPlayers(List<Player> list);
}
