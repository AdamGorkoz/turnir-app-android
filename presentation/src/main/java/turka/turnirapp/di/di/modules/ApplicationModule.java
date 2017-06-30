package turka.turnirapp.di.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.repository.LeagueRepository;
import com.repository.MatchesRepository;
import com.repository.MessagesRepository;
import com.repository.PlayersRepository;

import net.ApiConstants;
import net.LeagueApi;
import net.MatchesApi;
import net.MessagesApi;
import net.PlayersApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import repository.league.LeagueRepositoryImpl;
import repository.matches.MatchesRepositoryImpl;
import repository.messages.MessagesRepositoryImpl;
import repository.players.PlayersRepositoryImpl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import turka.turnirapp.AndroidApplication;

/**
 * Created by turka on 10/29/2016.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;
    private final Context context;

    public ApplicationModule(AndroidApplication application,Context context) {
        this.application = application;
        this.context = context;
    }

    private Retrofit getRestApiAdapter(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    AndroidApplication provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Context context() {return this.context;}

    @Provides @Singleton
    MessagesRepository provideMessagesRepository(MessagesRepositoryImpl restDataSource) {
        return restDataSource;
    }

    @Provides @Singleton
    LeagueRepository provideLeagueRepository(LeagueRepositoryImpl restDataSource) {
        return restDataSource;
    }

    @Provides @Singleton
    PlayersRepository providePlayersRepository(PlayersRepositoryImpl restDataSource) {
        return restDataSource;
    }

    @Provides @Singleton
    MatchesRepository provideMatchesRepository(MatchesRepositoryImpl restDataSource) {
        return restDataSource;
    }

    @Provides @Singleton
    MessagesApi provideMessagesApi(){
        return getRestApiAdapter().create(MessagesApi.class);
    }

    @Provides @Singleton
    LeagueApi provideLeagueApi(){
        return getRestApiAdapter().create(LeagueApi.class);
    }

    @Provides @Singleton
    PlayersApi providePlayersApi(){
        return getRestApiAdapter().create(PlayersApi.class);
    }

    @Provides @Singleton
    MatchesApi provideMatchesApi(){
        return getRestApiAdapter().create(MatchesApi.class);
    }



    @Provides @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
