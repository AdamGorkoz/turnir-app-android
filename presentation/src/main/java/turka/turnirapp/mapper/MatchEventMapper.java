package turka.turnirapp.mapper;

import com.models.MatchEventModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import turka.turnirapp.model.MatchEvent;

/**
 * Created by turka on 5/25/2017.
 */

public class MatchEventMapper {

    @Inject
    public MatchEventMapper() {}


    public MatchEvent transform(MatchEventModel matchEventModel, int firstTeamId) {
        if (matchEventModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new MatchEvent(matchEventModel.getPlayerName(),
                matchEventModel.getMinute(),
                matchEventModel.getTeamId() == firstTeamId ? 1 : 2,
                matchEventModel.getType(),
                matchEventModel.getSubtype());
    }

    public Collection<MatchEvent> transform(Collection<MatchEventModel> matchEventModels , int firstTeamId) {
        Collection<MatchEvent> matchEventsCollection;

        if (matchEventModels != null && !matchEventModels.isEmpty()) {
            matchEventsCollection = new ArrayList<>();
            for (MatchEventModel matchEventModel : matchEventModels) {
                matchEventsCollection.add(transform(matchEventModel,firstTeamId));
            }
        } else {
            matchEventsCollection = Collections.emptyList();
        }

        return matchEventsCollection;
    }
}
