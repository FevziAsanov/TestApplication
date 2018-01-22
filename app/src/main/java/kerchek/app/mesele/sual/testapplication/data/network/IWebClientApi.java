package kerchek.app.mesele.sual.testapplication.data.network;

import java.util.List;

import io.reactivex.Observable;
import kerchek.app.mesele.sual.testapplication.data.models.Comment;
import kerchek.app.mesele.sual.testapplication.data.models.Post;
import kerchek.app.mesele.sual.testapplication.data.models.User;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWebClientApi {

    @GET("posts")
    Observable<Response<List<Post>>> getPosts();

    @GET("comments")
    Observable<Response<List<Comment>>> getComments(@Query("postId") long postId);

    @GET("comments")
    Observable<Response<List<User>>> getUser(@Query("id") long userId);
}

