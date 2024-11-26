# PlayerCommunication

## Description
This project facilitates communication between two players. It supports two modes of operation:
- Single JVM Execution:
  - Both players communicate within the same JVM.
  - Use the `start_communication.sh` script to run this mode.
- Separate JVM Execution:
  - Players communicate using separate JVMs.
  - Start the `start_server.sh` script first and then the `start_client.sh` script in separate terminals.

## Requirements
- Java 11 or higher
- Maven

## Usage

### 1. Compile the Project
To compile the project source code, use the following command:
mvn clean compile

### 2. Single JVM Execution
If you want both players to communicate within a single JVM, use the following script:
bash ./start_communication.sh

### 3. Separate JVM Execution
For players to communicate using separate JVMs:

1. Start the PlayerServer in one terminal:
   bash ./start_server.sh

2. Start the PlayerClient in another terminal:
   bash ./start_client.sh

This approach allows the server and client to run on separate JVMs.

## Project Structure
```
The project consists of the following files and directories:
project-root/
├── pom.xml                     
├── src/                       
│   ├── main/
│   │   ├── java/              
│   │   │   └── com/playerCommunication/
│   │   │       ├── Player.java
│   │   │       ├── MultiJVM/
│   │   │       │   ├── PlayerClient.java
│   │   │       │   └── PlayerServer.java
│   │   │       └── SingleJVM/
│   │   │           └── PlayerCommunication.java
│   │   └── resources/         
│   └── test/                  
├── start_server.sh            
├── start_client.sh             
├── start_communication.sh       
└── README.md                  
```

