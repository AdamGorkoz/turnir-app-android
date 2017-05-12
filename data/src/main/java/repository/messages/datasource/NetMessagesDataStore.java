package repository.messages.datasource;

import com.models.MessageModel;

import net.MessagesApi;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by turka on 10/29/2016.
 */

public class NetMessagesDataStore implements MessagesDataStore {

    private final MessagesApi messagesApi;

    @Inject
    public NetMessagesDataStore(MessagesApi messagesApi){
        this.messagesApi = messagesApi;
    }

    @Override
    public Observable<List<MessageModel>> messagesModelList() {
        return messagesApi.getMessages();
    }
}
