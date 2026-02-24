package umg.edu.gt.queuehandler;
public class Main {

    public static void main(String[] args) {
                SpotifyQueuePlayer player = new SpotifyQueuePlayer();

        
        player.add(new Song("Down Under", "Men At Work", 14, 1));
        player.add(new Song("TAL VEZ", "Malacates ft Rebeca Lane", 11, 1));
        player.add(new Song("Tu Sin Mi", "Dread Mar I", 9, 1));

        player.add(new Song("Welcome To Jamrock", "Damian Marley", 16, 2));
        player.add(new Song("Así Fue", "Dread Mar I", 8, 2));

        player.addNext(new Song("En una nube", "Necrojocker", 13, 2));

        player.add(new Song("Welcome To Jamrock", "Damian Marley", 16, 2));

        player.playAll();
    }
}
