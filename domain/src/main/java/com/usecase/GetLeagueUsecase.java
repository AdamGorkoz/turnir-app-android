package com.usecase;

import com.models.LeagueTeamModel;
import com.repository.LeagueRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by turka on 11/5/2016.
 */

public class GetLeagueUsecase extends Usecase {

    private final LeagueRepository mRepository;

    @Inject
    public GetLeagueUsecase(LeagueRepository  repository, @Named("ui_thread") Scheduler uiThread,
                              @Named("executor_thread") Scheduler executorThread) {
        super(uiThread,executorThread);
        mRepository = repository;
    }

    @Override
    public Observable buildObservable() {
        return mRepository.getLeague().map(new Func1<List<LeagueTeamModel>,List<LeagueTeamModel>>() {
            @Override
            public List<LeagueTeamModel> call(List<LeagueTeamModel> league) {
                Collections.sort(league, new Comparator<LeagueTeamModel>() {
                    @Override
                    public int compare(LeagueTeamModel teamA, LeagueTeamModel teamB) {
                        int result = teamB.getPoints() - teamA.getPoints();
                        if(result == 0){
                            result = teamB.getDiff() - teamA.getDiff();
                        }
                        return result;
                    }
                });

                return league;
            }
        });
    }
}
