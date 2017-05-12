package net;

import com.models.MessageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by turka on 10/29/2016.
 */

public interface  MessagesApi {
    @GET("message")
    Observable<List<MessageModel>> getMessages();
}
