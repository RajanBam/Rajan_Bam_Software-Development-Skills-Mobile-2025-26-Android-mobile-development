# Lappeenrannan teknillinen yliopisto (LUT University)
## School of Business and Management

---

# Software Development Skills DevOps, Online course

**Rajan Bam**, Student Number: 002295635

**Current Study:** LUT University, Lappeenranta, Finland  
**Previous Education:** Morgan International College, Kathmandu (+2 Science Stream)

---

# LEARNING DIARY, MOBILE MODULE

---

## Date: 21.06.2026

**Activity:** Environment Setup and Course Introduction

**Learning Outcome:**

I checked the general course information and understood the main objectives of the mobile development module. The goal is to learn Android Studio fundamentals and create a practical Android application demonstrating core Android concepts.

**What I learned:**
- How to set up Android Studio development environment on my Windows system
- Understanding of the Android project structure (app, gradle, manifest, resources)
- Basic Android components: Activities, Layouts, and Resources
- How to create and configure a new Android project with proper namespace (com.rajan.expensetracker)
- The importance of choosing a useful, non-typical app idea for portfolio purposes

**Project Decision:**
I decided to create an **Expense Tracker & Budget Manager** application. This app will help users track their daily expenses, manage budgets, and view spending statistics. It's a practical solution that I can use in my daily life and demonstrates professional Android development skills.

**Technical Setup:**
- Android Studio configured with SDK 34
- Java 8 compatibility set up
- Project renamed from generic "MyApplication" to "ExpenseTracker"
- Dependencies reviewed: AppCompat, Material Design, ConstraintLayout

---

## Date: 22.06.2026

**Activity:** Project Architecture Planning and Initial Setup

**Learning Outcome:**

**What I learned:**
- Android application architecture and project structure planning
- How to organize packages for scalability (models, activities, adapters, utils)
- Understanding of the MVC (Model-View-Controller) pattern in Android
- How to plan data models before writing code
- Git repository initialization and version control basics

**Project Architecture Designed:**
- **Models:** Expense, Category, Budget classes for data structure
- **Activities:** MainActivity (Dashboard), AddExpenseActivity, ExpenseListActivity, BudgetSettingsActivity, StatisticsActivity
- **Adapters:** Custom adapter for displaying expense lists
- **Utils:** Helper classes for date formatting, calculations

