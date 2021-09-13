


# bsm
 <a name="namespace_title_bsm.bsm"></a>


### Diagram
 <a name="namespace_diagram_bsm.bsm"></a>

```mermaid
classDiagram
 class Plan{
 EDate start
 EDate ende
 PlanTyp type
 EDate stand
 }

 Plan "1" --> "1..1" Traeger : traeger


 class PlanPosition{
 String nummer
 Integer order
 EString titel
 EString beschreibung
 EDate start
 EDate ende
 }

 PlanPosition "1" --> "1..1" Plan : plan
 PlanPosition "1" --> "0..-1" MassnahmeAnteil : massnahmeanteil


 class MassnahmeAnteil{
 EDoubleObject anteil
 EString beschreibung
 }

 MassnahmeAnteil "1" --> "1..1" Massnahme : massnahme


 class StrukturObjekt{
 EString uuid
 EString bezeichnung
 EString beschreibung
 EString externe_id
 EBoolean parkplatzBetroffen
 EString beschreibungExtern
 }

 StrukturObjekt "1" --> "0..1" StrukturObjekt : parent
 StrukturObjekt "1" --> "1..1" Traeger : traeger
 StrukturObjekt "1" --> "1..-1" Zeitraum : zeitraum


 class Traeger{
 EString title
 IntObject id
 }



 class Zeitraum{
 EDate erstellt
 EDate start
 EDate ende
 ZeitraumTyp typ
 EDate aufgehoben
 EString hinweis
 ZeitraumAenderungsTyp aenderungsTyp
 }



 class Massnahme{
 PolygonGeom the_geom
 }


 Massnahme ..> StrukturObjekt

 class Freihalteflaeche{
 PolygonGeom the_geom
 }


 Freihalteflaeche ..> StrukturObjekt

 class Umleitung{
 LineGeom the_geom
 }


 Umleitung ..> StrukturObjekt

 class Asset{
 EDate gewaehrleistungsDatum
 }




 class PlanTyp{
 <<enumeration>>
 WIRTSCHAFTSPLAN
 INVESTPLAN
 }

 class ZeitraumTyp{
 <<enumeration>>
 IDEE_TRAEGER
 KOORDINIERT_BSM
 BEAUFTRAGT
 ZWANG
 FREIGABE_STRASSE
 IST
 PLANUNG
 }

 class ZeitraumAenderungsTyp{
 <<enumeration>>
 Witterung
 Finanzielle Engpässe
 Fehlplanung
 Auschreibung
 Personal Engpässe
 NONE
 }


```


### Classes
 <a name="classes_bsm.bsm"></a>


#### Plan
 <a name="class_bsm.plan"></a>

##### Description
 <a name="class_description_bsm.plan"></a>

Ein Plan einer Trägers, in dem für einen vorgegebenen Zeitraum Wirtschaftliche tätigkeitsfelder aufgelistet werden.
Beispiel, Wirtschaftplan, Mittelfrist-Finanzplan.

##### Diagram
 <a name="class_diagram_bsm.plan"></a>

```mermaid
classDiagram
 class Plan{
 EDate start
 EDate ende
 PlanTyp type
 EDate stand
 }

 Plan "1" --> "1..1" Traeger : traeger


```

none


##### Fields
 <a name="class_fields_bsm.plan"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **start** | EDate| 1..1|Beginn des Zeitraums für den der Plan gilt.|
| **ende** | EDate| 1..1|Ende des Zeitraums für den der Plan gilt.|
| **type** | PlanTyp| 0..1|Typisierung des Wirtschaftsplanes.|
| **stand** | EDate| 0..1|Zeitpunkt zu dem der Plan gültig ist.|

##### References
 <a name="class_references_bsm.plan"></a>

