# FlexDetect Data Service

## Vsebina
- [Pregled](#pregled)
- [Namen mikrostoritve](#namen-mikrostoritve)
- [Arhitektura in tehnologije](#arhitektura-in-tehnologije)
- [API specifikacija](#api-specifikacija)
- [Podatkovni modeli](#podatkovni-modeli)
- [Primeri zahtevkov](#primeri-zahtevkov)
- [Integracija z drugimi mikrostoritvami](#integracija-z-drugimi-mikrostoritvami)

---

## Pregled
Mikrostoritev **flexdetect-data-service** je osrednji modul za upravljanje vhodnih podatkov v FlexDetect sistemu. Namenjena je sprejemanju, validaciji, čiščenju in shranjevanju velikih količin časovnih serij in metapodatkov, ki služijo kot vhod za nadaljnjo obdelavo in strojno učenje.

---

## Namen mikrostoritve
- Sprejemanje surovih podatkov preko REST API endpointov
- Validacija podatkovnih formatov in semantičnih pravil
- Čiščenje in normalizacija podatkov (odstranitev napak, manjkajočih vrednosti)
- Shranjevanje v MySQL bazo z optimizirano shemo
- Omogočanje hitrega dostopa za naslednje faze obdelave

---

## Arhitektura in tehnologije
- **Jezik:** Java 17
- **Okvir:** Spring Boot 3
- **Podatkovna baza:** MySQL 8.0, optimizirana s predpomnilnikom in indeksiranjem
- **Docker:** Za kontejnerizacijo in enostavno namestitev
- **Avtentikacija:** JWT preko flexdetect-user-service

---

## API specifikacija

| Endpoint               | Metoda | Opis                              |
|------------------------|--------|----------------------------------|
| `/data/import`         | POST   | Uvoz surovih časovnih podatkov   |
| `/data/status/{id}`    | GET    | Status validacije določenega uvoza |
| `/data/clean/{id}`     | POST   | Zaženi čiščenje in normalizacijo |
| `/data/export/{id}`    | GET    | Izvoz očiščenih podatkov         |

**Primer POST zahtevka za uvoz:**

```json
{
  "deviceId": "sensor-1234",
  "timestamp": "2025-12-01T12:00:00Z",
  "measurements": {
    "power": 1500,
    "voltage": 230
  }
}
```

---

## Podatkovni modeli

### Entiteta `Measurement`
| Polje        | Tip      | Opis                         |
|--------------|----------|------------------------------|
| `id`         | Long     | Unikatni ID meritve          |
| `deviceId`   | String   | Identifikator naprave        |
| `timestamp`  | DateTime | Čas meritve                  |
| `value`      | Float    | Merjena vrednost (npr. moč)  |
| `type`       | String   | Tip meritve (npr. power)     |

---


## Integracija z drugimi mikrostoritvami
- Komunikacija z **flexdetect-user-service** za avtentikacijo
- Posredovanje očiščenih podatkov mikrostoritvi **flexdetect-ml-service**
- Sprejem povratnih informacij za spremljanje kakovosti podatkov



**Avtor:** Aljaž Brodar  
**Zadnja posodobitev:** 1. december 2025
