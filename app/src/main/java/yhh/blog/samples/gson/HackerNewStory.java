package yhh.blog.samples.gson;

import com.google.gson.annotations.SerializedName;

public class HackerNewStory {

    @SerializedName(value = "by", alternate = {"by_id"})
    private String by;

    @SerializedName("descendants")
    private int descendants;

    @SerializedName("id")
    private int id;

    @SerializedName("kids")
    private int[] kids;

    @SerializedName("score")
    private int score;

    @SerializedName("time")
    private long time;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getKids() {
        return kids;
    }

    public void setKids(int[] kids) {
        this.kids = kids;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
