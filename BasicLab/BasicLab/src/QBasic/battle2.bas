DECLARE SUB ray5 ()
DECLARE SUB ray4 ()
DECLARE SUB ray3 ()
DECLARE SUB ray2 ()
DECLARE SUB outro ()
DECLARE SUB credits ()
DECLARE SUB shield ()
DECLARE SUB ray1 ()
DECLARE SUB mmenu ()
DECLARE SUB nmenu ()
DECLARE SUB notenough ()
DECLARE SUB ausg ()
DECLARE SUB player ()
DECLARE SUB enemyb ()
DECLARE SUB statusk ()
DECLARE SUB ausw ()

restart:
SCREEN 1
CLS
PRINT ""
PRINT " welcome to ..."
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT "     FINAL FANTASY"
PRINT ""
PRINT "                       THE DUELL"
PRINT ""
PRINT ""
PRINT ""
PRINT " a Christian Gellert game"
LINE (15, 35)-(295, 90), 1, B
PLAY "a2f8d"
PRINT ""
PRINT "________________________________________"
PRINT ""
PRINT " ENTER        =      start a new game"
PRINT " ESC / Q      =      quit game"
PRINT ""
PRINT " S            =      story "
PRINT " H            =      hilfe "
PRINT " C            =      credits"
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(27) OR a$ = CHR$(115) OR a$ = CHR$(113) OR a$ = CHR$(104) OR a$ = CHR$(99) OR a$ = CHR$(13)
SCREEN 2
IF a$ = CHR$(27) THEN CALL ausg   ' ESC
'IF a$ = CHR$(115) THEN CALL story' S
IF a$ = CHR$(113) THEN CALL ausg ' Q
IF a$ = CHR$(13) THEN GOTO 567 'enter
'IF a$ = CHR$(104) THEN GOTO help
IF a$ = CHR$(99) THEN CALL credits
GOTO restart
567 :

hp1! = 3466 ' Hitpoints des Spielers
rb1! = 2
mp1! = 300
sp1! = 3

hp2! = 1999            'Hitpoints des Gegners
mp2! = 200
sp2! = 0


SCREEN 2
CLS
GOTO fight


'-------------------Steuerungssektion--------------------------------------
'--------------------------------------------------------------------------
fight:
'IF hp1! < 1 THEN GOTO dead
'IF hp2! < 1 THEN GOTO victory
CLS
CALL player
CALL enemyb
PRINT ""
PRINT "                            HP :"; hp1!; "                              HP : "; hp2!
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT "________________________________________________________________________________"
PRINT ""
PRINT "             Spieler                                ", enemy$
LINE (320, 0)-(320, 103) ' senkrechter Trennstrich
PRINT ""
PRINT " S = STATUS                 N = normaler Angriff"
PRINT " M = Angriff mit Magie     ???= FATALITY"
PRINT " R = Rauchbombe werfen      P = Shield of protection"
PRINT " Q = Spiel verlassen      ESC = Spiel beenden"
CALL player
CALL enemyb

'Tastaturabfrage
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(27) OR a$ = CHR$(115) OR a$ = CHR$(109) OR a$ = CHR$(114) OR a$ = CHR$(110) OR a$ = CHR$(113) OR a$ = CHR$(13) OR a$ = CHR$(112)
IF a$ = CHR$(27) THEN GOTO restart   ' ESC
IF a$ = CHR$(115) THEN CALL statusk' S
IF a$ = CHR$(109) THEN CALL mmenu' M
IF a$ = CHR$(110) THEN CALL nmenu ' N
IF a$ = CHR$(113) THEN CALL ausg ' Q
IF a$ = CHR$(114) THEN CALL ausw  'R
IF a$ = CHR$(112) THEN CALL shield 'P

GOTO fight

SUB ausg
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
SYSTEM
END SUB

SUB ausw

' ---------------4.Prozedur : der Rauchbomben - Ausweichmodus--------------
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$

