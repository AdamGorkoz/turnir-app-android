package turka.turnirapp.mvp.presenters;

import com.models.LiveMatchModel;
import com.models.TeamMatchModel;
import com.usecase.DefaultSubscriber;
import com.usecase.GetLiveMatchesUsecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import turka.turnirapp.mapper.LiveMatchMapper;
import turka.turnirapp.model.LiveMatch;
import turka.turnirapp.mvp.views.LiveMatchesListView;
import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 6/30/2017.
 */

public class LiveMatchesPresenter implements Presenter {

    private LiveMatchesListView mLiveMatchesListView;
    private List<Object> matches;
    private final GetLiveMatchesUsecase mGetLiveMatchesUsecase;
    private final LiveMatchMapper mLiveMatchMapper;

    @Inject
    public LiveMatchesPresenter(GetLiveMatchesUsecase getLiveMatchesUsecase, LiveMatchMapper liveMatchMapper) {
        mGetLiveMatchesUsecase = getLiveMatchesUsecase;
        mLiveMatchMapper = liveMatchMapper;
    }

    @Override
    public void onStart() {
        fetchLiveMatches();
    }

    @Override
    public void onStop() {
        this.mGetLiveMatchesUsecase.unsubscribe();
        this.mLiveMatchesListView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View view) {
        this.mLiveMatchesListView = (LiveMatchesListView) view;
    }

    @Override
    public void onCreate() {
        this.matches = new ArrayList<Object>();
    }

    public void fetchLiveMatches() {
        mLiveMatchesListView.showLoadingIndicator();
        mGetLiveMatchesUsecase.execute(new LiveMatchesListSubscriber());
    }

    public void refreshLiveMatches(){
        this.fetchLiveMatches();
    }

    private final class LiveMatchesListSubscriber extends DefaultSubscriber<List<Object>> {

        @Override public void onCompleted() {
            mLiveMatchesListView.hideLoadingIndicator();
        }

        @Override public void onError(Throwable e) {
            mLiveMatchesListView.hideLoadingIndicator();
            mLiveMatchesListView.showNoMatchesView();
        }

        @Override public void onNext(List<Object> liveMatches) {
            LiveMatchesPresenter.this.matches.clear();
            List<Object> mappedObjects = new ArrayList<Object>();
            for (Object liveMatch :
                    liveMatches) {
                if(liveMatch instanceof  LiveMatchModel){
                    mappedObjects.add(mLiveMatchMapper.transform((LiveMatchModel)liveMatch));
                }else{
                    mappedObjects.add(liveMatch);
                }
            }

            LiveMatchesPresenter.this.matches.addAll(mappedObjects);
            mLiveMatchesListView.updateLiveMatchesList(LiveMatchesPresenter.this.matches);
            if(liveMatches.isEmpty() == false){
                mLiveMatchesListView.hideNoMatchesView();
            }
            else{
                mLiveMatchesListView.showNoMatchesView();
            }
        }
    }
}
