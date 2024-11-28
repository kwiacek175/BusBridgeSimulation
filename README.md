## Opis

Projekt symuluje operacje autobusów przejeżdżających przez wąski most, uwzględniając różne statusy autobusów oraz zasady zarządzania ruchem. System obejmuje wiele autobusów, z których każdy ma określony kierunek oraz status, a także zapewnia, że w danym momencie tylko ograniczona liczba autobusów może przejeżdżać przez most. Projekt składa się z kilku klas, które modelują zachowanie autobusów, interakcję z mostem oraz zasady ruchu drogowego.

### Opis plików

#### 1. **Bus.java**
   - Plik zawiera klasę `Bus`, która modeluje zachowanie autobusu w symulacji.
   - **Kluczowe metody**:
     - `boarding()`: Symuluje proces wsiadania pasażerów do autobusu.
     - `goToTheBridge()`: Symuluje autobus jadący w stronę mostu.
     - `rideTheBridge()`: Symuluje przejazd autobusu przez most.
     - `goToTheParking()`: Symuluje autobus jadący w stronę parkingu po przejechaniu przez most.
     - `unloading()`: Symuluje rozładunek pasażerów.
     - `run()`: Główna metoda wątku, która koordynuje całą podróż autobusu – od wsiadania do rozładunku.
   - **Atrybuty**:
     - `id`: Unikalny identyfikator każdego autobusu.
     - `dir`: Kierunek autobusu (wschód lub zachód).
     - `status`: Aktualny status autobusu w trakcie symulacji.
     - `bridge`: Obiekt mostu, z którym autobus wchodzi w interakcję.
   - **Wizualizacja**:
     - Autobus jest reprezentowany na ekranie za pomocą metody `draw()`, która rysuje autobus w jego aktualnym stanie i pozycji na moście.

#### 2. **BusDirection.java**
   - Plik definiuje wyliczeniowy typ `BusDirection`, który reprezentuje możliwe kierunki dla autobusu.
   - **Wartości**:
     - `EAST`: Autobus jedzie na wschód.
     - `WEST`: Autobus jedzie na zachód.
   - **Metoda**:
     - `toString()`: Zwraca reprezentację tekstową kierunku w celu wyświetlenia.

#### 3. **BusStatus.java**
   - Plik definiuje wyliczeniowy typ `BusStatus`, który reprezentuje możliwe statusy autobusu w trakcie symulacji.
   - **Wartości**:
     - `BOARDING`: Autobus czeka na pasażerów.
     - `GO_TO_THE_BRIDGE`: Autobus jedzie w stronę mostu.
     - `GET_ON_THE_BRIDGE`: Autobus wjeżdża na most.
     - `RIDE_THE_BRIDGE`: Autobus przejeżdża przez most.
     - `GET_OFF_THE_BRIDGE`: Autobus zjeżdża z mostu.
     - `GO_TO_THE_PARKING`: Autobus jedzie na parking.
     - `UNLOADING`: Autobus rozładowuje pasażerów.

#### 4. **TrafficLimit.java**
   - Plik definiuje wyliczeniowy typ `TrafficLimit`, który określa limity ruchu na moście.
   - **Wartości**:
     - `NO_LIMIT_TRAFFIC`: Brak ograniczeń ruchu.
     - `TWO_WAY_TRAFFIC`: Ruch dwukierunkowy (maksymalnie 3 autobusy).
     - `ONE_WAY_TRAFFIC`: Ruch jednokierunkowy (maksymalnie 3 autobusy).
     - `LIMITED_TRAFFIC`: Ruch ograniczony (maksymalnie 1 autobus).
   - **Metoda**:
     - `toString()`: Zwraca nazwę limitu ruchu.

### Przykład użycia

1. **Tworzenie mostu i autobusów**:
   - Możesz utworzyć obiekt mostu (`NarrowBridgeSimulation`) oraz kilka autobusów, które będą symulować przejazd przez most, zmieniając swoje statusy i kierunki.

2. **Symulacja wątku autobusu**:
   - Każdy autobus jest uruchamiany w osobnym wątku, co umożliwia równoczesne śledzenie kilku autobusów w trakcie przejazdu przez most.

3. **Rysowanie autobusów na ekranie**:
   - Metoda `draw()` pozwala na wizualizację każdego autobusu w jego aktualnym statusie i pozycji.

## Wymagania
- Java 11 lub nowsza
- Zainstalowana biblioteka do tworzenia interfejsów graficznych (np. Swing)

## Licencja
Projekt jest dostępny na licencji MIT.