CLS
IF NOT rb1! < 1 THEN GOTO furtherxc
CALL notenough
GOTO fine

furtherxc:
rb1! = rb1! - 1
LINE (1, 1)-(530, 40), 1, B
PRINT ""
PRINT " Vom Kampf erschîpft werfen Sie eine Rauchbombe, um etwas Zeit"
PRINT " zu gewinnen. Je îfter Sie es schaffen ihrem Feind im Nebel der"
PRINT " Rauchbombe zu entkommen, desto mehr regenerieren sich Ihre "
PRINT " Hitpoints."
LINE (1, 1)-(530, 40), 1, B
SHELL "pause > nul"
CLS
CALL player
CALL enemyb
eposi! = 9
posi1! = 1
FOR ic! = 1 TO 500 STEP 3
CIRCLE (200, 200), ic!
NEXT
CLS
redoo:
CLS
PRINT ""
PRINT "             Weiche deinem Gegner mit den Num-Tasten aus"
PRINT ""
PRINT ""
PRINT ""
PRINT "  ******************************************************"
PRINT "  ******         **********         ********        ****"
PRINT "  ******         **********         ********        ****"
PRINT "  ******         **********         ********        ****"
PRINT "  ******************************************************"
PRINT "  ******         **********         ********        ****"
PRINT "  ******         **********         ********        ****"
PRINT "  ******         **********         ********        ****"
PRINT "  ******************************************************"
PRINT "  ******         **********         ********        ****"
PRINT "  ******         **********         ********        ****"
PRINT "  ******         **********         ********        ****"
PRINT "  ******************************************************"
PRINT ""
counter! = (counter! + 1)
GOTO enemypos

playerpos:
IF posi1! = 1 THEN GOTO ppos1  'Player Position 1
IF posi1! = 2 THEN GOTO ppos2  'Player Position 2
IF posi1! = 3 THEN GOTO ppos3  '       "
IF posi1! = 4 THEN GOTO ppos4  '       "
IF posi1! = 5 THEN GOTO ppos5  '       "
IF posi1! = 6 THEN GOTO ppos6  '       "
IF posi1! = 7 THEN GOTO ppos7  '       "
IF posi1! = 8 THEN GOTO ppos8  '       "
IF posi1! = 9 THEN GOTO ppos9  '       "

ppos1:
CIRCLE (96, 60), 30  ' Spielerstellung 1
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 7   'oben
IF a$ = CHR$(50) THEN posi1! = 4   'unten
IF a$ = CHR$(52) THEN posi1! = 3   'links
IF a$ = CHR$(54) THEN posi1! = 2   'rechts
GOTO redoo

ppos2:
CIRCLE (250, 60), 30 ' Spielerstellung 2
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 8 'oben
IF a$ = CHR$(50) THEN posi1! = 5  'unten
IF a$ = CHR$(52) THEN posi1! = 1 'links
IF a$ = CHR$(54) THEN posi1! = 3 'rechts
GOTO redoo

ppos3:
CIRCLE (385, 60), 30 ' Spielerposition 3
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 9 'oben
IF a$ = CHR$(50) THEN posi1! = 6 'unten
IF a$ = CHR$(52) THEN posi1! = 2 'links
IF a$ = CHR$(54) THEN posi1! = 1 'rechts
GOTO redoo

ppos4:
CIRCLE (96, 91), 30  ' Spielerposition 4
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 1 'oben
IF a$ = CHR$(50) THEN posi1! = 7 'unten
IF a$ = CHR$(52) THEN posi1! = 6 'links
IF a$ = CHR$(54) THEN posi1! = 5 'rechts
GOTO redoo

ppos5:
CIRCLE (250, 91), 30 ' Spielerstellung 5
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 2 'oben
IF a$ = CHR$(50) THEN posi1! = 8 'unten
IF a$ = CHR$(52) THEN posi1! = 4 'links
IF a$ = CHR$(54) THEN posi1! = 6 'rechts
GOTO redoo

