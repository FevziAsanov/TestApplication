package kerchek.app.mesele.sual.testapplication.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fevzi_asanov project TestApplication on 22.01.18 .
 */

public class Post implements Serializable {
    @SerializedName("userId")
    private long uId;
    private long id;
    private String title;
    private String body;

    public Post() {
    }

    public long getUserId() {
        return uId;
    }

    public void setUserId(long userId) {
        this.uId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + uId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
