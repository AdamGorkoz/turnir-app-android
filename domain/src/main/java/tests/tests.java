package tests;

import com.models.MessageModel;
import com.repository.MessagesRepository;
import com.usecase.GetMessagesUsecase;

import org.junit.Test;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;

/**
 * Created by turka on 10/29/2016.
 */

public class tests {

//    private final GetMessagesUsecase getMessagesUsecase;
//
//
//    @Test
//    public void something(){
//        GetMessagesUsecase getMessagesUsecase = new GetMessagesUsecase(new MessagesRepository() {
//            @Override
//            public Observable<List<MessageModel>> getMessages() {
//                return null;
//            }
//        }, new Scheduler() {
//            @Override
//            public Worker createWorker() {
//                return null;
//            }
//        })
//        getMessagesUsecase.execute().subscribe(new Action1<List<MessageModel>>(){
//
//            @Override
//            public void call(List<MessageModel> messageModels) {
//                System.out.println(messageModels);
//            }
//        });
//    }
}
