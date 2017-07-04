package repository.authentication.datasource;

import com.models.LoginResultModel;

import net.AuthenticationApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by turka on 7/4/2017.
 */

public class NetAuthenticationDataSource implements  AuthenticationDataSource{
    private final AuthenticationApi authenticationApi;

    @Inject
    public NetAuthenticationDataSource(AuthenticationApi authenticationApi){
        this.authenticationApi = authenticationApi;
    }

    @Override
    public Observable<LoginResultModel> login(String username, String password) {
        return authenticationApi.login(username,password,"password");
    }
}
