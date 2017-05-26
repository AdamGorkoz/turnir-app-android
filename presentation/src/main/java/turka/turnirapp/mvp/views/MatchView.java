package turka.turnirapp.mvp.views;

import java.util.List;

import turka.turnirapp.model.Card;
import turka.turnirapp.model.Goal;
import turka.turnirapp.model.MatchEvent;

/**
 * Created by turka on 5/25/2017.
 */

public interface MatchView extends View {

    void updateMatchEvents(List<MatchEvent> events);
    void showLoadingIndicator();
    void hideLoadingIndicator();
    int getMatchId();
    int getFirstTeamId();
}
