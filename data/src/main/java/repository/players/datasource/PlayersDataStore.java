package repository.players.datasource;

import com.models.PlayerModel;

import java.util.List;

import rx.Observable;

/**
 * Created by turka on 5/6/2017.
 */

public interface PlayersDataStore {
    Observable<List<PlayerModel>> teamPlayersList(int teamId);
}
