package turka.turnirapp.mvp.views;

import com.models.LoginResultModel;

/**
 * Created by turka on 7/4/2017.
 */

public interface LoginView extends View {
    void onLoginResult(LoginResultModel loginResult);
    void showProgress();
    void hideProgress();
}
