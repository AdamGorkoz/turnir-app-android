package turka.turnirapp.di.di.components;

import dagger.Component;
import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.di.di.modules.ActivityModule;
import turka.turnirapp.di.di.modules.LeagueModule;
import turka.turnirapp.views.fragment.LeagueFragment;

/**
 * Created by turka on 11/5/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class ,LeagueModule.class})
public interface LeagueComponent {
    void inject (LeagueFragment fragment);
}
