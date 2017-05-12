package com.usecase;

import com.models.MessageModel;
import com.repository.MessagesRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by turka on 10/29/2016.
 */

public class GetMessagesUsecase extends Usecase {

    private final MessagesRepository mRepository;

    private boolean isForceUpdate;

    public void setForceUpdate(boolean forceUpdate) {
        isForceUpdate = forceUpdate;
    }

    @Inject
    public GetMessagesUsecase(MessagesRepository repository, @Named("ui_thread") Scheduler uiThread,
                              @Named("executor_thread") Scheduler executorThread) {
        super(uiThread,executorThread);
        mRepository = repository;
    }

    @Override
    public Observable<List<MessageModel>> buildObservable() {
        return mRepository.getMessages(this.isForceUpdate);
    }

}