ppos6:
CIRCLE (385, 91), 30 ' Spielerstellung 6
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 3 'oben
IF a$ = CHR$(50) THEN posi1! = 9 'unten
IF a$ = CHR$(52) THEN posi1! = 5 'links
IF a$ = CHR$(54) THEN posi1! = 4 'rechts
GOTO redoo

ppos7:
CIRCLE (96, 123), 30 ' Spielerstellung 7
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 4 'oben
IF a$ = CHR$(50) THEN posi1! = 1 'unten
IF a$ = CHR$(52) THEN posi1! = 9 'links
IF a$ = CHR$(54) THEN posi1! = 8 'rechts
GOTO redoo

ppos8:
CIRCLE (250, 123), 30' Spielerstellung 8
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 5 'oben
IF a$ = CHR$(50) THEN posi1! = 2 'unten
IF a$ = CHR$(52) THEN posi1! = 7 'links
IF a$ = CHR$(54) THEN posi1! = 9 'rechts
GOTO redoo

ppos9:
CIRCLE (385, 123), 30' Spielerstellung 9
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52)
IF a$ = CHR$(56) THEN posi1! = 6 'oben
IF a$ = CHR$(50) THEN posi1! = 3 'unten
IF a$ = CHR$(52) THEN posi1! = 8 'links
IF a$ = CHR$(54) THEN posi1! = 7 'rechts
GOTO redoo

enemypos:
IF eposi! = 1 THEN GOTO epos1 ' Gegnerstellung 1
IF eposi! = 2 THEN GOTO epos2 ' Gegnerstellung 2
IF eposi! = 3 THEN GOTO epos3' Gegnerstellung 3
IF eposi! = 4 THEN GOTO epos4 ' Gegnerstellung 4
IF eposi! = 5 THEN GOTO epos5 ' Gegnerstellung 5
IF eposi! = 6 THEN GOTO epos6 ' Gegnerstellung 6
IF eposi! = 7 THEN GOTO epos7 ' Gegnerstellung 7
IF eposi! = 8 THEN GOTO epos8 ' Gegnerstellung 8
IF eposi! = 9 THEN GOTO epos9 ' Gegnerstellung 9

epos1:
FOR r% = 1 TO 30 STEP 2
CIRCLE (96, 60), r%  ' Gegnerstellung 1
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 7
IF i% = 2 THEN eposi! = 2
IF i% = 3 THEN eposi! = 4
IF i% = 4 THEN eposi! = 3
IF eposi! = posi1! THEN GOTO catched
GOTO playerpos

epos2:
FOR r% = 1 TO 30 STEP 2
CIRCLE (250, 60), r% ' Gegnerstellung 2
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 1
IF i% = 2 THEN eposi! = 8
IF i% = 3 THEN eposi! = 3
IF i% = 4 THEN eposi! = 5
IF eposi! = posi1! THEN GOTO catched
GOTO playerpos


epos3:
FOR r% = 1 TO 30 STEP 2
CIRCLE (385, 60), r% ' Gegnerstellung 3
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 2
IF i% = 2 THEN eposi! = 9
IF i% = 3 THEN eposi! = 1
IF i% = 4 THEN eposi! = 6
IF eposi! = posi1! THEN GOTO catched
GOTO playerpos

epos4:
FOR r% = 1 TO 30 STEP 2
CIRCLE (96, 91), r%  ' Gegnerstellung 4
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 1
IF i% = 2 THEN eposi! = 5
IF i% = 3 THEN eposi! = 7
IF i% = 4 THEN eposi! = 6
IF eposi! = posi1! THEN GOTO catched
GOTO playerpos


epos5:
FOR r% = 1 TO 30 STEP 2
CIRCLE (250, 91), r% ' Gegnerstellung 5
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 4
IF i% = 2 THEN eposi! = 2
IF i% = 3 THEN eposi! = 6
IF i% = 4 THEN eposi! = 8
IF eposi! = posi1! THEN GOTO catched
GOTO playerpos


