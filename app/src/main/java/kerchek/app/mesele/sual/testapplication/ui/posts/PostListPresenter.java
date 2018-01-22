package kerchek.app.mesele.sual.testapplication.ui.posts;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import kerchek.app.mesele.sual.testapplication.App;
import kerchek.app.mesele.sual.testapplication.data.network.WebClient;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public class PostListPresenter implements PostListContract.Presenter {

    @Inject
    WebClient webClient;

    private PostListContract.View view;

    public PostListPresenter(PostListContract.View view) {

        App.getAppComponent().inject(this);

        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public Disposable getPosts() {
        view.indicateProgress(0);
        return webClient.getPosts(listResponse -> {
            view.showPostList(listResponse.body());
            view.stopProgressIndication();
        }, throwable -> {
            view.showError(throwable);
            view.stopProgressIndication();
        });
    }
}
