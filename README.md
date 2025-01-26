Personal Expense Tracker
A simple desktop application to track personal expenses, built using Java Swing. This application allows users to add expenses, view the expense list, calculate total expenses, and save the records to a file.

Features
Add Expense: Input details such as date, category, amount, and description for each expense.
View Expenses:
View a list of all expenses added.
Displays the total expenses incurred.
Save to File: Save expense details to a local file (expenses.txt) for record-keeping.
Interactive GUI: User-friendly interface with buttons and dialogs for smooth interaction.

The main functions:
Main Window

Add Expense Window

View Expenses Window

How to Use
Start Application: Open the program. The main menu offers two options:
Add Expense
View Expenses
Add Expense: Fill in the required fields (date, category, amount, and description) and click "Add To List."
View Expenses:
See the list of all added expenses with details.
View the total expenses calculated.
Save expenses to a file for future reference.
Exit: Close the application by clicking the close button on the window.
Project Structure

├── Expenses_tracker.java      # Main program file
├── Expense.java               # Model class for Expense
├── expenses.txt               # Saved expense data (generated at runtime)
└── README.md                  # Project documentation
