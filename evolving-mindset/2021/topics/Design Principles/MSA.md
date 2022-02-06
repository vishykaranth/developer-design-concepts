# Microservices and Object Oriented Programming

Sam Newman defines in his book [Building Microservices]( https://www.amazon.com.br/Building-Microservices-Designing-Fine-Grained-Systems-ebook/dp/B00T3N7XB4 ) (2015) that there are two key traits that make a good service in a Microservice Design: (1) **Loose Coupling** and (2) **High Cohesion**. These two traits have been previously raised in the context of Object-Oriented Design by authors such as Robert C. Martin, Martin Fowler and Sandi Metz. But what makes Object-Oriented Design and Microservices converge into these two design principles? The simplest answer can be: **Message Passing**. Both Microservices and Object-Orientation have Message Passing as their core for communication between its atomic entities. Where atomic entities is the *celullar like organism* that lives in each paradigm, being **Services** for Microservices and **Objects** for Object-Oriented Programming.

### Integrating Services

In this article, I intent to focus on the 4th Capther of Newman's book named *Integrating Services*. In this book, Newman walks us through different integration patterns for Microservices and the best and not-so-best way to implement Service-to-Service communication. Here are some points Newman highlights in this chapter:

- **Avoid Breaking Changes:** Make sure whoever is consuming your service doesn't have to make changes every time your service changes. 
  *i.e. Make your service more cohesive by avoiding having to change things in many places* 
- **Keep your APIs technology agnostic:** You'd have a hard-time developing Microservices if you bound yourself to a technology specific communication protocol (such as Java RMI). You're off better with agnostic HTTP based protocols such as REST. 
  *i.e. Make your service more loosely coupled by allowing consumers to use whatever tool is best for the job*
- **Make your service simple for Consumers:** The consumer must have an easy way to interact with the service, but this needs to be weighted against the technology agnostic principle. You can make it a lot easier to consume by providing a client library but at the same time this would make your services more coupled. 
  *i.e. Make your service more cohesive by making it simple to interact with. Your services provide everything a consumer needs to use it*
- **Hide Internal Implementation Detail:** Making a service simple to use is also about providing the right abstraction.  This point is very similar to the one above. Hiding implementation details and providing only the minimal boundary necessary for the consumer to send the necessary messages to this service. Thus, make your service highly cohesive, and expose simple interfaces. 
  *i.e. Make your service more cohesive by providing simple and easy to use interfaces*

#### References to Object Oriented Design

Keeping in mind the comparison between Microservices and Object-Oriented Design, we can leverage years of wisdom on the OO Design front when creating Microservices. When it comes to design principles there quite a few things that we can corroborate with Newman's points above. The idea of message passing is in the roots of Object Oriented design. It's even mentioned by Alan Kay it should have been called Message-Oriented programming due to it's focus on passing message between cell like structures. 

**Law of Demeter:** It illustrates how our objects don't need to be exposed to more information than they need. There is a good [article by David Bock's that explains the Law of Demeter very well](https://www2.ccs.neu.edu/research/demeter/demeter-method/LawOfDemeter/paper-boy/demeter.pdf). This law specifies that he must provide real world abstractions that makes sense in our code. These interfaces are *easy to use* and they don't expose more of the internal behavior than it's needed for the consumer to do it's job - or better - to send it's message. 

**Liskov Substitution Principle (LSP):** In his Book Clean Architecture (2018) Robert C. Martin provides an example of LSP at the architecture level by two services that provide the same API for Taxi ordering (Acme and PurpleCab). Even though they respect the same URI format for locating resources (e.g. `GET /driver/<id>`) and for posting driver's pickups (e.g. `POST /driver/<id>/pickupRequest`), they differ in property names, for example, PurpleCab expect `destination` and Acme `dest` as a property on the POST request. This could even be more subtle by having the same property name but different data structure. The problem is that now the consumer must identify *who* is the receiver in order to create the proper message. This is a smell when discussion Object-Oriented Inheritance. But as shown in the Martin's book example, this can be applied 1:1 to REST APIs. 

**Dependency Inversion Principle (DIP):** The idea behind this principle is to hide the concrete classed under abstractions. All references and message passing must be done through it's abstraction (i.e. Interface or Abstract Class in Java) and not to the concrete class itself. This way you can provide Stable Abstractions and avoid major changes in consumer when making changes to the concrete implementations. In my opinion, the structure of a Service must follow a similar pattern. The interface to interact with a service must be its stable interface and the implementation details are hidden inside the Service. Taking this principle even further, consumers must depend on a stable interface of that service, and the Service that is implementing that interface should not matter to the consumer. This means that if we want to re-implement a service in another programming language or a different framework providing the same interface, we can easily change the receiver's end without even informing the consumers. Making our Services loosly coupled, technology agnostic and independently deployable. 

I think all of the **SOLID Principles** offer good insights to designing loosely coupled and highly cohesive services, but I've highlighted the two above because they seem to be closer to the points that Newman brings in his books. 

### Integration Patterns 

In Newman's book, he mentions a few integrations patterns found in Microservices-based architectures.

**1. Shared Database:** The simplest way to integrated services - by having the same data storage.

**2. Service-to-Service Communication:** It gets a bit messy with definitions here, but what I gather is that is comes down to two different characteristics, regardless of the technology used:

#### Synchronous vs Asynchronous

This point comes down to either services calling each other directly, or communicating through a message queue. Regardless if services communicate with each other view RPC or HTTP, request/response style communication is simpler but limited. Any service that receives a request and then send requests to a dozen other services is likely to be a bottle-neck to the original caller. Message Queues allow consumers to not tie themselves to the receivers (i.e. open socket or waiting for an HTTP response). It does, however, increase the levels of complexity significantly by having to place Queues for Service-to-Service communication. As the author puts, it comes down to changin how you approach messages sent. It goes from *"these things need to be done"* to *"these things happened"*. Asynchronous communication is event-driven communication.

#### Orchestration vs Choreography

These concepts are not coined by Newman either, in fact, it's something inherited from SOA (Servoce Oriented Architecture). I've found a [few articles](#orchestration-and-choreography) from early 2000's explaining these. The concept can still be applied to Microservices as is, as Newman states, they are *styles* of architecture. **Orchestration** is where you have a central decision maker, who delegates activities to mindless workers who are task driven. This is very similar to a Primary-Worker threads or Client-Server communication in Networking. Where **Choreography** is when you have mindful services who can act and decide on themselves and communicate with other services. These services are like actors in a play, each have its role and they rely on their fellow actors to play their part accordingly. Much like a P2P communication. 

---

We notice that orchestration goes hand-in-hand with synchronous communication and choreography does the same to asynchronous. This shows the reader how not only there is some sort of a link between the two but they form sort of a maturity model of Service-to-Service communication. It's reasonable to assume that the author is showing that Asynchronous-Choreography based systems are more complex but more mature from a Microservice perspective. 

> Event-based collaboration is also highly decoupled

> I have found that systems that tend more toward the choreographed approach are more loosely coupled

Those two quotes from the book resonate to the mantra of *Loose Coupling and High Cohesion*, which defines a well designed services. I get it there are reasons why you'd create a synchronous service, specially for the sake of simplicity. But even though, in the long run, if you have a big application and it needs to be maintained, asynchronous and choreography should be what stands for a well designed architecture. 

### Object Oriented Affordences 

The last point I want to talk about is something Sandi Metz have said on her talk [Polly wants to send a message](https://www.youtube.com/watch?v=YtROlyWWhV0). In this great talk, she offers a few hihglights on OO design (like [Churn vs Complexity by Michael Feathers](https://www.agileconnection.com/article/getting-empirical-about-refactoring)), but one major point is when she mentions Affordences we have to make when developing with OO. They are also incredible close to how Newman expects Microservices to be. Here's the list of Affordences:

- **Anthropomorphic:** You should treat objects (and services) as if they are real. 
- **Polymorphic:** You don't care who the receiver is. As long as it respects an interface (and an agnostic protocol). 
- **Loosly-Coupled:** Things don't need to be bound to each other. I have stressed this enough in this article. 
- **Role-playing**: Each object (and service) has a role. Similar to the Choreography concept, all players rely that their colleagues are playing their role accordignly. 
- **Factory-created:** As she says: "*where conditions go to die*". This one I don't think we can related much to Microservices.
- **Message-sending:** As the first paragraph of this artcile mentions, the epiceter of Object-Orientation and Microservices is **Message Passing**. What she says about this affordance is: "*We have objects because we wan't to send messages. We don't send messages because we have objects*".

## Conclusion

There are design gems in the years of knowledge of building Object-Oriented Systems that can be adapted and re-applied to new challanges of Software Architecture. Given that the atomic entities of each paradigm (Services and Objects) are treated equally, these design principles can be used almost interchangedly in a Microservices based application. This doesn't mean that services must be written using an Object-Oriented programming language, far from that. What it means is that how you structure the communication between Services via message passing is similar to how you structure the communication between Objects. The code that lives in Service itself is a whole different universe, which can be it's own isolated box in any technology and design that is the best tool for the job it's trying to solve. As much as the same principles can be applied to the Micro level if the service is using an Object-Oriented language, it's irrelevant to how they are being consumed. 

## References

##### Sam Newman:

[His books](https://samnewman.io/books/): Building Microservices (2015) and Monolith to Microservices (2019)

##### Bounded Context:

- Martin Fowler's [Bounded Context Article](https://martinfowler.com/bliki/BoundedContext.html)
- Eric Evan's [DDD Book](https://www.amazon.com/gp/product/0321125215?ie=UTF8&tag=martinfowlerc-20&linkCode=as2&camp=1789&creative=9325&creativeASIN=0321125215)
- Vaughn Vernon's [Implementing Domain Driven Design Book](https://www.amazon.com.br/Implementing-Domain-Driven-Design-Vaughn-Vernon/dp/0321834577/ref=asc_df_0321834577/?tag=googleshopp00-20&linkCode=df0&hvadid=379787788238&hvpos=1o1&hvnetw=g&hvrand=648373583785777662&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1001681&hvtargid=pla-451193117745&psc=1)

##### Object Oriented Design:

- "The paperboy is being exposed to more information than he needs to be" - Law of Demeter David Bock's [article](https://www2.ccs.neu.edu/research/demeter/demeter-method/LawOfDemeter/paper-boy/demeter.pdf) 
- Alan Kay's [E-mail response](http://userpage.fu-berlin.de/~ram/pub/pub_jf47ht81Ht/doc_kay_oop_en) on Object Orientation
- Robert C. Martin [Clean Architecture Book](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164) - Note to Part III: Design Principles, where Martin explains the SOLID principles
- [Polly wants to send a message](https://www.youtube.com/watch?v=YtROlyWWhV0) talk by Sandi Metz
- [Churn vs Complexity by Michael Feathers](https://www.agileconnection.com/article/getting-empirical-about-refactoring)

##### Orchestration and Choreography:

- Dijkman, R. and Dumas, M., **2004**. Service-oriented design: A multi-viewpoint approach. *International journal of cooperative information systems*, *13*(04), pp.337-368.
- Papazoglou, M.P. and Van Den Heuvel, W.J., **2006**. Service-oriented design and development methodology. *International Journal of Web Engineering and Technology (IJWET)*, *2*(4), pp.412-442.
- Arsanjani, Ali. "Service-oriented modeling and architecture." *IBM developer works* 1 (**2004**): 15.