# Simple Banking 

This project includes swagger ui for easier access to controllers. 
You will be automatically navigated to swagger UI when you visit `http://localhost:8085`.
The IndexController you will see in swagger UI is for redirecting to swagger UI.
You will not be needed to utilize it manually.

You need to create a local database that will be used to store the data.

This project uses PostgreSQL, and it will create 3 tables in your local database 
(dev's preference PGAdmin can be used):
- `account` 
(**account_number**: String, **balance**: double, **create_date**: OffsetDateTime, **owner**: String)
- `account_transactions` 
(**account_number**: String, **transaction_id**: Integer)
- `transaction` 
(**id**: Integer, **amount**: double, **approval_code**: String, **date**: OffsetDateTime, **type**: String)

An account data will be automatically added when you start this application.
You can find and modify the data in `src/main/resources/import.sql`

Polymorphism is used for transactions and follows the Open-Closed Principle.  
When a new transaction type is added, its `apply()` method is overridden,  
so no other changes are needed in the codebase.

BaseEntity is used for centralizing the ID logic to avoid code repetitiveness. (DRY)

JPA is used for its built-in methods for common database operations (like save, find etc.)
and avoid inline SQL queries.

`PhoneBillPaymentTransaction` is added for an example for Open-Closed Principle,
its behaves just like `WithdrawalTransaction`, 
but any apply() method can be implemented freely.
