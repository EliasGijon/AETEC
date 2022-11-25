package com.example.gijon_vazquez_elias_u3_a7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Informacion extends AppCompatActivity {
    RelativeLayout Panel;
    TextView etiqueta;
    com.codesgood.views.JustifiedTextView tv;
    String actividad;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Panel = findViewById(R.id.panelxd);
        etiqueta = findViewById(R.id.etiqueta_actividad);
        tv= findViewById(R.id.texto);
        /*
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        */
        String[] a = getResources().getStringArray(R.array.opciones);
        String[] descripcion_ac = getResources().getStringArray(R.array.descripciones_actividades);
        String[] horario_ac = getResources().getStringArray(R.array.horarios_actividades);
        String[] dias_ac = getResources().getStringArray(R.array.dias_actividades);
        String[] video = getResources().getStringArray(R.array.video);


        Bundle Variables = this.getIntent().getExtras();
        if (Variables != null) {
            actividad = Variables.getString("queryxd");
        }

        RelativeLayout.LayoutParams txt = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );

        etiqueta.setText(actividad);

        int marginInDp42 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 42, getResources()
                        .getDisplayMetrics());
        int marginInDp7 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 7, getResources()
                        .getDisplayMetrics());
        int marginInDp25 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 25, getResources()
                        .getDisplayMetrics());
        int marginInDp50 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50, getResources()
                        .getDisplayMetrics());


        tv.setId(80);
        tv.setTextAppearance(R.font.montserrat);
        tv.setLineSpacing(1.0f,1.5f);
        tv.setTextSize(20);
        tv.setTextColor(this.getColor(R.color.TECNM_NEGRO));
        txt.setMargins(marginInDp42, marginInDp7, marginInDp42, marginInDp7);
        txt.addRule(RelativeLayout.BELOW, R.id.etiqueta_actividad);
        tv.setLayoutParams(txt);
        tv.setVisibility(View.VISIBLE);
       // Panel.addView(tv);

        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        RelativeLayout.LayoutParams vid = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        vid.setMargins(marginInDp42, marginInDp25, marginInDp50, marginInDp7);
        vid.addRule(RelativeLayout.BELOW,tv.getId());
        getLifecycle().addObserver(youTubePlayerView);

        String videoId="";
        for (int i = 1; i < a.length; i++) {
            if (actividad.equals(a[i])) {
                tv.setText("Descripción: \n" + descripcion_ac[i]
                        +"\n\nDias: \n" + dias_ac[i]
                        +"\n\nHorarios: \n" + horario_ac[i]);
                videoId = video[i];
                break;
            }
        }

        String finalVideoId = videoId;
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(finalVideoId, 0);
            }
        });

        youTubePlayerView.setLayoutParams(vid);
        Panel.addView(youTubePlayerView);

        //youTubePlayerView.setVisibility(View.VISIBLE);

        //Panel.addView(videoView);
/*
        TextView dias = new TextView(this);
        TextView horario = new TextView(this);

        dias.setTextAppearance(R.font.montserrat);
        dias.setTextSize(20);
        dias.setTextColor(this.getColor(R.color.TECNM_NEGRO));

        horario.setTextAppearance(R.font.montserrat);
        horario.setTextSize(20);
        horario.setTextColor(this.getColor(R.color.TECNM_NEGRO));

        RelativeLayout.LayoutParams Day,Hrs;
        Day=txt;
        Hrs=txt;

        Day.setMargins(marginInDp42, marginInDp7, marginInDp50, marginInDp7);
        Hrs.setMargins(marginInDp42, marginInDp50, marginInDp42, marginInDp7);

        Day.addRule(RelativeLayout.BELOW,R.id.etiqueta_actividad+1);
        Hrs.addRule(RelativeLayout.BELOW,dias.getId());

        dias.setLayoutParams(Day);
        horario.setLayoutParams(Hrs);

        Panel.addView(dias);
        Panel.addView(horario);
*/
    }
}