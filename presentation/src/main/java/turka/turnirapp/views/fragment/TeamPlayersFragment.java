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

import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.AndroidApplication;
import turka.turnirapp.R;
import turka.turnirapp.di.di.components.DaggerPlayersComponent;
import turka.turnirapp.model.LeagueTeam;
import turka.turnirapp.model.Player;
import turka.turnirapp.mvp.presenters.TeamPlayersPresenter;
import turka.turnirapp.mvp.views.TeamPlayersView;
import turka.turnirapp.views.adapter.TeamPlayersAdapter;

public class TeamPlayersFragment extends Fragment implements TeamPlayersView {

    private static final String TEAM_PARAM = "TEAM_PARAM";

    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TeamPlayersAdapter mAdapter;
    private RecyclerView playersList;
    private TextView noTeamPlayersTextView;
    private LeagueTeam mLeagueTeam;

    @Inject
    TeamPlayersPresenter presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TeamPlayersFragment() {
    }

    @SuppressWarnings("unused")
    public static TeamPlayersFragment newInstance(LeagueTeam leagueTeam) {
        TeamPlayersFragment fragment = new TeamPlayersFragment();
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
        DaggerPlayersComponent.builder()
                .applicationComponent(app.getApplicationComponent())
                .build().inject(this);

        View fragmentView =  inflater.inflate(R.layout.fragment_player_list, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchTeamPlayers();
            }
        });



        noTeamPlayersTextView = (TextView) fragmentView.findViewById(R.id.no_team_players);
        playersList = (RecyclerView) fragmentView.findViewById(R.id.team_players_list);

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
        playersList.setLayoutManager(mLinearLayoutManagerVertical);

        playersList.setItemAnimator(new DefaultItemAnimator());
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
    public void updateTeamPlayers(List<Player> players) {
        if(mAdapter == null){
            mAdapter = new TeamPlayersAdapter(mContext, players);
            playersList.setAdapter(mAdapter);
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
    public void showNoPlayersView() {
        playersList.setVisibility(View.GONE);
        noTeamPlayersTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoPlayersView() {
        if(playersList.getVisibility() != View.VISIBLE){
            playersList.setVisibility(View.VISIBLE);
            noTeamPlayersTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getTeamId() {
        return mLeagueTeam.getID();
    }

}
