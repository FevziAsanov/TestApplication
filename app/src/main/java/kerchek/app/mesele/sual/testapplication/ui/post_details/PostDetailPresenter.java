package kerchek.app.mesele.sual.testapplication.ui.post_details;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import kerchek.app.mesele.sual.testapplication.App;
import kerchek.app.mesele.sual.testapplication.data.models.User;
import kerchek.app.mesele.sual.testapplication.data.network.WebClient;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public class PostDetailPresenter implements PostDetailContract.Presenter {

    @Inject
    WebClient webClient;
    private PostDetailContract.View view;

    public PostDetailPresenter(PostDetailContract.View view) {
        App.getAppComponent().inject(this);

        this.view = view;
        this.view.setPresenter(this);

    }

    @Override
    public void start() {
    }

    @Override
    public Disposable getComments(long postId) {
        view.indicateProgress(0);
        return webClient.getComments(postId, listResponse -> {
            view.showCommentsNumber(listResponse.body());
            view.stopProgressIndication();
        }, throwable -> {
            view.showError(throwable);
            view.stopProgressIndication();
        });
    }

    @Override
    public Disposable getUser(long userId) {
        view.indicateProgress(0);
        return webClient.getUser(userId, listResponse -> {
            User user = null;

            if (listResponse != null) {
                List<User> users = listResponse.body();
                if (users != null && !users.isEmpty())
                    user = users.get(0);
            }

            Log.d("User", user.toString());

            view.showUserName(user);
            view.stopProgressIndication();
        }, throwable -> {
            view.showError(throwable);
            view.stopProgressIndication();
        });
    }
}
