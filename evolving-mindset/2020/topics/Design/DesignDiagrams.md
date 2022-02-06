- ![](images/Kafka%20Hackathon%202020.png)    
1. Consume Purchase Order from Purchase Order
2. WSZ posts the Orders over Informatica Request Kafka Topic 
3. Informatica consumes the message
4. Informatica acknowledges the receipt of message via Informatica Response Topic
5. WSZ consumes the response
6. Informatica Posts the message to Bulk Event & Payload
7. Bulk Event & Payload --> WIC Raw Log 
8. WIC Raw Log posts the WCS Payload to WCS Request Topic 
9. WCS consumes message from WCS Request Topic and creates Warranty 
10. WCS posts message to WCS Response Topic 

Informatica consumes from WCS Response Topic 
- ![](images/PAS%20Authentication.png)