epos6:
FOR r% = 1 TO 30 STEP 2
CIRCLE (385, 91), r% ' Gegnerstellung 6
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 5
IF i% = 2 THEN eposi! = 3
IF i% = 3 THEN eposi! = 4
IF i% = 4 THEN eposi! = 9
GOTO playerpos


epos7:
FOR r% = 1 TO 30 STEP 2
CIRCLE (96, 123), r% ' Gegnerstellung 7
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 4
IF i% = 2 THEN eposi! = 8
IF i% = 3 THEN eposi! = 9
IF i% = 4 THEN eposi! = 1
GOTO playerpos


epos8:
FOR r% = 1 TO 30 STEP 2
CIRCLE (250, 123), r%' Gegnerstellung 8
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 7
IF i% = 2 THEN eposi! = 5
IF i% = 3 THEN eposi! = 9
IF i% = 4 THEN eposi! = 2
GOTO playerpos

epos9:
FOR r% = 1 TO 30 STEP 2
CIRCLE (385, 123), r%' Gegnerstellung 9
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 8
IF i% = 2 THEN eposi! = 6
IF i% = 3 THEN eposi! = 3
IF i% = 4 THEN eposi! = 7
GOTO playerpos

catched:
CLS
PRINT " Sie konnten dem Feind genau ", counter!; "mal entkommen."
PRINT " DafÅr werden Ihnen ", counter! * 10; " Hitpoints gutgeschrieben."
PRINT
hp1! = hp1! + (counter! * 10)
SHELL "pause > nul"

fine:

END SUB

SUB credits
CLS
SCREEN 2
val2! = 1

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " concept by Christian Gellert"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " illustrations by Christian Gellert"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " graphics by Christian Gellert"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " text by Christian Gellert"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " story by Christian Gellert"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " music by Christian Gellert"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " main code by Christian Gellert"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " in assiocation with CHRY - Co"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " a Christian Gellert game"
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " (c) by CHRY - Co              "
v1! = v1! + .1
NEXT
val2! = val2! + 2

v1! = 1
FOR i% = 1 TO 600 STEP 1
LOCATE val2!, v1!
PRINT " 1999 - 2010 all rights reserved"
v1! = v1! + .1
NEXT
val2! = val2! + 1

SHELL "pause > nul"
CLS
SCREEN 1
END SUB

SUB enemyb
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
' ------------------2. Prozedur : der Beta-Gegner------------------
enemy$ = "Strohpuppe"  'Name des Gegners

LINE (490, 50)-(490, 80)  'Kîrper
LINE (490, 80)-(460, 103) 'Beine
LINE (490, 80)-(520, 103) 'Beine
LINE (490, 50)-(450, 70)  'Arme
LINE (490, 50)-(530, 70)  'Arme
CIRCLE (490, 40), 22      'Kopf
CIRCLE (480, 40), 4   'Auge
CIRCLE (500, 40), 4   'Auge
PSET (490, 45)        'Nase

END SUB

SUB mmenu
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
above:
CLS
PRINT ""
PRINT ""
PRINT "-_-_-_-_-_- BOOK OF SPELLS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-"
PRINT ""
PRINT "  page   -      spell result"
PRINT "_______________________________________"
PRINT "    1    : ring ray"
PRINT "    2    : super ring ray "
PRINT ""
PRINT "    3    : single ray missile"
PRINT "    4    : ray missile airstrike"
PRINT ""
PRINT "    5    : thorne wood"
PRINT "________________________________________"
PRINT ""
PRINT ""
INPUT " Which page do you want to read ?:", page!
'IF page! < 1 THEN GOTO above
'IF page! > 5 THEN GOTO above
IF page! = 1 THEN CALL ray1
IF page! = 2 THEN CALL ray2
IF page! = 3 THEN CALL ray3
IF page! = 4 THEN CALL ray4
IF page! = 5 THEN CALL ray5
END SUB

