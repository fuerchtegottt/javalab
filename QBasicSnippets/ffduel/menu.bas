hp1! = 3466
hp2! = 1999
enemy$ = "Strohpuppe"


GOTO schleif
SCREEN 0
CLS
COLOR 0, 7
PRINT "     Final Fantasy Duell                                                        "
COLOR 7, 0
PRINT ""
PRINT " Dies ist die Vorabversion fÅr den Aktionsmodus. Er stellt das "
PRINT " Herz des Spiels dar. Von hier aus kann man alle Kampfmodis"
PRINT " aktivieren. "
PRINT ""
SHELL "pause >nul"
schleif:

SCREEN 2
CLS
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



' Die Spielfigur :
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



' Strohmann
LINE (490, 50)-(490, 80)  'Kîrper
LINE (490, 80)-(460, 103) 'Beine
LINE (490, 80)-(520, 103) 'Beine
LINE (490, 50)-(450, 70)  'Arme
LINE (490, 50)-(530, 70)  'Arme
CIRCLE (490, 40), 22      'Kopf
CIRCLE (480, 40), 4   'Auge
CIRCLE (500, 40), 4   'Auge
PSET (490, 45)        'Nase


'Tastaturabfrage
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(27) OR a$ = CHR$(115) OR a$ = CHR$(109) OR a$ = CHR$(114) OR a$ = CHR$(110) OR a$ = CHR$(113) OR a$ = CHR$(13)
IF a$ = CHR$(27) THEN GOSUB ausg   ' ESC
IF a$ = CHR$(115) THEN GOSUB status' S
IF a$ = CHR$(109) THEN GOSUB mmenu' M
IF a$ = CHR$(114) THEN GOSUB ausw ' R
IF a$ = CHR$(110) THEN GOSUB nmenu' N
IF a$ = CHR$(113) THEN GOSUB ausg ' Q

















