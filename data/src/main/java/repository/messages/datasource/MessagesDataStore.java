package repository.messages.datasource;

import com.models.MessageModel;

import java.util.List;

import rx.Observable;

/**
 * Created by turka on 10/29/2016.
 */

public interface MessagesDataStore {
    Observable<List<MessageModel>> messagesModelList();
}
