## Social Graph 

- 5 billion friend relationships
	- 100 million users * 50 friends per user average
- 400 search requests per second
- Handy conversion guide:
- 2.5 million seconds per month
- 1 request per second = 2.5 million requests per month
- 40 requests per second = 100 million requests per month
- 400 requests per second = 1 billion requests per month


## Social Graph Implementation 

- We won't be able to fit all users on the same machine, we'll need to shard users across Person Servers and access them with a Lookup Service.
- The Client sends a request to the Web Server, running as a reverse proxy
- The Web Server forwards the request to the Search API server
- The Search API server forwards the request to the User Graph Service
- The User Graph Service does the following:
	- Uses the Lookup Service to find the Person Server where the current user's info is stored
	- Finds the appropriate Person Server to retrieve the current user's list of friend_ids
	- Runs a BFS search using the current user as the source and the current user's friend_ids as the ids for each adjacent_node
	- To get the adjacent_node from a given id:
- The User Graph Service will again need to communicate with the Lookup Service to determine which Person Server stores the adjacent_node matching the given id (potential for optimization)
