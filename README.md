# Traffic Simulation System

## Opis

**Traffic Simulation System** to zaawansowany symulator ruchu drogowego, który umożliwia wizualizację pojazdów poruszających się po torze okrężnym oraz kolizyjnym. Projekt został zaprojektowany w celu realistycznego odwzorowania ruchu drogowego z uwzględnieniem synchronizacji wątków, interakcji pojazdów z skrzyżowaniami i torami kolizyjnymi oraz unikania kolizji. Wykorzystuje mechanizmy wielowątkowości i synchronizacji, aby symulacja była płynna i realistyczna.

## Funkcjonalności

- **Rysowanie toru**: Wizualizacja torów okrężnego i kolizyjnego, gdzie pojazdy poruszają się w zależności od prędkości i przyspieszenia.
- **Pojazdy**: Pojazdy mogą zmieniać swoją prędkość, przyspieszenie oraz reagować na różne wydarzenia na torze, jak np. zmiana pasa ruchu czy interakcje z innymi pojazdami.
- **Synchronizacja**: Mechanizm synchronizacji umożliwia uniknięcie kolizji na skrzyżowaniach i torach. Każdy pojazd w symulacji jest odpowiednio zsynchronizowany z innymi, co zapobiega zderzeniom.
- **Wielowątkowość**: Symulacja wykorzystuje wątki, aby każdy pojazd i proces rysowania działały równolegle, zapewniając płynność ruchu.
- **Zarządzanie pojazdami kolizyjnymi**: Pojazdy poruszające się po torze kolizyjnym mogą wchodzić w interakcje z innymi pojazdami, co wpływa na dynamikę symulacji.

## Wymagania

- **System operacyjny**: Linux lub inny system operacyjny wspierający bibliotekę `ncurses`.
- **Biblioteki**:
  - `ncurses` – biblioteka do obsługi tekstowego interfejsu użytkownika (GUI) i wyświetlania na ekranie.
  - `pthread` – biblioteka do obsługi wątków w celu zapewnienia równoczesnego działania różnych elementów symulacji.

## Jak uruchomić

### 1. Klonowanie repozytorium

Aby rozpocząć korzystanie z projektu, sklonuj repozytorium:

```bash
git clone https://github.com/yourusername/traffic-simulation-system.git
