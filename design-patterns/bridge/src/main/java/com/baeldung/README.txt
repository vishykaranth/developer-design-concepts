
Bridge Design Pattern
—————————————————————


Problem
———————

"Hardening of the software arteries" has occurred by using subclassing of an abstract base class to provide alternative implementations. This locks in compile-time binding between interface and implementation. The abstraction and implementation cannot be independently extended or composed.


Intent
——————

Bridge pattern is about preferring composition over inheritance. Implementation details are pushed from a hierarchy to another object with a separate hierarchy.

Bridge is a synonym for the "handle/body" idiom. This is a design mechanism that encapsulates an implementation class inside of an interface class. The former is the body, and the latter is the handle. The handle is viewed by the user as the actual class, but the work is done in the body. "The handle/body class idiom may be used to decompose a complex abstraction into smaller, more manageable classes. The idiom may reflect the sharing of a single resource by multiple classes that control access to it (e.g. reference counting)."

Decouple an abstraction from its implementation so that the two can vary independently. Publish interface in an inheritance hierarchy, and bury implementation in its own inheritance hierarchy.
Beyond encapsulation, to insulation


Sec Security System is a security and electronic company which produces and assembles products for cars. It delivers any car electronic or security system you want, from air bags to GPS tracking system, reverse parking system etc. Big car companies use its products in their cars. The company uses a well defined object oriented approach to keep track of their products using software which is developed and maintained by them only. They get the car, produce the system for it and assemble it into the car.

Recently, they got new orders from BigWheel (a car company) to produce central locking and gear lock system for their new xz model. To maintain this, they are creating a new software system. They started by creating a new abstract class CarProductSecurity, in which they kept some car specific methods and some of the features which they thought are common to all security products. Then they extended the class and created two different sub classes named them BigWheelXZCentralLocking, and BigWheelXZGearLocking.

After a while, another car company Motoren asked them to produce a new system of central locking and gear lock for their lm model. Since, the same security system cannot be used in both models of different cars, the Sec Security System has produced the new system for them, and also has created to new classes MotorenLMCentralLocking, and MotorenLMGearLocking which also extend the CarProductSecurity class.


So far so good, but what happens if another car company demands another new system of central locking and gear lock? One needs to create another two new classes for it. This design will create one class per system, or worse, if the reverse parking system is produced for each of these two car companies, two more new classes will be created for each of them.

A design with too many subclasses is not flexible and is hard to maintain. An Inheritance also binds an implementation to the abstraction permanently, which makes it difficult to modify, extend, and reuse the abstraction and implementation independently.

Please note that, the car and the product should vary independently in order to make the software system easy to extend and reusable.

The Bridge design pattern can resolve this problem, but before that, let us first have some details about the Bridge Pattern.


The Bridge Pattern’s intent is to decouple an abstraction from its implementation so that the two can vary independently. It puts the abstraction and implementation into two different class hierarchies so that both can be extend independently.

The components of the Bridge Pattern comprise of an abstraction, refined abstraction, an implementer, and concrete implementer.

An abstraction defines the abstraction’s interface and also maintains a reference to an object of type implementer, and the link between the abstraction and the implementer is called a Bridge.

Refined Abstraction extends the interface defined by the abstraction.

The Implementer provides the interface for implementation classes (concrete implementers).

And the Concrete Implementer implements the Implementer interface and defines its concrete implementation.

The Bridge Pattern decouples the interface and the implementation. As a result, an implementation is not bound permanently to an interface. The implementation of an abstraction can be configured at run-time. It also eliminates compile-time dependencies on the implementation. Changing an implementation class doesn’t required recompiling the abstraction class and its clients. The Client only needs to know about the abstraction and you can hide the implementation from them.


Solution 
————————

Instead of creating a subclass for each product per car model in the above discussed problem, we can separate the design into two different hierarchies. One interface is for the product which will be used as an implementer and the other will be an abstraction of car type. The implementer will be implemented by the concrete implementers and provides an implementation for it. On the other side, the abstraction will be extended by more refined abstraction.


Usages
——————

You want to avoid a permanent binding between an abstraction and its implementation. This might be the case, for example, when the implementation must be selected or switched at run-time.
Both the abstractions and their implementations should be extensible by sub-classing. In this case, the Bridge pattern lets you combine the different abstractions and implementations and extend them independently.

Changes in the implementation of an abstraction should have no impact on clients; that is, their code should not have to be recompiled.

