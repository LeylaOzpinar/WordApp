import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AddSound implements LineListener {

    boolean isPlaybackCompleted;

    void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(audioStream);
            clip.start();

            while (!isPlaybackCompleted) {
                // Wait for the playback to complete
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            clip.close();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            isPlaybackCompleted = true;
        }
    }

    public void winSound() { 
        String audioFilePath = "win.wav"; // Ses dosyasının yolu
        AddSound player = new AddSound();
        player.play(audioFilePath);
    }

    public void loseSound() {
        String audioFilePath = "lose.wav"; // Ses dosyasının yolu
        AddSound player = new AddSound();
        player.play(audioFilePath);
    }


}

