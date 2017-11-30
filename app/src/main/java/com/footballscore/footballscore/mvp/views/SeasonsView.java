package com.footballscore.footballscore.mvp.views;


import com.footballscore.footballscore.model.Seasons;

import java.util.List;

public interface SeasonsView extends MvpView{
    void setData(List<Seasons> list);
}
