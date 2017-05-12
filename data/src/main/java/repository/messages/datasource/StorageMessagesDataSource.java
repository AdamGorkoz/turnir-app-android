package repository.messages.datasource;

import android.content.Context;

import com.models.MessageModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import storage.orm.MessageORM;
import utils.RxUtil;

/**
 * Created by turka on 11/1/2016.
 */

public class StorageMessagesDataSource implements MessagesDataStore {

    private final Context context;
    @Inject
    public StorageMessagesDataSource(Context context) {
        this.context = context;
    }

    public void ClearCache(){
        MessageORM.ClearCache(this.context);
    }

    public void insertMessages(List<MessageModel> messages){
        MessageORM.InsertMessages(context,messages);
    }

    @Override
    public Observable<List<MessageModel>> messagesModelList() {
        return RxUtil.makeObservable(new Callable<List<MessageModel>>() {
            @Override
            public List<MessageModel> call() throws Exception {
                List<MessageModel> messages = MessageORM.GetMessages(context);
                return messages.isEmpty() ? null : messages;
            }
        });
    }
}
