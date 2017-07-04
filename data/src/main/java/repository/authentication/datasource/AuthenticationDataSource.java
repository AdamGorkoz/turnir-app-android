package repository.authentication.datasource;

import com.models.LoginResultModel;

import rx.Observable;

/**
 * Created by turka on 7/4/2017.
 */

public interface AuthenticationDataSource {
    Observable<LoginResultModel> login(String username, String password);
}
