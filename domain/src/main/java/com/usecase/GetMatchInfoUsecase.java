package com.usecase;

import com.models.CardModel;
import com.models.GoalModel;
import com.models.MatchEventModel;
import com.repository.MatchesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by turka on 5/25/2017.
 */

public class GetMatchInfoUsecase extends Usecase {

    private final MatchesRepository mRepository;

    private int matchId;

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }


    @Inject
    public GetMatchInfoUsecase(MatchesRepository repository, @Named("ui_thread") Scheduler uiThread,
                               @Named("executor_thread") Scheduler executorThread) {
        super(uiThread,executorThread);
        mRepository = repository;
    }

    @Override
    public Observable buildObservable() {
        Observable<List<GoalModel>> goalsObservable = mRepository.getMatchGoals(matchId);
        Observable<List<CardModel>> cardsObservable = mRepository.getMatchCards(matchId);

        return Observable.zip(goalsObservable, cardsObservable, new Func2<List<GoalModel>, List<CardModel>, List<MatchEventModel>>() {
            @Override
            public List<MatchEventModel> call(List<GoalModel> goalModels, List<CardModel> cardModels) {
                List<MatchEventModel> matchEventModels = new ArrayList<MatchEventModel>();
                for (GoalModel goal : goalModels) {
                    matchEventModels.add(new MatchEventModel(goal.getPlayerName(),goal.getMinute(),goal.getTeamID(), 1 , 0));
                }

                for (CardModel card : cardModels) {
                    matchEventModels.add(new MatchEventModel(card.getPlayerName(),card.getMinute(),card.getTeamID(), 2 , card.getCardTypeID()));
                }
                Collections.sort(matchEventModels, new Comparator<MatchEventModel>() {
                    @Override
                    public int compare(MatchEventModel event1, MatchEventModel event2) {
                        return event1.getMinute().compareTo(event2.getMinute());
                    }
                });
                return matchEventModels;
            }
        });
    }
}
