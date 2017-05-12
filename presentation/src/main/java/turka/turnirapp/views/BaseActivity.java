package turka.turnirapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import turka.turnirapp.AndroidApplication;
import turka.turnirapp.di.di.components.ApplicationComponent;
import turka.turnirapp.di.di.modules.ActivityModule;

/**
 * Created by turka on 10/29/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }


    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }


    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}