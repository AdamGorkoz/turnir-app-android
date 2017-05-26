package turka.turnirapp.di.di.modules;

import com.repository.MatchesRepository;
import com.usecase.GetMatchInfoUsecase;
import com.usecase.GetTeamMatchesUsecase;
import com.usecase.Usecase;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import turka.turnirapp.di.di.PerActivity;

/**
 * Created by turka on 5/13/2017.
 */

@Module
public class MatchesModule {

    public MatchesModule() {

    }

    @Provides
    @PerActivity
    Usecase provideGetTeamMatchesUsecase(MatchesRepository matchesRepository, Scheduler uiThread, Scheduler executorThread) {
        return new GetTeamMatchesUsecase(matchesRepository,uiThread, executorThread);
    }


    @Provides
    @PerActivity
    Usecase provideGetMatchInfoUsecase(MatchesRepository matchesRepository, Scheduler uiThread, Scheduler executorThread) {
        return new GetMatchInfoUsecase(matchesRepository,uiThread, executorThread);
    }
}
