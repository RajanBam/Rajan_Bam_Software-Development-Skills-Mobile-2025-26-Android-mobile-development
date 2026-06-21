# 💰 ExpenseTracker - Personal Budget Manager

<div align="center">

**A modern Android application for tracking daily expenses and managing budgets**

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Material Design](https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white)

</div>

---

## 📱 About

ExpenseTracker is a comprehensive personal finance management application designed to help users track their daily expenses, manage monthly budgets, and gain insights into their spending habits through visual statistics. Built as part of the Mobile Development module at LUT University.

## ✨ Features

### Core Functionality
- ✅ **Add & Track Expenses** - Easy-to-use form for recording expenses with amount, description, category, and date
- ✅ **Expense List View** - Comprehensive list of all expenses with swipe-to-delete functionality
- ✅ **Budget Management** - Set monthly budgets and track spending progress
- ✅ **Statistics Dashboard** - Visual breakdown of expenses by category with progress bars
- ✅ **Multiple Categories** - 9 predefined categories (Food, Transport, Housing, Entertainment, Healthcare, Shopping, Utilities, Education, Others)

### Technical Features
- 📊 **Data Visualization** - Progress bars and percentage-based spending analysis
- 💾 **Data Persistence** - SharedPreferences with Gson for reliable data storage
- 🎨 **Material Design** - Modern UI following Material Design 3 guidelines
- 📱 **Responsive Layout** - Works on various screen sizes
- 🗂️ **RecyclerView** - Efficient list rendering with custom adapters
- 🔄 **Activity Navigation** - Smooth transitions between multiple screens
- ⚡ **Real-time Updates** - Dashboard refreshes automatically on resume

## 🏗️ Architecture

### Activities
1. **MainActivity** - Dashboard displaying budget summary and quick stats
2. **AddExpenseActivity** - Form for adding new expenses
3. **ExpenseListActivity** - List view of all expenses with swipe-to-delete
4. **StatisticsActivity** - Category-wise expense breakdown
5. **BudgetSettingsActivity** - Budget configuration screen

### Data Models
- **Expense** - Parcelable expense object (id, amount, description, category, timestamp, date)
- **Category** - Enum with 9 expense categories
- **ExpenseRepository** - Singleton repository managing data operations

### Key Components
- **ExpenseAdapter** - Custom RecyclerView adapter with ViewHolder pattern
- **SharedPreferences** - Lightweight data persistence
- **Gson** - JSON serialization for complex object storage
- **Material Components** - Modern UI components (CardView, MaterialButton, TextInputLayout)

## 🛠️ Technologies Used

- **Language:** Java 8
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Build System:** Gradle with Kotlin DSL
- **Libraries:**
  - AndroidX AppCompat
  - Material Design Components 3
  - ConstraintLayout
  - RecyclerView
  - CardView
  - Gson 2.10.1

## 🎥 Video Demonstration

**[📹 Watch the Full Demo Video on YouTube](VIDEO_DEMONSTRATION.md)**

> See the app in action! The video demonstrates all features, UI/UX design, and functionality running on a real Android device.

---

## 📸 Screenshots

### Dashboard
- Budget summary card with progress indicator
- Total expenses and transaction count
- Quick action buttons for navigation

### Add Expense
- Amount input with validation
- Description text field
- Category spinner dropdown
- Date picker dialog

### Expense List
- RecyclerView with custom list items
- Category icons with color coding
- Swipe-to-delete with undo
- Empty state placeholder

### Statistics
- Monthly spending summary
- Daily average calculation
- Category-wise breakdown cards
- Progress bars showing percentage

### Budget Settings
- Current budget display
- Input field for new budget
- Helpful tips and information

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version recommended)
- JDK 8 or higher
- Android SDK with API level 24+
- Gradle 8.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd AndriodMobileAPP
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the project directory
   - Wait for Gradle sync to complete

3. **Run the app**
   - Connect an Android device or start an emulator
   - Click Run ▶️ or press Shift + F10
   - Select your device/emulator

### Building APK

```bash
./gradlew assembleDebug
```

The APK will be generated in `app/build/outputs/apk/debug/`

## 📚 Learning Outcomes

This project demonstrates proficiency in:

### Android Fundamentals
- ✅ Activity lifecycle management
- ✅ Intent-based navigation with data passing
- ✅ Android Manifest configuration
- ✅ Resource management (layouts, strings, colors, styles)

### UI/UX Design
- ✅ Material Design 3 implementation
- ✅ Custom layouts and themes
- ✅ Responsive design patterns
- ✅ User input validation
- ✅ Loading states and empty states

### Data Management
- ✅ SharedPreferences for persistence
- ✅ JSON serialization/deserialization
- ✅ Repository pattern implementation
- ✅ CRUD operations

### Advanced Features
- ✅ RecyclerView with custom adapters
- ✅ ViewHolder pattern for performance
- ✅ ItemTouchHelper for swipe gestures
- ✅ DatePickerDialog
- ✅ Snackbar with undo functionality
- ✅ Dialog fragments
- ✅ Parcelable interface implementation

### Software Engineering
- ✅ Code organization and structure
- ✅ Singleton design pattern
- ✅ Clean code practices
- ✅ Comprehensive documentation
- ✅ Version control with Git

## 🎓 Academic Context

**Course:** Software Development Skills - DevOps, Online Course  
**Module:** Mobile Development  
**Institution:** LUT University (Lappeenrannan teknillinen yliopisto)  
**Location:** Lappeenranta, Finland  
**Duration:** June 21 - July 3, 2026

### Assignment Requirements Met

✅ **Functionality with components** - Buttons, text fields, spinners, date picker, progress bars  
✅ **Multiple views** - 5 distinct activities with navigation  
✅ **Information display component** - RecyclerView with custom adapter  
✅ **Custom styling** - Material Design theme with custom colors and styles  
✅ **Additional features** - Data persistence, swipe-to-delete, statistics, budget tracking

## 👨‍💻 Developer Information

**Name:** Rajan Bam  
**Student Number:** 002295635  
**Current Study:** LUT University, Lappeenranta, Finland  
**Previous Education:** Morgan International College, Kathmandu (Science Stream)  
**Contact:** [Add your email/LinkedIn]

## 🤝 Contributing

This is an academic project, but suggestions and feedback are welcome!

## 📄 License

This project is created for educational purposes as part of university coursework.

## 🙏 Acknowledgments

- LUT University for providing the learning opportunity
- Course instructors for guidance and support
- Material Design team for comprehensive UI guidelines
- Android documentation and community resources

---

<div align="center">

**Built with ❤️ for learning Android development**

*Last Updated: July 3, 2026*

</div>
