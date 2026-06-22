package com.rajan.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Expense model representing a single expense entry
 * Implements Parcelable for efficient data passing between activities
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public class Expense implements Parcelable {
    private String id;
    private double amount;
    private String description;
    private Category category;
    private long timestamp;
    private String date;

    /**
     * Constructor for creating a new expense
     */
    public Expense(double amount, String description, Category category, long timestamp) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.timestamp = timestamp;
        this.date = formatDate(timestamp);
    }

    /**
     * Constructor for Parcelable
     */
    protected Expense(Parcel in) {
        id = in.readString();
        amount = in.readDouble();
        description = in.readString();
        category = Category.valueOf(in.readString());
        timestamp = in.readLong();
        date = in.readString();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    // Getters
    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return date;
    }

    // Setters
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        this.date = formatDate(timestamp);
    }

    /**
     * Format timestamp to readable date string
     */
    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    /**
     * Get formatted amount with currency symbol
     */
    public String getFormattedAmount() {
        return String.format(Locale.getDefault(), "€%.2f", amount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeDouble(amount);
        dest.writeString(description);
        dest.writeString(category.name());
        dest.writeLong(timestamp);
        dest.writeString(date);
    }
}
