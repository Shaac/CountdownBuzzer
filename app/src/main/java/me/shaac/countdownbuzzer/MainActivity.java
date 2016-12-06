package me.shaac.countdownbuzzer;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) this.findViewById(R.id.button);
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.soho);


        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        final Handler handler = new Handler();
        button.setOnClickListener(new View.OnClickListener(){

            private Runnable lastRunnable = null;
            public void onClick(View v) {
                r.stop();
                if (lastRunnable != null){
                    handler.removeCallbacks(lastRunnable);
                }
                lastRunnable = new Runnable() {
                    @Override
                    public void run() {
                        r.play();
                    }
                };
                handler.postDelayed(lastRunnable, 8000);
            }
        });
    }
}
