package turka.turnirapp.mapper;

import com.models.TeamMatchModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.model.TeamMatch;

/**
 * Created by turka on 5/13/2017.
 */

@PerActivity
public class TeamMatchMapper {
    @Inject
    public TeamMatchMapper() {}


    public TeamMatch transform(TeamMatchModel teamMatchModel) {
        if (teamMatchModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new TeamMatch(teamMatchModel.getID(),
                teamMatchModel.getMatchDate(),
                teamMatchModel.getMatchOutcome(),
                teamMatchModel.getScore(),
                teamMatchModel.getOpponent());
    }

    public Collection<TeamMatch> transform(Collection<TeamMatchModel> matchModelsCollection) {
        Collection<TeamMatch> matchesCollection;

        if (matchModelsCollection != null && !matchModelsCollection.isEmpty()) {
            matchesCollection = new ArrayList<>();
            for (TeamMatchModel matchModel : matchModelsCollection) {
                matchesCollection.add(transform(matchModel));
            }
        } else {
            matchesCollection = Collections.emptyList();
        }

        return matchesCollection;
    }
}
