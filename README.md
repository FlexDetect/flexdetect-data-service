# FlexDetect Data Service

## Vsebina
- [Pregled](#pregled)
- [Namen mikrostoritve](#namen-mikrostoritve)
- [Arhitektura in tehnologije](#arhitektura-in-tehnologije)
- [API specifikacija](#api-specifikacija)
- [Podatkovni modeli](#glavni-api-endpointi)
- [Integracija z drugimi mikrostoritvami](#integracija-z-drugimi-mikrostoritvami)

---

## Pregled
Mikrostoritev **flexdetect-data-service** je osrednji modul za upravljanje vhodnih podatkov v FlexDetect sistemu. Namenjena je sprejemanju, validaciji, čiščenju in shranjevanju velikih količin časovnih serij in metapodatkov, ki služijo kot vhod za nadaljnjo obdelavo in strojno učenje.

---

## Namen mikrostoritve
- Sprejemanje surovih podatkov preko REST API endpointov
- Validacija podatkovnih formatov
- Čiščenje in normalizacija podatkov (odstranitev napak, manjkajočih vrednosti)
- Shranjevanje v MySQL bazo
- Omogočanje hitrega dostopa za naslednje faze obdelave

---

## Arhitektura in tehnologije
- **Jezik:** Java 17
- **Okvir:** Spring Boot 3
- **Podatkovna baza:** MySQL 8.0, optimizirana s predpomnilnikom in indeksiranjem
- **Docker:** Za kontejnerizacijo in enostavno namestitev
- **Avtentikacija:** JWT preko flexdetect-user-service

---

## Glavni API endpointi

## Entiteta Facility

| Metoda  | Endpoint                   | Opis                              | Payload                                                                                      |
|---------|----------------------------|----------------------------------|----------------------------------------------------------------------------------------------|
| GET     | /api/facilities            | Pridobi seznam vseh facilityjev  | -                                                                                            |
| POST    | /api/facilities            | Ustvari nov facility             | `{ name, address, type, sizeSqm, floors, contactName, contactPhone, contactEmail}` |
| PUT     | /api/facilities/{facilityId} | Posodobi obstoječi facility     | -                                                                                            |
| DELETE  | /api/facilities/{facilityId} | Izbriši facility                | -                                                                                            |

## Entiteta Dataset

| Metoda  | Endpoint                                    | Opis                                   | Payload                      |
|---------|---------------------------------------------|---------------------------------------|------------------------------|
| GET     | /api/facilities/{facilityId}/datasets       | Pridobi vse dataset-e za določen facility | -                            |
| POST    | /api/facilities/{facilityId}/datasets       | Ustvari nov dataset za določen facility | `{ source, createdAt }`       |
| PUT     | /api/facilities/{facilityId}/datasets/{datasetId} | Posodobi dataset                    | -                            |
| DELETE  | /api/facilities/{facilityId}/datasets/{datasetId} | Izbriši dataset                   | -                            |

## Entiteta MeasurementName (Custom Fields)

| Metoda  | Endpoint                     | Opis                            | Payload                    |
|---------|------------------------------|--------------------------------|----------------------------|
| GET     | /api/measurement-name         | Pridobi vse meritve uporabnika  | -                          |
| POST    | /api/measurement-name         | Ustvari nov measurement name   | `{ name, unit, dataType }`  |
| PUT     | /api/measurement-name/{id}    | Posodobi measurement name      | -                          |
| DELETE  | /api/measurement-name/{id}    | Izbriši measurement name       | -                          |

## Entiteta Measurement

| Metoda  | Endpoint                                    | Opis                           | Payload                                                                                          |
|---------|---------------------------------------------|-------------------------------|--------------------------------------------------------------------------------------------------|
| GET     | /api/datasets/{datasetId}/measurements      | Pridobi meritve za dataset     | -                                                                                                |
| GET     | /api/datasets/{datasetId}/measurements/{id} | Pridobi posamezno meritev      | -                                                                                                |
| POST    | /api/datasets/{datasetId}/measurements      | Ustvari novo meritev           | `{ measurementNameIdMeasurementName, timestamp, valueInt?, valueFloat?, valueBool? }`             |
| PUT     | /api/datasets/{datasetId}/measurements/{id} | Posodobi meritev               | -                                                                                                |
| DELETE  | /api/datasets/{datasetId}/measurements/{id} | Izbriši meritev                | -                                                                                                |

**Opomba:** Pri meritvah (`Measurement`), je veljavna samo ena od vrednosti `valueInt`, `valueFloat` ali `valueBool`.


## Integracija z drugimi mikrostoritvami
- Komunikacija z **flexdetect-user-service** za avtentikacijo
- Posredovanje očiščenih podatkov mikrostoritvi **flexdetect-ml-service**



**Avtor:** Aljaž Brodar  
