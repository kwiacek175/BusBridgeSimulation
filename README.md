# Symulacja przejazdu autobusów przez wąski most

## Opis projektu

Projekt przedstawia symulację przejazdu autobusów przez wąski most. Program zarządza różnymi etapami podróży autobusu, od wsiadania pasażerów, przez przejazd przez most, aż po rozładunek pasażerów na parkingu. Symulacja uwzględnia różne stany, w których może znajdować się autobus, oraz ograniczenia ruchu na moście, takie jak liczba autobusów, które mogą jednocześnie przejeżdżać przez most.

## Użyte narzędzia i biblioteki
- Java Development Kit (JDK) - używane do kompilacji i uruchamiania aplikacji.
- Swing - używane do implementacji interfejsu graficznego aplikacji (do rysowania autobusów na ekranie).

## Struktura plików

- **TrafficLimit.java** - Typ wyliczeniowy `TrafficLimit`, który określa dostępne limity ruchu na moście.
- **BusStatus.java** - Typ wyliczeniowy `BusStatus`, który definiuje różne stany, przez które przechodzi autobus podczas swojej podróży.
- **BusDirection.java** - Typ wyliczeniowy `BusDirection`, który definiuje kierunek jazdy autobusu.
- **Bus.java** - Klasa `Bus`, która implementuje główną logikę symulacji przejazdu autobusu przez most. Zawiera różne metody, takie jak wsiadanie pasażerów, przejazd przez most, dojazd do parkingu i rozładunek pasażerów.
- **NarrowBridgeSimulation.java** - Klasa zarządzająca ruchem autobusów na moście. Określa, jak wiele autobusów może jednocześnie przejeżdżać przez most i synchronizuje ich przejazdy.
- **NarrowBridgeAnimation.java** - Klasa odpowiedzialna za wizualizację symulacji. Rysuje autobusy na ekranie, umożliwiając śledzenie ich ruchu przez most.

## Funkcje aplikacji

- **Zarządzanie przejazdem autobusu** - Program śledzi ruch autobusów, które przejeżdżają przez most, uwzględniając różne stany, takie jak wsiadanie pasażerów, przejazd przez most, zjazd z mostu i rozładunek pasażerów.
- **Synchronizacja ruchu** - Program zapewnia, że tylko określona liczba autobusów może jednocześnie przejeżdżać przez most, w zależności od limitu ruchu.
- **Zarządzanie stanami autobusu** - Każdy autobus przechodzi przez różne stany, od wsiadania pasażerów, przez przejazd mostu, aż po rozładunek pasażerów na parkingu.
- **Rysowanie symulacji** - Program rysuje autobusy na ekranie, umożliwiając wizualne śledzenie ruchu.

## Używanie aplikacji
Po uruchomieniu aplikacji pojawi się okno wizualizacyjne, w którym będzie można śledzić symulację ruchu autobusów. Aplikacja graficznie przedstawia autobusy poruszające się przez wąski most, uwzględniając limity liczby autobusów, które mogą jednocześnie przejeżdżać przez most.

Aplikacja może mieć interaktywne elementy, takie jak przyciski do kontrolowania ruchu autobusów lub zmiany ustawień symulacji.

