package turka.turnirapp.di.di.modules;

import com.repository.LeagueRepository;
import com.usecase.GetLeagueUsecase;
import com.usecase.Usecase;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import turka.turnirapp.di.di.PerActivity;

/**
 * Created by turka on 11/5/2016.
 */

@Module
public class LeagueModule {
    public LeagueModule() {

    }

    @Provides
    @PerActivity
    Usecase provideGetLeagueUsecase(LeagueRepository leagueRepository, Scheduler uiThread, Scheduler executorThread) {
        return new GetLeagueUsecase(leagueRepository,uiThread, executorThread);
    }
}
