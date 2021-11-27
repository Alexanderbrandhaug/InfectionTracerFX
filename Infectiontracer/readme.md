# Infectiontracer

Målet med denne applikasjonen er å gi enkeltpersoner en oversikt over deres nærkontakter, samt varslinger dersom noen av de tester positivt på coronavirus. Dette gjøres ved å registrere en bruker, og deretter legge inn nærkontakter som senere kan varsles.

## Filstruktur

De ulike filene er fordelt i ulike mapper. <br>

FXML-filer finner man under [fxui/src/main/resources/infectiontracer/ui](fxui/src/main/resources/infectiontracer/ui/). <br>
Kontrollere finner man i mappen [fxui/src/main/java/infectiontracer/ui](fxui/src/main/java/infectiontracer/ui/). <br>
Kjernelogikk finner man i mappen [core/src/main/java/infectiontracer/core](core/src/main/java/infectiontracer/core/). <br>
Json-klasse finner man i mappen [core/src/main/java/infectiontracer/json](core/src/main/java/infectiontracer/json/). <br>

Test-filer for fxui finner man under [fxui/src/test/java/infectiontracer/ui](fxui/src/test/java/infectiontracer/ui/). <br>
Test-filer for core klasser finner man under [core/src/test/java/infectiontracer/core](core/src/test/java/infectiontracer/core/). <br>


Foreløpig release plan for release 1 og 2 finnes under [docs](docs). <br>

# Brukerhistorier

- Som Navn Navnesen som er smittet av koronaviruset, vil jeg opprette meg en konto slik at jeg får tilgang til å bruke applikasjonen.

- Som Navn Navnesen ønsker jeg tilbakemelding dersom noe går feil i applikasjon.

- Som Navn Navnesen ønsker jeg muligheten til å legge til nærkontakter.

- Som Navn Navnesen ønsker jeg muligheten til å varsle nærkontakter ved smitte.

- Som Navn Navnesen ønsker jeg beskjed dersom av noen av mine nærkontakter tester positivt.

- Som Navn Navnesen vil jeg ha muligheten til å friskmelde meg etter påvist smitte. 

- Som Navn Navnesen vil jeg ha en applikasjon som er intuitiv og brukervennlig. 

# Illustrasjoner

**Her er noen bilder av selve applikasjonen.**

 - Applikasjonen ved oppstart.  
![](docs/release3/images/login.png)

- Man må registrere seg ved manglende brukerkonto.
![](docs/release3/images/registration.png)

- Selve applikasjonen hvor en bruker kan administrere sine nærkontakter, og gi beskjed ved smitte. 

![](docs/release3/images/main.png)

- Profilsiden hvor en bruker kan redigere fornavn, etternavn og passord, og slette brukerkontoen sin.

![](docs/release3/images/profile.png)

# Diagrammer

**Sekvensdiagram**

![](docs/release2/images/core.png)



# Json-Skjema

![](docs/release2/images/jsonschema.jpg)
