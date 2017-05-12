package repository.league.datasource;

import android.content.Context;

import com.models.LeagueTeamModel;
import com.models.MessageModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import storage.orm.LeagueORM;
import storage.orm.MessageORM;
import utils.RxUtil;

/**
 * Created by turka on 11/4/2016.
 */

public class StorageLeagueDataStore  implements LeagueDataStore {

    private final Context context;
    @Inject
    public StorageLeagueDataStore(Context context) {
        this.context = context;
    }

    public void ClearCache(){
        LeagueORM.ClearCache(this.context);
    }

    public void insertLeague(List<LeagueTeamModel> leagueTeams){
        LeagueORM.InsertLeague(context,leagueTeams);
    }

    @Override
    public Observable<List<LeagueTeamModel>> leagueTeamList() {
        return RxUtil.makeObservable(new Callable<List<LeagueTeamModel>>() {
            @Override
            public List<LeagueTeamModel> call() throws Exception {
                List<LeagueTeamModel> league = LeagueORM.GetLeague(context);
                return league.isEmpty() ? null : league;
            }
        });
    }
}
