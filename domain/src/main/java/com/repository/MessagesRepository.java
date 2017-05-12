package com.repository;

import com.models.MessageModel;

import java.util.List;

import rx.Observable;

/**
 * Created by turka on 10/29/2016.
 */

public interface MessagesRepository {
    Observable<List<MessageModel>> getMessages(boolean isForceUpdate);
}
