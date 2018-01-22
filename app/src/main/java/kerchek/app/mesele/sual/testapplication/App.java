package kerchek.app.mesele.sual.testapplication;

import android.app.Application;

import kerchek.app.mesele.sual.testapplication.di.AppComponent;
import kerchek.app.mesele.sual.testapplication.di.AppModule;
import kerchek.app.mesele.sual.testapplication.di.DaggerAppComponent;
import kerchek.app.mesele.sual.testapplication.di.DaggerNetComponent;
import kerchek.app.mesele.sual.testapplication.di.NetComponent;
import kerchek.app.mesele.sual.testapplication.di.WebModule;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public class App extends Application {

    private static AppComponent sAppComponent;
    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger(this);
    }

    private void initDagger(Application application) {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .webModule(new WebModule())
                .build();

        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(application))
                .webModule(new WebModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}
