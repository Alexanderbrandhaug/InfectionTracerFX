# Group gr2181 repository

## Om prosjektet

Dette er en Infectiontracer app som registrerer nærkontaktene til personer, og varsler nærkontater dersom man skulle bli smittet. Frontend er skrevet i Java, med JavaFx. Backend er skrevet i Java med det populære rammeverket Spring-boot. Applikasjonen skriver og leser data via et REST-API som igjen skriver til en JSON-fil som lagres i brukerens hjemmekatalog lokalt på PC'en. For å sikre god kodekvalitet og testdekning har vi benyttet oss av [jacoco](https://www.eclemma.org/jacoco/), [checkstyle](https://checkstyle.sourceforge.io/) og [spotbugs](https://spotbugs.github.io/). 
Applikasjonen kan pakkes til et innstallerbart program på en PC, uavhengig av operativsystem. Fremgangsmåte er beskrevet under.
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
