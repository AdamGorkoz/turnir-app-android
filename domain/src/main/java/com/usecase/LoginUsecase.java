package com.usecase;

import com.repository.AuthenticationRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by turka on 7/4/2017.
 */

public class LoginUsecase extends Usecase {
    private final AuthenticationRepository mRepository;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Inject
    public LoginUsecase(AuthenticationRepository repository, @Named("ui_thread") Scheduler uiThread,
                                 @Named("executor_thread") Scheduler executorThread) {
        super(uiThread,executorThread);
        mRepository = repository;
    }

    @Override
    public Observable buildObservable() {
        return mRepository.login(this.getUsername(),this.getPassword());
    }
}
