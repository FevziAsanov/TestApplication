package kerchek.app.mesele.sual.testapplication.di;

import javax.inject.Singleton;

import dagger.Component;
import kerchek.app.mesele.sual.testapplication.ui.posts.PostListActivity;
import kerchek.app.mesele.sual.testapplication.ui.posts.PostListPresenter;


@Singleton
@Component(modules = {WebModule.class, AppModule.class})
public interface NetComponent {

    void inject(PostListPresenter postListPresenter);

    void inject(PostListActivity postListActivity);

}

