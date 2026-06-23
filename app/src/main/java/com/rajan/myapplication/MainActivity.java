package com.rajan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.rajan.myapplication.utils.ExpenseRepository;
import java.util.Locale;

/**
 * MainActivity - Dashboard of the Expense Tracker app
 * Displays budget summary, quick statistics, and navigation buttons
 * 
 * @author Rajan Bam
 * @studentNumber 002295635
 * @university LUT University, Lappeenranta, Finland
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvBudget, tvSpent, tvRemaining, tvTotalExpenses, tvTransactionCount;
    private ProgressBar progressBudget;
    private MaterialButton btnAddExpense, btnViewExpenses, btnStatistics, btnSettings, btnAbout, btnSearch, btnExport;
    private MaterialButton btnQuickCoffee, btnQuickLunch, btnQuickTransport;
    private ExpenseRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize repository
        repository = ExpenseRepository.getInstance(this);

        // Initialize views
        initializeViews();

        // Set up button listeners
        setupButtonListeners();

        // Update dashboard data
        updateDashboard();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when returning to this activity
        updateDashboard();
    }

    /**
     * Initialize all view components
     */
    private void initializeViews() {
        tvBudget = findViewById(R.id.tvBudget);
        tvSpent = findViewById(R.id.tvSpent);
        tvRemaining = findViewById(R.id.tvRemaining);
        tvTotalExpenses = findViewById(R.id.tvTotalExpenses);
        tvTransactionCount = findViewById(R.id.tvTransactionCount);
        progressBudget = findViewById(R.id.progressBudget);

        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnViewExpenses = findViewById(R.id.btnViewExpenses);
        btnStatistics = findViewById(R.id.btnStatistics);
        btnSettings = findViewById(R.id.btnSettings);
        btnAbout = findViewById(R.id.btnAbout);
        btnSearch = findViewById(R.id.btnSearch);
        btnExport = findViewById(R.id.btnExport);
    }

    /**
     * Set up click listeners for all buttons
     */
    private void setupButtonListeners() {
        btnAddExpense.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });

        btnViewExpenses.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExpenseListActivity.class);
            startActivity(intent);
        });

        btnStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BudgetSettingsActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        btnExport.setOnClickListener(v -> exportData());
    }

    /**
     * Export expense data
     */
    private void exportData() {
        String csvData = repository.exportToCSV();
        
        // Create share intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ExpenseTracker Data Export");
        shareIntent.putExtra(Intent.EXTRA_TEXT, csvData);
        
        startActivity(Intent.createChooser(shareIntent, "Export Expenses via..."));
    }

    /**
     * Update dashboard with latest data
     */
    private void updateDashboard() {
        double monthlyBudget = repository.getMonthlyBudget();
        double monthlyExpenses = repository.getMonthlyExpenses();
        double remaining = monthlyBudget - monthlyExpenses;
        int expenseCount = repository.getAllExpenses().size();

        // Update budget display
        tvBudget.setText(formatCurrency(monthlyBudget));
        tvSpent.setText(String.format("Spent: %s", formatCurrency(monthlyExpenses)));
        tvRemaining.setText(String.format("Remaining: %s", formatCurrency(remaining)));

        // Update progress bar
        int percentage = repository.getBudgetUsagePercentage();
        progressBudget.setProgress(percentage);

        // Update total expenses
        tvTotalExpenses.setText(formatCurrency(monthlyExpenses));

        // Update transaction count
        tvTransactionCount.setText(String.valueOf(expenseCount));
    }

    /**
     * Format double amount to currency string
     */
    private String formatCurrency(double amount) {
        return String.format(Locale.getDefault(), "€%.2f", amount);
    }
}