## Mint
- Size per transaction:
	- user_id - 8 bytes
	- created_at - 5 bytes
	- seller - 32 bytes
	- amount - 5 bytes
	- Total: ~50 bytes
- 250 GB of new transaction content per month
	- 50 bytes per transaction * 5 billion transactions per month
	- 9 TB of new transaction content in 3 years
	- Assume most are new transactions instead of updates to existing ones
- 2,000 transactions per second on average
- 200 read requests per second on average
- Handy conversion guide:
- 2.5 million seconds per month
- 1 request per second = 2.5 million requests per month
- 40 requests per second = 100 million requests per month
- 400 requests per second = 1 billion requests per month

### Mint - Data Flow 

- The Client sends a request to the Web Server
- The Web Server forwards the request to the Accounts API server
- The Accounts API server places a job on a Queue 
	- such as Amazon SQS or RabbitMQ
	- Extracting transactions could take awhile, 
	- we'd probably want to do this asynchronously with a queue, 
	- although this introduces additional complexity
- The Transaction Extraction Service does the following:
	- Pulls from the Queue and extracts transactions for the given account from the financial institution, 
	- storing the results as raw log files in the Object Store
	- Uses the Category Service to categorize each transaction
	- Uses the Budget Service to calculate aggregate monthly spending by category
		○ The Budget Service uses the Notification Service to let users know if they are nearing 
		○ or have exceeded their budget
	- Updates the SQL Database transactions table with categorized transactions
	- Updates the SQL Database monthly_spending table with aggregate monthly spending by category
	- Notifies the user the transactions have completed through the Notification Service:

Uses a Queue (not pictured) to asynchronously send out notifications