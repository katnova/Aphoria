# Aphoria

Aphoria is a discord bot written in Java. It is also designed to be a progressively challenging live-project those engaging in learning Java fundamentals.

I wrote this to for one person in particular, random PRs will be ignored.

## Tech Stack

Aphoria is a pure Java project and was written for Java 21 LTS.

[Discord4J](https://discord4j.com/) is utilized to interact with Discords API, and [Maven](https://maven.apache.org/) to build, package, and run the project.

You can technically build, run, and package the project manually, but I wouldn't recommend doing that.

## Java Basics for Beginners

### What is Java?

Java is a high-level, class-based, object-oriented programming language designed to have as few implementation dependencies as possible. It's a "write once, run anywhere" language, meaning that compiled Java code can run on all platforms that support Java without recompilation.

### Key Java Concepts

- **Classes and Objects**: Java is an object-oriented language where everything is an object. Classes are templates or blueprints for creating objects.
- **Packages**: Java organizes related classes into packages (like folders) for better organization and namespace management.
- **Inheritance**: Classes can inherit attributes and methods from other classes, promoting code reuse.
- **Abstract Classes**: Special classes that cannot be instantiated and may contain abstract methods that must be implemented by subclasses.
- **Interfaces**: Defines a contract that classes can implement, enabling polymorphism.
- **Exception Handling**: Java uses try-catch blocks to handle runtime errors gracefully.
- **Access Modifiers**: Keywords like `public`, `private`, and `protected` control the visibility of classes, methods, and fields.

# Getting Setup

> [!IMPORTANT]
> 
> Read each step fully *before* doing anything.

## Maven


### Installing

---

You will need to [download and install Maven](https://maven.apache.org/install.html). (If you are using IntelliJ, you can technically skip this step as it has build-in maven support and will download a wrapper for you. However this guide is written assuming you have installed Maven).

To test if Maven install correctly, run `mvn -v` or `mvn --version`.

**IF** you got a maven version back, and not a "command not found", you've installed it correctly and may continue.

At this point, it is worth giving [this page a read](https://maven.apache.org/run.html), however I'd suggest holding off on running any of the commands yet.


### Initializing the project

---

Maven will download and manage dependencies you choose to use in your project. Maven's configuration for this project can be found in `./pom.xml` (aka the "pom").

> [!NOTE]
> 
> I have added some simple comments in the pom to help you understand it's structure.

To get maven to download the dependency defined in the pom, run:

```bash
mvn install
```

If all went well, you should've gotten a bunch of logs, and at the end of the commands output you should see:

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.846 s
[INFO] Finished at: 2025-04-22T17:30:47-06:00
[INFO] ------------------------------------------------------------------------
```

### Running the project with Maven

---

Now, we're ready to run the project. You can use the following command to compile and run the project.

```shell
mvn compile exec:java
```

You should see something akin (but probably not exactly) to the following. Note the "OUTPUT FROM THE APP STARTS HERE" comment I added.

> [!NOTE]
> 
> Did you get an error here? Ask for help!


```
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< xyz.akat:Aphoria >---------------------------
[INFO] Building Aphoria 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ Aphoria ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ Aphoria ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 1 source file with javac [debug target 21] to target/classes
[INFO] Annotation processing is enabled because one or more processors were found
  on the class path. A future release of javac may disable annotation processing
  unless at least one processor is specified by name (-processor), or a search
  path is specified (--processor-path, --processor-module-path), or annotation
  processing is enabled explicitly (-proc:only, -proc:full).
  Use -Xlint:-options to suppress this message.
  Use -proc:none to disable annotation processing.
[WARNING] Supported source version 'RELEASE_8' from annotation processor 'com.austinv11.servicer.ServicerProcessor' less than -source '21'
[INFO] 
[INFO] --- exec:3.5.0:java (default-cli) @ Aphoria ---
Hello world! <== !!! OUTPUT FROM THE APP STARTS HERE !!!
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.504 s
[INFO] Finished at: 2025-04-22T17:38:03-06:00
[INFO] ------------------------------------------------------------------------
```

And that's it! You're ready to get coding! :3

## Discord4J Basics

[Discord4J](https://discord4j.com/) is a reactive Java wrapper for the Discord API. It allows you to create Discord bots in Java with a clean, modern API.

### Key Concepts

- **DiscordClient**: The main entry point for interacting with Discord's API
- **Gateway**: Manages the WebSocket connection to Discord
- **Event Handling**: Discord4J uses a reactive approach to handle events from Discord
- **Entities**: Objects representing Discord concepts like messages, channels, and users

### How Aphoria Uses Discord4J

Aphoria uses Discord4J to:
1. Connect to Discord using a token from the configuration file
2. Listen for message events in channels
3. Process commands in messages and respond accordingly

The `DiscordService` class handles the connection to Discord and sets up message handlers to process commands.

## Das Project

### File-Structure

---

A brief explanation of the files and folders in this repo.

```shell
- / (project root)
- /.idea <- This folder contains configuration and other information for IntelliJ, and is managed automatically. It is ok if you do not have this folder.
- /.mvn <- This folder is created and managed by Maven. It is ok if you do not have it.
- /src <- All of our code will be under the `src` folder. This is pretty standard.
---- /main
-------- /java <- Our Java code will be in this folder.
------------ /xyz/akat <- These folders are our "package". The package for this project is `xyz.akat`. Our code goes in here.
-------- /resources <- Non-java code goes here, and is used for non-java files we want in our project. Things like configuration files go in here.
---- /test <- Like main, but specifically for tests
- /.gitignore <- This file is specifically for Git, the industry standard code versioning tool. It tells git what files to ignore.
- /pom.xml <- This is where our Maven configuration for this project lives.
- /README.md <- This is the file you're currently reading :D
```

### App Structure Basics

```shell
- /src/main/java/xyz/akat
- Main.java <- This is the entrypoint of the application. In a linear sense, this is where we start to run code.
- /handlers <- Contains classes for handling Discord message commands
---- AbstractMessageHandler.java <- Base class for all message handlers
---- AbstractGenericRegister.java <- Base class for registering handlers
---- MessageHandlerRegister.java <- Registers message handlers with Discord4J
---- PongMessageHandler.java <- Handles the "!ping" command
---- SillyMessageHandler.java <- Handles the "!silly" command
- /services
---- DiscordService.java <- Manages the connection to Discord and sets up message handlers
- /util
---- ConfigLoader.java <- Loads configuration from config.properties
```

### Key Components

#### Main Class

The `Main` class is the entry point of the application. It initializes the configuration and starts the Discord service:

```
public class Main {
    public static void main(String[] args) {
        // Initialize configuration
        if (!ConfigLoader.init()) {
            System.err.println("Failed to initialize configuration. Application may not function correctly.");
        }

        // Start Discord service
        new DiscordService();
    }
}
```

#### Configuration

The application uses a properties file (`config.properties`) to store configuration values:

```
# Discord configuration
discord.token=YOUR_DISCORD_TOKEN_HERE

# Application settings
app.name=Aphoria
app.version=1.0.0
```

The `ConfigLoader` class provides methods to load and access these properties:

```
// Initialize configuration
boolean success = ConfigLoader.init();

// Read a property
String token = ConfigLoader.read("discord.token");
```

#### Message Handlers

Message handlers process commands from Discord messages. Each handler responds to a specific command:

1. Create a class that extends `AbstractMessageHandler`
2. Define the command in the constructor
3. Implement the `onMessage` method to handle the command

Example:

```
public class GreetingMessageHandler extends AbstractMessageHandler {
    public GreetingMessageHandler() {
        super("!hello");
    }

    @Override
    public void onMessage(Message message) {
        super.replyToMessageInChannel("Hello there!");
    }
}
```

To add a new handler, register it in the `DiscordService` constructor:

```
// In DiscordService.java constructor
DiscordClient.create(token)
        .withGateway(client -> new MessageHandlerRegister(client)
                .register(new PongMessageHandler())
                .register(new SillyMessageHandler())
                .register(new YourNewMessageHandler())  // Add your handler here
                .getProvider())
        .block();
```

#### Abstract Classes and Inheritance in Message Handlers

Aphoria makes extensive use of abstract classes and inheritance to create a flexible and extensible message handling system. Understanding these concepts is crucial for working with the codebase.

##### What are Abstract Classes?

An abstract class in Java is a class that cannot be instantiated on its own and is designed to be subclassed. Abstract classes can contain:

- Regular methods with implementations
- Abstract methods (methods without implementations that must be overridden by subclasses)
- Fields and constructors
- Static methods and fields

Abstract classes are declared using the `abstract` keyword:

```java
public abstract class AbstractMessageHandler {
    // Fields, methods, etc.

    // Abstract method that must be implemented by subclasses
    abstract void onMessage(Message message);
}
```

##### How Class Extension Works

When a class extends another class, it inherits all the non-private fields and methods from the parent class. The subclass can:

1. Use the inherited methods and fields directly
2. Override methods to provide its own implementation
3. Add new methods and fields
4. Call the parent class's methods using the `super` keyword

##### Abstract Classes in Aphoria's Message Handling System

Aphoria uses a two-level abstraction for handling Discord messages:

1. **AbstractGenericRegister<T, R>**: A generic abstract class for registering and managing handlers for any type of Discord event
   - Uses Java generics to work with different event and handler types
   - Provides methods for registering handlers and managing the event flow
   - Contains an abstract `seekHandler` method that subclasses must implement

2. **AbstractMessageHandler**: An abstract base class specifically for handling Discord message commands
   - Provides common functionality for all message handlers
   - Contains an abstract `onMessage` method that subclasses must implement
   - Handles message matching and response logic

3. **MessageHandlerRegister**: Extends AbstractGenericRegister to specifically handle message events
   - Implements the abstract `seekHandler` method to route message events to handlers
   - Manages a collection of AbstractMessageHandler instances

4. **Concrete Handlers**: Classes like PongMessageHandler and SillyMessageHandler that extend AbstractMessageHandler
   - Implement the abstract `onMessage` method to define specific behavior
   - Use the parent class's functionality for common tasks

##### Example: The Inheritance Chain in Action

Here's how a message flows through the system:

1. A message is received by Discord4J
2. MessageHandlerRegister's `seekHandler` method is called with the message event
3. The method passes the message to each registered AbstractMessageHandler
4. Each handler checks if the message matches its command
5. If matched, the handler calls its `onMessage` method to respond

For example, when a user types "!ping", the following happens:

```java
// In MessageHandlerRegister.java
@Override
protected void seekHandler(MessageCreateEvent event) {
    super.log("Seeking handler for message: " + event.getMessage().getContent());
    super.getHandlers().forEach(handler -> handler.handle(event.getMessage()));
}

// In AbstractMessageHandler.java
protected void handle(Message message) {
    final boolean shouldHandle = message.getContent().equalsIgnoreCase(command);

    if (shouldHandle) {
        this.message = message;
        onMessage(message);  // This calls the implementation in PongMessageHandler
    }
}

// In PongMessageHandler.java
@Override
public void onMessage(Message message) {
    super.replyToMessageInChannel("Pong!");  // Uses parent class method to reply
}
```

This design makes it easy to add new message handlers without modifying existing code, following the Open/Closed Principle of software design.

## Development Workflow

### Adding a New Feature

1. **Understand the requirement**: Clearly define what the new feature should do
2. **Plan your implementation**: Identify which classes need to be modified or created
3. **Write the code**: Implement the feature following Java best practices
4. **Test your changes**: Run the application and verify that the feature works as expected
5. **Refine and optimize**: Improve your code based on testing results

### Common Tasks

#### Adding a New Command

1. Create a new class that extends `AbstractMessageHandler`
2. Define the command in the constructor
3. Implement the `onMessage` method
4. Register the handler in the `DiscordService` constructor

#### Modifying Configuration

1. Add the new property to `config.properties`
2. Access the property using `ConfigLoader.read("property.name")`

## Troubleshooting

### Common Issues

#### Application Won't Start

- Verify that Maven is installed correctly
- Check that all dependencies are downloaded
- Ensure the Discord token is valid
- Look for error messages in the console output

#### Command Not Responding

- Verify that the handler is registered in `DiscordService`
- Check that the command string matches exactly
- Ensure the bot has the necessary permissions in Discord

#### Maven Build Errors

- Run `mvn clean` to clear previous build artifacts
- Verify that your Java version matches the project requirements (Java 21 LTS)
- Check for syntax errors in your code

## Learning Resources

### Java

- [Oracle Java Tutorials](https://dev.java/learn/)
- [Baeldung Java Guides](https://www.baeldung.com/java-tutorial)
- [Java Programming for Beginners](https://www.codecademy.com/learn/learn-java)
- [Java Design Patterns](https://java-design-patterns.com/)

### Discord4J

- [Discord4J Documentation](https://docs.discord4j.com/)
- [Discord4J GitHub Repository](https://github.com/Discord4J/Discord4J)
- [Discord4J Examples](https://github.com/Discord4J/Discord4J/tree/master/examples)

### Maven

- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [Maven Central Repository](https://search.maven.org/)

### IDEs for Java Development

- [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Powerful IDE with excellent Java support
- [Eclipse](https://www.eclipse.org/) - Free and open-source IDE
- [Visual Studio Code](https://code.visualstudio.com/) with [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
