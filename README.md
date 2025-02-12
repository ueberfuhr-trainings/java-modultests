# Automatisierte Tests mit Java

Dieses Projekt enthält eine kleine Beispielanwendung, die durch automatisierte Modultests erweitert werden soll. Verwendet werden dabei JUnit5, Mockito und AssertJ. Im weiteren Verlauf werden auch Spock, Cucumber und Spring Boot Tests eingeführt.

Umsetzt werden die Arbeiten am Projekt im `main`-Branch. Vorlagen und Lösungen stehen in separaten Branches zur Verfügung. Du kannst diese Lösungen anschauen bzw. in den `main`-Branch mergen.

## 1. JUnit-Grundlagen

### 1.1. Ein erster Modultest

Öffne die Klasse `GearTransmission`. Ermittle Testfälle für den Konstruktor und die Methode `shiftUp()`. Implementiere diese Testfälle.

_(Musterlösung siehe Branch `junit/simple`)_

### 1.2. AssertJ

Ersetze die bestehenden JUnit Assertions durch AssertJ Assertions. Worin besteht der Vorteil bei der Verwendung von AssertJ?

_(Musterlösung siehe Branch `junit/assertj` - basiert auf Branch `junit/simple`)_

### 1.3. Data Driven Test

Erweitere die Testklasse für `GearTransmission` um einen parametrisierten Test. So sollen Gangschaltungen mit 1, 6 oder 10 Gängen getestet werden.

_(Musterlösung siehe Branch `junit/data-driven` - basiert auf Branch `junit/assertj`)_

## 2. Mockito

### 2.1. Mocking

Öffne die Klasse `Car`. Diese hat Abhängigkeiten zu `GearTransmission`, `Engine`, `Clutch` und `GasTank`. Beachte den Konstruktor, mit dem diese Abhängigkeiten von außen übergeben werden können. Dieser ist hilfreich für bessere Testbarkeit. (*Design for Test*) Leite für die Methode `fillUp(double)` Testfälle her und implementiere diese.

_(Musterlösung siehe Branch `mockito/mocking`)_

### 2.2. Mockito Extension

Nutze die `MockitoExtension`, um die Mocks in die Tests zu injizieren.

_(Musterlösung siehe Branch `mockito/extension` - basiert auf Branches `mockito-mocking`)_

### 2.3. Argument Capturing

Schreibe einen Test für `GasStationAttendant`, bei dem sichergestellt wird, dass bei schrittweisem Auffüllen des `Car` in Summe die gewünschte Füllmenge erreicht wird.

_(Musterlösung siehe Branch `mockito/argument-captor`)_

## 3. JUnit - Weitere Features

### 3.1. Tags

Nutze einen Tag (`inefficient` o. ä.), um Tests zu markieren, deren Ausführung etwas länger dauern könnte. Markiere wahlweise Deine Tests. Erstelle auch eine Annotation `@Inefficient`, um dieses Tag zu zentralisieren. Beim Aufruf von Maven sollten diese Tags dann wie folgt ein- bzw. ausgeschlossen werden können:

```bash
# only those tests
mvn clean test -Dgroups=inefficient
# all tests except those
mvn clean test -DexcludedGroups=inefficient
```

Optional: Ergänze ein Maven-Profil `ci` (für CI-Builds), bei dem die Ausführung dieser Tests unterbunden wird. Dieses Profil lässt sich mit folgendem Befehl anwenden:

```bash
mvn clean test -Pci
```

_(Musterlösung siehe Branch `junit/tags`)_

### 3.2. Extensions

Implementiere eine eigene JUnit 5 Extension, die vor und nach der Ausführung eines Tests einen Zeitstempel nimmt, und nach dem Test die Differenz auf die Konsole loggt. Nutze diese Extension für die Tests, die mit `@Inefficient` markiert sind.

Implementiere weiterhin eine Extension, die es für die Tests der Klasse `Car` erlaubt, die `Car`-Instanz als Parameter an die Tests zu übergeben.

_(Musterlösung siehe Branch `junit/extension` - basiert auf Branches `junit/tags`)_

## 4. Sonstiges

### 4.1. Cucumber

Beschreibe das Shift-Verhalten der `GearTransmission` mit der Gherkin-Syntax und implementiere einen entsprechenden Test mithilfe von Cucumber.

_(Musterlösung siehe Branch `further/cucumber`)_

### 4.2. Spock

Implementiere die Tests für die `GearTransmission` und `Car` mithilfe von Spock (Groovy).

_(Musterlösung siehe Branch `further/spock`)_

## 5. Spring Boot Tests

Wechsle für die Erweiterung des Projektes und dieser Aufgabenstellung in den Branch `spring-boot/main`. Alternativ kannst Du diesen Branch auch in den `main`-Branch mergen.
