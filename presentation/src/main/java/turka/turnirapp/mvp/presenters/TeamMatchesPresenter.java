package turka.turnirapp.mvp.presenters;

import com.models.TeamMatchModel;
import com.usecase.DefaultSubscriber;
import com.usecase.GetTeamMatchesUsecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.mapper.TeamMatchMapper;
import turka.turnirapp.model.TeamMatch;
import turka.turnirapp.mvp.views.TeamMatchesView;
import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 5/13/2017.
 */

public class TeamMatchesPresenter implements Presenter {

    private TeamMatchesView mTeamMatchesView;
    private List<TeamMatch> matches;
    private final GetTeamMatchesUsecase mGetTeamMatchesUsecase;
    private final TeamMatchMapper mTeamMatchMapper;

    @Inject
    public TeamMatchesPresenter(GetTeamMatchesUsecase getTeamMatchesUsecase,TeamMatchMapper teamMatchMapper) {
        mGetTeamMatchesUsecase = getTeamMatchesUsecase;
        mTeamMatchMapper = teamMatchMapper;
    }

    @Override
    public void onStart() {
        fetchTeamMatches();
    }

    @Override
    public void onStop() {
        this.mGetTeamMatchesUsecase.unsubscribe();
        this.mTeamMatchesView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View view) {
        mTeamMatchesView = (TeamMatchesView)view;
    }

    @Override
    public void onCreate() {
        this.matches = new ArrayList<TeamMatch>();
    }

    public void fetchTeamMatches(){
        mTeamMatchesView.showLoadingIndicator();
        mGetTeamMatchesUsecase.setTeamId(mTeamMatchesView.getTeamId());
        mGetTeamMatchesUsecase.setForceUpdate(true);
        mGetTeamMatchesUsecase.execute(new TeamMatchesSubscriber());
    }

    private final class TeamMatchesSubscriber extends DefaultSubscriber<List<TeamMatchModel>> {

        @Override public void onCompleted() {
            mTeamMatchesView.hideLoadingIndicator();
        }

        @Override public void onError(Throwable e) {
            mTeamMatchesView.hideLoadingIndicator();
            mTeamMatchesView.showNoMatchesView();
        }

        @Override public void onNext(List<TeamMatchModel> teamMatchModels) {
            TeamMatchesPresenter.this.matches.clear();
            TeamMatchesPresenter.this.matches.addAll(mTeamMatchMapper.transform(teamMatchModels));
            mTeamMatchesView.updateTeamMatches(TeamMatchesPresenter.this.matches);
            if(matches.isEmpty() == false){
                mTeamMatchesView.hideNoMatchesView();
            }
        }
    }
}
