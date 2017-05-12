package turka.turnirapp.mvp.presenters;

import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 10/30/2016.
 */

public interface Presenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView (View v);

    void onCreate();
}
