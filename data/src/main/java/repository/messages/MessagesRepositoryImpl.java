package repository.messages;

import com.models.MessageModel;
import com.repository.MessagesRepository;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import repository.messages.datasource.NetMessagesDataStore;
import repository.messages.datasource.StorageMessagesDataSource;
import rx.Observable;
import rx.functions.Action1;
import utils.RxUtil;

/**
 * Created by turka on 10/29/2016.
 */

public class MessagesRepositoryImpl implements MessagesRepository {

    private final NetMessagesDataStore netMessagesDataStore;
    private final StorageMessagesDataSource storageMessagesDataSource;

    @Inject
    public MessagesRepositoryImpl(NetMessagesDataStore netMessagesDataStore, StorageMessagesDataSource storageMessagesDataSource) {
        this.netMessagesDataStore = netMessagesDataStore;
        this.storageMessagesDataSource = storageMessagesDataSource;
    }

    private Observable<List<MessageModel>> fetchNetMessages(){
        return netMessagesDataStore.messagesModelList().doOnNext(new Action1<List<MessageModel>>() {
            @Override
            public void call(List<MessageModel> messageModels) {
                storageMessagesDataSource.ClearCache();
                storageMessagesDataSource.insertMessages(messageModels);
            }
        });
    }

    @Override
    public Observable<List<MessageModel>> getMessages(boolean isForceUpdate) {
        if(isForceUpdate){
            return fetchNetMessages();
        }
        else{
            return Observable.concat(storageMessagesDataSource.messagesModelList(),fetchNetMessages());
        }
    }
}
