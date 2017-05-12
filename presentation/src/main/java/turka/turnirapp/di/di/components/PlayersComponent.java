package turka.turnirapp.di.di.components;

import dagger.Component;
import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.di.di.modules.ActivityModule;
import turka.turnirapp.di.di.modules.PlayersModule;
import turka.turnirapp.views.fragment.TeamPlayersFragment;

/**
 * Created by turka on 5/6/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class ,PlayersModule.class})
public interface PlayersComponent {
    void inject (TeamPlayersFragment fragment);
}
