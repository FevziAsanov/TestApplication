package kerchek.app.mesele.sual.testapplication.ui.post_details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import kerchek.app.mesele.sual.testapplication.R;
import kerchek.app.mesele.sual.testapplication.data.models.Post;

public class PostDetailActivity extends AppCompatActivity {
    public static final String POST_EXTRAS = "post_extra";

    @BindView(R.id.post_body_text_view)
    TextView postBodyTextView;

    private Unbinder unbinder;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        unbinder = ButterKnife.bind(this);

        Post post = (Post) getIntent().getSerializableExtra(POST_EXTRAS);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(post.getTitle());

        postBodyTextView.setText(post.getBody());

        compositeDisposable = new CompositeDisposable();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

}
