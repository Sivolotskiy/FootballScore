package com.footballscore.footballscore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.footballscore.footballscore.model.Seasons;
import com.footballscore.footballscore.model.Team;
import com.footballscore.footballscore.mvp.presenters.LeagueTablePresenter;
import com.footballscore.footballscore.mvp.views.LeagueTableView;
import com.footballscore.footballscore.ui.activity.MainActivity;
import com.footballscore.footballscore.ui.adapters.SeasonTableAdapter;
import com.footballscore.footballscore.ui.adapters.decorators.ItemListDividerDecorator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class LeagueTableFragment extends BaseFragment<LeagueTableView, LeagueTablePresenter>
        implements LeagueTableView {

    public static final String BUNDLE_TEAM_ID = "bundle_team_id";

    @BindView(R.id.fragment_league_table_parent_layout)
    RelativeLayout mParentLayout;
    @BindView(R.id.fragment_league_table_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_league_table_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fragment_league_table_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.error_view)
    TextView mErrorTextView;
    @BindView(R.id.view_progress_bar)
    ProgressBar mProgressBarView;

    @Inject
    LeagueTablePresenter mTablePresenter;

    private SeasonTableAdapter mAdapter;
    private List<Team> mTeamItems;
    private int mCompetitionId;

    public static LeagueTableFragment newInstance(int teamId) {
        LeagueTableFragment fragment = new LeagueTableFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_TEAM_ID, teamId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTablePresenter.attachView(this);
        initRecycler();
        if (getArguments() != null) {
            mCompetitionId = getArguments().getInt(BUNDLE_TEAM_ID);
            showLoading();
            loadData();
        }
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(view1 ->
                getActivity().getSupportFragmentManager().popBackStack());

        mSwipeRefreshLayout.setOnRefreshListener(this::loadData);
    }

    private void initRecycler() {
        mAdapter = new SeasonTableAdapter(getContext());
        mAdapter.setItems(mTeamItems);
        mAdapter.setListener((view, position) -> ((MainActivity) getActivity())
                .addTeamFragment(TeamFragment.newInstance(mTeamItems.get(position).getTeamId(),
                        mTeamItems.get(position).getTeamName())));

        mRecyclerView.addItemDecoration(new ItemListDividerDecorator(getContext(), R.drawable.divider));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        mTablePresenter.getLeagueTable(mCompetitionId);
        mTablePresenter.getCompetitionData(mCompetitionId);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_leagua_table;
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
        mSwipeRefreshLayout.setRefreshing(false);
        TransitionManager.beginDelayedTransition(mParentLayout);
        mErrorTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setText(R.string.something_error);
    }

    @Override
    public void setTable(List<Team> list) {
        mErrorTextView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        mTeamItems = list;
        mAdapter.setItems(mTeamItems);
    }

    @Override
    public void setCompetitionData(Seasons competitions) {
        mErrorTextView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        mToolbar.setTitle(competitions.getCaption());
    }
}
