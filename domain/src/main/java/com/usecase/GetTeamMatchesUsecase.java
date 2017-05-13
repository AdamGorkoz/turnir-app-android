package com.usecase;

import com.models.TeamMatchModel;
import com.repository.MatchesRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by turka on 5/12/2017.
 */

public class GetTeamMatchesUsecase extends Usecase {

    private final MatchesRepository mRepository;

    private boolean isForceUpdate;

    private int teamId;

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setForceUpdate(boolean forceUpdate) {
        isForceUpdate = forceUpdate;
    }

    @Inject
    public GetTeamMatchesUsecase(MatchesRepository repository, @Named("ui_thread") Scheduler uiThread,
                                 @Named("executor_thread") Scheduler executorThread) {
        super(uiThread,executorThread);
        mRepository = repository;
    }

    @Override
    public Observable buildObservable() {
        return mRepository.getTeamMatches(this.teamId,this.isForceUpdate).map(new Func1<List<TeamMatchModel>,List<TeamMatchModel>>() {
            @Override
            public List<TeamMatchModel> call(List<TeamMatchModel> matchesList) {
                Collections.sort(matchesList, new Comparator<TeamMatchModel>() {
                    @Override
                    public int compare(TeamMatchModel matchA, TeamMatchModel matchB) {
                        return matchA.getMatchDate().compareTo(matchB.getMatchDate());
                    }
                });

                return matchesList;
            }
        });
    }
}
