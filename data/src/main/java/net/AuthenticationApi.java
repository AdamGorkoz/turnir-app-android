package net;

import com.models.LoginResultModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by turka on 7/4/2017.
 */

public interface AuthenticationApi {
    @FormUrlEncoded
    @POST("login")
    Observable<LoginResultModel> login(@Field("UserName") String UserName, @Field("Password") String password, @Field("grant_type") String grant_type);
}
