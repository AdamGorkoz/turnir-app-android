package turka.turnirapp.di.di.components;

import dagger.Component;
import turka.turnirapp.TeamMatchActivity;
import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.di.di.modules.ActivityModule;
import turka.turnirapp.di.di.modules.MatchesModule;
import turka.turnirapp.mvp.views.LiveMatchesListView;
import turka.turnirapp.views.fragment.LiveMatchesFragment;
import turka.turnirapp.views.fragment.TeamMatchesFragment;

/**
 * Created by turka on 5/13/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class ,MatchesModule.class})
public interface MatchesComponent {
    void inject (TeamMatchesFragment fragment);
    void inject (TeamMatchActivity activity);
    void inject (LiveMatchesFragment fragment);
}