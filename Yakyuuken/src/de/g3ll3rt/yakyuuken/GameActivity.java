package de.g3ll3rt.yakyuuken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final ImageButton rockButton = (ImageButton) findViewById(R.id.btnRock);
        final ImageButton paperButton = (ImageButton) findViewById(R.id.btnPaper);
        final ImageButton scissorButton = (ImageButton) findViewById(R.id.btnScissors);
        final Button restartButton = (Button) findViewById(R.id.btnRestart);

        setMegumiImage(GameController.getInstance().getStage());

        toggleMatchButtons( GameController.getInstance().isGameRunning() );

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fightButtonPressed( GameController.HANDTYPE_ROCK);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fightButtonPressed( GameController.HANDTYPE_PAPER);
            }
        });

        scissorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fightButtonPressed( GameController.HANDTYPE_SCISSORS);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onResume(){
        setMegumiImage(GameController.getInstance().getStage());
        toggleMatchButtons( GameController.getInstance().isGameRunning() );
        super.onResume();
    }

    public void fightButtonPressed( char playerHand ){
        Intent fightIntent = new Intent(GameActivity.this, FightActivity.class);
        GameController.getInstance().placeHand((playerHand));
        startActivity(fightIntent);
    }

    private void setMegumiImage(int stage){
        Bitmap bmpMegumi;
        ImageView megumiView = (ImageView) findViewById(R.id.megumiView);
        if (stage == 2){
            bmpMegumi = BitmapFactory.decodeResource(getResources(), R.drawable.megumi2);
        } else if (stage == 3){
            bmpMegumi = BitmapFactory.decodeResource(getResources(), R.drawable.megumi3);
        } else if (stage == 4){
            bmpMegumi = BitmapFactory.decodeResource(getResources(), R.drawable.megumi4);
        } else {
            bmpMegumi = BitmapFactory.decodeResource(getResources(), R.drawable.megumi1);
        }
        megumiView.setImageBitmap(bmpMegumi);
    }

    private void toggleMatchButtons(boolean buttonsOn ){
        final Button restartButton = (Button) findViewById(R.id.btnRestart);
        if (!buttonsOn){
            final ImageButton rockButton = (ImageButton) findViewById(R.id.btnRock);
            final ImageButton paperButton = (ImageButton) findViewById(R.id.btnPaper);
            final ImageButton scissorButton = (ImageButton) findViewById(R.id.btnScissors);

            rockButton.setVisibility(View.INVISIBLE);
            paperButton.setVisibility(View.INVISIBLE);
            scissorButton.setVisibility(View.INVISIBLE);
            restartButton.setVisibility(View.VISIBLE);
        } else {
            restartButton.setVisibility(View.INVISIBLE);
        }
    }
}