SUB nmenu
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS
loss! = 0
LINE (1, 1)-(310, 20), 1, B
PRINT ""
PRINT " Der Spieler greift mit dem Schwert an "
LINE (1, 1)-(310, 20), 1, B

CALL enemyb

'if skill! < 500 then lev! = 14     ' muss noch verbessert werden
'if skill! > 599 then lev! =13
'if skill! < 700 then l
lev! = 10
RANDOMIZE TIMER
hit! = INT(RND * lev!) + 1

BEEP
IF hit! = 1 THEN LINE (500, 70)-(400, 50) 'Treffer 1
IF hit! = 2 THEN LINE (500, 60)-(400, 80) 'Treffer 2
IF hit! = 3 THEN LINE (500, 35)-(400, 55) 'Treffer 3
IF hit! = 4 THEN LINE (500, 75)-(400, 95) 'Treffer 4
IF hit! = 5 THEN LINE (500, 100)-(400, 70)'Treffer 5
IF hit! = 6 THEN LINE (500, 50)-(400, 30) 'Treffer 6
IF hit! = 7 THEN LINE (500, 80)-(400, 60) 'Treffer 7
IF hit! = 8 THEN LINE (500, 30)-(400, 20) 'kein Treffer
IF hit! = 9 THEN LINE (500, 30)-(400, 20) 'kein Treffer
IF hit! = 10 THEN LINE (500, 30)-(400, 20) 'kein Treffer
IF hit! = 11 THEN LINE (500, 30)-(400, 20) 'kein Treffer
IF hit! = 12 THEN LINE (500, 30)-(400, 20) 'kein Treffer
IF hit! = 13 THEN LINE (500, 30)-(400, 20) 'kein Treffer
IF hit! = 14 THEN LINE (500, 30)-(400, 20) 'kein Treffer

IF hit! > 7 THEN statf$ = " verfehlt " ELSE statf$ = " getroffen "
IF hit! < 8 THEN loss! = 70

PRINT ""
PRINT ""
PRINT "________________________"
PRINT ""
PRINT " Status : ", statf$
PRINT enemy$; " -"; loss!; " HP"
PRINT "________________________"

hp2! = hp2! - loss!
loss! = 0
SHELL "pause > nul"
END SUB

SUB notenough
'-----------------5. Prozedur : NOT ENOUGH Text-----------------------------
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS
LINE (1, 1)-(515, 20), 1, B
PRINT ""
PRINT " Leider haben Sie nicht mehr genug (bzw. gar nichts) mehr davon."
LINE (1, 1)-(515, 20), 1, B
SHELL "pause > nul"
CLS

END SUB

SUB outro
SCREEN 1
val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " concept : Christian Gellert"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " illustrations : Christian Gellert"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " graphics : Christian Gellert"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " text : Christian Gellert"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " story : Christian Gellert"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " music : Christian Gellert"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " main code : Christian Gellert"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " in assiocation with"
PRINT "     CHRY - Co "
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " a Christian Gellert game ..."
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 230 STEP 1
CLS
LOCATE val13!, 1
PRINT " of a Christian Gellert production"
val13! = val13! + .1
NEXT
CLS

val13! = 1
FOR i1% = 1 TO 110 STEP 1
CLS
LOCATE val13!, 1
PRINT " (c) by CHRY - Co"
PRINT " 1999 - 2010 "
PRINT " all rights reserved"
PRINT ""
PRINT " THE END"
val13! = val13! + .1
NEXT

SHELL "pause > nul"


END SUB

SUB player
' ------------1. Prozedur  : Die Spielfigur-----
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$

