
' Hallo, du interessierter RPG-Spieler !
' Ich finde es ganz toll, das du dir dieses geniale Spiel von
' Meiner Seite runtergeladen hast. Leider mu· ich dir an dieser
' Stelle eine traurige Mitteilung machen :
' Das Spiel ist noch gar nicht fertig !
' Schade, was? Naja, man kann es ja schon spielen, aber es ist viel
' zu einfach, und der "FINAL FIGHT" fehlt vîllig.
' Sollte das Proggy irgendwann einmal fertig werden, so werde ich
' es natuerlich auf meiner Seite "uploaden", und einen Vermerk auf die
' Seite schreiben.
' Irgendwelche Fragen, Kritikpunkte, oder VerbesserungsvorschlÑge ?
' dann schreib einfach an :
' Christian.Gellert@t-online.de
'
' Viel Spass beim spielen ... Christian





CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT " Wie soll ihr Held hei·en ? "
INPUT "Name : ", NAME1$
PRINT ""
PRINT " Wie soll die Rittersmaid hei·en ? "
INPUT "NAME : ", MAID$
PRINT ""
REM PRINT " Mit wieviel Hitpoints soll ihr Held anfangen ?   (z.b. 40) "
REM INPUT "HHP : ", HHP!
HHP! = 40  ' Festsetzung der Anfangsenergie
HSP! = 1
CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT " Wilkommen in 'Gisberts scary dungeons' "
PRINT " "
PRINT " Sie hei·en "; NAME1$; ", und starten ihr Abenteuer mit"
PRINT HHP!; "Hitpoints, und einem Messer (2 HSP)."
PRINT ""
PRINT "Ihre holde Maid "; MAID$; " wurde von einem bîsen Drachen"
PRINT "entfÅhrt. Das sehen Sie natÅrlich Åberhaupt nicht ein, und"
PRINT "beschlie·en, dem Drachen in sein Schlo· zu folgen. Das "
PRINT "Schlo· des Drachen ist 20 Tage von ihrem derzeitigen Standpunkt"
PRINT "entfernt. Sie machen sich also auf den Weg, ihre Maid zu retten."
PRINT ""
PRINT ""
SHELL "pause >nul"
CLS

REM        Anfangswerte
DAY! = 0
HW! = 0
HSP! = 2
ERF! = 0
REM        Anfangswerte



REM        Tagesablauf
tagX:          ' Anfangslabel fÅr normale Tage
DAY! = DAY! + 1
REM   Waffenerkennung

IF HSP! > 0 THEN WAFFE$ = "Messer"
IF HSP! > 35 THEN WAFFE$ = "HandrÅckenklaue"
IF HSP! > 60 THEN WAFFE$ = "Axt"
IF HSP! > 100 THEN WAFFE$ = "Schwert"
IF HSP > 140 THEN WAFFE$ = "Zauberstab"

'REM StÑrkenzuweisung
'IF HW! = 0 THEN HSP! = 2
'IF HW! = 1 THEN HSP! = 3
'IF HW! = 2 THEN HSP! = 4
'IF HW! = 3 THEN HSP! = 5
'IF HW! = 4 THEN HSP! = 6


REM Auswahl des Monsters
RANDOMIZE TIMER
m% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung

IF m% = 1 THEN GOTO ghoul
IF m% = 2 THEN GOTO zombie
IF m% = 3 THEN GOTO Skelett
IF m% = 4 THEN GOTO ritter

tagx2:
REM Auswahl der Landschaft
RANDOMIZE TIMER
i% = INT(RND * 5) + 1     'erster Wert (5) fÅr Begrenzung

IF i% = 1 THEN LS$ = "Berglandschaft"
IF i% = 2 THEN LS$ = "Sumpf"
IF i% = 3 THEN LS$ = "Wald"
IF i% = 4 THEN LS$ = "verlassenes Dorf"
IF i% = 5 THEN LS$ = "Smaragdhîhle"

'IF HSP! > 0 THEN WAFFE$ = "Messer"
'IF HSP! > 4 THEN WAFFE$ = "HandrÅckenklaue"
'IF HSP! > 8 THEN WAFFE$ = "Axt"
'IF HSP! > 12 THEN WAFFE$ = "Schwert"
'IF HSP > 16 THEN WAFFE$ = "Zauberstab"


REM     Kampfbildschirm

