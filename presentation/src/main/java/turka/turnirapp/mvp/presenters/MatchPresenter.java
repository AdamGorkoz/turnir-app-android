package turka.turnirapp.mvp.presenters;

import com.models.MatchEventModel;
import com.usecase.DefaultSubscriber;
import com.usecase.GetMatchInfoUsecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.mapper.MatchEventMapper;
import turka.turnirapp.model.MatchEvent;
import turka.turnirapp.mvp.views.MatchView;
import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 5/25/2017.
 */

public class MatchPresenter implements Presenter {

    private final MatchEventMapper mMatchEventMapper;
    private MatchView mMatchView;
    private List<MatchEvent> matchEvents;

    private GetMatchInfoUsecase mGetMatchInfoUsecase;

    @Inject
    public MatchPresenter(GetMatchInfoUsecase getMatchInfoUsecase, MatchEventMapper matchEventMapper) {
        mGetMatchInfoUsecase = getMatchInfoUsecase;
        mMatchEventMapper = matchEventMapper;
    }

    @Override
    public void onStart() {
        fetchMatchEvents();
    }

    public void fetchMatchEvents() {
        mMatchView.showLoadingIndicator();
        mGetMatchInfoUsecase.setMatchId(mMatchView.getMatchId());
        mGetMatchInfoUsecase.execute(new MatchEventsSubscriber());
    }

    @Override
    public void onStop() {
        this.mGetMatchInfoUsecase.unsubscribe();
        this.mMatchView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View view) {
        mMatchView = (MatchView)view;
    }

    @Override
    public void onCreate() {
        this.matchEvents = new ArrayList<MatchEvent>();
    }

    private final class MatchEventsSubscriber extends DefaultSubscriber<List<MatchEventModel>> {

        @Override public void onCompleted() {
            mMatchView.hideLoadingIndicator();
        }

        @Override public void onError(Throwable e) {
            mMatchView.hideLoadingIndicator();
        }

        @Override public void onNext(List<MatchEventModel> matchEventModels) {
            MatchPresenter.this.matchEvents.clear();
            MatchPresenter.this.matchEvents.addAll(mMatchEventMapper.transform(matchEventModels,mMatchView.getFirstTeamId()));
            mMatchView.updateMatchEvents(MatchPresenter.this.matchEvents);
        }
    }
}
