## Web Crawler 

- State assumptions
- Traffic is not evenly distributed
	- Some searches are very popular, while others are only executed once
- Support only anonymous users
- Generating search results should be fast
- The web crawler should not get stuck in an infinite loop
	- We get stuck in an infinite loop if the graph contains a cycle
- 1 billion links to crawl
	- Pages need to be crawled regularly to ensure freshness
	- Average refresh rate of about once per week, more frequent for popular sites
		○ 4 billion links crawled each month
	- Average stored size per web page: 500 KB
		○ For simplicity, count changes the same as new pages
- 100 billion searches per month
- Exercise the use of more traditional systems - don't use existing systems such as solr or nutch.
- Calculate usage
- Clarify with your interviewer if you should run back-of-the-envelope usage calculations.
- 2 PB of stored page content per month
	- 500 KB per page * 4 billion links crawled per month
	- 72 PB of stored page content in 3 years
- 1,600 write requests per second
- 40,000 search requests per second
- Handy conversion guide:
- 2.5 million seconds per month
- 1 request per second = 2.5 million requests per month
- 40 requests per second = 100 million requests per month
- 400 requests per second = 1 billion requests per month