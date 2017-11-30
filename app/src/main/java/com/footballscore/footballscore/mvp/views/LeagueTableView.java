package com.footballscore.footballscore.mvp.views;

import com.footballscore.footballscore.model.Competitions;
import com.footballscore.footballscore.model.Seasons;
import com.footballscore.footballscore.model.Team;

import java.util.List;

public interface LeagueTableView extends MvpView{

    void setTable(List<Team> list);
    void setCompetitionData(Seasons competitions);
}
