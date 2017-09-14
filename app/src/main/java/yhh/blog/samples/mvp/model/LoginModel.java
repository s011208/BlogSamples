package yhh.blog.samples.mvp.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import yhh.blog.samples.mvp.contract.LoginContract;

public class LoginModel implements LoginContract.Model {
    @Override
    public void checkUserValid(final String user, final String password, final Handler.Callback callback) {
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... strings) {
                try {
                    Thread.sleep((int) (Math.random() * 10000) % 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "s011208".equals(user) && "0000".equals(password);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                Message message = Message.obtain();
                message.what = result ? 0 : 1;
                callback.handleMessage(message);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
