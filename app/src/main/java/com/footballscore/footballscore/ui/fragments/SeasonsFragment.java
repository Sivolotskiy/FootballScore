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
import com.footballscore.footballscore.mvp.presenters.SeasonsPresenter;
import com.footballscore.footballscore.mvp.views.SeasonsView;
import com.footballscore.footballscore.ui.activity.MainActivity;
import com.footballscore.footballscore.ui.adapters.CompetitionsAdapter;
import com.footballscore.footballscore.ui.adapters.decorators.ItemListDividerDecorator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SeasonsFragment extends BaseFragment<SeasonsView, SeasonsPresenter>
        implements SeasonsView {

    @BindView(R.id.fragment_seasons_parent_layout)
    RelativeLayout mParentLayout;
    @BindView(R.id.fragment_seasons_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_seasons_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fragment_seasons_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.error_view)
    TextView mErrorTextView;
    @BindView(R.id.view_progress_bar)
    ProgressBar mProgressBarView;

    @Inject
    SeasonsPresenter mSeasonsPresenter;

    private CompetitionsAdapter mAdapter;
    private List<Seasons> mCompetitionItems;

    public static SeasonsFragment newInstance() {
        return new SeasonsFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSeasonsPresenter.attachView(this);
        initRecycler();
        showLoading();
        loadData();

        mToolbar.setTitle(R.string.competitions);
        mSwipeRefreshLayout.setOnRefreshListener(this::loadData);
    }

    private void initRecycler() {
        mAdapter = new CompetitionsAdapter();
        mAdapter.setItems(mCompetitionItems);
        mAdapter.setListener((view, position) -> ((MainActivity) getActivity())
                .addFragment(LeagueTableFragment.newInstance(mCompetitionItems.get(position).getId())));
        mRecyclerView.addItemDecoration(new ItemListDividerDecorator(getContext(), R.drawable.divider));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        mSeasonsPresenter.getSeasons();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_seasons;
    }

    @Override
    void injectDependencies() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void setData(List<Seasons> list) {
        mErrorTextView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        if (list != null) {
            mCompetitionItems = list;
            mAdapter.setItems(mCompetitionItems);
        }
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
}
