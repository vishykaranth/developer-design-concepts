## URL Rewriting 
- Assuming 500 million new URLs per month 
- And 100:1 read:write ratio, 
- Summary of the high level estimates for our service would be: 
	- New URLs 200/s
	- URL redirections 20K/s
	- Incoming data 100KB/s
	- Outgoing data 10MB/s
	- Storage for 5 years 15TB
	- Memory for cache 170GB 
- This encoding could be 
	- base36 ([a-z ,0-9]) or 
	- base62 ([A-Z, a-z, 0-9]) and 
	- if we add ‘-’ and ‘.’ we can use base64 encoding. 
	- A reasonable question would be, 
	- what should be the length of the short key? 6, 8 or 10 characters.
		○ Using base64 encoding, 
			□ a 6 letter long key would result in 64^6 = ~68.7 billion possible strings
		○ Using base64 encoding, 
			□ an 8 letter long key would result in 64^8 = ~281 trillion possible strings
	- With 68.7B unique strings, let’s assume six letter keys would suffice for our system.
- We can cache URLs that are frequently accessed.
- We can add a Load balancing layer at three places (C -1- AS -2- Cache, AS -3- DB)
- Purging or DB cleanup, Since storage is getting cheap, we can decide to keep links forever.
- Telemetry, 