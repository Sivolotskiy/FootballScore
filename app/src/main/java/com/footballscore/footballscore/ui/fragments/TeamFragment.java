package com.footballscore.footballscore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballscore.footballscore.R;
import com.footballscore.footballscore.model.Player;
import com.footballscore.footballscore.model.Team;
import com.footballscore.footballscore.mvp.presenters.TeamPresenter;
import com.footballscore.footballscore.mvp.views.TeamView;
import com.footballscore.footballscore.ui.activity.MainActivity;
import com.footballscore.footballscore.ui.adapters.PlayerAdapter;
import com.footballscore.footballscore.ui.adapters.decorators.ItemListDividerDecorator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TeamFragment extends BaseFragment<TeamView, TeamPresenter>
        implements TeamView {

    public static final String BUNDLE_TEAM_ID = "bundle_team_id";
    public static final String BUNDLE_TEAM_NAME = "bundle_team_name";

    @BindView(R.id.fragment_team_parent)
    RelativeLayout mParentLayout;
    @BindView(R.id.fragment_team_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_team_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.error_view)
    TextView mErrorTextView;
    @BindView(R.id.view_progress_bar)
    ProgressBar mProgressBarView;

    @Inject
    TeamPresenter mTeamPresenter;

    private PlayerAdapter mAdapter;
    private List<Player> mTeamItems;
    private String mTeamId;
    private String mName;

    public static TeamFragment newInstance(String teamId, String name) {
        TeamFragment fragment = new TeamFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TEAM_ID, teamId);
        bundle.putString(BUNDLE_TEAM_NAME, name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTeamPresenter.attachView(this);
        initRecycler();
        if (getArguments() != null) {
            mTeamId = getArguments().getString(BUNDLE_TEAM_ID);
            mName = getArguments().getString(BUNDLE_TEAM_NAME);
            loadData();
        }
        mToolbar.setTitle(mName);
        mToolbar.setNavigationIcon(R.drawable.ic_close);
        mToolbar.setNavigationOnClickListener(view1 -> ((MainActivity) getActivity()).removeTeamFragment());
    }

    private void initRecycler() {
        mAdapter = new PlayerAdapter();
        mAdapter.setItems(mTeamItems);

        mRecyclerView.addItemDecoration(new ItemListDividerDecorator(getContext(), R.drawable.divider));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        showLoading();
        mTeamPresenter.getPlayers(mTeamId);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_team;
    }

    @Override
    void injectDependencies() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void showLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBarView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        TransitionManager.beginDelayedTransition(mParentLayout);
        mErrorTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setText(R.string.something_error);
    }

    @Override
    public void setTeam(Team list) {
        //TODO: impl team statistic if it need
    }

    @Override
    public void setPlayers(List<Player> list) {
        mErrorTextView.setVisibility(View.GONE);
        mTeamItems = list;
        mAdapter.setItems(mTeamItems);
    }
}
