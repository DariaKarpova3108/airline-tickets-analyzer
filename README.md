# Flight Tickets Analyzer

Программа на Java для анализа данных о билетах из файла `tickets.json`.

## Задание

Программа читает файл `tickets.json` и рассчитывает:

1. **Минимальное время полета** между городами **Владивосток** и **Тель-Авив** для каждого авиаперевозчика.
2. **Разницу между средней ценой и медианой** для полета между городами **Владивосток** и **Тель-Авив**.

## Структура проекта

- `Main` — точка входа, запуск приложения;
- `FlightStatisticsService` — сервис для расчёта статистики;
- `Reader` / `ConfigReader` — загрузка и парсинг `tickets.json`;
- `DateTimeUtils` — утилита для работы с датами/временем;
- `ObjectMapperSingleton` — настройка `Jackson` для работы с датами и временем;
- `Tickets` / `City` — модели данных.

## ️ Настройка
Файл настроек: `src/main/resources/config.properties`
```properties
file-name=tickets.json 
```

## Используемые технологии

- Java 17
- Maven
- Jackson (для работы с JSON)
- Lombok

## Требования
- Java 17
- Maven (для сборки проекта)

## Сборка

Собрать исполняемый JAR с зависимостями можно командой:

``` bash
mvn clean package
```

После сборки файл будет доступен по пути:
```
target/tikets-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Запуск
Программу можно запустить из командной строки Linux:
```
java -jar target/tikets-1.0-SNAPSHOT-jar-with-dependencies.jar
```
