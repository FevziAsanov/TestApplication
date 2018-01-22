package kerchek.app.mesele.sual.testapplication.di;


import javax.inject.Singleton;

import dagger.Component;
import kerchek.app.mesele.sual.testapplication.data.network.WebClient;

@Singleton
@Component(modules = {AppModule.class, WebModule.class})
public interface AppComponent {


}
