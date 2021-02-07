SCREEN 2
CLS

' als erstes werden die Anfangswerte fr den Spieler festgelegt :
hp1! = 3466 ' Hitpoints des Spielers
rb1! = 2
mp1! = 300
sp1! = 500
GOTO fight

' dann werden die Prozeduren fr SFX und Spielmens festgelegt :

' ------------1. Prozedur  : Die Spielfigur-----
player:

CIRCLE (150, 14), 15    'Kopf
LINE (150, 7)-(118, 7)  'Kopf
LINE (150, 20)-(118, 20)'Kopf
LINE (118, 8)-(118, 20) 'Kopf
PSET (145, 13)   'Auge
PSET (155, 13)   'Auge
LINE (100, 20)-(190, 20)'K”rper
LINE (100, 20)-(100, 65)'K”rper
LINE (190, 20)-(190, 65)'K”rper
LINE (100, 65)-(190, 65)'K”rper
LINE (100, 50)-(190, 50)'Grtel
LINE (100, 54)-(190, 54)'Grtel
LINE (180, 50)-(180, 54)'Grtel
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
RETURN



' ------------------2. Prozedur : der Beta-Gegner-------------------
enemyb:
enemy$ = "Strohpuppe"  'Name des Gegners
hp2! = 1999            'Hitpoints des Gegners
mp2! = 200
sp2! = 50

LINE (490, 50)-(490, 80)  'K”rper
LINE (490, 80)-(460, 103) 'Beine
LINE (490, 80)-(520, 103) 'Beine
LINE (490, 50)-(450, 70)  'Arme
LINE (490, 50)-(530, 70)  'Arme
CIRCLE (490, 40), 22      'Kopf
CIRCLE (480, 40), 4   'Auge
CIRCLE (500, 40), 4   'Auge
PSET (490, 45)        'Nase
RETURN

' -----------------3. Prozedur : Statusmen fr den Kampfmodus-------------
statusk:
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
PRINT " Schwert                                     Handrckenklaue"
PRINT " Sai-Messer                                  Streitaxt"
PRINT "________________________________________________________________________________"
PRINT ""
PRINT ""
PRINT ""
PRINT ""
PRINT "                  LOCATION :", location$
LINE (320, 0)-(320, 135) ' senkrechter Trennstrich
SHELL "pause >nul"
RETURN

' ---------------4.Prozedur : der Rauchbomben - Ausweichmodus--------------
ausw:
CLS
IF NOT rb1! < 1 THEN GOTO furtherxc
GOSUB notenough
RETURN
furtherxc:
rb1! = rb1! - 1
PRINT ""
PRINT " Vom Kampf ersch”pft werfen Sie eine Rauchbombe, um etwas Zeit"
PRINT " zu gewinnen. Je ”fter Sie es schaffen ihrem Feind im Nebel der"
PRINT " Rauchbombe zu entkommen, desto mehr regenerieren sich Ihre "
PRINT " Hitpoints."
SHELL "pause > nul"
CLS
GOSUB player
GOSUB enemyb
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
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
i% = INT(RND * 4) + 1     'erster Wert (4) fr Begrenzung
IF eposi! = posi1! THEN GOTO catched
IF i% = 1 THEN eposi! = 8
IF i% = 2 THEN eposi! = 6
IF i% = 3 THEN eposi! = 3
IF i% = 4 THEN eposi! = 7
GOTO playerpos

catched:
CLS
PRINT " Sie konnten dem Feind genau ", counter!; "mal entkommen."
PRINT " Dafr werden Ihnen ", counter! * 10; " Hitpoints gutgeschrieben."
PRINT
hp1! = hp1! + (counter! * 10)
SHELL "pause > nul"
RETURN

'-----------------5. Prozedur : NOT ENOUGH Text-----------------------------
notenough:
CLS
PRINT " Leider haben Sie nicht mehr genug (bzw. gar nichts) mehr davon."
SHELL "pause > nul"
CLS
RETURN

'-------------------Steuerungssektion--------------------------------------
'--------------------------------------------------------------------------
fight:
'IF hp1! < 1 THEN GOTO dead
'IF hp2! < 1 THEN GOTO victory
CLS
GOSUB player
GOSUB enemyb
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
PRINT "    S = STATUS                         N = normaler Angriff"
PRINT "    M = Angriff mit Magie              S = Spezialattacke"
PRINT "    R = Rauchbombe werfen              Q = Spiel verlassen"
GOSUB player
GOSUB enemyb

'Tastaturabfrage
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(27) OR a$ = CHR$(115) OR a$ = CHR$(109) OR a$ = CHR$(114) OR a$ = CHR$(110) OR a$ = CHR$(113) OR a$ = CHR$(13)
IF a$ = CHR$(27) THEN GOSUB ausg   ' ESC
IF a$ = CHR$(115) THEN GOSUB statusk' S
'IF a$ = CHR$(109) THEN GOSUB mmenu' M
'IF a$ = CHR$(110) THEN GOSUB nmenu' N
IF a$ = CHR$(113) THEN GOSUB ausg ' Q
IF a$ = CHR$(114) THEN GOSUB ausw

GOTO fight




















ausg:

