package repository.authentication;

import com.models.LoginResultModel;
import com.repository.AuthenticationRepository;

import javax.inject.Inject;

import repository.authentication.datasource.NetAuthenticationDataSource;
import rx.Observable;

/**
 * Created by turka on 7/4/2017.
 */

public class AuthenticationRespositoryImpl implements AuthenticationRepository {

    private final NetAuthenticationDataSource netAuthenticationDataSource;

    @Inject
    public AuthenticationRespositoryImpl(NetAuthenticationDataSource netAuthenticationDataSource){
        this.netAuthenticationDataSource = netAuthenticationDataSource;
    }

    @Override
    public Observable<LoginResultModel> login(String username, String password) {
        return netAuthenticationDataSource.login(username,password);
    }
}
