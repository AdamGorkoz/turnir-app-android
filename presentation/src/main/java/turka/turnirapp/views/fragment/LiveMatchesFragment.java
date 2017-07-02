package turka.turnirapp.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.models.TeamMatchModel;

import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.AndroidApplication;
import turka.turnirapp.R;
import turka.turnirapp.di.di.components.DaggerMatchesComponent;
import turka.turnirapp.model.LiveMatch;
import turka.turnirapp.mvp.presenters.LiveMatchesPresenter;
import turka.turnirapp.mvp.views.LiveMatchesListView;
import turka.turnirapp.views.adapter.LiveMatchesAdapter;

public class LiveMatchesFragment extends Fragment implements LiveMatchesListView {

    private LiveMatchesAdapter mAdapter;
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView liveMatchesList;
    private TextView noLiveMatchesTextView;

    @Inject
    LiveMatchesPresenter presenter;

    public LiveMatchesFragment() {
    }

    public static LiveMatchesFragment newInstance() {
        LiveMatchesFragment fragment = new LiveMatchesFragment();
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
        DaggerMatchesComponent.builder()
                .applicationComponent(app.getApplicationComponent())
                .build().inject(this);

        View fragmentView = inflater.inflate(R.layout.fragment_livematches_list, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshLiveMatches();
            }
        });

        noLiveMatchesTextView = (TextView) fragmentView.findViewById(R.id.no_live_matches);
        liveMatchesList = (RecyclerView) fragmentView.findViewById(R.id.live_matches_list);


        setupLiveMatchesView();
        initPresenter();

        return fragmentView;
    }

    private void setupLiveMatchesView() {
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(mContext);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        liveMatchesList.setLayoutManager(mLinearLayoutManagerVertical);

        liveMatchesList.setItemAnimator(new DefaultItemAnimator());
    }

    private void initPresenter() {
        presenter.attachView(this);
        presenter.onCreate();
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
    public void updateLiveMatchesList(List<Object> matches) {
        if(mAdapter == null){
            mAdapter = new LiveMatchesAdapter(mContext, matches);
            liveMatchesList.setAdapter(mAdapter);
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
        liveMatchesList.setVisibility(View.GONE);
        noLiveMatchesTextView.setVisibility(View.VISIBLE );
    }

    @Override
    public void hideNoMatchesView() {
        if(liveMatchesList.getVisibility() != View.VISIBLE){
            liveMatchesList.setVisibility(View.VISIBLE);
            noLiveMatchesTextView.setVisibility(View.INVISIBLE);
        }
    }
}
