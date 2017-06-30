package repository.matches.datasource;

import com.models.CardModel;
import com.models.GoalModel;
import com.models.LiveMatchModel;
import com.models.MatchRequestFilter;
import com.models.TeamMatchModel;

import net.MatchesApi;

import java.util.List;

import javax.inject.Inject;
import rx.Observable;

/**
 * Created by turka on 5/12/2017.
 */

public class NetMatchesDataSource implements MatchesDataSource {

    private final MatchesApi matchesApi;

    @Inject
    public NetMatchesDataSource(MatchesApi matchesApi){
        this.matchesApi = matchesApi;
    }

    @Override
    public Observable<List<TeamMatchModel>> teamMatchesList(int teamId) {
        return matchesApi.getTeamMatches(teamId);
    }

    @Override
    public Observable<List<GoalModel>> matchGoals(int matchId) {
        return matchesApi.getMatchGoals(matchId);
    }

    @Override
    public Observable<List<CardModel>> matchCards(int matchId) {
        return matchesApi.getMatchCards(matchId);
    }

    @Override
    public Observable<List<LiveMatchModel>> liveMatchesList() {
        MatchRequestFilter filter = new MatchRequestFilter();
        filter.setShowAll(false);
        return matchesApi.getLiveMatches(filter);
    }
}