You want to share an implementation among multiple objects (perhaps using reference counting), and this fact should be hidden from the client.

You want run-time binding of the implementation.

You have a proliferation of classes resulting from a coupled interface and numerous implementations

You want to share an implementation among multiple objects

You need to map orthogonal class hierarchies.

You want to avoid a permanent binding between an abstraction and its implementation. This might be the case, for example, when the implementation must be selected or switched at run-time.
Both the abstractions and their implementations should be extensible by sub-classing. In this case, the Bridge pattern lets you combine the different abstractions and implementations and extend them independently.

Changes in the implementation of an abstraction should have no impact on clients; that is, their code should not have to be recompiled.

You want to share an implementation among multiple objects (perhaps using reference counting), and this fact should be hidden from the client.



Example
———————

Consider the domain of "thread scheduling". There are two types of thread schedulers, and two types of operating systems or "platforms". Given this approach to specialization, we have to define a class for each permutation of these two dimensions. If we add a new platform (say ... Java's Virtual Machine), what would our hierarchy look like?

What if we had three kinds of thread schedulers, and four kinds of platforms? What if we had five kinds of thread schedulers, and ten kinds of platforms? The number of classes we would have to define is the product of the number of scheduling schemes and the number of platforms.

The Bridge design pattern proposes refactoring this exponentially explosive inheritance hierarchy into two orthogonal hierarchies – one for platform-independent abstractions, and the other for platform-dependent implementations.

The Bridge pattern decouples an abstraction from its implementation, so that the two can vary independently. A household switch controlling lights, ceiling fans, etc. is an example of the Bridge. The purpose of the switch is to turn a device on or off. The actual switch can be implemented as a pull chain, simple two position switch, or a variety of dimmer switches.


Consider you have a weapon with different enchantments and you are supposed to allow mixing different weapons with different enchantments. What would you do? Create multiple copies of each of the weapons for each of the enchantments or would you just create separate enchantment and set it for the weapon as needed? Bridge pattern allows you to do the second.


Participants
————————————

The classes and objects participating in this pattern are:

Abstraction/ BusinessObject: defines the abstraction's interface. maintains a reference to an object of type Implementor.

RefinedAbstraction/ CustomersBusinessObject: extends the interface defined by Abstraction.

Implementor/ DataObject:  defines the interface for implementation classes. This interface doesn't have to correspond exactly to Abstraction's interface; in fact the two interfaces can be quite different. Typically the Implementation interface provides only primitive operations, and Abstraction defines higher-level operations based on these primitives.

ConcreteImplementor/ CustomersDataObject: implements the Implementor interface and defines its concrete implementation.


Structure
—————————

The Client doesn't want to deal with platform-dependent details. The Bridge pattern encapsulates this complexity behind an abstraction "wrapper".

Bridge emphasizes identifying and decoupling "interface" abstraction from "implementation" abstraction.

Decoupling the object's interface

Improved extensibility (you can extend (i.e. subclass) the abstraction and implementation hierarchies independently)

Hiding details from clients




Check list
——————————

Decide if two orthogonal dimensions exist in the domain. These independent concepts could be: abstraction/platform, or domain/infrastructure, or front-end/back-end, or interface/implementation.

Design the separation of concerns: what does the client want, and what do the platforms provide.

Design a platform-oriented interface that is minimal, necessary, and sufficient. Its goal is to decouple the abstraction from the platform.

Define a derived class of that interface for each platform.

Create the abstraction base class that "has a" platform object and delegates the platform-oriented functionality to it.

Define specializations of the abstraction class if desired.




Rules of thumb
——————————————

Adapter makes things work after they're designed; Bridge makes them work before they are.

Bridge is designed up-front to let the abstraction and the implementation vary independently. Adapter is retrofitted to make unrelated classes work together.

State, Strategy, Bridge (and to some degree Adapter) have similar solution structures. They all share elements of the "handle/body" idiom. They differ in intent - that is, they solve different problems.

The structure of State and Bridge are identical (except that Bridge admits hierarchies of envelope classes, whereas State allows only one). The two patterns use the same structure to solve different problems: State allows an object's behavior to change along with its state, while Bridge's intent is to decouple an abstraction from its implementation so that the two can vary independently.

If interface classes delegate the creation of their implementation classes (instead of creating/coupling themselves directly), then the design usually uses the Abstract Factory pattern to create the implementation objects.


