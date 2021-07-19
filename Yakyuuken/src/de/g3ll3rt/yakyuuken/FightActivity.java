package de.g3ll3rt.yakyuuken;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        GameController cntl = GameController.getInstance();
        boolean safe;
        char result = cntl.getResult();
        if ((result == GameController.RESULT_DRAW)
            || (result == GameController.RESULT_WIN)){
           safe = true;
        } else {
            safe = false;
        }

        setImages(cntl.getPlayerHand(), cntl.getOpponentHand(), safe);
        final Button back = (Button)findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void setImages(char playerHand, char opponentHand, boolean safe){
        Bitmap bmpPlayer;
        Bitmap bmpOpponent;
        Bitmap bmpResult;
        ImageView playerImage = (ImageView) findViewById(R.id.playerChoice);
        ImageView opponentImage = (ImageView) findViewById(R.id.opponentChoice);
        ImageView resultImage   = (ImageView) findViewById(R.id.fightResult);

        if ( playerHand == GameController.HANDTYPE_SCISSORS ){
            bmpPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.scissors);
        } else if (playerHand == GameController.HANDTYPE_ROCK){
            bmpPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.rock);
        } else {
            bmpPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.paper);
        }
        playerImage.setImageBitmap(bmpPlayer);

        if ( opponentHand == GameController.HANDTYPE_SCISSORS ){
            bmpOpponent = BitmapFactory.decodeResource(getResources(), R.drawable.scissors);
        } else if (opponentHand == GameController.HANDTYPE_ROCK){
            bmpOpponent = BitmapFactory.decodeResource(getResources(), R.drawable.rock);
        } else {
            bmpOpponent = BitmapFactory.decodeResource(getResources(), R.drawable.paper);
        }
        opponentImage.setImageBitmap(bmpOpponent);

        if (safe){
            bmpResult = BitmapFactory.decodeResource(getResources(), R.drawable.safe);
        } else {
            bmpResult = BitmapFactory.decodeResource(getResources(), R.drawable.out);
        }
        resultImage.setImageBitmap(bmpResult);
    }
}