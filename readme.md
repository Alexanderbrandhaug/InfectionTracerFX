# Group gr2181 repository

## Om prosjektet

Dette er en Infectiontracer app som registrerer nærkontaktene til personer, og varsler nærkontakter dersom man skulle bli smittet.
Applikasjonen lar deg enkelt administrere antall nærkontakter, personalia til nærkontaktene dine og varsler dine nærkontakter dersom du registrerer at du har blitt smittet av Covid-19. 
Applikasjonen krever en aktiv brukerkonto for å la deg bruke den. Nærkontakter som legges til må også ha en aktiv bruker i applikasjonen. 
I tillegg kan man enkelt endre sin egen personalia og slette kontoen dersom det skulle bli aktuelt. 

Selve applikasjonen finner man [her](Infectiontracer), her finner man også relevant README fil.

## Gitpod oppsett

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2181/gr2181)

- Trykk på lenken over.
- Vent på at den virtuelle maskinen er klar.
- Bruk `cd rest` for å gå inn i rest-modulen og skriv kommandoen: `mvn spring-boot:run` slik at du får startet serveren.
- Cd deretter til `cd fxui` og skriv kommandoen: `mvn javafx:run`.
- Når applikasjonen er ferdig bygget og kjørt, åpne port 6080 for å se applikasjonen.
- For å kjøre tester og sjekke kodekvalitet vha Jacoco, Checkstyles og Spotbugs - bruk `mvn verify`på rotnivå i terminalen (bruk kommandoen `cd ../` for å gå til rotnivå når man er i fxui).
- Du må registere minimum to brukere for at testing av applikasjonen skal gi noe mening, ettersom man kun kan legge til nærkontakter som også er brukere av applikasjonen.
- CD til fxui mappen og skriv kommandoen `mvn javafx:jlink jpackage:jpackage` for å bygge applikasjonen til en kjørbar applikasjon som kan installeres på en vilkårlig PC. **Merk:** For å kunne få bygget applikasjonen til en kjørbar applikasjon vha Gitpod, 
så må kommandoen: `sudo apt-get update` etterfulgt av `sudo apt-get install -y fakeroot` kjøres før ovennevnte kommando.


## Utvikler oppsett

1. git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2181/gr2181.git
2. Åpne prosjektet som et Maven bygg i din IDE.
3. Installer nødvendige avhengigheter ved å kjøre `mvn install` i root.
4. Start Spring-boot serveren ved å kjøre `mvn spring-boot:run`i rest-mappen.
5. Start applikasjonen ved å kjøre `mvn javafx:run` i fxui-mappen. 

 - For å generere en **innstallerbar** applikasjon kan følgende kommando benyttes `mvn javafx:jlink jpackage:jpackage` i fxui-mappen.  