CIRCLE (150, 14), 15    'Kopf
LINE (150, 7)-(118, 7)  'Kopf
LINE (150, 20)-(118, 20)'Kopf
LINE (118, 8)-(118, 20) 'Kopf
PSET (145, 13)   'Auge
PSET (155, 13)   'Auge
LINE (100, 20)-(190, 20)'Kîrper
LINE (100, 20)-(100, 65)'Kîrper
LINE (190, 20)-(190, 65)'Kîrper
LINE (100, 65)-(190, 65)'Kîrper
LINE (100, 50)-(190, 50)'GÅrtel
LINE (100, 54)-(190, 54)'GÅrtel
LINE (180, 50)-(180, 54)'GÅrtel
LINE (130, 65)-(130, 102) 'Beine
LINE (150, 65)-(150, 93)  'Beine
LINE (120, 93)-(170, 93)  'Beine
LINE (170, 93)-(170, 102) 'Beine
LINE (120, 102)-(170, 102)'Beine
LINE (120, 65)-(120, 102) 'bein
LINE (130, 30)-(220, 34) 'Arme
LINE (130, 25)-(220, 30) 'Arme
CIRCLE (220, 32), 5 'Arme
CIRCLE (218, 32), 9 'Arme
LINE (100, 20)-(0, 60) 'Cape
LINE (0, 60)-(100, 60) 'Cape

END SUB

SUB ray1
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS
IF mp1! < 50 THEN GOTO noway456


CALL enemyb
SOUND 500, 10
FOR c% = 1 TO 800 STEP 10
CIRCLE (c%, 90), 40
NEXT c%
SHELL "pause > nul"


loss! = 150
LINE (1, 1)-(530, 35), 1, B
PRINT ""
PRINT " Du hast deinen einfachen ~Ring Ray~ abgeschossen. Durch die "
PRINT " hohe psychische Anstrengung verlierst du 50 Magiepunkte."
PRINT , enemy$; " verliert", loss!; " Energiepunkte."
LINE (1, 1)-(530, 35), 1, B
mp1! = mp1! - 50
hp2! = hp2! - loss!

SHELL "pause >nul"
GOTO sdfghhj

noway456:
CALL notenough

sdfghhj:
END SUB

SUB ray2
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS

IF mp1! < 90 THEN GOTO noway789

CALL enemyb
SOUND 500, 10
FOR c% = 1 TO 800 STEP 10
CIRCLE (c%, 90), 40
CIRCLE (c%, c%), 40
CIRCLE (0, c%), 40
CIRCLE (c%, 180), 40
NEXT c%
SHELL "pause > nul"


loss! = 300
LINE (1, 1)-(530, 35), 1, B
PRINT ""
PRINT " Du hast deinen ~Super Ring Ray~ abgeschossen. Durch die "
PRINT " hohe psychische Anstrengung verlierst du 90 Magiepunkte."
PRINT , enemy$; " verliert", loss!; " Energiepunkte."
LINE (1, 1)-(530, 35), 1, B
mp1! = mp1! - 90
hp2! = hp2! - loss!

SHELL "pause >nul"
GOTO jkas

noway789:



jkas:
END SUB

SUB ray3
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS
IF mp1! < 40 THEN GOTO nowayh

CALL enemyb
arrowl1! = 0
arrowl2! = 30
FOR va! = 1 TO 30 STEP 1
CALL enemyb
LINE (arrowl1!, 80)-(arrowl2!, 80)
CALL enemyb
arrowl1! = arrowl1! + 30
arrowl2! = arrowl2! + 30
CLS
NEXT
CALL enemyb
SHELL "pause > nul"


loss! = 100
LINE (1, 1)-(530, 35), 1, B
PRINT ""
PRINT " Du hast deinen ~single ray missile~ abgeschossen. Durch die "
PRINT " hohe psychische Anstrengung verlierst du 40 Magiepunkte."
PRINT , enemy$; " verliert", loss!; " Energiepunkte."
LINE (1, 1)-(530, 35), 1, B

mp1! = mp1! - 40
hp2! = hp2! - loss!
SHELL "pause >nul"
GOTO hjk

nowayh:
CALL notenough

hjk:
END SUB

SUB ray4
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS
IF mp1! < 130 THEN GOTO nowayman

