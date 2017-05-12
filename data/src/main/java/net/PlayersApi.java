package net;

import com.models.PlayerModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by turka on 5/6/2017.
 */

public interface PlayersApi {
    @GET("team/{id}/profile/players")
    Observable<List<PlayerModel>> getTeamPlayers(@Path("id")int id);
}
