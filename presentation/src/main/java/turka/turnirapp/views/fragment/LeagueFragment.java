package turka.turnirapp.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.models.LeagueTeamModel;

import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.AndroidApplication;
import turka.turnirapp.R;
import turka.turnirapp.di.di.components.DaggerLeagueComponent;
import turka.turnirapp.di.di.components.DaggerMessagesComponent;
import turka.turnirapp.model.LeagueTeam;
import turka.turnirapp.mvp.presenters.LeaguePresenter;
import turka.turnirapp.mvp.views.LeagueView;
import turka.turnirapp.views.adapter.LeagueAdapter;
import turka.turnirapp.views.adapter.MessagesAdapter;

public class LeagueFragment extends Fragment implements LeagueView {

    private Context mContext;
    private LeagueAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView noLeagueTextView;
    private RecyclerView leagueList;

    @Inject
    LeaguePresenter presenter;

    public LeagueFragment() {
        // Required empty public constructor
    }

    public static LeagueFragment newInstance() {
        LeagueFragment fragment = new LeagueFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         AndroidApplication app = (AndroidApplication) getActivity().getApplication();
        DaggerLeagueComponent.builder()
                .applicationComponent(app.getApplicationComponent())
                .build().inject(this);

        View fragmentView =  inflater.inflate(R.layout.fragment_league, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshLeague();
            }
        });



        noLeagueTextView = (TextView) fragmentView.findViewById(R.id.no_league);
        leagueList = (RecyclerView) fragmentView.findViewById(R.id.league_list);

        setupLeagueView();
        initPresenter();

        return fragmentView;
    }

    private void initPresenter() {
        presenter.attachView(this);
        presenter.onCreate();
    }

    private void setupLeagueView() {

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(mContext);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        leagueList.setLayoutManager(mLinearLayoutManagerVertical);

        leagueList.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void showLoadingIndicator() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingIndicator() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoLeagueView() {
        leagueList.setVisibility(View.GONE);
        noLeagueTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoLeagueView() {
        if(leagueList.getVisibility() != View.VISIBLE){
            leagueList.setVisibility(View.VISIBLE);
            noLeagueTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void updateLeague(List<LeagueTeam> league) {
        if(mAdapter == null){
            mAdapter = new LeagueAdapter(mContext, league);
            leagueList.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
