package turka.turnirapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import turka.turnirapp.di.di.components.DaggerMatchesComponent;
import turka.turnirapp.model.LeagueTeam;
import turka.turnirapp.model.MatchEvent;
import turka.turnirapp.model.TeamMatch;
import turka.turnirapp.mvp.presenters.MatchPresenter;
import turka.turnirapp.mvp.views.MatchView;
import turka.turnirapp.views.BaseActivity;
import turka.turnirapp.views.adapter.MatchEventsAdapter;
import turka.turnirapp.views.adapter.TeamMatchesAdapter;

public class TeamMatchActivity extends BaseActivity implements MatchView, AppBarLayout.OnOffsetChangedListener {

    private TextView mTimer;
    private TextView mScore;
    private TextView mTeamAName;
    private TextView mTeamBName;
    private AppBarLayout mAppBarLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView eventsList;
    private MatchEventsAdapter mAdapter;

    private LeagueTeam mLeagueTeam;
    private TeamMatch mTeamMatch;
    private Context mContext;

    @Inject
    MatchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_match);

        AndroidApplication app = (AndroidApplication) this.getApplication();
        DaggerMatchesComponent.builder()
                .applicationComponent(app.getApplicationComponent())
                .build().inject(this);

        mContext = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAppBarLayout   = (AppBarLayout) findViewById(R.id.app_bar);
        mTimer = (TextView) findViewById(R.id.timer);
        mScore = (TextView) findViewById(R.id.score);
        mTeamAName = (TextView) findViewById(R.id.teamAName);
        mTeamBName = (TextView) findViewById(R.id.teamBName);

        mAppBarLayout.addOnOffsetChangedListener(this);

        Intent intent = getIntent();
        mLeagueTeam = intent.getParcelableExtra("team");
        mTeamMatch = intent.getParcelableExtra("match");

        mScore.setText(mTeamMatch.getScore());
        String locale = Locale.getDefault().getLanguage();
        if(locale == "he" || locale == "iw"){
            String [] scoreArray = mTeamMatch.getScore().split("-");
            mScore.setText(scoreArray[1] + "-" + scoreArray[0]);
        }

        mTeamAName.setText(mLeagueTeam.getName());
        mTeamBName.setText(mTeamMatch.getOpponent());
        mTimer.setText(getString(R.string.full_time));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        eventsList = (RecyclerView) findViewById(R.id.match_events_list);

        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchMatchEvents();
            }
        });

        setupEventsView();
        initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart();
    }

    private void setupEventsView(){
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(mContext);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        eventsList.setLayoutManager(mLinearLayoutManagerVertical);

        eventsList.setItemAnimator(new DefaultItemAnimator());
    }

    private void initPresenter() {
        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        final float percentage = (float) Math.abs(offset) / (float) maxScroll;

        float timerSizeToSubtract = 5f;
        mTimer.setTextSize(20f - (timerSizeToSubtract *percentage));
        float scoreSizeToSubtract = 10f;
        mScore.setTextSize(25f - (scoreSizeToSubtract * percentage));
        float teamSizeToSubtract = 5f;
        mTeamAName.setTextSize(30f - (teamSizeToSubtract *percentage));
        mTeamBName.setTextSize(30f - (teamSizeToSubtract *percentage));

        final float marginTopToAdd = (maxScroll / 2) - (mTeamAName.getHeight() / 4);

        mScore.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)mScore.getLayoutParams();
                if(percentage == 1f && marginTopToAdd  != params.topMargin){
                    params.setMargins(0, (int)marginTopToAdd, 0, 0);
                    mScore.setLayoutParams(params);
                    mTeamAName.setLayoutParams(params);
                    mTeamBName.setLayoutParams(params);
                }
                else if(percentage == 0.0f &&  params.topMargin != 0){
                    params.setMargins(0, 0, 0, 0);
                    mScore.setLayoutParams(params);
                    mTeamAName.setLayoutParams(params);
                    mTeamBName.setLayoutParams(params);
                }
                else if(Math.ceil(marginTopToAdd * percentage) != params.topMargin && Math.floor(marginTopToAdd * percentage) != params.topMargin ) {
                    params.setMargins(0, (int)(marginTopToAdd * percentage), 0, 0);
                    mScore.setLayoutParams(params);
                    mTeamAName.setLayoutParams(params);
                    mTeamBName.setLayoutParams(params);
                }
            }
        });
    }

    @Override
    public void updateMatchEvents(List<MatchEvent> events) {
        if(mAdapter == null){
            mAdapter = new MatchEventsAdapter(mContext, events);
            eventsList.setAdapter(mAdapter);
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
    public int getMatchId() {
        return mTeamMatch.getID();
    }

    @Override
    public int getFirstTeamId(){
        return mLeagueTeam.getID();
    }
}
