package turka.turnirapp.di.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by turka on 10/29/2016.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
