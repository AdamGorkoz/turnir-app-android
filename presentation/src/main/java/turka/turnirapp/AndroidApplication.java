package turka.turnirapp;

import android.app.Application;

import turka.turnirapp.di.di.components.ApplicationComponent;
import turka.turnirapp.di.di.components.DaggerApplicationComponent;
import turka.turnirapp.di.di.modules.ApplicationModule;

/**
 * Created by turka on 10/29/2016.
 */

public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;
    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this,getApplicationContext()))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
