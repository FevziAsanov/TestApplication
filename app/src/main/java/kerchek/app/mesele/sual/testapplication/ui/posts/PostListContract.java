package kerchek.app.mesele.sual.testapplication.ui.posts;

import java.util.List;

import io.reactivex.disposables.Disposable;
import kerchek.app.mesele.sual.testapplication.data.models.Post;
import kerchek.app.mesele.sual.testapplication.ui.base.MVPPresenter;
import kerchek.app.mesele.sual.testapplication.ui.base.MVPView;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public interface PostListContract {
    interface View extends MVPView<Presenter> {
        void showPostList(List<Post> postList);
    }

    interface Presenter extends MVPPresenter {
        Disposable getPosts();
    }
}
