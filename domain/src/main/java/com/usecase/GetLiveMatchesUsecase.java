package com.usecase;

import com.repository.MatchesRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by turka on 6/30/2017.
 */

public class GetLiveMatchesUsecase extends Usecase {

    private final MatchesRepository mRepository;

    @Inject
    public GetLiveMatchesUsecase(MatchesRepository  repository, @Named("ui_thread") Scheduler uiThread,
                            @Named("executor_thread") Scheduler executorThread) {
        super(uiThread,executorThread);
        mRepository = repository;
    }

    @Override
    public Observable buildObservable() {
        return mRepository.getLiveMatches();
    }
}