**Key Decisions:**
- Using SharedPreferences for data persistence (suitable for learning and this app's scope)
- Material Design guidelines for modern, clean UI
- Custom color scheme: Blue primary (#2196F3), Orange accent (#FF9800)
- Category-based expense organization with icons

I created the initial Git repository and made my first commit with the base project structure.

---

## Date: 23.06.2026

**Activity:** Part 2 - Core Android Elements (Activities and Intents)

**Learning Outcome:**

**What I learned:**
- **Activities:** The fundamental building blocks of Android apps. Each screen is an Activity with its own lifecycle (onCreate, onStart, onResume, onPause, onStop, onDestroy)
- **Intents:** How to navigate between activities and pass data
  - Explicit Intents: Navigate to specific activities
  - Implicit Intents: Let system choose appropriate app for action
- **Activity Lifecycle:** Understanding when activities are created, paused, and destroyed
- **Data passing:** Using Intent extras (putExtra/getExtra) to send data between activities

**Implementation Progress:**
- Created all five Activity classes with proper lifecycle methods
- Implemented navigation from MainActivity to other screens using Intents
- Set up AndroidManifest.xml with all activities registered
- Created data models: Expense.java, Category.java, Budget.java with proper constructors and getters/setters
- Implemented Parcelable interface for passing Expense objects between activities

**Challenges Faced:**
Understanding the Activity lifecycle was initially confusing, but after drawing a diagram and testing with Log statements, it became clear how Android manages activity states.

---

## Date: 24.06.2026

**Activity:** Data Models and Business Logic Implementation

**Learning Outcome:**

**What I learned:**
- Java class design principles and encapsulation
- How to create POJO (Plain Old Java Object) classes for data structure
- Implementing Parcelable for efficient data passing in Android
- SharedPreferences for lightweight data storage
- JSON serialization for storing complex objects

**Implementation Progress:**
- Completed Expense model with fields: id, amount, category, description, date, timestamp
- Created Category enum with predefined categories (Food, Transport, Housing, Entertainment, Healthcare, Shopping, Utilities, Education, Others)
- Implemented ExpenseManager utility class for CRUD operations
- Created BudgetManager for budget calculations and tracking
- Added data validation logic (amount > 0, required fields check)

**Technical Skills Gained:**
- Working with Java generics and collections (ArrayList, HashMap)
- Date and time handling with Calendar and SimpleDateFormat
- String formatting for currency display
- Singleton pattern for manager classes

---

## Date: 25.06.2026

**Activity:** Part 3 - Layouts, ListView, and Custom Components

**Learning Outcome:**

**What I learned:**
- **Layout types:** ConstraintLayout, LinearLayout, RelativeLayout, and when to use each
- **ListView vs RecyclerView:** RecyclerView is more efficient and flexible for large lists
- **Custom Adapters:** How to create custom ArrayAdapter to display complex list items
- **ViewHolder pattern:** Improves performance by recycling views
- **XML layouts:** Designing UI declaratively with proper constraints and dimensions

**Implementation Progress:**
- Designed MainActivity dashboard layout with CardViews for statistics
- Created custom list item layout (expense_list_item.xml) with:
  - Category icon (ImageView)
  - Expense description (TextView)
  - Amount with currency (TextView)
  - Date display (TextView)
  - Custom styling with rounded corners and shadows
- Implemented ExpenseListAdapter extending RecyclerView.Adapter
- Added ViewHolder class for efficient view recycling

**Design Principles Applied:**
- Material Design guidelines for spacing (8dp grid system)
- Consistent typography hierarchy
- Color coding: expenses in red, income in green
- Responsive layouts that work on different screen sizes

---

## Date: 26.06.2026

**Activity:** ImageView, Icons, and Visual Design

**Learning Outcome:**

**What I learned:**
- **ImageView:** Displaying images and icons in Android
- **Vector Drawables:** Using XML vector graphics for scalable icons
- **Resource management:** Organizing drawables, mipmaps, and different density folders
- **Material Icons:** Integrating Google's Material Design icon library
- **Styling:** Creating custom themes, colors, and styles in XML

**Implementation Progress:**
- Created category icons using vector drawables (9 categories)
- Added custom app icon and launcher icon
- Implemented color scheme across the app:
  - Primary: Blue (#2196F3)
  - Primary Dark: Dark Blue (#1976D2)
  - Accent: Orange (#FF9800)
  - Success: Green (#4CAF50)
  - Error: Red (#F44336)
- Created custom button styles with rounded corners
- Designed card shadows and elevation for depth
- Added splash screen with app branding

**Design Skills Developed:**
- Understanding of dp (density-independent pixels) vs sp (scale-independent pixels)
- Image scaling and scaleType attributes
- Gradient backgrounds for visual appeal
- Night mode color considerations

---

## Date: 27.06.2026

**Activity:** Advanced UI Components and User Input

**Learning Outcome:**

**What I learned:**
- **EditText:** Text input with input types (number, text, date)
- **Spinner:** Dropdown selection for categories
- **DatePickerDialog:** Selecting dates with calendar interface
- **Buttons and OnClickListeners:** Handling user interactions
- **Toast and Snackbar:** Providing user feedback
- **Input validation:** Ensuring data integrity before saving

**Implementation Progress:**
- Completed AddExpenseActivity layout with all input fields
- Implemented Spinner with custom adapter for category selection
- Added DatePickerDialog for expense date selection
- Created form validation:
  - Amount must be greater than zero
  - Description cannot be empty
  - Category must be selected
- Added Save and Cancel button functionality
- Implemented Toast messages for user feedback
- Added TextWatcher for real-time input formatting

**User Experience Improvements:**
- Keyboard automatically shows for amount input
- Date defaults to today
- Clear error messages for validation failures
- Confirmation dialog before discarding changes

---

## Date: 28.06.2026

**Activity:** Data Persistence with SharedPreferences

**Learning Outcome:**

**What I learned:**
- **SharedPreferences:** Key-value storage for simple data
- **JSON serialization:** Converting objects to strings for storage
- **Gson library:** Efficient JSON parsing and serialization
- **Data migration:** Handling app updates and data structure changes
- **CRUD operations:** Create, Read, Update, Delete expenses

**Implementation Progress:**
- Added Gson dependency to build.gradle
- Implemented ExpenseRepository class for data management
- Created methods for:
  - saveExpense(): Add new expense
  - getAllExpenses(): Retrieve all expenses sorted by date
  - updateExpense(): Modify existing expense
  - deleteExpense(): Remove expense
  - getExpensesByCategory(): Filter expenses
  - getExpensesByDateRange(): Filter by date range
- Implemented budget data persistence in BudgetSettings
- Added data backup and restore functionality

**Challenges Overcome:**
Working with JSON and type conversion was tricky initially. I learned about TypeToken for deserializing ArrayList<Expense> from JSON strings.

---

## Date: 29.06.2026

**Activity:** Statistics and Data Visualization

**Learning Outcome:**

**What I learned:**
- **Custom Views:** Creating custom UI components by extending View class
- **Canvas and Paint:** Drawing shapes, text, and graphics programmatically
- **Data aggregation:** Grouping and calculating statistics from expense data
- **ProgressBar:** Showing budget usage visually
- **ScrollView:** Making layouts scrollable for more content

**Implementation Progress:**
- Created StatisticsActivity with expense breakdown
- Implemented CategoryChartView (custom view) for visual representation
- Calculated statistics:
  - Total expenses by category
  - Percentage of budget used
  - Daily average spending
  - Month-over-month comparison
- Added color-coded ProgressBars for budget tracking
- Created summary cards showing:
  - Total spent this month
  - Budget remaining
  - Highest spending category
  - Number of transactions

**Advanced Concepts Learned:**
- Override onDraw() method for custom drawing
- Use of RectF for rounded rectangles
- Color manipulation and alpha channels
- Responsive sizing based on screen dimensions

---

## Date: 30.06.2026

**Activity:** BroadcastReceiver and Notifications

**Learning Outcome:**

**What I learned:**
- **BroadcastReceiver:** Listening to system-wide and custom broadcasts
- **AlarmManager:** Scheduling future notifications
- **NotificationManager:** Creating and displaying notifications
- **PendingIntent:** Intent that other apps can use
- **Notification Channels:** Required for Android 8.0+ (API 26)

**Implementation Progress:**
- Created BudgetAlertReceiver extending BroadcastReceiver
- Implemented budget warning notifications:
  - 75% budget used: Warning notification
  - 90% budget used: Critical notification
  - 100% budget exceeded: Alert notification
- Added daily reminder notification for expense logging
- Registered receiver in AndroidManifest.xml
- Implemented notification click action to open app
- Added notification preferences in Settings

**Key Concepts:**
- Broadcast types: Ordered vs Normal broadcasts
- Context-registered vs Manifest-registered receivers
- Notification importance levels
- Battery optimization considerations

**Challenges:**
Understanding PendingIntent flags (FLAG_UPDATE_CURRENT, FLAG_IMMUTABLE) and Android version compatibility for notifications required careful testing.

---

## Date: 01.07.2026

**Activity:** Advanced Features and Polish

**Learning Outcome:**

**What I learned:**
- **RecyclerView animations:** ItemAnimator for smooth list updates
- **Swipe gestures:** ItemTouchHelper for swipe-to-delete
- **Dialog fragments:** Reusable confirmation dialogs
- **Menu options:** Creating options menu and context menu
- **Intents for system apps:** Opening camera, gallery, email

**Implementation Progress:**
- Implemented swipe-to-delete in expense list with undo functionality
- Added long-press edit functionality for expenses
- Created confirmation dialogs for destructive actions
- Added options menu with:
  - Settings
  - Export data
  - About app
- Implemented data export to CSV file
- Added animations for activity transitions
- Created empty state views with helpful messages
- Implemented pull-to-refresh in expense list

**UX Improvements:**
- Smooth animations enhance user experience
- Undo option prevents accidental deletions
- Loading indicators during data operations
- Empty states guide users on next actions

---

## Date: 02.07.2026

**Activity:** Testing, Debugging, and Bug Fixes

**Learning Outcome:**

**What I learned:**
- **Android debugging:** Using Logcat effectively
- **Breakpoints:** Step-through debugging in Android Studio
- **Error handling:** Try-catch blocks and graceful error recovery
- **Testing on emulator:** Creating AVDs (Android Virtual Devices)
- **Edge cases:** Handling null values, empty lists, extreme values

**Implementation Progress:**
- Fixed bug: App crash when no budget is set
- Fixed bug: Date formatting issue with different locales
- Fixed bug: Expense list not updating after deletion
- Added null checks throughout the codebase
- Improved error messages for better user understanding
- Tested on different screen sizes (phone and tablet)
- Tested on different Android versions (API 24 to 34)
- Added input sanitization for currency amounts
- Fixed memory leaks by properly closing resources

**Testing Approach:**
- Manual testing of all user flows
- Testing edge cases (zero amount, very large numbers, special characters)
- Rotation testing (landscape/portrait orientation)
- Low memory scenarios

**Debugging Skills Gained:**
- Reading stack traces effectively
- Using Android Studio's Layout Inspector
- Profiling app performance
- Identifying memory leaks

---

## Date: 03.07.2026

**Activity:** Final Polish, Documentation, and Repository Preparation

**Learning Outcome:**

**What I learned:**
- **Git best practices:** Meaningful commit messages, branching strategy
- **README documentation:** Writing clear project documentation
- **Code comments:** Documenting complex logic for future reference
- **APK generation:** Building release APK for distribution
- **Code refactoring:** Cleaning up code and removing unused resources

**Implementation Progress:**
- Created comprehensive README.md with:
  - App description and features
  - Screenshots of all major screens
  - Technical architecture overview
  - Setup and installation instructions
  - Technologies used
  - Developer information
- Added code comments for complex methods
- Refactored code for better readability
- Removed unused imports and resources
- Organized string resources properly (no hardcoded strings)
- Created proper .gitignore file
- Made final Git commit and pushed to repository
- Generated signed APK for testing
- Created app icon with proper sizing for all densities

**Final Project Structure:**
```
ExpenseTracker/
├── Activities (5 total)
├── Models (3 classes)
├── Adapters (Custom RecyclerView adapter)
├── Utils (Helper classes)
├── Receivers (BroadcastReceiver)
├── Layouts (10+ XML files)
├── Drawables (Custom icons and shapes)
├── Values (Colors, strings, styles, themes)
```

**Project Summary:**
Successfully created a fully functional Expense Tracker application with:
- Multiple activities with navigation
- Various UI components (buttons, text fields, spinners, date picker)
- RecyclerView with custom adapter and list items
- Custom layouts and Material Design styling
- Images and category icons
- Data persistence with SharedPreferences
- BroadcastReceiver for notifications
- Statistics and data visualization
- Professional UI/UX design
- Complete documentation

**Skills Demonstrated:**
- Android fundamentals (Activities, Intents, Layouts)
- Java programming
- Material Design principles
- Data management and persistence
- User interface design
- Problem-solving and debugging
- Version control with Git
- Technical documentation

**Personal Reflection:**
This project has been an excellent learning experience. I now understand the complete Android app development lifecycle from planning to deployment. The Expense Tracker is a practical application that I can actually use and show to potential employers as proof of my Android development skills. The most challenging part was understanding the Activity lifecycle and data persistence, but through practice and debugging, I gained confidence in these areas.

I'm proud of creating a clean, professional app that goes beyond basic tutorials. This project represents my ability to plan, design, implement, test, and document a complete Android application.

---

**Repository Link:** [Will be added after repository is created]

**Video Demonstration:** [Watch Demo Video - VIDEO_DEMONSTRATION.md](VIDEO_DEMONSTRATION.md)

**Final Commit Date:** 03.07.2026

---

*End of Learning Diary*
