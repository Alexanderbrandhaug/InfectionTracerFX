# Group gr2181 repository 

## Om prosjektet
Dette er en Infectiontracer app som registrerer nærkontaktene til personer, og varsler nærkontater dersom man skulle bli smittet. Applikasjonen er laget med JavaFx, og lagrer og skriver informasjonen til/fra "skyen" (For release 1 og 2 lagres det lokalt) ved bruk av JSON-objekter.
Selve applikasjonen finner man [her](Infectiontracer), her finner man også relevant README fil.

## Gitpod oppsett
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2181/gr2181)
- Trykk på lenken over.
- Vent på at den virtuelle maskinen er klar.
- Bruk `cd fxui` for å gå inn i korrekt mappe.
- Derretter kjør `mvn compile javafx:run`.
- Når applikasjonen er ferdig bygget og kjørt, åpne port 6080 for å se applikasjonen.
- For å kjøre tester og sjekke kodekvalitet vha Jacoco, Checkstyles og Spotbugs - bruk `mvn verify`på rotnivå i terminalen (bruk kommandoen `cd ../` for å gå til rotnivå når man er i fxui).
- Du kan logge inn med brukernavn: test@gmail.com og passordet: Passord123, eller du kan lage deg din egen bruker.