FOR v1! = 50 TO 500 STEP 8
CALL enemyb
LINE (v1!, 100)-(v2!, 40)
CALL enemyb
NEXT v1!
CALL enemyb
SHELL "pause > nul"


loss! = 400
LINE (1, 1)-(530, 35), 1, B
PRINT ""
PRINT " Du hast deinen ~ray missile airstrike~ abgeschossen. Durch die "
PRINT " hohe psychische Anstrengung verlierst du 130 Magiepunkte."
PRINT , enemy$; " verliert", loss!; " Energiepunkte."
LINE (1, 1)-(530, 35), 1, B

mp1! = mp1! - 130
hp2! = hp2! - loss!

SHELL "pause >nul"
GOTO 1943

nowayman:
CALL notenough

1943 :
END SUB

SUB ray5
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS
IF mp1! < 130 THEN GOTO nowayal

CALL enemyb
SOUND 40, 6
FOR v1! = 50 TO 500 STEP 8  ' bestimmung von V1 + Begrenzung der Prozedur

v2! = INT(RND * 70) + 5     ' Bestimmung von V2
v3! = v3! + 12              ' Bestimmung von V3
v4! = 200                  ' Bestimmung von V4

CALL enemyb
LINE (v1!, v2!)-(v3!, v4!)  ' Schreiben der LINE
CALL enemyb
NEXT v1!
CALL enemyb
SHELL "pause > nul"

loss! = 400
LINE (1, 1)-(530, 35), 1, B
PRINT ""
PRINT " Du hast deinen ~thorne wood~ abgeschossen. Durch die "
PRINT " hohe psychische Anstrengung verlierst du 130 Magiepunkte."
PRINT , enemy$; " verliert", loss!; " Energiepunkte."
LINE (1, 1)-(530, 35), 1, B

mp1! = mp1! - 130
hp2! = hp2! - loss!

SHELL "pause >nul"
GOTO yeahhh

nowayal:
CALL notenough

yeahhh:
END SUB

SUB shield
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$
CLS
IF sp1! < 1 THEN GOTO noway
LINE (1, 1)-(530, 40), 1, B
PRINT ""
PRINT " In den Augen ihres Gegners erkennen Sie, das er sogleich zu"
PRINT " einem vernichtenden Schlag ausholen wird. Daher erschaffen Sie"
PRINT " schnell einen Energieschutzwall, der die darauffolgende Attacke"
PRINT " ihres Gegners absorbiert."
LINE (1, 1)-(530, 40), 1, B
PRINT ""
PRINT ""
SHELL "pause >nul"
CLS
CALL player
CALL enemyb
FOR vwx! = 160 TO 200 STEP 5
CIRCLE (130, 65), vwx!
NEXT
sp1! = sp1! - 1
SHELL "pause >nul"
GOTO departure





noway:
CALL notenough
departure:
CLS
END SUB

SUB statusk
SHARED hp1!, hp2!, mp1!, mp2!, sp1!, sp2!, enemy$, rb1!, rb2!, location$

' -----------------3. Prozedur : StatusmenÅ fÅr den Kampfmodus-------------

CLS
PRINT ""
PRINT "       SPIELER                                        ", enemy$
PRINT "________________________________________________________________________________"
PRINT ""
PRINT " Hitpoints :", hp1!; "                     Hitpoints :", hp2!;
PRINT "________________________________________________________________________________"
PRINT ""
PRINT " Magicpts  :", mp1!; "                        Magicpts  :", mp2!;
PRINT "________________________________________________________________________________"
PRINT ""
PRINT " Shieldpts :", sp1!; "                        Shieldpts :", sp2!;
PRINT "________________________________________________________________________________"
PRINT " "
PRINT " Rauchbomben :", rb1!; "          Rauchbomben :", rb2!;
PRINT " Schwert                                     HandrÅckenklaue"
PRINT "________________________________________________________________________________"
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT "                  LOCATION :", location$
LINE (320, 0)-(320, 135) ' senkrechter Trennstrich
SHELL "pause >nul"

END SUB

