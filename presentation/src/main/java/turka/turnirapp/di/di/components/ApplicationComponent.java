package turka.turnirapp.di.di.components;

import android.content.Context;

import com.repository.LeagueRepository;
import com.repository.MessagesRepository;
import com.repository.PlayersRepository;

import net.LeagueApi;
import net.MessagesApi;
import net.PlayersApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;
import turka.turnirapp.AndroidApplication;
import turka.turnirapp.di.di.modules.ApplicationModule;
import turka.turnirapp.views.BaseActivity;

/**
 * Created by turka on 10/29/2016.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    AndroidApplication app();
    Context context();
    @Named("ui_thread")
    Scheduler uiThread();
    @Named("executor_thread")
    Scheduler executorThread();
    MessagesRepository messagesRepository();
    MessagesApi messagesApi();
    LeagueRepository leagueRepository();
    LeagueApi leagueApi();
    PlayersRepository playersRepository();
    PlayersApi playersApi();
}