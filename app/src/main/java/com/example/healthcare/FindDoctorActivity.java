package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Move from FindDoctorActivity to HomeActivity
        CardView cardFDBack = findViewById(R.id.cardFDBack);
        cardFDBack.setOnClickListener(v -> {
            startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
        });
        // Move from FindDoctorActivity to DoctorDetailsActivity
        CardView cardFDFamilyPhysicians = findViewById(R.id.cardFDFamilyPhysicians);
        cardFDFamilyPhysicians.setOnClickListener(v -> {
            startActivity(new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class).putExtra("title", "Family Physicians"));
        });

        CardView cardFDCardiologist = findViewById(R.id.cardFDCardiologist);
        cardFDCardiologist.setOnClickListener(v -> {
            startActivity(new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class).putExtra("title", "Cardiologist"));
        });

        CardView cardFDDietician = findViewById(R.id.cardFDDietician);
        cardFDDietician.setOnClickListener(v -> {
            startActivity(new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class).putExtra("title", "Dietician"));
        });

        CardView cardFDDentist = findViewById(R.id.cardFDDentist);
        cardFDDentist.setOnClickListener(v -> {
            startActivity(new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class).putExtra("title", "Dentist"));
        });

        CardView cardFDSurgeon = findViewById(R.id.cardFDSurgeon);
        cardFDSurgeon.setOnClickListener(v -> {
            startActivity(new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class).putExtra("title", "Surgeon"));
        });
    }
}