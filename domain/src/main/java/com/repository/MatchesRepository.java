package com.repository;

import com.models.TeamMatchModel;

import java.util.List;

import rx.Observable;

/**
 * Created by turka on 5/12/2017.
 */

public interface MatchesRepository {
    Observable<List<TeamMatchModel>> getTeamMatches(int teamId, boolean isForceUpdate);
}
