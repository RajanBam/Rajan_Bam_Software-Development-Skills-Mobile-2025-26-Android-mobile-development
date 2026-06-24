package com.rajan.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rajan.myapplication.models.Category;
import com.rajan.myapplication.models.Expense;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Repository class for managing expense data
 * Uses SharedPreferences with Gson for data persistence
 * Singleton pattern ensures single instance throughout the app
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public class ExpenseRepository {
    private static final String PREF_NAME = "ExpenseTrackerPrefs";
    private static final String KEY_EXPENSES = "expenses";
    private static final String KEY_MONTHLY_BUDGET = "monthly_budget";
    
    private static ExpenseRepository instance;
    private SharedPreferences preferences;
    private Gson gson;
    private List<Expense> expenses;

    private ExpenseRepository(Context context) {
        preferences = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        loadExpenses();
    }

    /**
     * Get singleton instance of ExpenseRepository
     */
    public static synchronized ExpenseRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseRepository(context);
        }
        return instance;
    }

    /**
     * Load expenses from SharedPreferences
     */
    private void loadExpenses() {
        String json = preferences.getString(KEY_EXPENSES, null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<Expense>>() {}.getType();
            expenses = gson.fromJson(json, type);
        } else {
            expenses = new ArrayList<>();
            // Add sample data on first launch
            addSampleData();
        }
    }

    /**
     * Add realistic sample expenses for first launch
     */
    private void addSampleData() {
        long now = System.currentTimeMillis();
        long oneDay = 24 * 60 * 60 * 1000L;
        
        // Add expenses from the last 10 days
        expenses.add(new Expense(45.50, "Grocery Shopping", Category.FOOD, now - oneDay));
        expenses.add(new Expense(15.00, "Coffee & Breakfast", Category.FOOD, now - oneDay));
        expenses.add(new Expense(25.00, "Bus Pass", Category.TRANSPORT, now - 2 * oneDay));
        expenses.add(new Expense(120.00, "Monthly Rent Payment", Category.HOUSING, now - 3 * oneDay));
        expenses.add(new Expense(50.00, "Movie Tickets", Category.ENTERTAINMENT, now - 4 * oneDay));
        expenses.add(new Expense(32.00, "Pharmacy", Category.HEALTHCARE, now - 4 * oneDay));
        expenses.add(new Expense(80.00, "New Shoes", Category.SHOPPING, now - 5 * oneDay));
        expenses.add(new Expense(65.00, "Electricity Bill", Category.UTILITIES, now - 6 * oneDay));
        expenses.add(new Expense(95.00, "Textbooks", Category.EDUCATION, now - 7 * oneDay));
        expenses.add(new Expense(28.50, "Restaurant Dinner", Category.FOOD, now - 7 * oneDay));
        expenses.add(new Expense(12.00, "Taxi Ride", Category.TRANSPORT, now - 8 * oneDay));
        expenses.add(new Expense(38.00, "Grocery Shopping", Category.FOOD, now - 9 * oneDay));
        expenses.add(new Expense(18.00, "Netflix Subscription", Category.ENTERTAINMENT, now - 10 * oneDay));
        expenses.add(new Expense(22.00, "Haircut", Category.OTHERS, now - 10 * oneDay));
        
        sortExpensesByDate();
        saveExpenses();
    }

    /**
     * Save expenses to SharedPreferences
     */
    private void saveExpenses() {
        String json = gson.toJson(expenses);
        preferences.edit().putString(KEY_EXPENSES, json).apply();
    }

    /**
     * Add a new expense
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
        sortExpensesByDate();
        saveExpenses();
    }

    /**
     * Update an existing expense
     */
    public void updateExpense(Expense expense) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId().equals(expense.getId())) {
                expenses.set(i, expense);
                sortExpensesByDate();
                saveExpenses();
                break;
            }
        }
    }

    /**
     * Delete an expense
     */
    public void deleteExpense(String expenseId) {
        expenses.removeIf(expense -> expense.getId().equals(expenseId));
        saveExpenses();
    }

    /**
     * Get all expenses sorted by date (newest first)
     */
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }

    /**
     * Get expenses by category
     */
    public List<Expense> getExpensesByCategory(Category category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory() == category) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    /**
     * Get expenses within a date range
     */
    public List<Expense> getExpensesByDateRange(long startTime, long endTime) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            long timestamp = expense.getTimestamp();
            if (timestamp >= startTime && timestamp <= endTime) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    /**
     * Calculate total expenses
     */
    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    /**
     * Calculate total for specific category
     */
    public double getTotalByCategory(Category category) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory() == category) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    /**
     * Get monthly expenses (current month)
     */
    public double getMonthlyExpenses() {
        long now = System.currentTimeMillis();
        long startOfMonth = getStartOfMonth(now);
        long endOfMonth = getEndOfMonth(now);
        
        double total = 0;
        for (Expense expense : expenses) {
            long timestamp = expense.getTimestamp();
            if (timestamp >= startOfMonth && timestamp <= endOfMonth) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    /**
     * Sort expenses by date (newest first)
     */
    private void sortExpensesByDate() {
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return Long.compare(e2.getTimestamp(), e1.getTimestamp());
            }
        });
    }

    /**
     * Get start of current month timestamp
     */
    private long getStartOfMonth(long timestamp) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        calendar.set(java.util.Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Get end of current month timestamp
     */
    private long getEndOfMonth(long timestamp) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 
            calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
        calendar.set(java.util.Calendar.MINUTE, 59);
        calendar.set(java.util.Calendar.SECOND, 59);
        calendar.set(java.util.Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * Save monthly budget
     */
    public void setMonthlyBudget(double budget) {
        preferences.edit().putFloat(KEY_MONTHLY_BUDGET, (float) budget).apply();
    }

    /**
     * Get monthly budget
     */
    public double getMonthlyBudget() {
        return preferences.getFloat(KEY_MONTHLY_BUDGET, 1000.0f);
    }

    /**
     * Get budget remaining
     */
    public double getBudgetRemaining() {
        return getMonthlyBudget() - getMonthlyExpenses();
    }

    /**
     * Get budget usage percentage
     */
    public int getBudgetUsagePercentage() {
        double budget = getMonthlyBudget();
        if (budget <= 0) return 0;
        double usage = (getMonthlyExpenses() / budget) * 100;
        return Math.min((int) usage, 100);
    }

    /**
     * Clear all data (for testing)
     */
    public void clearAllData() {
        expenses.clear();
        saveExpenses();
    }

    /**
     * Export expenses to CSV format string
     */
    public String exportToCSV() {
        StringBuilder csv = new StringBuilder();
        csv.append("Date,Description,Category,Amount\n");
        
        for (Expense expense : expenses) {
            csv.append(expense.getDate()).append(",");
            csv.append(expense.getDescription()).append(",");
            csv.append(expense.getCategory().getDisplayName()).append(",");
            csv.append(expense.getAmount()).append("\n");
        }
        
        return csv.toString();
    }
}
