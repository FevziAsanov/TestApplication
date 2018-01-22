package kerchek.app.mesele.sual.testapplication.ui.posts.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kerchek.app.mesele.sual.testapplication.R;
import kerchek.app.mesele.sual.testapplication.data.models.Post;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private List<Post> posts;
    private Callback callback;

    public PostListAdapter(Callback callback) {
        this.callback = callback;
        this.posts = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_post, parent, false);
        return new PostListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.titlePostView.setText(post.getTitle());
        holder.itemView.setOnClickListener(v -> callback.showPost(post));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_title_text_view)
        TextView titlePostView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titlePostView.getText() + "'";
        }
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public interface Callback {
        void showPost(Post post);
    }
}
