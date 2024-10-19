Bank System with Akka Framework
================================

This is a simple bank account system implemented in Java, using the Akka framework for actor-based concurrency. The system allows for basic bank account operations such as checking the balance, depositing, and withdrawing money, all handled concurrently using Akka actors.

Features
--------

- Initial Balance: Each bank account starts with an initial balance of £100.
- Deposit: You can deposit any amount into the bank account.
- Withdrawal: You can withdraw an amount, as long as the balance is sufficient. If not, an error message will be displayed indicating the required deposit.
- Concurrency: The system uses Akka actors to process transactions concurrently.
- Transaction Logging: Every deposit and withdrawal is logged with the updated balance.

Project Structure
-----------------

The project includes the following classes:

- BankAccount: The main actor that handles bank account operations.
- Deposit: Represents a deposit operation with a specific amount.
- Withdrawal: Represents a withdrawal operation with a specific amount.
- GetBalance: Used to fetch the current balance of the account.
- Main: The entry point of the system. It simulates random deposit and withdrawal transactions.

How it Works
------------

1. The Main class creates an Akka actor system.
2. The bank account starts with a balance of £100.
3. A loop runs a series of random transactions (10 in total), which could be deposits or withdrawals.
4. After all transactions, the balance is checked again before the system terminates.

How to Run
----------

To run the project:

1. Clone the repository.
2. Compile the project using a Java compiler:
   javac -classpath akka-actor-2.6.14.jar *.java
3. Run the Main class to start the system:
   java -classpath .:akka-actor-2.6.14.jar com.example.Main

Dependencies
------------

- Akka Framework (version 2.6.x)
- Java 8 or above

Future Improvements
-------------------

- Add support for multiple bank accounts.
- Implement persistence for transactions.
- Add user authentication and security features.

License
-------

This project is licensed under the MIT License.
