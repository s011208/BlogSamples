package yhh.blog.samples.dagger2;

public class User {

    private String mUserName, mPassword;

    public User(String userName, String password) {
        mUserName = userName;
        mPassword = password;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getPassword() {
        return mPassword;
    }
}
