package net;

import com.models.LeagueTeamModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by turka on 11/4/2016.
 */

public interface LeagueApi {
    @GET("league")
    Observable<List<LeagueTeamModel>> getLeague();
}
