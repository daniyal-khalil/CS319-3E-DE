package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

/**
 * This class inherently uses JavaFX classes. In order to run it
 * programmer must implement it inside a JavaFX application!
 */

public class Audio
{
    // variables
    private ArrayList<String> url;
    private int index;
    private Media media;
    private MediaPlayer player;
    private boolean isMuted;

    // constructors
    public Audio(String url)
    {

        this.url = new ArrayList<String>();
        this.url.add(url);
        media = new Media(new File(this.url.get(0)).toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(0.5);
        isMuted = false;
        index = 0;
    }

    public Audio( Audio audio, String url)
    {

        this.url = new ArrayList<String>();
        this.url.add(url);
        media = new Media(new File(this.url.get(0)).toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(audio.getVol());
        isMuted = false;
        index = 0;
    }


    public Audio(ArrayList<String> url)
    {
        this.url = url;
        if(url.size() > 0)
            media = new Media(new File(this.url.get(0)).toURI().toString());
        else
            media = new Media(new File("src\\sample\\musicAbba.mp3").toURI().toString());
        player = new MediaPlayer(media);
        index = 0;
    }

    public boolean getIsMuted()
    {
        return isMuted;
    }

    public void setIsMuted( boolean mutedOrNot )
    {
        isMuted = mutedOrNot;
    }



    // methods
    public boolean play()
    {
        player.setAutoPlay(true);
        player.play();
        return true;
    }

    public boolean playContinously()
    {
        player.setAutoPlay(true);
        player.setOnEndOfMedia(new Runnable() {
            public void run() {
                player.seek(Duration.ZERO);
            }
        });
        player.play();
        return true;
    }


    public boolean pause()
    {
        player.pause();
        return true;
    }

    public void resetAndStop()
    {
        player.stop();
    }

    public void changeVolume( int volume )
    {
        volume = volume/10;
        double vol = volume * 0.1;
        //if(vol <= 0.9)
        //{
        //vol += 0.1;

        player.setVolume(vol);
        //}
    }

    public double getVol()
    {
        double vol = player.getVolume();
        return vol;

    }
    public void setVol( double vol)
    {
        player.setVolume(vol);

    }
    public void decreaseVol()
    {
        double vol = player.getVolume();
        if(vol >= 0.1)
        {
            vol -= 0.1;
            player.setVolume(vol);
        }
    }

    public void mute()
    {
        player.setMute(true);
    }

    public void unmute()
    {
        player.setMute(false);
    }

    public void nextAudio()
    {
        if(url.size() > index + 1)
        {
            player.stop();
            index++;
            media = new Media((new File(url.get(index))).toURI().toString());
            player = new MediaPlayer(media);
            player.play();
        }
        else
        {
            player.stop();
            index = 0;
            media = new Media((new File(url.get(index))).toURI().toString());
            player = new MediaPlayer(media);
            player.play();
        }
    }

    public ArrayList<String> getUrl()
    {
        return url;
    }

}
