package com.rajan.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rajan.myapplication.adapters.ExpenseAdapter;
import com.rajan.myapplication.models.Expense;
import com.rajan.myapplication.utils.ExpenseRepository;
import java.util.List;

/**
 * ExpenseListActivity - Displays all expenses in a RecyclerView
 * Implements swipe-to-delete with undo functionality
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public class ExpenseListActivity extends AppCompatActivity implements ExpenseAdapter.OnExpenseClickListener {

    private RecyclerView rvExpenses;
    private LinearLayout layoutEmptyState;
    private TextView tvExpenseCount;
    private FloatingActionButton fabAddExpense;
    private MaterialButton btnBack;

    private ExpenseRepository repository;
    private ExpenseAdapter adapter;
    private List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        // Initialize repository
        repository = ExpenseRepository.getInstance(this);

        // Initialize views
        initializeViews();

        // Set up RecyclerView
        setupRecyclerView();

        // Set up listeners
        setupListeners();

        // Load expenses
        loadExpenses();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadExpenses();
    }

    /**
     * Initialize all view components
     */
    private void initializeViews() {
        rvExpenses = findViewById(R.id.rvExpenses);
        layoutEmptyState = findViewById(R.id.layoutEmptyState);
        tvExpenseCount = findViewById(R.id.tvExpenseCount);
        fabAddExpense = findViewById(R.id.fabAddExpense);
        btnBack = findViewById(R.id.btnBack);
    }

    /**
     * Set up RecyclerView with adapter and layout manager
     */
    private void setupRecyclerView() {
        expenses = repository.getAllExpenses();
        adapter = new ExpenseAdapter(expenses, this);
        
        rvExpenses.setLayoutManager(new LinearLayoutManager(this));
        rvExpenses.setAdapter(adapter);

        // Add swipe-to-delete functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, 
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, 
                                RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Expense deletedExpense = adapter.getExpenseAt(position);
                
                // Remove from adapter
                adapter.removeExpense(position);
                
                // Delete from repository
                repository.deleteExpense(deletedExpense.getId());
                
                // Update count
                updateExpenseCount();
                checkEmptyState();
                
                // Show undo snackbar
                Snackbar.make(rvExpenses, "Expense deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", v -> {
                        repository.addExpense(deletedExpense);
                        loadExpenses();
                    })
                    .show();
            }
        });
        
        itemTouchHelper.attachToRecyclerView(rvExpenses);
    }

    /**
     * Set up button click listeners
     */
    private void setupListeners() {
        fabAddExpense.setOnClickListener(v -> {
            Intent intent = new Intent(ExpenseListActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> finish());
    }

    /**
     * Load expenses from repository
     */
    private void loadExpenses() {
        expenses = repository.getAllExpenses();
        adapter.updateExpenses(expenses);
        updateExpenseCount();
        checkEmptyState();
    }

    /**
     * Update expense count display
     */
    private void updateExpenseCount() {
        int count = expenses.size();
        String countText = count == 1 ? "1 expense" : count + " expenses";
        tvExpenseCount.setText(countText);
    }

    /**
     * Show/hide empty state based on expense list
     */
    private void checkEmptyState() {
        if (expenses.isEmpty()) {
            rvExpenses.setVisibility(View.GONE);
            layoutEmptyState.setVisibility(View.VISIBLE);
        } else {
            rvExpenses.setVisibility(View.VISIBLE);
            layoutEmptyState.setVisibility(View.GONE);
        }
    }

    @Override
    public void onExpenseClick(Expense expense) {
        // Show expense details in a dialog
        new AlertDialog.Builder(this)
            .setTitle(expense.getDescription())
            .setMessage(
                "Category: " + expense.getCategory().getDisplayName() + "\n" +
                "Amount: " + expense.getFormattedAmount() + "\n" +
                "Date: " + expense.getDate()
            )
            .setPositiveButton("OK", null)
            .show();
    }

    @Override
    public void onExpenseLongClick(Expense expense) {
        // Show delete confirmation dialog
        new AlertDialog.Builder(this)
            .setTitle("Delete Expense")
            .setMessage("Are you sure you want to delete this expense?")
            .setPositiveButton("Delete", (dialog, which) -> {
                repository.deleteExpense(expense.getId());
                loadExpenses();
                Toast.makeText(this, "Expense deleted", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
}