CLS
runde! = 1
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT "Heute ist der"; DAY!; ". Tag. Sie ("; NAME1$; ") werden von einem"
PRINT NAME2$; " angegriffen."
PRINT ""
PRINT "Statistik :"
PRINT ""
PRINT "                   Tag :"; DAY!
PRINT "            Landschaft :"; LS$
PRINT ""
PRINT "                  Held :"; NAME1$
PRINT "                 Waffe :"; WAFFE$
PRINT "                StÑrke :"; HSP!
PRINT "             Hitpoints :"; HHP!
PRINT "      Erfahrungspunkte :"; ERF!
PRINT ""
PRINT "                  Gold :"; GOLD!
PRINT "             SchlÅssel :"; KEYS!
PRINT ""
PRINT ""
PRINT "                Gegner :"; NAME2$
PRINT "    StÑrke des Gegners :"; ESP!
PRINT " Hitpoints des Gegners :"; EHP!
PRINT " "
PRINT "                 Runde :"; runde!
SHELL "pause >nul"

GOTO fight



fight:
CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT " Kampfprotokoll :"
fightfor:
PRINT ""
PRINT " Der "; NAME2$; " greift an :"

er:
RANDOMIZE TIMER
i% = INT(RND * 2) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN GOTO geghit
IF i% = 2 THEN PRINT "Der "; NAME2$; " hat dich verfehlt "
GOTO du

du:
PRINT ""
PRINT ""
PRINT " Jetzt greifst du an : "
RANDOMIZE TIMER
i% = INT(RND * 2) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN GOTO hit
IF i% = 2 THEN PRINT " Du verfehlst den "; NAME2$
GOTO newround

hit:
RANDOMIZE TIMER
i% = INT(RND * 3) + 1     'erster Wert (3) fÅr Begrenzung
IF i% = 1 THEN loca$ = "Kopf"
IF i% = 2 THEN loca$ = "Bein"
IF i% = 3 THEN loca$ = "Arsch"
PRINT " Du triffst den "; NAME2$; " am "; loca$
PRINT ""
EHP! = EHP! - HSP!

GOTO newround


geghit:
RANDOMIZE TIMER
i% = INT(RND * 3) + 1     'erster Wert (3) fÅr Begrenzung
IF i% = 1 THEN loca$ = "Kopf"
IF i% = 2 THEN loca$ = "Bein"
IF i% = 3 THEN loca$ = "Arsch"
PRINT " Der "; NAME2$; " trifft dich am "; loca$
PRINT ""
HHP! = HHP! - ESP!
GOTO du

newround:
IF EHP! < 1 THEN GOTO victory
IF HHP! < 1 THEN GOTO death
SHELL "pause >nul"
GOTO fightfor

SYSTEM



victory:
CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
REM Ermittlung der Beute
RANDOMIZE TIMER
i% = INT(RND * 5) + 1     'erster Wert (5) fÅr Begrenzung
GOLD! = GOLD + i%
HSP! = HSP! + (m% + 5)
IF HHP! < 1 THEN GOTO death
PRINT ""
PRINT " Sie haben den Kampf gewonnen !"
PRINT ""
PRINT " In den Eingeweiden des "; NAME2$; " finden Sie "; i%; " GoldstÅcke."
PRINT " Sie haben jetzt "; ERF!; " Erfahrungspunkt(e)."
PRINT " Ihr Status sieht nun so aus :"
PRINT ""
PRINT " FÅr den Sieg Åber ein "; NAME2$; " erhalten sie zusÑtzlich"; (m% * 2); " "
PRINT " StÑrkepunkte."
PRINT ""
PRINT "                   Tag :"; DAY!
PRINT "            Landschaft :"; LS$
PRINT ""
PRINT "                  Held :"; NAME1$
PRINT "                 Waffe :"; WAFFE$
PRINT "                StÑrke :"; HSP!
PRINT "             Hitpoints :"; HHP!
PRINT "      Erfahrungspunkte :"; ERF!
PRINT ""
PRINT "                  Gold :"; GOLD!
PRINT "             SchlÅssel :"; KEYS!
PRINT ""
PRINT ""
PRINT " Taste drÅcken, um den "; DAY!; ". Tag zu beenden ! "
SHELL "pause >nul"


REM Auswertung des Tageswerkes ...
IF DAY! > 18 THEN GOTO finale
IF DAY! = 2 THEN GOTO shop
IF DAY! = 9 THEN GOTO shop
IF DAY! = 14 THEN GOTO shop
IF DAY! = 19 THEN GOTO shop
GOTO tagX


death:
CLS
CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT "               Sie sind tot !"
PRINT ""
PRINT " Leider haben Sie es nicht geschaft ihre holde Maid "; MAID$
PRINT " zu befreien. Immerhin erlangten Sie einen Punktestand von"
PRINT ERF!; " Erfahrungspunkten"
PRINT ""
PRINT " bis zum nÑchsten mal ..."
PRINT ""
PRINT ""
SHELL "pause >nul"
CLS
SYSTEM











