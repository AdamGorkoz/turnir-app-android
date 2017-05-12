package repository.players;

import com.models.PlayerModel;
import com.repository.PlayersRepository;

import java.util.List;

import javax.inject.Inject;

import repository.players.datasource.NetPlayersDataStore;
import rx.Observable;

/**
 * Created by turka on 5/6/2017.
 */

public class PlayersRepositoryImpl implements PlayersRepository {

    private final NetPlayersDataStore netPlayersDataStore;

    @Inject
    public PlayersRepositoryImpl(NetPlayersDataStore netPlayersDataStore) {
        this.netPlayersDataStore = netPlayersDataStore;
    }

    @Override
    public Observable<List<PlayerModel>> getTeamPlayers(int teamId,boolean isForceUpdate) {
        return netPlayersDataStore.teamPlayersList(teamId);
    }
}
