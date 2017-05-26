package com.repository;

import com.models.CardModel;
import com.models.GoalModel;
import com.models.TeamMatchModel;

import java.util.List;

import javax.smartcardio.Card;

import rx.Observable;

/**
 * Created by turka on 5/12/2017.
 */

public interface MatchesRepository {
    Observable<List<TeamMatchModel>> getTeamMatches(int teamId, boolean isForceUpdate);
    Observable<List<GoalModel>> getMatchGoals(int matchId);
    Observable<List<CardModel>> getMatchCards(int matchId);
}
