package com.usecase;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by turka on 10/29/2016.
 */

public abstract class Usecase {
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;

    private Subscription subscription = Subscriptions.empty();

    protected Usecase(Scheduler UiThread,
                      Scheduler ExecutorThread) {
        this.mUiThread = UiThread;
        this.mExecutorThread = ExecutorThread;
    }

    public abstract Observable buildObservable();

    public void execute(Subscriber useCaseSubscriber) {
        this.subscription = this.buildObservable()
                .subscribeOn(mExecutorThread)
                .observeOn(mUiThread)
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
