package repository.matches;

import com.models.CardModel;
import com.models.GoalModel;
import com.models.LiveMatchModel;
import com.models.TeamMatchModel;
import com.repository.MatchesRepository;

import java.util.List;

import javax.inject.Inject;

import repository.matches.datasource.NetMatchesDataSource;
import rx.Observable;

/**
 * Created by turka on 5/12/2017.
 */

public class MatchesRepositoryImpl implements MatchesRepository {

    private final NetMatchesDataSource netMatchesDataSource;

    @Inject
    public MatchesRepositoryImpl(NetMatchesDataSource netMatchesDataSource) {
        this.netMatchesDataSource = netMatchesDataSource;
    }

    @Override
    public Observable<List<TeamMatchModel>> getTeamMatches(int teamId, boolean isForceUpdate) {
        return netMatchesDataSource.teamMatchesList(teamId);
    }

    @Override
    public Observable<List<GoalModel>> getMatchGoals(int matchId) {
        return netMatchesDataSource.matchGoals(matchId);
    }

    @Override
    public Observable<List<CardModel>> getMatchCards(int matchId) {
        return netMatchesDataSource.matchCards(matchId);
    }

    @Override
    public Observable<List<LiveMatchModel>> getLiveMatches() {
        return netMatchesDataSource.liveMatchesList();
    }
}
