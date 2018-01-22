package kerchek.app.mesele.sual.testapplication.ui.posts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import kerchek.app.mesele.sual.testapplication.App;
import kerchek.app.mesele.sual.testapplication.R;
import kerchek.app.mesele.sual.testapplication.data.models.Post;
import kerchek.app.mesele.sual.testapplication.ui.posts.adapters.PostListAdapter;
import kerchek.app.mesele.sual.testapplication.utils.Utils;

public class PostListActivity extends AppCompatActivity implements PostListContract.View, PostListAdapter.Callback {

    @BindView(R.id.posts_list)
    RecyclerView postsList;

    private Unbinder unbinder;
    private PostListContract.Presenter presenter;
    private CompositeDisposable compositeDisposable;
    private PostListAdapter postListAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        presenter = new PostListPresenter(this);
        App.getNetComponent().inject(this);
        progressDialog = Utils.createProgressDialog(this);

        compositeDisposable = new CompositeDisposable();
        initPostList();
    }

    private void initPostList() {
        postListAdapter = new PostListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        postsList.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        postsList.addItemDecoration(dividerItemDecoration);
        postsList.setAdapter(postListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        compositeDisposable.add(presenter.getPosts());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void setPresenter(PostListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void indicateProgress(int messageResourceId) {
        progressDialog.show();
    }

    @Override
    public void stopProgressIndication() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(int messageTitleResource, int messageBodyResource) {

    }

    @Override
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void showPostList(List<Post> postList) {
        postListAdapter.setPosts(postList);
    }

    @Override
    public void showPost(Post post) {
    }
}
