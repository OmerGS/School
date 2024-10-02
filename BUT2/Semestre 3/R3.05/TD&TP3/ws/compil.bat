javac --module-path "../javafx-sdk-22.0.1\lib" --add-modules javafx.controls -d ../class ../src/*.java

java --module-path "../javafx-sdk-22.0.1\lib" --add-modules javafx.controls -cp ../class src.Game

pause