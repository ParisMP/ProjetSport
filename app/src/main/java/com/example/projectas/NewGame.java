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

public class NewGame extends AppCompatActivity {

    DatabaseHelper myDb; //creating instance
    EditText editName, editSurname, editWinner, editTextId, editS1J1,editS2J1, editS3J1, editS1J2, editS2J2, editS3J2;
    Button button_add;
    Button button_viewAll;
    Button button_update;
    //Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        myDb = new DatabaseHelper(this); //this constructor creates database and table inside database

        editName = (EditText)findViewById(R.id.editText_name);// findViewById finds the view from the layout resource file associated with the activity
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editWinner = (EditText)findViewById(R.id.editText_Winner);
        editTextId = (EditText)findViewById(R.id.editText_id);
        editS1J1 = (EditText)findViewById(R.id.editText_S1J1);
        editS2J1 = (EditText)findViewById(R.id.editText_S2J1);
        editS3J1 = (EditText)findViewById(R.id.editText_S3J1);
        editS1J2 = (EditText)findViewById(R.id.editText_S1J2);
        editS2J2 = (EditText)findViewById(R.id.editText_S2J2);
        editS3J2 = (EditText)findViewById(R.id.editText_S3J2);

        button_add = (Button)findViewById(R.id.button_add);
        button_viewAll = (Button)findViewById(R.id.button_viewAll);
        button_update = (Button)findViewById(R.id.button_update);
        //btnDelete = (Button)findViewById(R.id.button_delete);

        //functions called
        AddData();
        viewAll();
        UpdateData();
        //DeleteData();
        configureBackButton();
    }

    //add data to db
    public void AddData(){
        button_add.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isInserted = myDb.insertData(editName.getText().toString(),editSurname.getText().toString(),editWinner.getText().toString(),
                                editS1J1.getText().toString(),editS2J1.getText().toString(),editS3J1.getText().toString(),
                                editS1J2.getText().toString(),editS2J2.getText().toString(),editS3J2.getText().toString()); //nine arguments corresponding to the nine data types
                        if(isInserted == true) //if data is inserted
                            Toast.makeText(NewGame.this,"Data has been inserted",Toast.LENGTH_LONG).show();
                        else //if no data is inserted
                            Toast.makeText(NewGame.this,"Data hasn't been inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //defining what happens when we click the viewAll button
    public void viewAll(){
        button_viewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res = myDb.getAllData();
                        //if no data is available
                        if(res.getCount()==0){
                            showMessage("Error", "no data found");
                            return;
                        }
                        //to display data we create an instance of StringBuffer
                        StringBuffer buffer = new StringBuffer();
                        //to get data one at a time, moveToNext moves Cursor to next result
                        while(res.moveToNext()){
                            buffer.append("Id : "+ res.getString(0)+"\n");//stores data in buffer, \n changes le line to next
                            buffer.append("Name : "+ res.getString(1)+"\n");
                            buffer.append("Surname : "+ res.getString(2)+"\n");
                            buffer.append("Set Scores Player 1  : "+ res.getString(4)+ " " +res.getString(5)+ " "+res.getString(6)+"\n");
                            buffer.append("Set Scores Player 2  : "+ res.getString(7)+ " " + res.getString(8)+ " " + res.getString(9)+"\n\n");
                            buffer.append("Winner : "+ res.getString(3)+"\n\n");
                        }
                        //if we get all the data
                        showMessage("Data", buffer.toString()); //displays data and transforms buffer to String
                    }
                }
        );
    }

    //delete function
    /*public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        // boolean isUpdate = myDb.updateData(editTextId.getText().toString(), editName.getText().toString(), editSurname.getText().toString(),editMarks.getText().toString());
                        //creates instance of Integer to call DeleteData
                        //if some rows are deleted
                        if(deletedRows > 0)
                            Toast.makeText(NewGame.this,"Data has been deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(NewGame.this,"Data has not been updated",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }*/

    //Update function
    public void UpdateData(){
        button_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editWinner.getText().toString(),
                                editS1J1.getText().toString(),editS2J1.getText().toString(),editS3J1.getText().toString(),
                                editS1J2.getText().toString(),editS2J2.getText().toString(),editS3J2.getText().toString());
                        //if data has been update then true
                        if (isUpdate == true)
                            Toast.makeText(NewGame.this,"Data Updated",Toast.LENGTH_LONG).show();
                            //if data hasn't been updated
                        else
                            Toast.makeText(NewGame.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //creating instance of AlertDialog.Builder
        builder.setCancelable(true);//so we can cancel builder after it is used
        builder.setTitle(title); //display
        builder.setMessage(Message); //display
        builder.show(); //to display dialog
    }

    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backButton); //new Button object Button backButton, find the id of button
        backButton.setOnClickListener(new View.OnClickListener() {
            //defining the actions that will take place once the button is clicked
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewGame.this, MainActivity.class)); //takes us to NewGame
            }
        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handles the item clicks here from the action bar
        int id = item.getItemId();

        if (id == R.id.action_settings){

        }

    }*/
}