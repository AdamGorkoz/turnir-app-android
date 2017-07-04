package turka.turnirapp.di.di.components;

import dagger.Component;
import turka.turnirapp.LoginActivity;
import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.di.di.modules.ActivityModule;
import turka.turnirapp.di.di.modules.AuthenticationModule;

/**
 * Created by turka on 7/4/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class ,AuthenticationModule.class})
public interface AuthenticationComponent {
    void inject (LoginActivity activity);
}