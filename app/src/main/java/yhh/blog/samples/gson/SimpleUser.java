package yhh.blog.samples.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;

class SimpleUser {

    @Expose
    @Since(5)
    @SerializedName("id")
    long mId;

    @Expose(deserialize = false)
    @SerializedName("name")
    String mName;

    @SerializedName("story")
    SimpleStory mSimpleStory;

    static class SimpleStory {
        @SerializedName("title")
        String mTitle;

        @SerializedName("id")
        int mId;
    }
}
