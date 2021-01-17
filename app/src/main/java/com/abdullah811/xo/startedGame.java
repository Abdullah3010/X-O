package com.abdullah811.xo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class startedGame extends AppCompatActivity {

    //a is first row ,b i second row ,c is third row and {1,2,3} belong to columns.
    private TextView player1Name, player2Name, a1, a2, a3, b1, b2, b3, c1, c2, c3, score1, score2;
    private player player1, player2;
    private TextView[][] arr;
    private int test = 0, t = 1, x = 0, O_score = 0, X_score = 0;
    private Button newGame, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_game);
        declaration();
        setListener();
    }


    private void checkPlayer() {
        if (player1.getChoose().equals("X")) {
            score1.setText(String.valueOf(X_score));
            score2.setText(String.valueOf(O_score));
        } else {
            score1.setText(String.valueOf(O_score));
            score2.setText(String.valueOf(X_score));
        }
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j].setText("");
            }
        }
        test = 0;
    }

    private void setListener() {
        a1.setOnClickListener(add_X_or_O);
        a2.setOnClickListener(add_X_or_O);
        a3.setOnClickListener(add_X_or_O);
        b1.setOnClickListener(add_X_or_O);
        b2.setOnClickListener(add_X_or_O);
        b3.setOnClickListener(add_X_or_O);
        c1.setOnClickListener(add_X_or_O);
        c2.setOnClickListener(add_X_or_O);
        c3.setOnClickListener(add_X_or_O);
        newGame.setOnClickListener(new_game);
        reset.setOnClickListener(reset_game);
    }

    private int check() {
        for (int i = 0; i < 3; i++) {
            int rx = 0, ro = 0, cx = 0, co = 0;
            for (int j = 0; j < 3; j++) {
                if (arr[i][j].getText().toString().equals("X")) {
                    rx++;
                } else if (arr[i][j].getText().toString().equals("O")) {
                    ro++;
                }

                if (arr[j][i].getText().toString().equals("X")) {
                    cx++;
                } else if (arr[j][i].getText().toString().equals("O")) {
                    co++;
                }
            }
            if (rx == 3 || cx == 3) {

                return 1;

            } else if (ro == 3 || co == 3) {

                return 0;
            }
        }
        if (arr[0][0].getText().toString().equals("X") && arr[1][1].getText().toString().equals("X") && arr[2][2].getText().toString().equals("X") ||
                arr[0][2].getText().toString().equals("X") && arr[1][1].getText().toString().equals("X") && arr[2][0].getText().toString().equals("X")) {

            return 1;

        } else if (arr[0][0].getText().toString().equals("O") && arr[1][1].getText().toString().equals("O") && arr[2][2].getText().toString().equals("O") ||
                arr[0][2].getText().toString().equals("O") && arr[1][1].getText().toString().equals("O") && arr[2][0].getText().toString().equals("O")) {
            return 0;

        }
        return 5;
    }

    private void declaration() {
        //All VARIABLES
        player1Name = findViewById(R.id.player_1_Name);
        player2Name = findViewById(R.id.player_2_Name);
        arr = new TextView[][]{{a1 = findViewById(R.id.a1), a2 = findViewById(R.id.a2), a3 = findViewById(R.id.a3)},
                {b1 = findViewById(R.id.b1), b2 = findViewById(R.id.b2), b3 = findViewById(R.id.b3)},
                {c1 = findViewById(R.id.c1), c2 = findViewById(R.id.c2), c3 = findViewById(R.id.c3)}};
        score1 = findViewById(R.id.score_1);
        score2 = findViewById(R.id.score_2);
        String player1_chooes = getIntent().getStringExtra("PLAYER_1");
        String player2_chooes = getIntent().getStringExtra("PLAYER_2");
        String name1 = getIntent().getStringExtra("PLAYER_1_NAME");
        String name2 = getIntent().getStringExtra("PLAYER_2_NAME");
        player1 = new player(player1_chooes, name1, 0);
        player2 = new player(player2_chooes, name2, 0);
        player1Name.setText(name1);
        player2Name.setText(name2);
        newGame = findViewById(R.id.newGame);
        reset = findViewById(R.id.reset);
    }

    private void playing(View v) {
        TextView textView = (TextView) v;
        if (t % 2 != 0 && textView.getText().equals("")) {
            player1Name.setBackground(null);
            player2Name.setBackground(ContextCompat.getDrawable(startedGame.this, R.drawable.current_player));
            textView.setText(player1.getChoose());
            t++;
            test++;
        } else if (t % 2 == 0 && textView.getText().equals("")) {
            player2Name.setBackground(null);
            player1Name.setBackground(ContextCompat.getDrawable(startedGame.this, R.drawable.current_player));
            textView.setText(player2.getChoose());
            t++;
            test++;
        }
        //1 x is win,0 o is win
        if (check() == 1) {
                checkNameX();
                X_score++;
                checkPlayer();
                reset();
        } else if (check() == 0) {
            checkNameO();
            O_score++;
            checkPlayer();
            reset();
        } else if (test == 9) {
            Toast.makeText(startedGame.this, "Draw", Toast.LENGTH_SHORT).show();
            reset();
        }
    }

    private void checkNameX(){
        if (player1.getChoose().equals("X")){
            Toast.makeText(this, player1.getName() + " is win", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, player2.getName() + " is win", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkNameO(){
        if (player1.getChoose().equals("O")){
            Toast.makeText(this, player1.getName() + " is win", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, player2.getName() + " is win", Toast.LENGTH_SHORT).show();
        }
    }
    public void newGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void resetGame() {
        score1.setText("0");
        score2.setText("0");
        O_score = 0;
        X_score = 0;
        reset();
    }

    //All OnClickListener Methods.>>>
    private View.OnClickListener add_X_or_O = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playing(v);
        }
    };
    private View.OnClickListener reset_game = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetGame();
        }
    };
    private View.OnClickListener new_game = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            newGame();
        }
    };
}