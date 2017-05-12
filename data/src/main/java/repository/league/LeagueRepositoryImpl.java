package repository.league;

import com.models.LeagueTeamModel;
import com.repository.LeagueRepository;

import java.util.List;

import javax.inject.Inject;

import repository.league.datasource.NetLeagueDataStore;
import repository.league.datasource.StorageLeagueDataStore;
import rx.Observable;

/**
 * Created by turka on 11/4/2016.
 */

public class LeagueRepositoryImpl implements LeagueRepository {

    private final NetLeagueDataStore netLeagueDataStore;
    private final StorageLeagueDataStore storageLeagueDataSource;

    @Inject
    public LeagueRepositoryImpl(NetLeagueDataStore netLeagueDataStore, StorageLeagueDataStore storageLeagueDataSource) {
        this.netLeagueDataStore = netLeagueDataStore;
        this.storageLeagueDataSource = storageLeagueDataSource;
    }

    @Override
    public Observable<List<LeagueTeamModel>> getLeague() {
        return netLeagueDataStore.leagueTeamList();
    }
}
