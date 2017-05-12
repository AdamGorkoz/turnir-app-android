package turka.turnirapp.mvp.views;

import java.util.List;

import turka.turnirapp.model.LeagueTeam;

/**
 * Created by turka on 11/5/2016.
 */

public interface LeagueView extends View {
    void updateLeague(List<LeagueTeam> league);
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showNoLeagueView();
    void hideNoLeagueView();
}
