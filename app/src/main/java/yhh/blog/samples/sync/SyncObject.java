package yhh.blog.samples.sync;

import java.util.ArrayList;
import java.util.List;

public class SyncObject {

    private static final long SLEEP_TIME = 50;
    private static final int LOOP_TIME = 10;

    private final static List<String> sList = new ArrayList<>();

    public synchronized static void synchronizedAdd(String prefix) {
        for (int i = 0; i < LOOP_TIME; ++i) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sList.add(prefix + i);
        }
    }

    public static void add(String prefix) {
        for (int i = 0; i < LOOP_TIME; ++i) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sList.add(prefix + i);
        }
    }

    public static void clearList() {
        sList.clear();
    }

    public static List<String> getSList() {
        return sList;
    }

    private final List<String> mList = new ArrayList<>();

    public void instanceAdd(String prefix) {
        for (int i = 0; i < LOOP_TIME; ++i) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                mList.add(prefix + i);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void synchronizedInstanceAdd(String prefix) {
        for (int i = 0; i < LOOP_TIME; ++i) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mList.add(prefix + i);
        }
    }

    public List<String> getMList() {
        return mList;
    }
}
