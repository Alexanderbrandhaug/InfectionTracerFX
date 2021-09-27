# Group gr2181 repository 

## Om prosjektet
Dette er en Smittesporer app som registrerer nærkontaktene til personer, og varsler nærkontater dersom man skulle bli smittet. Applikasjonen er laget med JavaFx, og lagrer og skriver informasjonen til/fra "skyen" (For release 1 lagres det lokalt) ved bruk av JSON-objekter.
Selve applikasjonen finner man [her](Smittesporer), her finner man også relevant README fil.

## Gitpod oppsett
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2181/gr2181)
- Trykk på lenken over.
- Vent på at den virtuelle maskinen er klar.
- Gå inn i Smittesporer prosjektet ved `cd Smittesporer` i terminalen.
- Deretter kjør `mvn compile javafx:run`.
- Når applikasjonen er ferdig bygget og kjørt, åpne port 6080 for å se applikasjonen.
- For å kjøre tester bruk `mvn test` i terminalen.
- For å sjekke kodekvalitet vha Jacoco, Checkstyles og Spotbugs bruk `mvn verify`i terminalen.
