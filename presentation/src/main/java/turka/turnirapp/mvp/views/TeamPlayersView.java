package turka.turnirapp.mvp.views;

import java.util.List;

import turka.turnirapp.model.Player;

/**
 * Created by turka on 5/6/2017.
 */

public interface TeamPlayersView extends View {
    void updateTeamPlayers(List<Player> players);
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showNoPlayersView();
    void hideNoPlayersView();
    int getTeamId();
}
