package turka.turnirapp.mapper;

import com.models.LeagueTeamModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.model.LeagueTeam;

/**
 * Created by turka on 5/4/2017.
 */

@PerActivity
public class LeagueTeamMapper {

    @Inject
    public LeagueTeamMapper() {}


    public LeagueTeam transform(LeagueTeamModel leagueTeamModel) {
        if (leagueTeamModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new LeagueTeam(leagueTeamModel.getID(),
                leagueTeamModel.getName(),
                leagueTeamModel.getMatches(),
                leagueTeamModel.getPoints(),
                leagueTeamModel.getWins(),
                leagueTeamModel.getLosses(),
                leagueTeamModel.getDraws(),
                leagueTeamModel.getDiff(),
                leagueTeamModel.getGoals());
    }

    public Collection<LeagueTeam> transform(Collection<LeagueTeamModel> leagueTeamModelCollection) {
        Collection<LeagueTeam> leagueTeamCollection;

        if (leagueTeamModelCollection != null && !leagueTeamModelCollection.isEmpty()) {
            leagueTeamCollection = new ArrayList<>();
            for (LeagueTeamModel leagueTeamModel : leagueTeamModelCollection) {
                leagueTeamCollection.add(transform(leagueTeamModel));
            }
        } else {
            leagueTeamCollection = Collections.emptyList();
        }

        return leagueTeamCollection;
    }
}
