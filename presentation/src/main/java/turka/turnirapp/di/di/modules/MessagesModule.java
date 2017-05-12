package turka.turnirapp.di.di.modules;

import android.content.Context;

import com.repository.MessagesRepository;
import com.usecase.GetMessagesUsecase;
import com.usecase.Usecase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import turka.turnirapp.di.di.PerActivity;

/**
 * Created by turka on 10/30/2016.
 */

@Module
public class MessagesModule {

    public MessagesModule() {

    }

    @Provides
    @PerActivity
    Usecase provideGetMessagesUsecase(MessagesRepository messagesRepository, Scheduler uiThread, Scheduler executorThread) {
        return new GetMessagesUsecase(messagesRepository,uiThread, executorThread);
    }
}
