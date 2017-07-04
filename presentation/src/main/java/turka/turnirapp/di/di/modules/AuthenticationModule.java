package turka.turnirapp.di.di.modules;

import com.repository.AuthenticationRepository;
import com.usecase.LoginUsecase;
import com.usecase.Usecase;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import turka.turnirapp.di.di.PerActivity;

/**
 * Created by turka on 7/4/2017.
 */

@Module
public class AuthenticationModule {

    public AuthenticationModule(){

    }

    @Provides
    @PerActivity
    Usecase provideLoginUsecase(AuthenticationRepository leagueRepository, Scheduler uiThread, Scheduler executorThread) {
        return new LoginUsecase(leagueRepository,uiThread, executorThread);
    }
}
