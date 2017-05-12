package com.usecase;

import com.models.PlayerModel;
import com.repository.PlayersRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by turka on 5/6/2017.
 */

public class GetTeamPlayersUsecase extends Usecase  {

    private final PlayersRepository mRepository;

    private boolean isForceUpdate;

    private int teamId;

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setForceUpdate(boolean forceUpdate) {
        isForceUpdate = forceUpdate;
    }

    @Inject
    public GetTeamPlayersUsecase(PlayersRepository repository, @Named("ui_thread") Scheduler uiThread,
                                 @Named("executor_thread") Scheduler executorThread) {
        super(uiThread,executorThread);
        mRepository = repository;
    }

    @Override
    public Observable buildObservable() {
        return mRepository.getTeamPlayers(this.teamId,this.isForceUpdate).map(new Func1<List<PlayerModel>,List<PlayerModel>>() {
            @Override
            public List<PlayerModel> call(List<PlayerModel> playersList) {
                Collections.sort(playersList, new Comparator<PlayerModel>() {
                    @Override
                    public int compare(PlayerModel playerA, PlayerModel playerB) {
                        return playerB.getGoals() - playerA.getGoals();
                    }
                });

                return playersList;
            }
        });
    }
}
