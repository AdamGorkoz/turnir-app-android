package com.usecase;

import com.models.LiveMatchModel;
import com.repository.MatchesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

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
    public Observable<List<Object>> buildObservable() {
        return mRepository.getLiveMatches().map(new Func1<List<LiveMatchModel>,List<Object>>() {
            @Override
            public List<Object> call(List<LiveMatchModel> liveMatchModels) {

                Set<String> timeList = new TreeSet<String>();
                for (LiveMatchModel match : liveMatchModels) {
                    if(!timeList.contains(match.getTime())){
                        timeList.add(match.getTime());
                    }
                }

                List<Object> output = new ArrayList<Object>();

                for(String time : timeList){
                    output.add(time);
                    for(LiveMatchModel match : liveMatchModels){
                        if(match.getTime().equals(time)){
                            output.add(match);
                        }
                    }
                }

                return output;
            }
        });
    }
}
