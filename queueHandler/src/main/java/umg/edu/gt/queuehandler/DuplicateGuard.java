package umg.edu.gt.queuehandler;
public class DuplicateGuard {

    private String[] keys = new String[16];
    private int size = 0;

    public boolean isDuplicate(Song song) {
        String k = song.key();
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(k)) {
                return true;
            }
        }
        return false;
    }

    public void remember(Song song) {
        if (isDuplicate(song)) return;
        if (size == keys.length) {
            String[] next = new String[keys.length * 2];
            for (int i = 0; i < keys.length; i++) {
                next[i] = keys[i];
            }
            keys = next;
        }
        keys[size++] = song.key();
    }
}
