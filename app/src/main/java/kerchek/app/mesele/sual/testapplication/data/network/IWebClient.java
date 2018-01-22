package kerchek.app.mesele.sual.testapplication.data.network;


import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kerchek.app.mesele.sual.testapplication.data.models.Comment;
import kerchek.app.mesele.sual.testapplication.data.models.Post;
import kerchek.app.mesele.sual.testapplication.data.models.User;
import retrofit2.Response;

public interface IWebClient {

    //Authentication
    Disposable getPosts(Consumer<Response<List<Post>>> onSuccess, Consumer<Throwable> onError);

    Disposable getComments(long postId, Consumer<Response<List<Comment>>> onSuccess, Consumer<Throwable> onError);

    Disposable getUser(long userId, Consumer<Response<List<User>>> onSuccess, Consumer<Throwable> onError);
}