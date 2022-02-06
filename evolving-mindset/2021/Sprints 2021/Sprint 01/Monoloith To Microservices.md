## Monolith To Microservices 
### What Are Microservices?
- Microservices are independently deployable services modeled around a business domain. 
    - They communicate with each other via networks, and 
    - as an architecture choice offer many options for solving the problems you may face. 
    - It follows that a microservice architecture is based on multiple collaborating microservices.
- They are a type of service-oriented architecture (SOA), 
    - albeit one that is opinionated about how service boundaries should be drawn, and that independent deployability is key. 
    - Microservices also have the advantage of being technology agnostic.
- From a technology viewpoint, 
    - microservices expose the business capabilities that they encapsulate via one or more network endpoints. 
    - Microservices communicate with each other via these networks—making them a form of distributed system. 
    - They also encapsulate data storage and retrieval, exposing data, via well-defined interfaces. 
    - So databases are hidden inside the service boundary.
Independent Deployability
    - Independent deployability is the idea that 
    - we can make a change to a microservice and deploy it into a production environment without having to utilize any other services. 
    - More importantly, it’s not just that we can do this; it’s that this is actually how you manage deployments in your system. 
    - It’s a discipline you practice for the bulk of your releases. 
    - This is a simple idea that is nonetheless complex in execution.
    - ensure you embrace the concept of independent deployability of your microservices. 
    - Get into the habit of releasing changes to a single microservice into production without having to deploy anything else. 
    - To guarantee independent deployability, 
    - we need to ensure our services are loosely coupled
    - we need to be able to change one service without having to change anything else. 
    - This means we need explicit, well-defined, and stable contracts between services. 
    - Some implementation choices make this difficult
        - the sharing of databases, for example, is especially problematic. 
    - The desire for loosely coupled services with stable interfaces guides our thinking about how we find service boundaries in the first place.
Modeled Around a Business Domain
- Making a change across a process boundary is expensive. 
    - If you need to make a change to two services to roll out a feature, and 
    - orchestrate the deployment of these two changes, 
    - that takes more work than making the same change inside a single service 
        - (or, for that matter, a monolith). 
    - It therefore follows that we want to find ways of ensuring we make cross-service changes as infrequently as possible.
-  it isn’t possible to share real-world stories. 
- The company in question is Music Corp, 
    - a large multi-national organization that somehow remains in business, 
    - despite it focussing almost entirely on selling CDs.
