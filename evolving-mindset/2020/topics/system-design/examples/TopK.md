## Design a systems which finds Top K(Heavy Hitters)
Use Cases
Find 100 most
Viewed videos on youtube
Played songs on Spotify
Shared posts on facebook.
Retweeted tweets on twitter.
Liked Photos on Instagram
Searched keywords on Google.
With Such a scale, database or distributed cache is not an option. We might be dealing with 1M RPS. If we would use DB to track view counts, first the writes/updates would be super slow, and then finding the top K items would require scanning the whole dataset.
May be MapReduce can help. But it is not sufficient. We need to return heavy hitters in as close to realtime as possible.
e.g. Calculate top 100 list for last
1 min, 5 min, 15 mins, 60 mins etc.
This makes this problem in the flavors of stream processing problem.
Requirements
Functional
topK(k, startTime, endTime)
Non-Functional
Scalable(Scales together with increasing amount of data.)
Highly available(survives hardware/network failures, no SPOF)
Highly Performant(few 10s of ms to return top 100 list)
Given the performance requirement, it is a hint that the final list should be pre-calculated and we should avoid heavy calculations while calling the top K API.
Accurate
For e.g. by using data sampling, we may not count every element, but only a small fraction of events.
Approaches:
Hash table, Single Host.
Keep the count of the incoming list of events in a hashmap.
2 Approaches.
Sort the list of entries in the hashmap by frequency and return the first K elements. Time O(nLogn)
Put the elements on a heap of size K. Time-O(nLogK)
This is not scalable as the volume of events incoming goes too high. We may need to process events in parallel.
HashTable, multiple Hosts.
Memory would be a problem if you would store all the youtube videos IDs in the memory you the host. 