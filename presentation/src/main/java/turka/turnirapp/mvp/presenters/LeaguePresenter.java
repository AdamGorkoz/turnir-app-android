package turka.turnirapp.mvp.presenters;

import com.models.LeagueTeamModel;
import com.usecase.DefaultSubscriber;
import com.usecase.GetLeagueUsecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.mapper.LeagueTeamMapper;
import turka.turnirapp.model.LeagueTeam;
import turka.turnirapp.mvp.views.LeagueView;
import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 11/5/2016.
 */

public class LeaguePresenter  implements Presenter {
    private LeagueView mLeagueView;
    private List<LeagueTeam> league;
    private final GetLeagueUsecase mGetLeagueUsecase;
    private final LeagueTeamMapper mLeagueTeamMapper;

    @Inject
    public LeaguePresenter(GetLeagueUsecase getLeagueUsecase, LeagueTeamMapper leagueTeamMapper) {
        mGetLeagueUsecase = getLeagueUsecase;
        mLeagueTeamMapper = leagueTeamMapper;
    }

    @Override
    public void onStart() {
        fetchLeague();
    }

    @Override
    public void onStop() {
        this.mGetLeagueUsecase.unsubscribe();
        this.mLeagueView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View view) {
        this.mLeagueView = (LeagueView)view;
    }

    @Override
    public void onCreate() {
        this.league = new ArrayList<LeagueTeam>();
    }

    public void fetchLeague() {
        mLeagueView.showLoadingIndicator();
        mGetLeagueUsecase.execute(new LeagueSubscriber());
    }

    public void refreshLeague(){
        mLeagueView.showLoadingIndicator();
        mGetLeagueUsecase.execute(new LeagueSubscriber());
    }

    private final class LeagueSubscriber extends DefaultSubscriber<List<LeagueTeamModel>> {

        @Override public void onCompleted() {
            mLeagueView.hideLoadingIndicator();
        }

        @Override public void onError(Throwable e) {
            mLeagueView.hideLoadingIndicator();
            mLeagueView.showNoLeagueView();
        }

        @Override public void onNext(List<LeagueTeamModel> leagueTeamModels) {
            LeaguePresenter.this.league.clear();
            LeaguePresenter.this.league.addAll(mLeagueTeamMapper.transform(leagueTeamModels));
            mLeagueView.updateLeague(LeaguePresenter.this.league);
            if(league.isEmpty() == false){
                mLeagueView.hideNoLeagueView();
            }
        }
    }
}
