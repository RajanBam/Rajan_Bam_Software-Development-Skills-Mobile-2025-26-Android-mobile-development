package com.rajan.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.rajan.myapplication.adapters.ExpenseAdapter;
import com.rajan.myapplication.models.Category;
import com.rajan.myapplication.models.Expense;
import com.rajan.myapplication.utils.ExpenseRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchActivity - Search and filter expenses
 */
public class SearchActivity extends AppCompatActivity implements ExpenseAdapter.OnExpenseClickListener {

    private EditText etSearch;
    private Spinner spinnerCategoryFilter;
    private RecyclerView rvSearchResults;
    private MaterialButton btnBack;
    
    private ExpenseRepository repository;
    private ExpenseAdapter adapter;
    private List<Expense> allExpenses;
    private List<Expense> filteredExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        repository = ExpenseRepository.getInstance(this);
        allExpenses = repository.getAllExpenses();
        filteredExpenses = new ArrayList<>(allExpenses);

        initializeViews();
        setupCategoryFilter();
        setupSearchListener();
        setupRecyclerView();
    }

    private void initializeViews() {
        etSearch = findViewById(R.id.etSearch);
        spinnerCategoryFilter = findViewById(R.id.spinnerCategoryFilter);
        rvSearchResults = findViewById(R.id.rvSearchResults);
        btnBack = findViewById(R.id.btnBack);
        
        btnBack.setOnClickListener(v -> finish());
    }

    private void setupCategoryFilter() {
        List<String> categories = new ArrayList<>();
        categories.add("All Categories");
        for (Category cat : Category.values()) {
            categories.add(cat.getDisplayName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryFilter.setAdapter(adapter);

        spinnerCategoryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterExpenses();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSearchListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterExpenses();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupRecyclerView() {
        adapter = new ExpenseAdapter(filteredExpenses, this);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
        rvSearchResults.setAdapter(adapter);
    }

    private void filterExpenses() {
        String searchText = etSearch.getText().toString().toLowerCase();
        int categoryPosition = spinnerCategoryFilter.getSelectedItemPosition();

        filteredExpenses.clear();

        for (Expense expense : allExpenses) {
            boolean matchesSearch = expense.getDescription().toLowerCase().contains(searchText);
            boolean matchesCategory = categoryPosition == 0 || 
                expense.getCategory() == Category.values()[categoryPosition - 1];

            if (matchesSearch && matchesCategory) {
                filteredExpenses.add(expense);
            }
        }

        adapter.updateExpenses(filteredExpenses);
    }

    @Override
    public void onExpenseClick(Expense expense) {}

    @Override
    public void onExpenseLongClick(Expense expense) {}
}
