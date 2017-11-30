package com.footballscore.footballscore.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.footballscore.footballscore.R;
import com.footballscore.footballscore.mvp.presenters.MainActivityPresenter;
import com.footballscore.footballscore.mvp.views.MainActivityView;
import com.footballscore.footballscore.ui.fragments.SeasonsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainActivityView, MainActivityPresenter> {

    @BindView(R.id.activity_main_team_container)
    RelativeLayout mTeamContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            replaceFragment(SeasonsFragment.newInstance());
        }
    }

    @Override
    public void onBackPressed() {
        if (mTeamContainer.getVisibility() == View.VISIBLE) {
            removeTeamFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    public void addFragment(Fragment fragment) {
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_container, fragment)
                .addToBackStack(fragment.getTag())
                .commit();
    }

    public void addTeamFragment(Fragment fragment) {
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content));
        mTeamContainer.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_team_container, fragment)
                .commit();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_container, fragment)
                .commitAllowingStateLoss();
    }

    public void removeTeamFragment() {
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content));
        mTeamContainer.setVisibility(View.GONE);
    }
}
