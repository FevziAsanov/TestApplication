package kerchek.app.mesele.sual.testapplication.data.network;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kerchek.app.mesele.sual.testapplication.data.models.Comment;
import kerchek.app.mesele.sual.testapplication.data.models.Post;
import kerchek.app.mesele.sual.testapplication.data.models.User;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebClient implements IWebClient {

    private IWebClientApi api;

    public WebClient(Retrofit retrofit) {
        api = retrofit.create(IWebClientApi.class);
    }

    @Override
    public Disposable getPosts(Consumer<Response<List<Post>>> onSuccess,
                               Consumer<Throwable> onError) {
        return api.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
    }

    @Override
    public Disposable getComments(long postId,
                                  Consumer<Response<List<Comment>>> onSuccess,
                                  Consumer<Throwable> onError) {
        return api.getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
    }

    @Override
    public Disposable getUser(long userId,
                              Consumer<Response<List<User>>> onSuccess,
                              Consumer<Throwable> onError) {
        return api.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError);
    }
}