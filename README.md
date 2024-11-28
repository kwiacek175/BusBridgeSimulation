# Symulacja przejazdu autobusów przez wąski most

## Opis projektu

Projekt przedstawia symulację przejazdu autobusów przez wąski most. Program zarządza różnymi etapami podróży autobusu, od wsiadania pasażerów, przez przejazd przez most, aż po rozładunek pasażerów na parkingu. Symulacja uwzględnia różne stany, w których może znajdować się autobus, oraz ograniczenia ruchu na moście, takie jak liczba autobusów, które mogą jednocześnie przejeżdżać przez most.

## Struktura plików

- **TrafficLimit.java** - Typ wyliczeniowy `TrafficLimit`, który określa dostępne limity ruchu na moście.
- **BusStatus.java** - Typ wyliczeniowy `BusStatus`, który definiuje różne stany, przez które przechodzi autobus podczas swojej podróży.
- **BusDirection.java** - Typ wyliczeniowy `BusDirection`, który definiuje kierunek jazdy autobusu.
- **Bus.java** - Klasa `Bus`, która implementuje główną logikę symulacji przejazdu autobusu przez most. Zawiera różne metody, takie jak wsiadanie pasażerów, przejazd przez most, dojazd do parkingu i rozładunek pasażerów.
