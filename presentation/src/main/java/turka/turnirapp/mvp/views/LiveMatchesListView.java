package turka.turnirapp.mvp.views;

import com.models.TeamMatchModel;

import java.util.List;

import turka.turnirapp.model.LiveMatch;

/**
 * Created by turka on 6/30/2017.
 */

public interface LiveMatchesListView extends View {
    void updateLiveMatchesList(List<LiveMatch> matches);
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showNoMatchesView();
    void hideNoMatchesView();
}
