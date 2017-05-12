package turka.turnirapp.di.di.modules;

import com.repository.PlayersRepository;
import com.usecase.GetTeamPlayersUsecase;
import com.usecase.Usecase;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import turka.turnirapp.di.di.PerActivity;

/**
 * Created by turka on 5/6/2017.
 */

@Module
public class PlayersModule {
    public PlayersModule() {

    }

    @Provides
    @PerActivity
    Usecase provideGetTeamPlayersUsecase(PlayersRepository playersRepository, Scheduler uiThread, Scheduler executorThread) {
        return new GetTeamPlayersUsecase(playersRepository,uiThread, executorThread);
    }
}
