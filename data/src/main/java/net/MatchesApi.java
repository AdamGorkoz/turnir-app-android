package net;

import com.models.CardModel;
import com.models.GoalModel;
import com.models.TeamMatchModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by turka on 5/12/2017.
 */

public interface MatchesApi {
    @GET("team/{id}/history")
    Observable<List<TeamMatchModel>> getTeamMatches(@Path("id")int id);

    @GET("match/{id}/goals")
    Observable<List<GoalModel>> getMatchGoals(@Path("id")int id);

    @GET("match/{id}/cards")
    Observable<List<CardModel>> getMatchCards(@Path("id")int id);
}
