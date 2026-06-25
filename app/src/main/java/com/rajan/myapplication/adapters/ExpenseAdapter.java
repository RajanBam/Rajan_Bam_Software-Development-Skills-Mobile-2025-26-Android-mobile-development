package com.rajan.myapplication.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rajan.myapplication.R;
import com.rajan.myapplication.models.Category;
import com.rajan.myapplication.models.Expense;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter for displaying expense list
 * Implements ViewHolder pattern for efficient view recycling
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<Expense> expenses;
    private OnExpenseClickListener listener;

    /**
     * Interface for handling expense item clicks
     */
    public interface OnExpenseClickListener {
        void onExpenseClick(Expense expense);
        void onExpenseLongClick(Expense expense);
    }

    public ExpenseAdapter(List<Expense> expenses, OnExpenseClickListener listener) {
        this.expenses = expenses != null ? expenses : new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.bind(expense);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    /**
     * Update adapter with new expense list
     */
    public void updateExpenses(List<Expense> newExpenses) {
        this.expenses = newExpenses != null ? newExpenses : new ArrayList<>();
        notifyDataSetChanged();
    }

    /**
     * Remove expense at position
     */
    public void removeExpense(int position) {
        if (position >= 0 && position < expenses.size()) {
            expenses.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Get expense at position
     */
    public Expense getExpenseAt(int position) {
        if (position >= 0 && position < expenses.size()) {
            return expenses.get(position);
        }
        return null;
    }

    /**
     * ViewHolder class for expense items
     */
    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDescription, tvCategory, tvDate, tvAmount;
        private ImageView ivCategoryIcon;
        private View viewCategoryBackground;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvExpenseDescription);
            tvCategory = itemView.findViewById(R.id.tvExpenseCategory);
            tvDate = itemView.findViewById(R.id.tvExpenseDate);
            tvAmount = itemView.findViewById(R.id.tvExpenseAmount);
            ivCategoryIcon = itemView.findViewById(R.id.ivCategoryIcon);
            viewCategoryBackground = itemView.findViewById(R.id.viewCategoryBackground);
        }

        public void bind(Expense expense) {
            // Set expense details
            tvDescription.setText(expense.getDescription());
            tvCategory.setText(expense.getCategory().getDisplayName());
            tvDate.setText(expense.getDate());
            tvAmount.setText(expense.getFormattedAmount());

            // Set category icon
            ivCategoryIcon.setImageResource(Category.getIconResource(expense.getCategory()));

            // Set category color
            try {
                int color = Color.parseColor(expense.getCategory().getColorCode());
                viewCategoryBackground.setBackgroundColor(color);
                ivCategoryIcon.setColorFilter(color);
            } catch (IllegalArgumentException e) {
                // Default color if parsing fails
                viewCategoryBackground.setBackgroundColor(Color.GRAY);
            }

            // Set click listeners
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onExpenseClick(expense);
                }
            });

            itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onExpenseLongClick(expense);
                }
                return true;
            });
        }
    }
}
