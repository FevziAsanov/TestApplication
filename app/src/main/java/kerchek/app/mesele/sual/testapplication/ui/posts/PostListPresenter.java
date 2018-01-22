package kerchek.app.mesele.sual.testapplication.ui.posts;

import io.reactivex.disposables.Disposable;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public class PostListPresenter implements PostListContract.Presenter {


    private PostListContract.View view;

    public PostListPresenter(PostListContract.View view) {

        this.view = view;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public Disposable getPosts() {
        view.indicateProgress(0);
//        return webClient.getPosts(listResponse -> {
//            view.showPostList(listResponse.body());
//            view.stopProgressIndication();
//        }, throwable -> {
//            view.showError(throwable);
//            view.stopProgressIndication();
//        });

        return null;
    }
}
