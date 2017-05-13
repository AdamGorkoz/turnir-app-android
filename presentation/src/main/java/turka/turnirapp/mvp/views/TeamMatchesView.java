package turka.turnirapp.mvp.views;

import java.util.List;

import turka.turnirapp.model.TeamMatch;

/**
 * Created by turka on 5/13/2017.
 */

public interface TeamMatchesView extends View {
    void updateTeamMatches(List<TeamMatch> matches);
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showNoMatchesView();
    void hideNoMatchesView();
    int getTeamId();
}
