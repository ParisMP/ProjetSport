package com.example.projectas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureNextButton();
        configurePreviousGameButton();
    }
    private void configureNextButton() {
        Button newGameButton = (Button) findViewById(R.id.newGameButton); //new Button object Button newGameButton, find the id of button
        newGameButton.setOnClickListener(new View.OnClickListener() { //defining the actions that will take place once the button is clicked
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewGame.class)); //takes us to NewGame
            }
        });
    }

    private void configurePreviousGameButton() {
        Button previousGameButton = (Button) findViewById(R.id.previousGameButton); //new Button object Button previousGameButton, find the id of button
        previousGameButton.setOnClickListener(new View.OnClickListener() { //defining the actions that will take place once the button is clicked
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, History.class)); //takes us to NewGame
            }
        });
    }
}
