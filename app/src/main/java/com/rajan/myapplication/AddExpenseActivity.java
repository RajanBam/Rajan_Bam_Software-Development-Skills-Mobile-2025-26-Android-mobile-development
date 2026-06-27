package com.rajan.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.rajan.myapplication.models.Category;
import com.rajan.myapplication.models.Expense;
import com.rajan.myapplication.utils.ExpenseRepository;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * AddExpenseActivity - Form for adding new expenses
 * Includes input validation and date picker
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public class AddExpenseActivity extends AppCompatActivity {

    private EditText etAmount, etDescription;
    private Spinner spinnerCategory;
    private TextView tvSelectedDate, tvSplitCount, tvPerPerson;
    private MaterialButton btnSelectDate, btnSave, btnCancel, btnPlus, btnMinus;
    
    private ExpenseRepository repository;
    private Calendar selectedCalendar;
    private SimpleDateFormat dateFormat;
    private int splitCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Initialize repository and date
        repository = ExpenseRepository.getInstance(this);
        selectedCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        // Initialize views
        initializeViews();

        // Set up category spinner
        setupCategorySpinner();

        // Set up listeners
        setupListeners();

        // Display current date
        updateDateDisplay();
    }

    /**
     * Initialize all view components
     */
    private void initializeViews() {
        etAmount = findViewById(R.id.etAmount);
        etDescription = findViewById(R.id.etDescription);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        tvSplitCount = findViewById(R.id.tvSplitCount);
        tvPerPerson = findViewById(R.id.tvPerPerson);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
    }

    /**
     * Set up category spinner with all categories
     */
    private void setupCategorySpinner() {
        Category[] categories = Category.values();
        String[] categoryNames = new String[categories.length];
        
        for (int i = 0; i < categories.length; i++) {
            categoryNames[i] = categories[i].getDisplayName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            categoryNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    /**
     * Set up button click listeners
     */
    private void setupListeners() {
        btnSelectDate.setOnClickListener(v -> showDatePicker());
        btnSave.setOnClickListener(v -> saveExpense());
        btnCancel.setOnClickListener(v -> finish());
        
        // Split bill calculator
        btnPlus.setOnClickListener(v -> {
            splitCount++;
            updateSplitCalculation();
        });
        
        btnMinus.setOnClickListener(v -> {
            if (splitCount > 1) {
                splitCount--;
                updateSplitCalculation();
            }
        });
        
        etAmount.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSplitCalculation();
            }
            
            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }
    
    /**
     * Update split bill calculation
     */
    private void updateSplitCalculation() {
        tvSplitCount.setText(String.valueOf(splitCount));
        
        String amountStr = etAmount.getText().toString().trim();
        if (!amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                double perPerson = amount / splitCount;
                tvPerPerson.setText(String.format(Locale.getDefault(), "€%.2f/person", perPerson));
            } catch (NumberFormatException e) {
                tvPerPerson.setText("€0.00/person");
            }
        } else {
            tvPerPerson.setText("€0.00/person");
        }
    }

    /**
     * Show date picker dialog
     */
    private void showDatePicker() {
        int year = selectedCalendar.get(Calendar.YEAR);
        int month = selectedCalendar.get(Calendar.MONTH);
        int day = selectedCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            (view, selectedYear, selectedMonth, selectedDay) -> {
                selectedCalendar.set(Calendar.YEAR, selectedYear);
                selectedCalendar.set(Calendar.MONTH, selectedMonth);
                selectedCalendar.set(Calendar.DAY_OF_MONTH, selectedDay);
                updateDateDisplay();
            },
            year, month, day
        );

        // Set max date to today (can't add future expenses)
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    /**
     * Update date display text
     */
    private void updateDateDisplay() {
        String formattedDate = dateFormat.format(selectedCalendar.getTime());
        tvSelectedDate.setText(formattedDate);
    }

    /**
     * Validate and save expense
     */
    private void saveExpense() {
        // Get input values
        String amountStr = etAmount.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        int categoryPosition = spinnerCategory.getSelectedItemPosition();

        // Validate inputs
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
            etAmount.requestFocus();
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                Toast.makeText(this, "Amount must be greater than zero", Toast.LENGTH_SHORT).show();
                etAmount.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            etAmount.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            Toast.makeText(this, "Please enter description", Toast.LENGTH_SHORT).show();
            etDescription.requestFocus();
            return;
        }

        // Create and save expense
        Category category = Category.values()[categoryPosition];
        
        // Set time to 12:00 PM to avoid timezone issues
        selectedCalendar.set(Calendar.HOUR_OF_DAY, 12);
        selectedCalendar.set(Calendar.MINUTE, 0);
        selectedCalendar.set(Calendar.SECOND, 0);
        selectedCalendar.set(Calendar.MILLISECOND, 0);
        
        long timestamp = selectedCalendar.getTimeInMillis();

        Expense expense = new Expense(amount, description, category, timestamp);
        repository.addExpense(expense);

        // Show success message and finish
        Toast.makeText(this, "Expense added successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
