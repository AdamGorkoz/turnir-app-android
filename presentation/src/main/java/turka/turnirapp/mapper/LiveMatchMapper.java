package turka.turnirapp.mapper;

import com.models.LiveMatchModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.model.LiveMatch;

/**
 * Created by turka on 6/30/2017.
 */

@PerActivity
public class LiveMatchMapper {
    @Inject
    public LiveMatchMapper() {}


    public LiveMatch transform(LiveMatchModel liveMatchModel) {
        if (liveMatchModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new LiveMatch(liveMatchModel.getID(),liveMatchModel.getMatchDate(),liveMatchModel.getFirstTeamID(),liveMatchModel.getFirstTeamName(),liveMatchModel.getFirstTeamGoals(),liveMatchModel.getSecondTeamID(),liveMatchModel.getSecondTeamName(),liveMatchModel.getSecondTeamGoals(),liveMatchModel.getRefereeName(),liveMatchModel.getMatchStatus(),liveMatchModel.getTime(),liveMatchModel.getStartTime(),
                liveMatchModel.getSecondHalfStartTime(),liveMatchModel.getSecondHalf(),liveMatchModel.getScore(),liveMatchModel.getMatchOutcome(),liveMatchModel.getGoals(),liveMatchModel.getField(),liveMatchModel.getMatchTypeID());
    }

    public Collection<LiveMatch> transform(Collection<LiveMatchModel> matchModelsCollection) {
        Collection<LiveMatch> matchesCollection;

        if (matchModelsCollection != null && !matchModelsCollection.isEmpty()) {
            matchesCollection = new ArrayList<>();
            for (LiveMatchModel matchModel : matchModelsCollection) {
                matchesCollection.add(transform(matchModel));
            }
        } else {
            matchesCollection = Collections.emptyList();
        }

        return matchesCollection;
    }
}
