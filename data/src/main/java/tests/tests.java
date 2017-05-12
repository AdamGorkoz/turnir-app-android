package tests;

import android.util.Log;

import com.models.MessageModel;

import net.MessagesApi;

import org.junit.Test;

import java.util.List;

import repository.messages.datasource.NetMessagesDataStore;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by turka on 10/29/2016.
 */

public class tests {
    @Test
    public void something(){
       NetMessagesDataStore x =  new NetMessagesDataStore(new MessagesApi() {
           @Override
           public Observable<List<MessageModel>> getMessages() {
               return getMessages();
           }
       });
        Observable y = x.messagesModelList();
        y.doOnNext(new Action1<List<MessageModel>>() {
            @Override
            public void call(List<MessageModel> messages) {
                Log.d("PBP", messages.toString());
            }
        });
        Log.d("PBP", "something: ");
    }
}
