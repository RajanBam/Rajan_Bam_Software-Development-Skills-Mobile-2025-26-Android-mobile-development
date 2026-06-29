package com.rajan.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;
import com.rajan.myapplication.models.Category;
import com.rajan.myapplication.utils.ExpenseRepository;
import java.util.Locale;

/**
 * StatisticsActivity - Displays expense statistics and breakdowns
 * Shows category-wise spending analysis
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public class StatisticsActivity extends AppCompatActivity {

    private TextView tvTotalSpent, tvDailyAverage;
    private LinearLayout layoutCategoryStats;
    private MaterialButton btnBack;
    private ExpenseRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        repository = ExpenseRepository.getInstance(this);

        initializeViews();
        setupListeners();
        displayStatistics();
    }

    private void initializeViews() {
        tvTotalSpent = findViewById(R.id.tvTotalSpent);
        tvDailyAverage = findViewById(R.id.tvDailyAverage);
        layoutCategoryStats = findViewById(R.id.layoutCategoryStats);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
    }

    private void displayStatistics() {
        double totalSpent = repository.getMonthlyExpenses();
        
        // Calculate daily average (assuming 30 days)
        double dailyAverage = totalSpent / 30.0;

        tvTotalSpent.setText(formatCurrency(totalSpent));
        tvDailyAverage.setText(formatCurrency(dailyAverage));

        // Display category breakdown
        displayCategoryBreakdown();
    }

    private void displayCategoryBreakdown() {
        layoutCategoryStats.removeAllViews();
        
        double totalSpent = repository.getMonthlyExpenses();
        
        for (Category category : Category.values()) {
            double categoryTotal = repository.getTotalByCategory(category);
            
            if (categoryTotal > 0) {
                View categoryView = createCategoryStatView(category, categoryTotal, totalSpent);
                layoutCategoryStats.addView(categoryView);
            }
        }

        // Show message if no expenses
        if (totalSpent == 0) {
            TextView tvEmpty = new TextView(this);
            tvEmpty.setText("No expenses recorded yet");
            tvEmpty.setTextSize(14);
            tvEmpty.setTextColor(getColor(R.color.text_secondary));
            tvEmpty.setPadding(16, 32, 16, 32);
            layoutCategoryStats.addView(tvEmpty);
        }
    }

    private View createCategoryStatView(Category category, double amount, double total) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_category_stat, layoutCategoryStats, false);
        
        TextView tvCategoryName = view.findViewById(R.id.tvCategoryName);
        TextView tvCategoryAmount = view.findViewById(R.id.tvCategoryAmount);
        TextView tvCategoryPercentage = view.findViewById(R.id.tvCategoryPercentage);
        ProgressBar progressBar = view.findViewById(R.id.progressCategory);
        View colorIndicator = view.findViewById(R.id.viewColorIndicator);

        tvCategoryName.setText(category.getDisplayName());
        tvCategoryAmount.setText(formatCurrency(amount));
        
        int percentage = (int) ((amount / total) * 100);
        tvCategoryPercentage.setText(percentage + "%");
        progressBar.setProgress(percentage);

        // Set category color
        try {
            int color = Color.parseColor(category.getColorCode());
            colorIndicator.setBackgroundColor(color);
            progressBar.getProgressDrawable().setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
        } catch (IllegalArgumentException e) {
            // Use default color
        }

        return view;
    }

    private String formatCurrency(double amount) {
        return String.format(Locale.getDefault(), "€%.2f", amount);
    }
}
