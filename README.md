# FlexDetect Data Service

![Build Status](https://img.shields.io/github/actions/workflow/status/FlexDetect/flexdetect-data-service/ci.yml)
![License](https://img.shields.io/github/license/FlexDetect/flexdetect-data-service)

---

## 📖 Vsebina

- [Opis storitve](#opis-storitve)
- [Arhitektura](#arhitektura)
- [API specifikacija](#api-specifikacija)
- [Podatkovni model](#podatkovni-model)
- [Namestitev in zagon](#namestitev-in-zagon)
- [Testiranje](#testiranje)
- [Integracija z ostalimi storitvami](#integracija-z-ostalimi-storitvami)
- [Prispevanje](#prispevanje)

---

## Opis storitve

Mikrostoritev **Data Service** je zadolžena za **sprejem, validacijo, čiščenje in trajno shranjevanje vhodnih podatkov** o porabi energije iz različnih objektov. Zagotavlja zanesljiv in robusten pipeline za pripravo podatkov, ki jih uporabljajo ostale komponente FlexDetect sistema.

- Sprejema časovno označene meritve energije.
- Validira integriteto in formate vhodnih podatkov.
- Shranjuje podatke v relacijsko bazo MySQL.
- Omogoča osnovne CRUD operacije preko REST API.

---

## Arhitektura

```mermaid
sequenceDiagram
    participant Client
    participant DataService
    participant Database

    Client->>DataService: POST /data (JSON payload)
    DataService->>DataService: Validate & clean data
    DataService->>Database: Insert validated data
    Database-->>DataService: Confirm insert
    DataService-->>Client: 201 Created
