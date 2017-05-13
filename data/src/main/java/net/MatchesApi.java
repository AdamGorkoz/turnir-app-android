package net;

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
}
