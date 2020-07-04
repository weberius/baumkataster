# Baumkataster Köln

Dieser Service soll es ermöglichen, auf die Daten des [Baumkataster Koeln](https://offenedaten-koeln.de/dataset/baumkataster-koeln) zuzugreifen. Die Daten werden von den [Offenen Daten Köln](https://offenedaten-koeln.de/) zur Verfügung gestellt. Die Daten sollen in eine Datenbank übernommen werden. Auf diese Weise ist es möglich per API auf den Datenbestand zu zugreifen.

Damit ist die Grundlage für die Antwort auf unterschiedliche Fragestellungen gelegt:
- Was für ein Baum ist das vor dem ich stehe? Wie groß ist er? Und wie alt ist er eigentlich?
- Wie viele Bäume gibt es in einem Stadtbezirk? Wie viele Bäume stehen im Stadtviertel und wieviele in Grünanlagen?
- Wo stehen die ältesten Bäume?
- In welchem Stadtteil gibt es die meisten Bäume?
- Wie Grün ist die Stadt (evtl. im Zusammenhang mit dem [Grünflächen Kataster Köln](https://github.com/codeforcologne/gruenflaechenkoeln))

Die Daten werden nicht dynamisch von [Offenen Daten Köln](https://offenedaten-koeln.de/) gezogen, sondern müssen durch ein weiteres Tool aufbereitet werden (z.B. per [QGIS](https://www.qgis.org)). Sind die Daten in geeigneter Weise aufbereitet, können sie über einen eingebauten Mechanismus eingelesen werden.  

# Status

Die Applikation befindet sich in der Entwicklung

# Verwendete Technologien

- Java
- Postgres/ Postgis

# REST Endpoints

## /baumkataster/service/ping

Diese Schnittstelle wird verwendet um zu prüfen, ob der Service selbst erreichbar ist.

## /baumkataster/service/objekttypen

Diese Schnittstelle liefert alle verwendeten Objekttypen zurück. Es gibt 14 Objekttypen die wie folgt unterteilt sind:

0 keine Information verfügbar
1 NN; 
2 Kleingarten; 
3 Sportplatz; 
4 Kinderspielplatz; 
5 Gebäude/Schule/Heim; 
6 STraße/Platz; 
7 Grünanlage; 
8 Friedhof; 
9 Biotopflächen; 
10 Fluss/Bach; 
11 Sonderanlage; 
12 Forst; 
13 Ausgleichsfläche; 
14 Unbekannt

Beispiel: [/baumkataster/service/objekttypen](https://tom.cologne.codefor.de/baumkataster/service/objekttypen)

## /baumkataster/service/gattungen

Die Schnittstelle gibt alle verzeichneten Gattungen zurück. Bäume ohne zugewiesener Gattung werden als 'null' zurückgegeben.
	 
Beispiel: [/baumkataster/service/gattungen](https://tom.cologne.codefor.de/baumkataster/service/gattungen)

## /baumkataster/service/baeume/{gattung}

Schnittstelle, die es erlaubt, nur Bäume einer bestimmten Gattung aufzufinden. Es wird eine json Struktur zurückgegeben.

Beispiel: [/baumkataster/service/baeume/zelkova](https://tom.cologne.codefor.de/baumkataster/service/baeume/zelkova)

## /baumkataster/service/baeume/{gattung}?geojson

Schnittstelle, die es erlaubt, nur Bäume einer bestimmten Gattung aufzufinden. Die Rückgabe erfolgt im geojson Format.

Beispiel: [/baumkataster/service/baeume/zelkova?geojson](https://tom.cologne.codefor.de/baumkataster/service/baeume/zelkova?geojson)

## /baumkataster/service/baeume?latlng={lat,lng}

Diese Schnittstelle liefert alle verzeichneten Bäume im Umkreis von 100 m zur übergebenen Koordinate im json Format zurück. 

Beispiel: [/baumkataster/service/baeume?latlng=50.959582,6.971568](https://tom.cologne.codefor.de/baumkataster/service/baeume?latlng=50.959582,6.971568)

## /baumkataster/service/baeume?latlng={lat,lng}&geojson

Diese Schnittstelle liefert alle verzeichneten Bäume im Umkreis von 100 m  übergebenen Koordinate im [GeoJson](http://geojson.org/) Format zurück.

Beispiel [/baumkataster/service/baeume?latlng=50.959582,6.971568&geojson](https://tom.cologne.codefor.de/baumkataster/service/baeume?latlng=50.959582,6.971568&geojson)

## /baumkataster/service/baeume?bbox={lat,lng,lat,lng}

Diese Schnittstelle liefert alle verzeichneten Bäume im Rahmen der übergebenen Bounding Box im json Format zurück.

Beispiel [/baumkataster/service/baeume?bbox=50.940692,6.951216,50.931568,6.977266](https://tom.cologne.codefor.de/baumkataster/service/baeume?bbox=50.940692,6.951216,50.931568,6.977266)

## /baumkataster/service/baeume?bbox={lat,lng,lat,lng}&geojson

Diese Schnittstelle liefert alle verzeichneten Bäume im Rahmen der übergebenen Bounding Box im [GeoJson](http://geojson.org/) Format zurück.

Beispiel [/baumkataster/service/baeume?bbox=50.940692,6.951216,50.931568,6.977266&geojson](https://tom.cologne.codefor.de/baumkataster/service/baeume?bbox=50.940692,6.951216,50.931568,6.977266&geojson)

## /baumkataster/service/load

Der Service persistiert die Daten des Baumkataster in der Datenbank. Die Daten müssen im GeoJson-Format vor im Verzeichnis resources vorliegen. Wird der Service aufgerufen, werden die bisherigen Daten gelöscht und die ggf. aktualisierten Daten werden eingelesen. Von der Kommandozeile kann die Schnittstelle mit folgendem Kommando aufgerufen werden:

    curl -X PUT http://localhost:8080/baumkataster/service/load

# Datenbank
    
## Tabelle

### baumkataster

| Spalte | Typ | Beschreibung |
| ------ | --- | ------------ |
| id | integer | interner Schlüssel für Relation |
| pflegeobjekt | integer | unbekannt |
| objekttyp | integer | Es gibt 14 Objekttypen die wie folgt unterteilt sind: 1 NN; 2 Kleingarten; 3 Sportplatz; 4 Kinderspielplatz; 5 Gebäude/Schule/Heim; 6 STraße/Platz; 7 Grünanlage; 8 Friedhof; 9 Biotopflächen; 10 Fluss/Bach; 11 Sonderanlage; 12 Forst; 13 Ausgleichsfläche; 14 Unbekannt |
| bezirk | integer | mutmasslich Nummer des Stadtbezirk |
| baumbestand | varchar(256) | Z.B Baumbest:1 : 22P => 22 P ist die Baumnummer Gängig sind folgende Buchstabenkürzel: G = Bäume auf der Seite mit geraden Hausnummern U = Bäume auf der Seite mit ungeraden Hausnummern P = Bäume auf einen Platz M = Bäume auf einem Mittelstreifen MU = Bäume auf einem Mittelstreifen zur Seite mit den ungeraden Hausnummern MG = Bäume auf einem Mittelstreifen zur Seite mit den geraden Hausnummern MM = Bäume auf einem Mittelstreifen in der mittleren Reihe Ein Teil der Bäume hat auch nur eine Nummer, das ist z.B. auf Spielplätzen der Fall oder wenn in einer Straße nur wenige Bäume stehen. Die Nummerierung ist teilweise so eingerichtet, dass bei einem Kontrollgang der kürzeste Weg genommen werden kann – dafür sind die Buchstaben teilweise auch hinter die Baumnummern gesetzt.   |
| stammvon | double precision | mutmasslich kleinster stammdurchmesser (in cm) |
| stammbis | double precision | mutmasslich größter stammdurchmesser (in cm) |
| krone | double precision | mutmassich Durchmesser der Krone (in m) |
| hoehe | double precision | mutmasslich Höhe (in m) |
| alter | double precision | geschätzes Alter (in Jahren) |
| gattung | varchar(256) | Gattung |
| art | varchar(256) | Art |
| sorte | varchar(256) | z.B. "Sorte": null, In der botanischen Nomenklatur unterteilt man Pflanzen in Gattung, Art und Sorte Bei Pflanzungen in früheren Zeiten wurden hierzu leider keine Angaben gemacht. Bei Neupflanzungen sollen diese Einträge nun standardmäßig durchgeführt werden. Der Eintrag „null“ gibt an, dass hier keine Sorte eingetragen wurde. |
| deutsch | varchar(256) | deutschsprachiger Name |
| modtime | timestamp DEFAULT current_timestamp | Zeitpunkt der Erstellung des Datensatzes |
| geom | POINT | Geometry als POINT |

    CREATE TABLE BAUMKATASTER (
      id            SERIAL PRIMARY KEY, 
      pflegeobjekt  integer, 
      objekttyp     integer, 
      bezirk        integer, 
      baumbestand   varchar(256),
	  stammvon      double precision,
	  stammbis      double precision,
	  krone         double precision,
	  hoehe         double precision,
	  alter         double precision,
      gattung       varchar(256),
      art           varchar(256),
      sorte         varchar(256),
      deutsch       varchar(256),
      modtime       timestamp DEFAULT current_timestamp
    );
    SELECT AddGeometryColumn ('public','baumkataster','geom',4326,'POINT',2);

## Verbindungsparameter

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.