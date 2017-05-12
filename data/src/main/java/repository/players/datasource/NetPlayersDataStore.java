package repository.players.datasource;

import com.models.PlayerModel;

import net.PlayersApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by turka on 5/6/2017.
 */

public class NetPlayersDataStore implements PlayersDataStore {

    private final PlayersApi playersApi;

    @Inject
    public NetPlayersDataStore(PlayersApi playersApi){
        this.playersApi = playersApi;
    }
    @Override
    public Observable<List<PlayerModel>> teamPlayersList(int teamId) {
        return playersApi.getTeamPlayers(teamId);
    }
}
