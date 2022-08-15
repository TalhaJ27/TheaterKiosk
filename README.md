# TheaterKiosk


Developed software implementable in self-help kiosk at theaters.
![alt text](https://github.com/TalhaJ27/TheaterKiosk/blob/master/Theater-Kiosk.jpg?raw=true)


We Used several design patterns including Singleton, Proxy, Factory, and Model View Controller Patterns.


### Model-View-Controller
![alt text](https://github.com/TalhaJ27/TheaterKiosk/blob/master/MVC1.png?raw=true)

![alt text](https://github.com/TalhaJ27/TheaterKiosk/blob/master/MVC2.png?raw=true)

![alt text](https://github.com/TalhaJ27/TheaterKiosk/blob/master/MVC3.png?raw=true)

MVC is a software design pattern commonly used for developing user interfaces that divide the related program logic into three interconnected elements: Model, View and Controller. This is done to separate internal representations of information from the ways information is presented to and accepted from the user.

### Singleton Pattern
![alt text](https://github.com/TalhaJ27/TheaterKiosk/blob/master/Singleton.png?raw=true)

Singleton Pattern basically means defining a class that has only one instance and providing a global point of access to it". In other words, a class must ensure that only a single instance should be created and a single object can be used by all other classes.

So, it made sense to implement Singleton Pattern in Booking Service because Booking information can be called from different classes and the information they will be getting is from a single user like their transaction ID which is important for storing and identifying for future references

### Proxy Pattern
![alt text](https://github.com/TalhaJ27/TheaterKiosk/blob/master/Proxy.png?raw=true)

A proxy object provide a surrogate or placeholder for another object to control access to it. A proxy is basically a substitute for an intended object which we create due to many reasons e.g. security reasons or cost associated with creating fully initialized original object.

I used the proxy pattern to add an additional security layer around the original object.
This Protected information can be used to verify any customer concerns. In the GUI user can click on view Account Info and view such information(protected view email, and phone #). While using the kiosk, it is important to Protect sensitive information from the public.

### Factory Pattern
![alt text](https://github.com/TalhaJ27/TheaterKiosk/blob/master/Factory%20Pattern.png?raw=true)


The factory method pattern uses Factory methods to deal with the problems of creating objects without specifying the exact class of the object to be created.
It promotes the loose-coupling by eliminating the need to bind application-specific classes into the code. That means the code interacts solely with the interface, so that it will work with any classes that implement that interface.
Factory method patterns allows the sub-classes to choose with type of object to create.


Design patterns were assigned to differnt group members.
This project was completed with the help of my group members:
- Md Ashikur Rahman
- Elvis Moyolema
- Asif Talukder
