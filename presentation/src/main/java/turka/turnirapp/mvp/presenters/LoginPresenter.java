package turka.turnirapp.mvp.presenters;

import com.models.LoginResultModel;
import com.usecase.DefaultSubscriber;
import com.usecase.LoginUsecase;

import javax.inject.Inject;

import turka.turnirapp.mvp.views.LoginView;
import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 7/4/2017.
 */

public class LoginPresenter implements Presenter {
    private LoginView mLoginView;
    private final LoginUsecase mLoginUsecase;

    @Inject
    public LoginPresenter(LoginUsecase loginUsecase){
        mLoginUsecase = loginUsecase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        this.mLoginUsecase.unsubscribe();
        this.mLoginView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View view) {
        mLoginView = (LoginView)view;
    }

    @Override
    public void onCreate() {

    }

    public void login(String username , String password){
        mLoginUsecase.setUsername(username);
        mLoginUsecase.setPassword(password);
        mLoginUsecase.execute(new LoginSubscriber());
    }

    private final class LoginSubscriber extends DefaultSubscriber<LoginResultModel> {

        @Override public void onCompleted() {
            mLoginView.hideProgress();
        }

        @Override public void onError(Throwable e) {
            mLoginView.hideProgress();
            mLoginView.onLoginResult(null);
        }

        @Override
        public void onNext(LoginResultModel loginResultModel) {
            mLoginView.hideProgress();
            mLoginView.onLoginResult(loginResultModel);
        }
    }
}
