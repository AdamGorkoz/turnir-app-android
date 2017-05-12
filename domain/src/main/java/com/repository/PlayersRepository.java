package com.repository;

import com.models.PlayerModel;

import java.util.List;

import rx.Observable;

/**
 * Created by turka on 5/6/2017.
 */

public interface PlayersRepository {
    Observable<List<PlayerModel>> getTeamPlayers(int teamId,boolean isForceUpdate);
}
