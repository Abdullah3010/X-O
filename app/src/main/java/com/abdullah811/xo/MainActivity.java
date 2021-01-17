package com.abdullah811.xo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView player2_selection;
    private EditText player1Name, player2Name;
    private String player_1, player_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player2_selection = findViewById(R.id.player2_selection);
        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
        Spinner player1_choose = findViewById(R.id.player1_choose);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.players_Choices,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1_choose.setAdapter(adapter);
        player1_choose.setOnItemSelectedListener(this);
        player1Name.addTextChangedListener(textWatcher1);
        player2Name.addTextChangedListener(textWatcher2);
    }

    private TextWatcher textWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            player1Name.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.edit_text));
            player1Name.setHint("Player 1 name");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher textWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            player2Name.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.edit_text));
            player2Name.setHint("Player 2 name");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        player_1 = parent.getItemAtPosition(position).toString();

        if (player_1.equals("X")) {
            player2_selection.setText("O");
            player_2 = "O";
        } else {
            player2_selection.setText("X");
            player_2 = "X";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void play(View view) {
        String name1 = player1Name.getText().toString().trim();
        String name2 = player2Name.getText().toString().trim();
        if (name1.equals("")) {
            player1Name.setBackground(ContextCompat.getDrawable(this, R.drawable.empty_input));
            player1Name.setHint("Please enter player 1 name");
        }
        if (name2.equals("")) {
            player2Name.setBackground(ContextCompat.getDrawable(this, R.drawable.empty_input));
            player2Name.setHint("Please enter player 2 name");
        }
        if (!name1.equals("") && !name2.equals("")) {
            Intent intent = new Intent(this, startedGame.class);
            intent.putExtra("PLAYER_1", player_1);
            intent.putExtra("PLAYER_2", player_2);
            intent.putExtra("PLAYER_1_NAME", name1);
            intent.putExtra("PLAYER_2_NAME", name2);
            startActivity(intent);
        }
    }


}