# Personality-Test-Application
Personality Test Application - Java Swing

A desktop-based Java application that evaluates a user's personality through multiple-choice questions (MCQs). 
The application supports user registration, login, and an admin role to add questions. Each MCQ option is mapped 
to a specific personality type. Based on the user's selections, the app tracks personality scores and displays the 
dominant personality in a result bar. All information is stored in a database.

Features:
![Start](Assets/Start)
- User registration and login
  ![Login](Assets/userlogin)
- Admin panel to add questions and assign personalities
 ![Admin](Assets/Adminpanel)
- MCQ-based personality test
   ![MCQS](Assets/Questionframe (1))
- Each option mapped to a personality trait (e.g., Introvert, Extrovert, Ambivert, Analytical)
- Result shown as a Progress Bar of personality percentages
  ![Result](Assets/Result.png)
- Data stored in a database using JDBC

Technologies Used:
- Java (Swing for GUI)
- JDBC (for database operations)
- Access Database

How It Works:
1. Admin logs in and adds questions.
2. User registers/logs in and answers personality questions.
3. Each selected option increments the count of a mapped personality type.
4. At the end, the dominant personality is shown via a visual result bar.
5. Results and user data are stored in the database.

Author: Hamna Maryam

