# Infectiontracer

Målet med denne applikasjonen er å gi enkeltpersoner en oversikt over deres nærkontakter, samt E-post varsel dersom noen av de tester positivt på coronavirus. Dette gjøres ved å registrere en bruker, og deretter legge inn nærkontakter som senere kan varsles. Man kan fjerne nærkontakter dersom en kontakt ikke lengre er nærkontakt, eller ved å legge til en person ved en feil. Man kan også endre personalia, passord og/eller slette brukerkontoen sin. 

## Struktur og Maven bygg

Frontend er skrevet i Java, med JavaFx. Backend er skrevet i Java med det populære rammeverket Spring-boot. Applikasjonen skriver og leser data via et REST-API som igjen skriver til en JSON-fil som lagres i brukerens hjemmekatalog lokalt på PC'en. For å sikre god kodekvalitet og testdekning har vi benyttet oss av [jacoco](https://www.eclemma.org/jacoco/), [checkstyle](https://checkstyle.sourceforge.io/) og [spotbugs](https://spotbugs.github.io/). 

Jacoco vil generere en rapport som HTML.fil etter endt kjøring til hver modul som enkelt kan leses vha en nettleser. 
Spotbugs vil stoppe byggingen om det rapporteres en bug.
Checkstyle standariserer vår formattering av kode ihht [Google sin kode konvensjoner](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)



## Filstruktur

De ulike filene er fordelt i ulike mapper. <br>

FXML-filer finner man under [fxui/src/main/resources/infectiontracer/ui](fxui/src/main/resources/infectiontracer/ui/). <br>
Kontrollere finner man i mappen [fxui/src/main/java/infectiontracer/ui](fxui/src/main/java/infectiontracer/ui/). <br>
Kjernelogikk finner man i mappen [core/src/main/java/infectiontracer/core](core/src/main/java/infectiontracer/core/). <br>
Json-klasse finner man i mappen [core/src/main/java/infectiontracer/json](core/src/main/java/infectiontracer/json/). <br>
Rest-tjenesten finner man i mappen [rest/src/main/java/infectiontracer/rest](rest/src/main/java/infectiontracer/rest/). <br>

Test-filer for fxui finner man under [fxui/src/test/java/infectiontracer/ui](fxui/src/test/java/infectiontracer/ui/). <br>
Test-filer for core klasser finner man under [core/src/test/java/infectiontracer/core](core/src/test/java/infectiontracer/core/). <br>
Integrasjonstester finner man under [integrationtests/src/test/java/infectiontracer/ui](integrationtests/src/test/java/infectiontracer/ui/). <br>
Test-filer for rest-tjenesten finner man under [rest/src/test/java/infectiontracer/ui](rest/src/test/java/infectiontracer/ui/). <br>




**API-dokumentasjon** av REST-tjenesten finner du [her](https://documenter.getpostman.com/view/14944616/UVJYHySS?fbclid=IwAR0qR2jcDQGa54JkL8ySvVE5ZJCRAWiQy8QLyaC8vfphxTTRo-owYuh9gB0). <br>

# Brukerhistorier

- Som Navn Navnesen som er smittet av koronaviruset, vil jeg opprette meg en konto slik at jeg får tilgang til å bruke applikasjonen.

- Som Navn Navnesen ønsker jeg tilbakemelding dersom noe går feil i applikasjon.

- Som Navn Navnesen ønsker jeg muligheten til å legge til nærkontakter.

- Som Navn Navnesen ønsker jeg muligheten til å varsle nærkontakter ved smitte.

- Som Navn Navnesen ønsker jeg beskjed dersom av noen av mine nærkontakter tester positivt.

- Som Navn Navnesen vil jeg ha muligheten til å friskmelde meg etter påvist smitte. 

- Som Navn Navnesen vil jeg ha en applikasjon som er intuitiv og brukervennlig. 

- Som Navn Navnesen ønsker jeg å ha mulighet til å generere et nytt passord og få det tilsendt på mail dersom jeg glemmer passordet mitt.

- Som Navn Navnesen ønsker jeg å ha muligheten til å slette brukeren min dersom jeg ikke ønkser å ha en bruker på InfectionTracer lenger.

- Som Navn Navnesen ønsker jeg å ha muligheten til å endre fornavn, etternavn og passord på brukeren.

- Som Navn Navnesen ønsker jeg å ha mulighet til å slette en nærkontakt dersom nærkontakten er lagt til ved en feil.

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

![](docs/release3/diagrams/sequencediagram.png)


