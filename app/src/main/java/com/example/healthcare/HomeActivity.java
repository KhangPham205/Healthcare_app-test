package com.example.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT).show();

        CardView labTest = findViewById(R.id.cardLabTest);

        CardView buyMedicine = findViewById(R.id.cardBuyMedicine);

        //Move to FindDoctorActivity
        CardView findDoctor = findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class)));

        CardView healthDoctor = findViewById(R.id.cardHealthDoctor);

        CardView orderDetails = findViewById(R.id.cardOrderDetails);

        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        });
    }
}