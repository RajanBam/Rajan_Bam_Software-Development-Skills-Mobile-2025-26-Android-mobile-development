package com.rajan.myapplication.models;

/**
 * Category enum for expense classification
 * Each category has a display name and associated color
 * 
 * @author Rajan Bam
 * @version 1.0
 */
public enum Category {
    FOOD("Food & Dining", "#FF5722"),
    TRANSPORT("Transportation", "#2196F3"),
    HOUSING("Housing", "#9C27B0"),
    ENTERTAINMENT("Entertainment", "#E91E63"),
    HEALTHCARE("Healthcare", "#00BCD4"),
    SHOPPING("Shopping", "#FF9800"),
    UTILITIES("Utilities", "#607D8B"),
    EDUCATION("Education", "#3F51B5"),
    OTHERS("Others", "#795548");

    private final String displayName;
    private final String colorCode;

    Category(String displayName, String colorCode) {
        this.displayName = displayName;
        this.colorCode = colorCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColorCode() {
        return colorCode;
    }

    /**
     * Get icon resource ID for the category
     */
    public static int getIconResource(Category category) {
        // Using simple built-in Android icons
        switch (category) {
            case FOOD:
                return android.R.drawable.ic_menu_gallery;
            case TRANSPORT:
                return android.R.drawable.ic_menu_directions;
            case HOUSING:
                return android.R.drawable.ic_menu_mylocation;
            case ENTERTAINMENT:
                return android.R.drawable.ic_menu_today;
            case HEALTHCARE:
                return android.R.drawable.ic_menu_add;
            case SHOPPING:
                return android.R.drawable.ic_menu_gallery;
            case UTILITIES:
                return android.R.drawable.ic_menu_manage;
            case EDUCATION:
                return android.R.drawable.ic_menu_info_details;
            case OTHERS:
                return android.R.drawable.ic_menu_more;
            default:
                return android.R.drawable.ic_menu_help;
        }
    }
}
