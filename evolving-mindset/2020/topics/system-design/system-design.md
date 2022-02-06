
## System Design  
- Context of System 
- Functional requirements
- Non functional requirements
	- Caching
	- Scaling out, 
	- Replication 
	- High availability 
	- Partitioning, 
	- Shard-ing 
	- Load Balancing 
- Brainstorming with Interviewer 
- Traffic estimates 
- Storage estimates 
- Bandwidth estimates 
- Memory estimates 
- System APIs
- Database Schema 
- High Level Design 

### Understand Problem and Scope
- Recognize stakeholders and prioritize them. Create a RACI matrix
- Understand business drivers of the project
- Recognize end-users of the project and understand how they use that system
- Check functional requirements
- Define external dependencies
- Suggest additional features
- Remove items that interviewer considers out of scope

### Think about constraints and non-functional requirements
- (use PASS ME if you do not remember all of NFRs)
- Recognize the number of users
- Estimate users growth rate (for the next year/next five years)
- Define average response time
- Understand database size (current / for the next year/ for the next five years)
- Understand storage size (current / for the next year/ for the next five years)
- Recognize security needs
- Define acceptable downtime of the system
- Recognize the number of requests (per month/per second)
- Estimate reads vs. writes operations percentage
- Define time to market
- Check customer related NFR: legacy/proprietary soft, etc

### Abstract Design 
- Choose which architectural views to define based on the stakeholders’ matrix. Use common C4/4+1/etc otherwise
- Choose Architecture Style (Monolith, SOA — microservices, layered architecture, etc.)
- Choose between cloud solution or on-premise servers
- Consider authentication/authorization and privacy
- Suggest security rules and protocols
- Define infrastructure: load balancing, messaging
- Make a rough overview of any critical algorithm that drives the service
- Consider bottlenecks and determine solutions
- Choose storage type (SQL or NoSQL)
- Understand what data should be cached and how to improve performance/security/availability with caching
- Choose monitoring system and logging. Analytics and automatically reboot the system in case of exceptions
- Define separation between public and restricted areas

### System Design Flow
- Understand the problem and scope:
    - define the use cases, with interviewer's help
    - suggest additional features
    - remove items that interviewer deems out of scope
    - assume high availability is required, add as a use case
- Think about constraints:
    - ask how many requests per month
    - ask how many requests per second (they may volunteer it or make you do the math)
    - estimate reads vs. writes percentage
    - keep 80/20 rule in mind when estimating
    - how much data written per second
    - total storage required over 5 years
    - how much data read per second
- Abstract design:
    - layers (service, data, caching)
    - infrastructure: load balancing, messaging
    - rough overview of any key algorithm that drives the service
    - consider bottlenecks and determine solutions
- Estimates 
    - Traffic estimates
    - Storage estimates
    - Database Schema
    - Bandwidth estimates 
    - Memory estimates 
    - High Level Design 
    
### Architectural Patterns
- Object-oriented architecture (OOA)
- Component-based assembly (CBD) architecture
- Domain-driven design architecture
- Client/server architecture
- Multi-tier distributed computing architecture
- Layered/tiered architecture
- Event-driven architecture (EDA)
- Service-oriented architecture (SOA)
- Microservices architecture (MSA)
- Space-based architecture (SBA)
- Special-purpose architectures         