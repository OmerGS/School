javac --module-path "C:\Users\omerf\Desktop\Ressource\Semestre 2\R2.02 - Developpement Application IHM\Partie 2\TP4\javafx-sdk-22.0.1\lib" --add-modules javafx.controls -d ../class ../src/controller/*.java
javac --module-path "C:\Users\omerf\Desktop\Ressource\Semestre 2\R2.02 - Developpement Application IHM\Partie 2\TP4\javafx-sdk-22.0.1\lib" --add-modules javafx.controls -d ../class ../src/model/*.java
javac --module-path "C:\Users\omerf\Desktop\Ressource\Semestre 2\R2.02 - Developpement Application IHM\Partie 2\TP4\javafx-sdk-22.0.1\lib" --add-modules javafx.controls -d ../class ../src/view/*.java

java --module-path "C:\Users\omerf\Desktop\Ressource\Semestre 2\R2.02 - Developpement Application IHM\Partie 2\TP4\javafx-sdk-22.0.1\lib" --add-modules javafx.controls -cp ../class view.EvalApp

pause