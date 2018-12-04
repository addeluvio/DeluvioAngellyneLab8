package com.deluvio.angellyne;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference root;


    private EditText fullName;
    private EditText age;
    private EditText gender;

    private String saveCurrentDate = "";
    private String saveCurrentTime = "";
    private String uniqueId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("Users");
        fullName = findViewById(R.id.fullname);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
//        keylist = new ArrayList<>();
//        init();
    }

    public void saveData(EditText fullName, EditText age, EditText gender) {

        Calendar calendarDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMMM:dd:yyyy");
        saveCurrentDate = currentDate.format(calendarDate.getTime());

        Calendar calendarTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss:SSS");
        saveCurrentTime = currentTime.format(calendarTime.getTime());

        uniqueId = saveCurrentDate.concat(saveCurrentTime);

        String getFullName = fullName.getText().toString().trim();
        String getAge = age.getText().toString().trim();
        String getGender = gender.getText().toString().trim();

        User user = new User(getFullName, getAge, getGender);

        root.child(uniqueId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "saving is Successful!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public void option (View v){
        if (v.getId() == R.id.Save){
            saveData(fullName, age, gender);
        }
    }
}