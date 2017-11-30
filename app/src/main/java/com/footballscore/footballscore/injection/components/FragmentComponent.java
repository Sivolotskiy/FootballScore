package com.footballscore.footballscore.injection.components;


import com.footballscore.footballscore.injection.module.FragmentModule;
import com.footballscore.footballscore.ui.fragments.SeasonsFragment;
import com.footballscore.footballscore.ui.fragments.LeagueTableFragment;
import com.footballscore.footballscore.ui.fragments.TeamFragment;

import dagger.Subcomponent;

@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(SeasonsFragment chatListFragment);

    void inject(LeagueTableFragment leagueTableFragment);

    void inject(TeamFragment teamFragment);
}
