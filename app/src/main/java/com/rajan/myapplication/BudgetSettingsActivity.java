package com.rajan.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.rajan.myapplication.utils.ExpenseRepository;
import java.util.Locale;

/**
 * BudgetSettingsActivity - Manage monthly budget settings
 * Allows users to set and update their budget
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public class BudgetSettingsActivity extends AppCompatActivity {

    private TextView tvCurrentBudget;
    private EditText etNewBudget;
    private MaterialButton btnSaveBudget, btnBack;
    private ExpenseRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_settings);

        repository = ExpenseRepository.getInstance(this);

        initializeViews();
        setupListeners();
        displayCurrentBudget();
    }

    private void initializeViews() {
        tvCurrentBudget = findViewById(R.id.tvCurrentBudget);
        etNewBudget = findViewById(R.id.etNewBudget);
        btnSaveBudget = findViewById(R.id.btnSaveBudget);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupListeners() {
        btnSaveBudget.setOnClickListener(v -> saveBudget());
        btnBack.setOnClickListener(v -> finish());
    }

    private void displayCurrentBudget() {
        double currentBudget = repository.getMonthlyBudget();
        tvCurrentBudget.setText(formatCurrency(currentBudget));
    }

    private void saveBudget() {
        String budgetStr = etNewBudget.getText().toString().trim();

        if (budgetStr.isEmpty()) {
            Toast.makeText(this, "Please enter a budget amount", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double budget = Double.parseDouble(budgetStr);
            
            if (budget <= 0) {
                Toast.makeText(this, "Budget must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save budget
            repository.setMonthlyBudget(budget);
            
            // Update display
            displayCurrentBudget();
            
            // Clear input
            etNewBudget.setText("");
            
            // Show success message
            Toast.makeText(this, "Budget saved successfully!", Toast.LENGTH_SHORT).show();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private String formatCurrency(double amount) {
        return String.format(Locale.getDefault(), "€%.2f", amount);
    }
}
