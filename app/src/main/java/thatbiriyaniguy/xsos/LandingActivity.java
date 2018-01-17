package thatbiriyaniguy.xsos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LandingActivity extends AppCompatActivity {

    private ImageView new_game;
    private ImageView quit_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        new_game = findViewById(R.id.new_game);
        quit_app = findViewById(R.id.quit_game);

        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newGame();
            }
        });

        quit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private void newGame(){

        Intent intent = new Intent(LandingActivity.this,MainActivity.class);
        startActivity(intent);

    }
}
