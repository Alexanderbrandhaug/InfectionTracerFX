# Group gr2181 repository

## Om prosjektet

Dette er en Infectiontracer app som registrerer nærkontaktene til personer, og varsler nærkontater dersom man skulle bli smittet. Applikasjonen er laget med JavaFx, og lagrer og skriver informasjonen til/fra "skyen" (For release 1 og 2 lagres det lokalt) ved bruk av JSON-objekter.
Selve applikasjonen finner man [her](Infectiontracer), her finner man også relevant README fil.

## Gitpod oppsett

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2181/gr2181)

- Trykk på lenken over.
- Vent på at den virtuelle maskinen er klar.
- Bruk `cd rest` for å gå inn i rest-modulen slik at du får startet serveren.
- Cd deretter til `cd fxui` og skriv kommandoen: `mvn javafx:run`.
- Når applikasjonen er ferdig bygget og kjørt, åpne port 6080 for å se applikasjonen.
- For å kjøre tester og sjekke kodekvalitet vha Jacoco, Checkstyles og Spotbugs - bruk `mvn verify`på rotnivå i terminalen (bruk kommandoen `cd ../` for å gå til rotnivå når man er i fxui).
- Du må registere minimum to brukere for at testing av applikasjonen skal gi noe mening, ettersom man kun kan legge til nærkontakter som også er brukere av applikasjonen.
- CD til fxui mappen og skriv kommandoen `mvn javafx:jlink jpackage:jpackage` for å bygge applikasjonen til en kjørbar applikasjon som kan installeres på en vilkårlig PC. **Merk:** For å kunne få bygget applikasjonen til en kjørbar applikasjon vha Gitpod, 
så må kommandoen: `sudo apt-get update` etterfulgt av `sudo apt-get install -y fakeroot` kjøres før ovennevnte kommando.
