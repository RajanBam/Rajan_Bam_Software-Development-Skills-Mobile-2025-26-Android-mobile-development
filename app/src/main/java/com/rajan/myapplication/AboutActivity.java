package com.rajan.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

/**
 * AboutActivity - Displays developer and app information
 * 
 * @author Rajan Bam
 * @studentNumber 002295635
 * @university LUT University, Lappeenranta, Finland
 * @version 1.0
 */
public class AboutActivity extends AppCompatActivity {

    private MaterialButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}
