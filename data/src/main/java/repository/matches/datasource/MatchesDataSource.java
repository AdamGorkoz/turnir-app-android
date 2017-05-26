package repository.matches.datasource;

import com.models.CardModel;
import com.models.GoalModel;
import com.models.TeamMatchModel;

import java.util.List;

import rx.Observable;

/**
 * Created by turka on 5/12/2017.
 */
public interface MatchesDataSource {
    Observable<List<TeamMatchModel>> teamMatchesList(int teamId);
    Observable<List<GoalModel>> matchGoals(int matchId);
    Observable<List<CardModel>> matchCards(int matchId);
}
