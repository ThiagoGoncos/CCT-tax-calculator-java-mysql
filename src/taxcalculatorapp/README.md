# Tax Calculator Application

## Project Overview

This project was developed as part of the **Object-Oriented Programming module** at **CCT College Dublin**.

The objective of the project was to design and implement a **Java-based tax calculation system** capable of managing users, storing tax calculation records, and computing different components of Irish taxation.

The system allows users to:

- Register new accounts
- Log in securely
- Calculate taxes based on their income
- Store and review previous tax calculations

The project demonstrates the application of **object-oriented programming principles**, **database integration**, and **modular software design**.

The application runs as a **console-based program** and interacts with a **MySQL database** to store user accounts and calculation records.

---

## System Architecture

The application follows a **modular object-oriented architecture**, where different responsibilities are separated into classes.

Key components of the system include:

- User management
- Authentication system
- Tax calculation logic
- Database communication
- Administrative control features

This separation improves code readability, maintainability, and scalability.

---

## User Types

The system supports two different user roles.

### Admin

Administrator users have elevated privileges that allow them to manage the system.

Admin users can:

- Modify their profile
- Access the list of registered users
- Remove users from the system
- Review tax calculation operations
- Manage system functionality

Administrative actions are defined using an enumeration representing available operations. :contentReference[oaicite:0]{index=0}

---

### Regular User

Regular users are the primary users of the system.

They can:

- Register new accounts
- Log in to the system
- Perform tax calculations
- Store and review their tax calculation history

The system stores each calculation for later review.

---

## User Model

All users in the system inherit from an abstract **User** class that defines common user properties.

The base user class includes attributes such as:

- Username
- Password
- Name
- Surname
- User type

It also defines abstract methods that must be implemented by subclasses for modifying profiles and retrieving job roles. :contentReference[oaicite:1]{index=1}

This approach demonstrates **inheritance and abstraction**, key concepts in object-oriented programming.

---

## User Authentication

The system includes a login module responsible for authenticating users.

During login:

1. The user enters a username and password
2. The system queries the database
3. If credentials are valid, the user is authenticated and granted access

If authentication fails, an error message is displayed.

The login logic interacts with the database reader class responsible for retrieving user records. :contentReference[oaicite:2]{index=2}

---

## User Registration

New users can register through a guided registration process.

The registration system performs validation checks including:

- Username must be at least **8 characters long**
- Password must contain **at least 6 letters and 2 numbers**
- Name and surname must contain **only letters**

These validations help ensure basic input integrity before storing user data.

After successful validation, the system writes the new user to the database. :contentReference[oaicite:3]{index=3}

---

## Tax Calculation System

The core functionality of the system is calculating different components of Irish taxation.

The application calculates three main tax categories:

### Income Tax (PAYE)

Income tax is calculated based on income thresholds.

- Income up to €40,000 is taxed at **20%**
- Income above €40,000 is taxed at **40%**

Tax credits are applied before calculating the final taxable income.

---

### Universal Social Charge (USC)

USC is calculated using progressive thresholds:

- Up to €12,012 → 0%
- €12,013 – €22,920 → 0.5%
- €22,921 – €70,044 → 2%
- Above €70,044 → 4%

---

### Pay Related Social Insurance (PRSI)

PRSI is applied when income exceeds €18,354.30.

When applicable, the PRSI rate is **4% of gross income**.

---

### Total Tax

The system calculates total tax by summing:

- Income Tax
- USC
- PRSI

The calculation logic is implemented using a dedicated tax calculator class that encapsulates the tax rules. :contentReference[oaicite:4]{index=4}

---

## Database Integration

The application uses **MySQL** to persist data.

The database stores:

- User accounts
- Login credentials
- Tax calculation records

The system interacts with the database through dedicated classes:

- DatabaseReader
- DatabaseWriter
- DatabaseSetup

These classes manage the interaction between the application and the database.

The project includes the **MySQL JDBC connector** for database connectivity. :contentReference[oaicite:5]{index=5}

---

## Application Workflow

The typical workflow of the system is:

1. User launches the application
2. User registers or logs into an account
3. System authenticates credentials
4. User accesses tax calculator functionality
5. Tax components are calculated
6. Results are stored in the database
7. Users or administrators can review calculations

---

## Technologies Used

### Java

The entire system is implemented in **Java**, following object-oriented programming principles including:

- Inheritance
- Encapsulation
- Abstraction
- Interfaces
- Enumerations

---

### MySQL

The system uses **MySQL** as the relational database for storing user information and tax records.

---

### JDBC

Java Database Connectivity (JDBC) is used to establish communication between the application and the MySQL database.

---

### Apache Ant (NetBeans Build System)

The project includes a build configuration file used by NetBeans to compile and run the project. :contentReference[oaicite:6]{index=6}

---

## Learning Outcomes

This project demonstrates several key software development concepts:

- Object-Oriented Programming in Java
- Class design and abstraction
- Role-based user systems
- Authentication and user validation
- Database integration using JDBC
- Modular software architecture
- Console-based application development

The project helped reinforce both **software design principles and practical implementation skills** in Java.

---

## Author 

This project was developed by **Thiago Goncalves da Costa** as part of the **Bachelor of Science in Computing and Information Technology** at **CCT College Dublin**.

During my studies, I used the institutional GitHub account associated with my student email:

2022161@student.cct.ie

Since institutional accounts and student emails may be deactivated after graduation, this repository was migrated to my personal GitHub account:

https://github.com/ThiagoGoncos

This ensures long-term preservation of the project, commit history, and academic work completed during the degree program.