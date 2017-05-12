package turka.turnirapp.mvp.presenters;

import com.models.PlayerModel;
import com.usecase.DefaultSubscriber;
import com.usecase.GetTeamPlayersUsecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.mapper.TeamPlayerMapper;
import turka.turnirapp.model.Player;
import turka.turnirapp.mvp.views.TeamPlayersView;
import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 5/6/2017.
 */

public class TeamPlayersPresenter implements Presenter {

    private TeamPlayersView mTeamPlayersView;
    private List<Player> players;
    private final GetTeamPlayersUsecase mGetTeamPlayersUsecase;
    private final TeamPlayerMapper mTeamPlayerMapper;

    @Inject
    public TeamPlayersPresenter(GetTeamPlayersUsecase getTeamPlayersUsecase,TeamPlayerMapper teamPlayerMapper) {
        mGetTeamPlayersUsecase = getTeamPlayersUsecase;
        mTeamPlayerMapper = teamPlayerMapper;
    }

    @Override
    public void onStart() {
        fetchTeamPlayers();
    }

    @Override
    public void onStop() {
        this.mGetTeamPlayersUsecase.unsubscribe();
        this.mTeamPlayersView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View view) {
        mTeamPlayersView = (TeamPlayersView)view;
    }

    @Override
    public void onCreate() {
        this.players = new ArrayList<Player>();
    }

    public void fetchTeamPlayers(){
        mTeamPlayersView.showLoadingIndicator();
        mGetTeamPlayersUsecase.setTeamId(mTeamPlayersView.getTeamId());
        mGetTeamPlayersUsecase.setForceUpdate(true);
        mGetTeamPlayersUsecase.execute(new LeagueSubscriber());
    }

    private final class LeagueSubscriber extends DefaultSubscriber<List<PlayerModel>> {

        @Override public void onCompleted() {
            mTeamPlayersView.hideLoadingIndicator();
        }

        @Override public void onError(Throwable e) {
            mTeamPlayersView.hideLoadingIndicator();
            mTeamPlayersView.showNoPlayersView();
        }

        @Override public void onNext(List<PlayerModel> teamPlayerModels) {
            TeamPlayersPresenter.this.players.clear();
            TeamPlayersPresenter.this.players.addAll(mTeamPlayerMapper.transform(teamPlayerModels));
            mTeamPlayersView.updateTeamPlayers(TeamPlayersPresenter.this.players);
            if(players.isEmpty() == false){
                mTeamPlayersView.hideNoPlayersView();
            }
        }
    }
}
