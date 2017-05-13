package repository.matches.datasource;

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
}
