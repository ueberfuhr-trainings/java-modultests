# Automatisierte Tests mit Java

Dieses Projekt enthält eine kleine Beispielanwendung, die durch automatisierte Modultests erweitert werden soll. Verwendet werden dabei JUnit5 und Mockito.

## Allgemeines zur Verwendung

Umsetzt werden die Arbeiten am Projekt im `master`-Branch. Vorlagen und Lösungen werden in Feature-Branches (`feature/*`) bereitgestellt. Es ist möglich, per `git checkout` auf den Branch zu wechseln und Musterlösungen anzuschauen, oder per `git merge` eine Musterlösung direkt in den Master zu kopieren.

## Aufgabenstellungen

### Einfache Modultests

1. Öffne die Klasse `GearTransmission`. Ermittle Testfälle für den Konstruktor und die Methode `shiftUp()`.
2. Implementiere diese Testfälle.

(Musterlösung siehe Branch `feature/test-simple`)

### Mocking

1. Öffne die Klasse `Car`. Diese hat Abhängigkeiten zu `GearTransmission`, `Engine`, `Clutch` und `GasTank`. Beachte den Konstruktor, mit dem diese Abhängigkeiten von außen übergeben werden können. Dieser ist hilfreich für bessere Testbarkeit. (*Design for Test*)
2. Leite für die Methode `fillUp(double)` Testfälle her und implementiere diese.

(Musterlösung siehe Branch `feature/test-mockito`)

### (Optional) Code Coverage

1.  Prüfe, ob Deine Entwicklungsumgebung Tests mit Messung der Code Coverage ausführen kann. (Eclipse: *Coverage As > JUnit Test*)
2. Erweitere den Maven-Build um Messung der Code Coverage während des automatisierten Builds.

(Musterlösung siehe Branch `feature/code-coverage`)

### (Optional) JUnit4-Beispiel

Ein Beispiel für einen Test nach JUnit4-Vorgaben findest Du im Branch `feature/junit4-sample`.