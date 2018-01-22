package kerchek.app.mesele.sual.testapplication.ui.post_details;

import java.util.List;

import io.reactivex.disposables.Disposable;
import kerchek.app.mesele.sual.testapplication.data.models.Comment;
import kerchek.app.mesele.sual.testapplication.data.models.User;
import kerchek.app.mesele.sual.testapplication.ui.base.MVPPresenter;
import kerchek.app.mesele.sual.testapplication.ui.base.MVPView;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public interface PostDetailContract {
    interface View extends MVPView<PostDetailContract.Presenter> {
        void showCommentsNumber(List<Comment> commentList);
        void showUserName(User user);
    }

    interface Presenter extends MVPPresenter {
        Disposable getComments(long postId);
        Disposable getUser(long userId);
    }
}
