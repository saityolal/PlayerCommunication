# PlayerServer'ı yeni bir xterm penceresinde başlat
echo "Starting PlayerServer in a new terminal..."
xterm -hold -e "java -cp target/classes com.playerCommunication.MultiJVM.PlayerServer" &

# PlayerClient'ı yeni bir xterm penceresinde başlat
echo "Starting PlayerClient in a new terminal..."
xterm -hold -e "java -cp target/classes com.playerCommunication.MultiJVM.PlayerClient" &
