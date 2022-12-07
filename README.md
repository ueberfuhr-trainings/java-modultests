# Automatisierte Tests mit Java

Dieses Projekt enthält eine kleine Beispielanwendung, die durch automatisierte Modultests 
erweitert werden soll. Verwendet werden dabei JUnit5, Mockito und AssertJ. Im weiteren Verlauf
werden auch Spock, Cucumber und Spring Boot Tests eingeführt.

## Allgemeines zur Verwendung

Umsetzt werden die Arbeiten am Projekt im `main`-Branch. Vorlagen und Lösungen werden in 
Feature-Branches (`feature/*`) bereitgestellt. Es ist möglich, per `git checkout` auf den Branch
zu wechseln und Musterlösungen anzuschauen, oder per `git merge` eine Musterlösung direkt in den
`main`-Branch zu kopieren.

## Aufgabenstellungen

### 1. Einfache Modultests

1. Öffne die Klasse `GearTransmission`. Ermittle Testfälle für den Konstruktor 
   und die Methode `shiftUp()`.
2. Implementiere diese Testfälle.

(Musterlösung siehe Branch `feature/test-simple` - basiert auf Branch `main`)

### 2. AssertJ

Ersetze die bestehenden JUnit Assertions durch AssertJ Assertions. Worin besteht der Vorteil bei der Verwendung von AssertJ?

(Musterlösung siehe Branch `feature/test-simple-assertj` - basiert auf Branch `feature/test-simple`)

### 3. Mocking

1. Öffne die Klasse `Car`. Diese hat Abhängigkeiten zu `GearTransmission`, `Engine`, 
   `Clutch` und `GasTank`. Beachte den Konstruktor, mit dem diese Abhängigkeiten von 
   außen übergeben werden können. Dieser ist hilfreich für bessere Testbarkeit.
   (*Design for Test*)
2. Leite für die Methode `fillUp(double)` Testfälle her und implementiere diese.

(Musterlösung siehe Branch `feature/test-mockito` - basiert auf Branch `feature/test-simple-assertj`)

### 4. Code Coverage

1. Prüfe, ob Deine Entwicklungsumgebung Tests mit Messung der Code Coverage ausführen kann.
2. Erweitere den Maven-Build um Messung der Code Coverage während des automatisierten Builds.

(Musterlösung siehe Branch `feature/code-coverage` - basiert auf Branch `main`)

### 5. Data Driven Test

Erweitere die Testklasse für `GearTransmission` um einen parametrisierten Test.
So soll nicht nur für einen maximalen Gang, sondern für mehrere testweise durchgeschalten werden.

(Musterlösung siehe Branch `feature/test-data-driven` - basiert auf Branch `feature/test-simple-assertj`)

### 6. JUnit 5 Extension

Implementiere eine JUnit 5 Extension, die vor und nach der Ausführung eines Tests einen
Zeitstempel nimmt, und nach dem Test die Differenz auf die Konsole loggt. Stelle für das leichtere 
Einbinden der Extension eine Meta Annotation bereit. Diese kann auf Methodenebene für einzelne Tests,
aber auch auf Klassenebene für alle Testmethoden verwendet werden.

(Musterlösung siehe Branch `feature/extension` - basiert auf Branch `feature/test-simple`)

### 7. JUnit 5 Tags

Nutze einen Tag (`perf-m` o. ä.), um alle Tests, die mit eben erstellter Extension ausgeführt werden, ein- bzw. auszuschließen.
Beim Aufruf von Maven sollten diese Tags dann wie folgt ein- bzw. ausgeschlossen werden können:

```bash
# only those tests
mvn clean test -Dgroups=perf-m
# all tests except those
mvn clean test -DexcludedGroups=perf-m
```

Optional: Ergänze ein Profil `only-perf-m` in der `pom.xml` für die einfachere Ausführung per 

```bash
mvn clean test -Ponly-perf-m
```

(Musterlösung siehe Branch `feature/tags` - basiert auf Branch `feature/extension`)