REM -------------------- the Monster domain ----------------------

ghoul:
NAME2$ = "Ghoul"
EHP! = 4
ESP! = 2
ERF! = ERF! + 1
GOTO tagx2

zombie:
NAME2$ = "Zombie"
EHP! = 5
ESP! = 3
ERF = ERF! + 2
GOTO tagx2

Skelett:
NAME2$ = "Skelett"
EHP! = 6
ESP! = 4
ERF! = ERF! + 3
GOTO tagx2

ritter:
NAME2$ = "schwarzer Ritter"
EHP! = 7
ESP! = 5
ERF! = ERF! + 4
GOTO tagx2


                         

shop:
CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT "Am "; DAY!; ". Tag kommen Sie an einem Laden vorbei, an bei dem"
PRINT "Sie sich mit Medizin und SchlÅsseln versorgen kînnen. Sie wissen"
PRINT "zwar im Moment nicht, was Sie mit den SchlÅsseln sollen, aber "
PRINT "eine innere Stimme sagt ihnen, das sie mindestens 3 SchlÅssel"
PRINT "fÅr ihre letzte Konfrontation mit dem Drachen brauchen."
PRINT ""
PRINT "Anmerkung des Autors :"
PRINT "In dem Land, in dem diese Geschichte spielt, passt jeder SchlÅssel"
PRINT "zu jeder TÅr. Und jeder SchlÅssel kann nur ein einziges mal "
PRINT "verwendet werden. Das ist eben das Gesetz von Gisberts Welt !"
PRINT ""
PRINT " DrÅcken sie eine Taste, um den Laden zu betreten ..."
PRINT ""
PRINT ""
SHELL "pause >nul"

inside:
CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT " Preisliste :"
PRINT ""
PRINT "      1 x Medizin    =  2 GoldstÅcke   (5 HHP)"
PRINT "      1 x SchlÅssel  = 10 GoldstÅcke"
PRINT "      1 Glas Bier    =  1 GoldstÅck"
PRINT ""
PRINT "   Status :"
PRINT ""
PRINT "                   Tag :"; DAY!
PRINT "            Landschaft :Laden"
PRINT ""
PRINT "                  Held :"; NAME1$
PRINT "             Hitpoints :"; HHP!
PRINT ""
PRINT "                  Gold :"; GOLD!
PRINT "             SchlÅssel :"; KEYS!
PRINT ""
PRINT " VerkÑufer :"
PRINT "DrÅcken Sie die 7 um Medizin zu kaufen, und die 8 fÅr einen SchlÅssel."
PRINT "Mit ESC und ENTER verlassen Sie meinen Laden. Um ein Glas Bier zu"
PRINT "bestellen, drÅcken Sie die 9 !"
start23:
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(56) OR a$ = CHR$(13) OR a$ = CHR$(55) OR a$ = CHR$(27)
IF a$ = CHR$(57) THEN GOTO bier
IF a$ = CHR$(56) THEN GOTO schluessel
IF a$ = CHR$(13) THEN GOTO raus
IF a$ = CHR$(55) THEN GOTO medikit
IF a$ = CHR$(27) THEN GOTO raus
GOTO start23

bier:
IF GOLD! < 1 THEN GOTO pleite
GOLD! = GOLD! - 1
GOTO inside

schluessel:
IF GOLD! < 10 THEN GOTO pleite
GOLD! = GOLD! - 10
KEYS! = KEYS! + 1
GOTO inside

medikit:
IF GOLD! < 2 THEN GOTO pleite
GOLD! = GOLD! - 2
HHP! = HHP! + 5
GOTO inside


pleite:
CLS
COLOR 0, 7
PRINT "          Gisberts scary dungeons   BETA                                        "
COLOR 7, 0
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT " Achtung : Sie haben zuwenig Gold um diesen Gegenstand zu kaufen"
PRINT ""
PRINT ""
PRINT ""
PRINT "         restliches Gold :"; GOLD!
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT "VerkÑufer :"
PRINT "Wenn Sie dafÅr nicht genug Gold haben, mÅssen Sie den Laden"
PRINT "verlassen, oder etwas anderes kaufen."
PRINT ""
SHELL "pause >nul"
GOTO inside

raus:
GOTO tagX


finale:
PRINT ""
PRINT " Das Finale gibt es leider noch nicht."
PRINT " Deshalb mÅssen Sie leider sterben !."
SHELL "pause >nul"
CLS
GOTO death

