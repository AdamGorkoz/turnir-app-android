package turka.turnirapp.di.di.components;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.di.di.modules.ActivityModule;

/**
 * Created by turka on 10/29/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