| Name| Type| Bounds| Description|
| ----| ----| ------| -----------|
| **traeger** | [Traeger](#class_bsm.traeger)| 1..1|Der Träger, der den Plan erstellt.|

##### Methods
 <a name="class_methods_bsm.plan"></a>

none





#### PlanPosition
 <a name="class_bsm.planposition"></a>

##### Description
 <a name="class_description_bsm.planposition"></a>

Eine Zeile in einem Plan.

##### Diagram
 <a name="class_diagram_bsm.planposition"></a>

```mermaid
classDiagram
 class PlanPosition{
 String nummer
 Integer order
 EString titel
 EString beschreibung
 EDate start
 EDate ende
 }

 PlanPosition "1" --> "1..1" Plan : plan
 PlanPosition "1" --> "0..-1" MassnahmeAnteil : massnahmeanteil


```

none


##### Fields
 <a name="class_fields_bsm.planposition"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **nummer** | String| 0..1|Die Nummer zur ermögliches eines schnellen Erfasses einer Position in einer Liste mehrerer Positionen.|
| **order** | Integer| 0..1|Sortiert die Positionen im Plan.|
| **titel** | EString| 0..1|Die Kurzbezeichnung der Position.|
| **beschreibung** | EString| 0..1|Eine erklärende Beschreibung der der Position.|
| **start** | EDate| 0..1|Start der Position, wenn leer wird ist das Startdatum des Plans gemeint.|
| **ende** | EDate| 0..1|Start der Position, wenn leer wird ist das Enddatum des Plans gemeint.|

##### References
 <a name="class_references_bsm.planposition"></a>

| Name| Type| Bounds| Description|
| ----| ----| ------| -----------|
| **plan** | [Plan](#class_bsm.plan)| 1..1|Verweis auf den Plan der die Planpositionen Gruppiert.|
| **massnahmeanteil** | [MassnahmeAnteil](#class_bsm.massnahmeanteil)| 0..-1||

##### Methods
 <a name="class_methods_bsm.planposition"></a>

none





#### MassnahmeAnteil
 <a name="class_bsm.massnahmeanteil"></a>

##### Description
 <a name="class_description_bsm.massnahmeanteil"></a>

Der Anteil mit dem eine Massnahme in einer Planposition enthalten ist.

##### Diagram
 <a name="class_diagram_bsm.massnahmeanteil"></a>

```mermaid
classDiagram
 class MassnahmeAnteil{
 EDoubleObject anteil
 EString beschreibung
 }

 MassnahmeAnteil "1" --> "1..1" Massnahme : massnahme


```

none


##### Fields
 <a name="class_fields_bsm.massnahmeanteil"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **anteil** | EDoubleObject| 0..1|Der Anteil in Prozent.|
| **beschreibung** | EString| 0..1|Textliche beschreibung des Anteils.|

##### References
 <a name="class_references_bsm.massnahmeanteil"></a>

| Name| Type| Bounds| Description|
| ----| ----| ------| -----------|
| **massnahme** | [Massnahme](#class_bsm.massnahme)| 1..1|Die Massnehme dem der Anteil zugeordnet ist.|

##### Methods
 <a name="class_methods_bsm.massnahmeanteil"></a>

none





#### StrukturObjekt
 <a name="class_bsm.strukturobjekt"></a>

##### Description
 <a name="class_description_bsm.strukturobjekt"></a>

Eine räumlich und zeitlich abgrenzbare Aktion eines Trägers.

##### Diagram
 <a name="class_diagram_bsm.strukturobjekt"></a>

```mermaid
classDiagram
 class StrukturObjekt{
 EString uuid
 EString bezeichnung
 EString beschreibung
 EString externe_id
 EBoolean parkplatzBetroffen
 EString beschreibungExtern
 }

 StrukturObjekt "1" --> "0..1" StrukturObjekt : parent
 StrukturObjekt "1" --> "1..1" Traeger : traeger
 StrukturObjekt "1" --> "1..-1" Zeitraum : zeitraum


```

none


##### Fields
 <a name="class_fields_bsm.strukturobjekt"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **uuid** | EString| 0..1|Eindeutlige und unveränderliche ID der Massnahnme.|
| **bezeichnung** | EString| 0..1|Kurzer schnell verständlicher Name der Massnahme, der in der Regel auf jedem offiziellen Dokument verwendet wird.
Für Straßenbaumaßnahmen idealerweise im Muster Straßenname: Art d. Maßnahme.|
| **beschreibung** | EString| 0..1|Text, der hilft, intern die Masnahme näher zu erklären. Länge des textes sollte 500 zeichen nicht überschreiten.|
| **externe_id** | EString| 0..1|Id im Quellsystem dieses Datensatzes.|
| **parkplatzBetroffen** | EBoolean| 0..1|Gibt an, ob die Baumassnahme zu Einschänkungen an Parkplätzen führt.|
| **beschreibungExtern** | EString| 0..1|Text, der hilft, der öffentlichkeit die Masnahme näher zu erklären. Länge des textes sollte 500 zeichen nicht überschreiten.|

##### References
 <a name="class_references_bsm.strukturobjekt"></a>

| Name| Type| Bounds| Description|
| ----| ----| ------| -----------|
| **parent** | [StrukturObjekt](#class_bsm.strukturobjekt)| 0..1|Verweis auf die übergeordnete Massnahme.|
| **traeger** | [Traeger](#class_bsm.traeger)| 1..1||
| **zeitraum** | [Zeitraum](#class_bsm.zeitraum)| 1..-1|Verweis aus die Massnahme des Zeitraums|

##### Methods
 <a name="class_methods_bsm.strukturobjekt"></a>

none





#### Traeger
 <a name="class_bsm.traeger"></a>

##### Description
 <a name="class_description_bsm.traeger"></a>

none

##### Diagram
 <a name="class_diagram_bsm.traeger"></a>

```mermaid
classDiagram
 class Traeger{
 EString title
 IntObject id
 }



```

none


##### Fields
 <a name="class_fields_bsm.traeger"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **title** | EString| 0..1|Eine kurze übersichtliuche Bezeichnung des Trägers.|
| **id** | IntObject| 0..1|ID des Trägers, wird durch Stadt vergeben|

##### References
 <a name="class_references_bsm.traeger"></a>

none

##### Methods
 <a name="class_methods_bsm.traeger"></a>

none





#### Zeitraum
 <a name="class_bsm.zeitraum"></a>

##### Description
 <a name="class_description_bsm.zeitraum"></a>

Historisierter Zeitraum einer Massnahme. Genauigkeit von 'start' und 'ende' ist abhängig von dem Horizint der Umsetzung
- >4 Jahre mindestens jährlich
- 2-4 Jahre mindestens halbjährlich
- <2 Jahre monatsscharf

Gültig ist,
- je Typ, immer der Zeitraum mit dem spätesten 'erstellt' , der keine 'aufgehoben' hat.
- typübergreifend, wie 'je Typ'  und dann in folgender Rangordnung der Zeitraumtypen: 
> ZWANG
> IDEE_TRAEGER
> KOORDINIERT_BSM
> BEAUFTRAGT
> FREIGABE_STRASSE
> PLANUNG
> IST


##### Diagram
 <a name="class_diagram_bsm.zeitraum"></a>

```mermaid
classDiagram
 class Zeitraum{
 EDate erstellt
 EDate start
 EDate ende
 ZeitraumTyp typ
 EDate aufgehoben
 EString hinweis
 ZeitraumAenderungsTyp aenderungsTyp
 }



```

none


##### Fields
 <a name="class_fields_bsm.zeitraum"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **erstellt** | EDate| 1..1||
| **start** | EDate| 1..1|Markiert den in der Realität stattfindenden ZeitraumStart.|
| **ende** | EDate| 0..1|Markiert den in der Realität stattfindenden ZeitraumEnde.
Nur im IST ist das Ende Optional|
| **typ** | ZeitraumTyp| 1..1|Markiert die art des Zeitraumes|
| **aufgehoben** | EDate| 0..1|Datum, an dem dieser Zeitraum als als aufgehoben markiert wird.|
| **hinweis** | EString| 0..1|Hinweis darauf, wieso ein neuer Zeitabschnitt erstellt wird.|
| **aenderungsTyp** | ZeitraumAenderungsTyp| 0..1|Typisiete Begründung für die Änderung des vorhergehenden Zeitraum.|

##### References
 <a name="class_references_bsm.zeitraum"></a>

none

##### Methods
 <a name="class_methods_bsm.zeitraum"></a>

none





#### Massnahme
 <a name="class_bsm.massnahme"></a>

##### Description
 <a name="class_description_bsm.massnahme"></a>

Baumassnahme eines Trägers.

##### Diagram
 <a name="class_diagram_bsm.massnahme"></a>

```mermaid
classDiagram
 class Massnahme{
 PolygonGeom the_geom
 }


 Massnahme ..> StrukturObjekt

```

| Name| Type| 
| ----| ----|
| **** | [StrukturObjekt](#class_bsm.strukturobjekt) |


##### Fields
 <a name="class_fields_bsm.massnahme"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **the_geom** | PolygonGeom| 0..1|Das Polygon|

##### References
 <a name="class_references_bsm.massnahme"></a>

none

##### Methods
 <a name="class_methods_bsm.massnahme"></a>

none





#### Freihalteflaeche
 <a name="class_bsm.freihalteflaeche"></a>

##### Description
 <a name="class_description_bsm.freihalteflaeche"></a>

Bereiche, die freigehalten werden sollen. Sollten Massnahmen auf einer Freihaltefläche stattfinden, muss derr Massnahmeträger und das Baustellenmanagement darüber informiert werden und einen Ausnahmegrund formulieren.

##### Diagram
 <a name="class_diagram_bsm.freihalteflaeche"></a>

```mermaid
classDiagram
 class Freihalteflaeche{
 PolygonGeom the_geom
 }


 Freihalteflaeche ..> StrukturObjekt

```

| Name| Type| 
| ----| ----|
| **** | [StrukturObjekt](#class_bsm.strukturobjekt) |


##### Fields
 <a name="class_fields_bsm.freihalteflaeche"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **the_geom** | PolygonGeom| 0..1|Das Polygon|

##### References
 <a name="class_references_bsm.freihalteflaeche"></a>

none

##### Methods
 <a name="class_methods_bsm.freihalteflaeche"></a>

none





#### Umleitung
 <a name="class_bsm.umleitung"></a>

##### Description
 <a name="class_description_bsm.umleitung"></a>

Bescheiben Wege, die als Umleitung für massnahmen dienen. Umleitungen die sich oberschneiden sollen dem zuständigen angezeigt werden.

##### Diagram
 <a name="class_diagram_bsm.umleitung"></a>

```mermaid
classDiagram
 class Umleitung{
 LineGeom the_geom
 }


 Umleitung ..> StrukturObjekt

```

| Name| Type| 
| ----| ----|
| **** | [StrukturObjekt](#class_bsm.strukturobjekt) |


##### Fields
 <a name="class_fields_bsm.umleitung"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **the_geom** | LineGeom| 0..1|Die Linie|

##### References
 <a name="class_references_bsm.umleitung"></a>

none

##### Methods
 <a name="class_methods_bsm.umleitung"></a>

none





#### Asset
 <a name="class_bsm.asset"></a>

##### Description
 <a name="class_description_bsm.asset"></a>

Ein Gegenstand im Stadtraum.
- Wird unter führung von Frau Dietsch im SmartCityProjekt zusammen mit KIJ und Stadt separat und Detailierter Modeliert.
Betrifft:
- Straßen
- Gebäude
- Haltestellen
- Mülleiner
- technische Anlagen
- ....

und deren Metadaten, Zustände und Prüfintervalle!



##### Diagram
 <a name="class_diagram_bsm.asset"></a>

```mermaid
classDiagram
 class Asset{
 EDate gewaehrleistungsDatum
 }



```

none


##### Fields
 <a name="class_fields_bsm.asset"></a>

| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
| **gewaehrleistungsDatum** | EDate| 0..1||

##### References
 <a name="class_references_bsm.asset"></a>

none

##### Methods
 <a name="class_methods_bsm.asset"></a>

none






### Enumerations
 <a name="enums_bsm.bsm"></a>


#### PlanTyp
 <a name="enum_bsm.plantyp"></a>

##### Description
 <a name="enum_description_bsm.plantyp"></a>

Typisiert die Art des Plans

##### Literals
 <a name="enum_literals_bsm.plantyp"></a>

| Literal| Description|
| -------| ----------|
| **WIRTSCHAFTSPLAN**| Wirtschaftsplan Fokus Folgejahr|
| **INVESTPLAN**| Fokus mittelfristige Investitionen|




#### ZeitraumTyp
 <a name="enum_bsm.zeitraumtyp"></a>

##### Description
 <a name="enum_description_bsm.zeitraumtyp"></a>

Liste an Markern, die verwendet werden muss um Zeiträume zu klassifizieren.

##### Literals
 <a name="enum_literals_bsm.zeitraumtyp"></a>

| Literal| Description|
| -------| ----------|
| **IDEE_TRAEGER**| Markiert den Zeitraum, den sich der Täger initial und unamgestimmt Vorschlägt. Idee des Trägers|
| **KOORDINIERT_BSM**| Markiert den Zeitraum, der nach Abstimmung innerhalb des Baustellenmanagements festgelegt wurde.|
| **BEAUFTRAGT**| Markiert den Zeitraum, in dem das Unternehmen mit der Umsetzung beauftragt ist.|
| **ZWANG**| Markiert einen Zeitraum in dem die Massnahme zwingend erledigt erden muss. Kann gleichzeitig zum VORSSCHLAG_TRAEGER erstellt werden.|
| **FREIGABE_STRASSE**| Der Zeitraum für die die Baustelle eine Freigabe der Straßen/Verkehrsbehörde hat.|
| **IST**| Tatsächlicher Zeitpunkt der realen Ausführung.|
| **PLANUNG**| Markiert den jeweils genausten Zeitraum vor IST|




#### ZeitraumAenderungsTyp
 <a name="enum_bsm.zeitraumaenderungstyp"></a>

##### Description
 <a name="enum_description_bsm.zeitraumaenderungstyp"></a>

Typisiete Begründung für die Änderung des  eines Zeitraums
.

##### Literals
 <a name="enum_literals_bsm.zeitraumaenderungstyp"></a>

| Literal| Description|
| -------| ----------|
| **Witterung**| |
| **Finanzielle Engpässe**| |
| **Fehlplanung**| |
| **Auschreibung**| |
| **Personal Engpässe**| |
| **NONE**| |






