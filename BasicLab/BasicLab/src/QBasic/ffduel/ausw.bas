
anfang:
SCREEN 0
CLS
COLOR 0, 7
PRINT "     Final Fantasy Duell                                                        "
COLOR 7, 0
PRINT ""
PRINT " Dies ist eine Vorabversion des Ausweichmanoever-Modus"
PRINT " von Final Fantasy Duell. Dieser Modus soll dem Spieler"
PRINT " im fertigen Spiel ca. 3 mal zur VerfÅgung stehen, um"
PRINT " die eigenen Energiepunkte wieder aufzufrischen."
PRINT " FÅr jedes Mal, das man dem Gegner ausweichen konnte, "
PRINT " erhÑllt man X Energiepunkte gutgeschrieben."
PRINT " Durch die neue Game-Routine konnte der Quelltext fÅr"
PRINT " Das Ausweichmanîver von 25 Bildschirmseiten auf 15 "
PRINT " Bildschirmseiten verkleinert werden."
SHELL "pause >nul"
CLS
pposi1! = 1    ' Zahlenwert der Spieler-Position
eposi! = 9    ' Zahlenwert der Gegner-Position
counter! = 0

redoo:
IF posi1! = eposi! THEN GOTO catched
SCREEN 2
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
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 7   'oben
IF a$ = CHR$(50) THEN posi1! = 4   'unten
IF a$ = CHR$(52) THEN posi1! = 3   'links
IF a$ = CHR$(54) THEN posi1! = 2   'rechts
GOTO redoo

ppos2:
CIRCLE (250, 60), 30 ' Spielerstellung 2
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 8 'oben
IF a$ = CHR$(50) THEN posi1! = 5  'unten
IF a$ = CHR$(52) THEN posi1! = 1 'links
IF a$ = CHR$(54) THEN posi1! = 3 'rechts
GOTO redoo

ppos3:
CIRCLE (385, 60), 30 ' Spielerposition 3
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 9 'oben
IF a$ = CHR$(50) THEN posi1! = 6 'unten
IF a$ = CHR$(52) THEN posi1! = 2 'links
IF a$ = CHR$(54) THEN posi1! = 1 'rechts
GOTO redoo

ppos4:
CIRCLE (96, 91), 30  ' Spielerposition 4
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 1 'oben
IF a$ = CHR$(50) THEN posi1! = 7 'unten
IF a$ = CHR$(52) THEN posi1! = 6 'links
IF a$ = CHR$(54) THEN posi1! = 5 'rechts
GOTO redoo

ppos5:
CIRCLE (250, 91), 30 ' Spielerstellung 5
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 2 'oben
IF a$ = CHR$(50) THEN posi1! = 8 'unten
IF a$ = CHR$(52) THEN posi1! = 4 'links
IF a$ = CHR$(54) THEN posi1! = 6 'rechts
GOTO redoo

ppos6:
CIRCLE (385, 91), 30 ' Spielerstellung 6
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 3 'oben
IF a$ = CHR$(50) THEN posi1! = 9 'unten
IF a$ = CHR$(52) THEN posi1! = 5 'links
IF a$ = CHR$(54) THEN posi1! = 4 'rechts
GOTO redoo

ppos7:
CIRCLE (96, 123), 30 ' Spielerstellung 7
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 4 'oben
IF a$ = CHR$(50) THEN posi1! = 1 'unten
IF a$ = CHR$(52) THEN posi1! = 9 'links
IF a$ = CHR$(54) THEN posi1! = 8 'rechts
GOTO redoo


ppos8:
CIRCLE (250, 123), 30' Spielerstellung 8
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
IF a$ = CHR$(56) THEN posi1! = 5 'oben
IF a$ = CHR$(50) THEN posi1! = 2 'unten
IF a$ = CHR$(52) THEN posi1! = 7 'links
IF a$ = CHR$(54) THEN posi1! = 9 'rechts
GOTO redoo

ppos9:
CIRCLE (385, 123), 30' Spielerstellung 9
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(54) OR a$ = CHR$(56) OR a$ = CHR$(50) OR a$ = CHR$(52) OR a$ = CHR$(27)
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
IF i% = 1 THEN eposi! = 7
IF i% = 2 THEN eposi! = 2
IF i% = 3 THEN eposi! = 4
IF i% = 4 THEN eposi! = 3
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos

epos2:
FOR r% = 1 TO 30 STEP 2
CIRCLE (250, 60), r% ' Gegnerstellung 2
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 1
IF i% = 2 THEN eposi! = 8
IF i% = 3 THEN eposi! = 3
IF i% = 4 THEN eposi! = 5
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos


epos3:
FOR r% = 1 TO 30 STEP 2
CIRCLE (385, 60), r% ' Gegnerstellung 3
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 2
IF i% = 2 THEN eposi! = 9
IF i% = 3 THEN eposi! = 1
IF i% = 4 THEN eposi! = 6
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos

epos4:
FOR r% = 1 TO 30 STEP 2
CIRCLE (96, 91), r%  ' Gegnerstellung 4
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 1
IF i% = 2 THEN eposi! = 5
IF i% = 3 THEN eposi! = 7
IF i% = 4 THEN eposi! = 6
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos


epos5:
FOR r% = 1 TO 30 STEP 2
CIRCLE (250, 91), r% ' Gegnerstellung 5
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 4
IF i% = 2 THEN eposi! = 2
IF i% = 3 THEN eposi! = 6
IF i% = 4 THEN eposi! = 8
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos


epos6:
FOR r% = 1 TO 30 STEP 2
CIRCLE (385, 91), r% ' Gegnerstellung 6
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 5
IF i% = 2 THEN eposi! = 3
IF i% = 3 THEN eposi! = 4
IF i% = 4 THEN eposi! = 9
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos


epos7:
FOR r% = 1 TO 30 STEP 2
CIRCLE (96, 123), r% ' Gegnerstellung 7
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 4
IF i% = 2 THEN eposi! = 8
IF i% = 3 THEN eposi! = 9
IF i% = 4 THEN eposi! = 1
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos


epos8:
FOR r% = 1 TO 30 STEP 2
CIRCLE (250, 123), r%' Gegnerstellung 8
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 7
IF i% = 2 THEN eposi! = 5
IF i% = 3 THEN eposi! = 9
IF i% = 4 THEN eposi! = 2
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos

epos9:
FOR r% = 1 TO 30 STEP 2
CIRCLE (385, 123), r%' Gegnerstellung 9
NEXT r%
RANDOMIZE TIMER
i% = INT(RND * 4) + 1     'erster Wert (4) fÅr Begrenzung
IF i% = 1 THEN eposi! = 8
IF i% = 2 THEN eposi! = 6
IF i% = 3 THEN eposi! = 3
IF i% = 4 THEN eposi! = 7
IF eposi! = pposi! THEN GOTO catched
GOTO playerpos



catched:
CLS
SCREEN 0
PRINT "Sie konnten dem Feind genau ", counter!; "mal entkommen."
PRINT "Jetzt hat er Sie."
SYSTEM

