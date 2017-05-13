package turka.turnirapp.views.fragment;

import android.content.Context;
import android.net.Uri;
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

import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.AndroidApplication;
import turka.turnirapp.R;
import turka.turnirapp.di.di.components.DaggerTeamMatchesComponent;
import turka.turnirapp.model.LeagueTeam;
import turka.turnirapp.model.TeamMatch;
import turka.turnirapp.mvp.presenters.TeamMatchesPresenter;
import turka.turnirapp.mvp.views.TeamMatchesView;
import turka.turnirapp.views.adapter.TeamMatchesAdapter;

public class TeamMatchesFragment extends Fragment implements TeamMatchesView {

    private static final String TEAM_PARAM = "TEAM_PARAM";

    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TeamMatchesAdapter mAdapter;
    private RecyclerView matchesList;
    private TextView noTeamMatchesTextView;
    private LeagueTeam mLeagueTeam;

    @Inject
    TeamMatchesPresenter presenter;

    public TeamMatchesFragment() {
        // Required empty public constructor
    }

    public static TeamMatchesFragment newInstance(LeagueTeam leagueTeam) {
        TeamMatchesFragment fragment = new TeamMatchesFragment();
        Bundle args = new Bundle();
        args.putParcelable(TEAM_PARAM, leagueTeam);
        fragment.setArguments(args);
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
        if (getArguments() != null) {
            mLeagueTeam = getArguments().getParcelable(TEAM_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AndroidApplication app = (AndroidApplication) getActivity().getApplication();
        DaggerTeamMatchesComponent.builder()
                .applicationComponent(app.getApplicationComponent())
                .build().inject(this);

        View fragmentView =  inflater.inflate(R.layout.fragment_team_matches, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchTeamMatches();
            }
        });



        noTeamMatchesTextView = (TextView) fragmentView.findViewById(R.id.no_team_matches);
        matchesList = (RecyclerView) fragmentView.findViewById(R.id.team_matches_list);

        setupTeamMatchesView();
        initPresenter();

        return fragmentView;
    }

    private void initPresenter() {
        presenter.attachView(this);
        presenter.onCreate();
    }

    private void setupTeamMatchesView() {

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(mContext);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        matchesList.setLayoutManager(mLinearLayoutManagerVertical);

        matchesList.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void updateTeamMatches(List<TeamMatch> matches) {
        if(mAdapter == null){
            mAdapter = new TeamMatchesAdapter(mContext, matches);
            matchesList.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
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
    public void showNoMatchesView() {
        matchesList.setVisibility(View.GONE);
        noTeamMatchesTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoMatchesView() {
        if(matchesList.getVisibility() != View.VISIBLE){
            matchesList.setVisibility(View.VISIBLE);
            noTeamMatchesTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getTeamId() {
        return mLeagueTeam.getID();
    }
}
