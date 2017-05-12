package turka.turnirapp.di.di.components;

import android.content.Context;


import com.usecase.GetMessagesUsecase;

import dagger.Component;
import turka.turnirapp.MainActivity;
import turka.turnirapp.di.di.PerActivity;
import turka.turnirapp.di.di.modules.ActivityModule;
import turka.turnirapp.di.di.modules.ApplicationModule;
import turka.turnirapp.di.di.modules.MessagesModule;
import turka.turnirapp.views.fragment.MessageFragment;

/**
 * Created by turka on 10/30/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class ,MessagesModule.class})
public interface MessagesComponent {
    void inject (MessageFragment fragment);
}
