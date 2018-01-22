package kerchek.app.mesele.sual.testapplication.di;


import javax.inject.Singleton;

import dagger.Component;
import kerchek.app.mesele.sual.testapplication.ui.posts.PostListPresenter;

@Singleton
@Component(modules = {AppModule.class, WebModule.class})
public interface AppComponent {
    void inject(PostListPresenter postListPresenter);
}
