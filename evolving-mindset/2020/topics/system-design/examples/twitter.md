## Twitter 
- 200 ms latency for timeline generation
- 28 Billion tweet views per day
- most/half messages has video/photo
- Can't just query db every time
	- 28B daily tvs / 200M users = 140 daily page views on average
	- 1 SQL DB query * 10ms = 10ms
	- 1 NoSQL queries * 1ms = 1ms per query
- 1 http request/response * 100ms = 100ms per request
- 1 query + 1 http request = 110ms
	- http response = 201-210ms total per request
- latency cost: 80-150ms
- Complete page rendering on browser = 5 seconds = 5000 ms
	- request time = 5.35 seconds total
- 28B queries / 86400 seconds per day

24,074 queries per second per day (324 SQL servers @ 1kQPS)