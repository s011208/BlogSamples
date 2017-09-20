package yhh.blog.samples.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class SimpleUserWithResponse extends SimpleUser {

    @SerializedName("response")
    List<Response> mResponse;

    static class Response {

        @SerializedName("name")
        String mName;

        @SerializedName("id")
        int mId;
    }
}
