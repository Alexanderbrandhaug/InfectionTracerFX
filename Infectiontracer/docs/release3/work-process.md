# Arbeidsprosess
I dette dokumentet finner man informasjon om hvordan vi går frem i hvordan vi jobber/jobbet gjennom prosjektet.

## Møter
En normal uke bestod av to møter mandag og torsdag/fredag. 

På mandagsmøtet diskuterte vi hvilke utvidelser som var aktuelle for uken, samt hvilke utvidelser som ville bli aktuelle ved et senere tidspunkt.

Møtet senere i uken ble brukt til å se på ferdigstilte utvidelser fra uken. Ofte diskuterte vi bugs og veivalg som kom opp i løp av uken.

## Issues
For å få oversikt over utvidelsene, laget vi fortløpende issues i Gitlab for utvidelser. 
### Tags
For å kategorisere/gruppere issues bruker vi tags hovedsaklig underveis for å få en oversikt. Flere av tags'ene fjernes etter vi lukker issue'ene.
- **Low, medium og highpriority**: Brukes til å indikere hvor viktig en issue er. (Selvforklarende)
- **On-hold**: Brukes til å indikere at issue'en ikke skal jobbes med. (F.eks dersom den krever en annen implimentering før den kan påbegynnes.)
- **Todo**: Brukes til å markere at issue'en må gjøres.
- **In-progress**: Brukes til å indikere at en issue jobbes med.
- **QA**: Brukes på issues som er ferdig, men venter på kvalitetssikring.
- **Completed**: Brukes for å markere at en issue er ferdig.
- **Bug**: Brukes til å indikere at vi har en bug i koden for å løse problemet.
- **us-1 - us-7**: Denne taggen brukes til å markere hvilke brukerhistorier issue'en løser.
- **API, documentation, optimazation og core**: Brukes til å kategorisere hva issue'en endrer på.


### Boards
Vi trengte en oversikt på hvor langt de ulike issue'ene er komt i arbeidsprosessen. Dette løste vi ved å bruke boards i Gitlab. Med denne funksjonen kan vi flytte issue'ene til ulike stadier som samsvarer med hvor langt issue'en er på vei til å bli løst.
- **On-hold**: Brukes til å indikere at issue'en ikke skal jobbes med. (F.eks dersom den krever en annen implimentering før den kan påbegynnes.)
- **Todo**: Brukes til å markere at issue'en må gjøres.
- **In-progress**: Brukes til å indikere at en issue jobbes med.
- **QA**: Brukes på issues som er ferdig, men venter på kvalitetssikring.
- **Closed**: Brukes for å markere at en issue er ferdig og lukket.

## Endringer på master/Dev branch
Når gruppen er enig om at en utvidelse skal legges til i hovedbranchen(e), gjøres dette via en merge request i Gitlab. Ettersom de er protected, er dette eneste måten man kan modifisere de. Vi opererer med 2 slik branch'er.
### Master
Master er branch'en vi orginalt tenkte å merge alle endringer til, men mellom release'ene oppdateres ikke denne. Dette skyldes at master til en hver tid skal være oppdatert etter hvordan applikasjonen var ved innleveringsfristen. Dermed unngår vi at faglærer eller studass ser gjennom en ikke-funksjonell master-branch når vi gjør endringer i senere tid, selvom master var funksjonell på innleveringstidspunktet.
### Dev
Dette problemet løser vi ved å opprette en Dev-branch. Denne branchen blir da den effektive "master"-branchen når vi jobber mellom fristene. Vi merger alt av endringer inn i denne. Og når innleveringsfristen kommer, overskriver Dev branchen master, og blir den nye fungerende master.

