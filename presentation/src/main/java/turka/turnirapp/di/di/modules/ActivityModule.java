package turka.turnirapp.di.di.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import turka.turnirapp.di.di.PerActivity;

/**
 * Created by turka on 10/29/2016.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides @PerActivity Activity activity() {
        return this.activity;
    }
}
