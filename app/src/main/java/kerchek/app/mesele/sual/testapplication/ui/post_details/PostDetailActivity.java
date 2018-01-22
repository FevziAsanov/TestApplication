package kerchek.app.mesele.sual.testapplication.ui.post_details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import kerchek.app.mesele.sual.testapplication.R;
import kerchek.app.mesele.sual.testapplication.data.models.Comment;
import kerchek.app.mesele.sual.testapplication.data.models.Post;
import kerchek.app.mesele.sual.testapplication.data.models.User;
import kerchek.app.mesele.sual.testapplication.utils.CircleTransform;
import kerchek.app.mesele.sual.testapplication.utils.Constants;
import kerchek.app.mesele.sual.testapplication.utils.Utils;

public class PostDetailActivity extends AppCompatActivity implements PostDetailContract.View {
    public static final String POST_EXTRAS = "post_extra";

    @BindView(R.id.post_body_text_view)
    TextView postBodyTextView;
    @BindView(R.id.user_image_view)
    ImageView userImageView;
    @BindView(R.id.user_name_text_view)
    TextView userNameTextView;
    @BindView(R.id.comment_number_text_view)
    TextView commentNumberTextView;
    private Post post;

    private Unbinder unbinder;
    private CompositeDisposable compositeDisposable;
    private PostDetailContract.Presenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        unbinder = ButterKnife.bind(this);
        presenter = new PostDetailPresenter(this);

        progressDialog = Utils.createProgressDialog(this);

        post = (Post) getIntent().getSerializableExtra(POST_EXTRAS);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(post.getTitle());

        postBodyTextView.setText(post.getBody());

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        compositeDisposable.add(presenter.getComments(post.getId()));
        compositeDisposable.add(presenter.getUser(post.getUserId()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (compositeDisposable != null
                && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void setPresenter(PostDetailContract.Presenter presenter) {
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
        Utils.showAppropriateError(this, throwable);
    }

    @Override
    public void showCommentsNumber(List<Comment> commentList) {
        if (commentList != null && !commentList.isEmpty())
            commentNumberTextView.setText(String.format(Locale.getDefault(), "%d", commentList.size()));
    }

    @Override
    public void showUserName(User user) {
        if (user != null) {
            if (!TextUtils.isEmpty(user.getName()))
                userNameTextView.setText(user.getName());

            if (!TextUtils.isEmpty(user.getEmail()))
                Picasso.with(this)
                        .load(Constants.USER_IMAGE_ENDPOINT + user.getEmail())
                        .placeholder(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
                        .transform(new CircleTransform())
                        .into(userImageView);
        }
    }
}
