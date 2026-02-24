package umg.edu.gt.queuehandler;

import umg.edu.gt.data_structure.queue.Queue;

public class SpotifyQueuePlayer {

    private final Queue<Song> highPriority = new Queue<>();
    private final Queue<Song> normalPriority = new Queue<>();

    private final Queue<Song> upNext = new Queue<>();

    private final HistoryStack history = new HistoryStack();
    private int totalSongsPlayed = 0;
    private int totalSecondsPlayed = 0;

    private final DuplicateGuard duplicateGuard = new DuplicateGuard();

    public void add(Song song) {
        if (duplicateGuard.isDuplicate(song)) {
            log("[LOG] Duplicate ignored: " + song);
            return;
        }
        duplicateGuard.remember(song);

        if (song.getPriority() == 1) {
            highPriority.enqueue(song);
        } else {
            normalPriority.enqueue(song);
        }
        log("[LOG] Added to queue (P" + song.getPriority() + "): " + song);
    }

    public void addNext(Song song) {
        if (duplicateGuard.isDuplicate(song)) {
            log("[LOG] Duplicate ignored: " + song);
            return;
        }
        duplicateGuard.remember(song);
        upNext.enqueue(song);
        log("[LOG] Added NEXT: " + song);
    }

    public void playAll() {
        log("[LOG] Starting playlist...");

        while (hasNext()) {
            Song nextSong = nextSong();
            playSong(nextSong);
        }

        log("[LOG] Playlist finished.");
        log("[LOG] Total songs played: " + totalSongsPlayed);
        log("[LOG] Total time played: " + totalSecondsPlayed + "s");
        log("[LOG] Last played: " + history.peekLastPlayed());
    }

    private boolean hasNext() {
        return !upNext.isEmpty() || !highPriority.isEmpty() || !normalPriority.isEmpty();
    }

    private Song nextSong() {
        if (!upNext.isEmpty()) return upNext.dequeue();
        if (!highPriority.isEmpty()) return highPriority.dequeue();
        return normalPriority.dequeue();
    }

    private void playSong(Song song) {
        String label = song.getArtist() + " - " + song.getTitle();
        log("[LOG] Now playing: " + label + " (" + song.getDurationSeconds() + "s)");

        int duration = song.getDurationSeconds();
        for (int sec = 1; sec <= duration; sec++) {
            totalSecondsPlayed++;
            String bar = progressBar(sec, duration, 10);
            log("[LOG] Playing: " + label + " | " + bar + " " + sec + "s / " + duration + "s");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log("[LOG] Playback interrupted.");
                return;
            }
        }

        log("[LOG] Finished: " + label);
        totalSongsPlayed++;
        history.push(song);
    }

    private static String progressBar(int current, int total, int width) {
        if (total <= 0) return "[]";
        int filled = (int) Math.round(((double) current / (double) total) * width);
        if (filled < 0) filled = 0;
        if (filled > width) filled = width;

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < width; i++) {
            sb.append(i < filled ? '#' : '-');
        }
        sb.append(']');
        return sb.toString();
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}
