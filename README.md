For my game project, I'm going to use three design patterns to make things work better. 
First, I'll use Singleton to keep all my game settings in one place so they're easy to change. 
Then, I'll use Factory Method to make different parts of the game, like enemies, without getting 
everything mixed up. Lastly, Observer will help parts of the game talk to each other when stuff 
happens, like scoring points or moving levels. This should make my game easier to handle and more fun.
^^Part A^^

Part B:
Singleton Source: https://refactoring.guru/design-patterns/singleton

Description: The Singleton pattern is a software design pattern that ensures a class has only one instance while 
providing a global point of access to that instance. This pattern is especially useful in scenarios where multiple 
parts of a program need to interact with the same state or resource, such as in managing connections to a database, 
logging, or, as in my RPG project, managing enemy spawns. In the context of my RPG, I applied the Singleton pattern 
to the EnemyManager class. The primary goal was to centralize the control and management of enemy spawns throughout 
the game, ensuring consistency and preventing issues that might arise from having multiple instances, such as 
duplicate enemies or inconsistent enemy states. To implement this, I first made the EnemyManager's constructor 
private, preventing any external class from instantiating it directly. Then, I created a private static variable 
within EnemyManager to hold its single instance and provided a public static method, getInstance(), that returns 
this instance. This method checks if the instance exists; if not, it creates it, thus ensuring that only one instance 
of EnemyManager is ever created and accessible globally. By employing the Singleton pattern in this manner, I ensured 
that all parts of my RPG interact with a single, unified enemy management system. This design choice streamlined the 
enemy spawn process, making it more manageable and less error-prone, as all enemy spawn requests funnel through a 
single point of access. The Singleton pattern not only enhanced the integrity and consistency of the enemy management 
logic but also provided a clear and concise way to access and utilize the EnemyManager throughout my game's codebase. 

Factory Method Source:https://www.geeksforgeeks.org/factory-method-for-designing-pattern/

Description: The Factory Method is a creational pattern that provides an interface for creating objects in a 
superclass but allows subclasses to alter the type of objects that will be created. This pattern is pivotal when 
the exact types and dependencies of the objects cannot be known until runtime, or when a class wants its subclasses 
to specify the objects it creates. I implemented this pattern through the EnemyFactory class within my RPG, aiming 
to encapsulate the logic required to instantiate different types of enemy characters based on the game's current 
context, such as the floor level. The createEnemy static method within EnemyFactory serves as the factory method, 
determining the type of enemy to instantiate based on input parameters — the enemy type and the floor number. This 
method uses the floor number to calculate a scaling factor, which then adjusts the health and attack attributes of 
the spawned enemies, ensuring that the game's difficulty scales with the player's progression. The createEnemy 
method significantly simplifies enemy creation, abstracting the complexity of instantiating various enemy types with 
differing attributes. For example, spawning a "small" orc involves applying a specific scaling factor to its health 
and attack, while a "boss" troll receives a more substantial scaling to represent its higher difficulty level. This 
abstraction not only enhances the flexibility and scalability of the enemy spawning mechanism but also adheres to 
the principle of open/closed design by being open to extension (for new enemy types) while being closed for modification.
Through the EnemyFactory, I leveraged the Factory Method pattern to centralize enemy creation, providing a clear, 
maintainable, and scalable approach to generating diverse enemy instances.

Strategy Design Pattern Source: https://www.freecodecamp.org/news/a-beginners-guide-to-the-strategy-design-pattern/#:~:text=The%20Strategy%20Design%20Pattern%20is,statically%20choosing%20a%20single%20one.

Description: The Strategy pattern helps in changing the behavior of the program dynamically, which is perfect for 
managing different combat scenarios and effects that can occur during the game. For example, in combat, the actions 
that can be taken—like attacking or using special abilities—change based on the situation, such as the player's health, 
the enemy's speed, and even the special effects caused by the game's environment (like the floor effects in the dungeon).
The CombatManager makes these combat decisions by considering the current state of the game. It adjusts the combat rules 
on the fly, like applying double damage or swapping speed between the player and enemy, showcasing the flexibility 
provided by the Strategy pattern. This way, the combat system can easily adapt and expand with new rules or effects 
without needing major changes to the code. For instance, the choice between a regular attack and using a special ability 
is made dynamically during the player's turn, based on certain conditions like the player's mana. This approach makes 
the combat more interesting and less predictable, which was exactly my goal. By implementing the Strategy pattern, 
I was able to keep the combat logic separate and easily manageable, making the game more fun and engaging.

