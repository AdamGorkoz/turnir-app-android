package turka.turnirapp.mvp.presenters;

import android.util.Log;

import com.models.MessageModel;
import com.usecase.DefaultSubscriber;
import com.usecase.GetMessagesUsecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;
import turka.turnirapp.*;
import turka.turnirapp.mvp.views.MessagesListView;
import turka.turnirapp.mvp.views.View;

/**
 * Created by turka on 10/30/2016.
 */

public class MessagesListPresenter implements Presenter {

    private MessagesListView mMessagesListView;
    private List<MessageModel> messages;
    private final GetMessagesUsecase mGetMessagesUsecase;

    @Inject
    public MessagesListPresenter(GetMessagesUsecase getMessagesUsecase) {
        mGetMessagesUsecase = getMessagesUsecase;
    }

    @Override
    public void onStart() {
        fetchMessages();
    }

    @Override
    public void onStop() {
        this.mGetMessagesUsecase.unsubscribe();
        this.mMessagesListView = null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View view) {
        this.mMessagesListView = (MessagesListView)view;
    }

    @Override
    public void onCreate() {
        this.messages = new ArrayList<MessageModel>();
    }

    public void fetchMessages() {
        mMessagesListView.showLoadingIndicator();
        mGetMessagesUsecase.setForceUpdate(false);
        mGetMessagesUsecase.execute(new MessageListSubscriber());
    }

    public void refreshMessages(){
        mMessagesListView.showLoadingIndicator();
        mGetMessagesUsecase.setForceUpdate(true);
        mGetMessagesUsecase.execute(new MessageListSubscriber());
    }

    private final class MessageListSubscriber extends DefaultSubscriber<List<MessageModel>> {

        @Override public void onCompleted() {
            mMessagesListView.hideLoadingIndicator();
        }

        @Override public void onError(Throwable e) {
            mMessagesListView.hideLoadingIndicator();
            mMessagesListView.showNoMessagesView();
        }

        @Override public void onNext(List<MessageModel> messages) {
            MessagesListPresenter.this.messages.clear();
            Collections.sort(messages, new Comparator<MessageModel>() {
                @Override
                public int compare(MessageModel messageModel, MessageModel t1) {
                    return t1.getId() - messageModel.getId();
                }
            });
            MessagesListPresenter.this.messages.addAll(messages);
            mMessagesListView.updateMessagesList(MessagesListPresenter.this.messages);
            if(messages.isEmpty() == false){
                mMessagesListView.hideNoMessagesView();
            }
        }
    }
}
