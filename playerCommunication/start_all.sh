#!/bin/bash

echo "Compiling Maven project..."
mvn clean compile

echo "Starting PlayerServer in a new terminal..."
gnome-terminal -- bash -c "java -cp target/classes com.playerCommunication.MultiJVM.PlayerServer; exec bash"

echo "Starting PlayerClient in a new terminal..."
gnome-terminal -- bash -c "java -cp target/classes com.playerCommunication.MultiJVM.PlayerClient; exec bash"

echo "Both PlayerServer and PlayerClient are running in separate terminals."
