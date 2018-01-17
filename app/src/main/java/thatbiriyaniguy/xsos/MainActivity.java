package thatbiriyaniguy.xsos;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //<----vars for widgets----->
    private TextView winner;
    private ImageView reset;
    private ImageView exit;

    //<----vars for game logic---->
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean isGameActive = true;


    /*<----Game Logic---->
      <----Checks if a position is already tapped---->
      <----Checks and assigns turns---->
     */
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && isGameActive) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            //<----Checking if someone has a won---->
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    isGameActive = false;

                    winner = findViewById(R.id.winner);
                    if (gameState[winningPosition[0]] == 0) {
                        winner.setTextColor(getResources().getColor(R.color.red));
                        winner.setText(R.string.xWon);
                    } else {
                        winner.setTextColor(getResources().getColor(R.color.yellow));
                        winner.setText(R.string.oWon);
                    }
                }
            }
        } else {
            boolean gameIsOver = true;
            for (int counterState : gameState) {
                if (counterState == 2)
                    gameIsOver = false;
            }
            if (gameIsOver) {
                Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show();
            }
        }
    }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            //<----Handling Click Events---->
            exit = findViewById(R.id.exit_game);
            exit.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    finishAffinity();
                    System.exit(0);
                }
            });
            reset = findViewById(R.id.reset_game);
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }
}



