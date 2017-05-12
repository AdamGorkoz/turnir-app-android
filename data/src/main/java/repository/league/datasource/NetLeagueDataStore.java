package repository.league.datasource;

import com.models.LeagueTeamModel;

import net.LeagueApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by turka on 11/4/2016.
 */

public class NetLeagueDataStore implements LeagueDataStore {

    private final LeagueApi leagueApi;

    @Inject
    public NetLeagueDataStore(LeagueApi leagueApi){
        this.leagueApi = leagueApi;
    }
    @Override
    public Observable<List<LeagueTeamModel>> leagueTeamList() {
        return leagueApi.getLeague();
    }
}
