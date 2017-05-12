package com.repository;

import com.models.LeagueTeamModel;

import java.util.List;

import rx.Observable;

/**
 * Created by turka on 11/4/2016.
 */

public interface LeagueRepository {
    Observable<List<LeagueTeamModel>> getLeague();
}
