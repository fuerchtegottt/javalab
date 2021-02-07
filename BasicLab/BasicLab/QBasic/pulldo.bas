CLS
COLOR 0, 7
PRINT "                                                              "
PRINT "               CHRY - PULL DOWN  V3.0                         "
PRINT "                                                              "
PRINT "               NUM - LOCK DRIVER V2.0                         "
PRINT "                                                              "
PRINT "               INERACTIVE SCHOOL V1.0                         "
PRINT "                                                              "
PRINT "                                (press ENTER or ESC)          "
COLOR 7, 0
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(13) OR a$ = CHR$(27)
IF a$ = CHR$(13) THEN GOTO start
IF a$ = CHR$(27) THEN GOTO start
start:
CLS
COLOR 0, 7
PRINT "   *(START)*         EXIT              HELP        SYSTEM      386   "
COLOR 7, 0
PRINT "   ---------"
GOTO menu
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(13) OR a$ = CHR$(57) OR a$ = CHR$(55) OR a$ = CHR$(27)
IF a$ = CHR$(57) THEN GOTO ex
IF a$ = CHR$(13) THEN GOTO menu
IF a$ = CHR$(55) THEN GOTO 386
IF a$ = CHR$(27) THEN GOTO ende
CLS
116

ex:
CLS
COLOR 0, 7
PRINT "     START         *(EXIT)*            HELP        SYSTEM      386   "
COLOR 7, 0
PRINT "                   --------"
PRINT "     "
COLOR 0, 7
PRINT "Wollen sie Christians"
PRINT "PULL-DOWN wirklich   "
PRINT "verlassen ? (esc)    "
COLOR 7, 0

DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(27) OR a$ = CHR$(55)

IF a$ = CHR$(57) THEN GOTO help
IF a$ = CHR$(27) THEN GOTO ende
IF a$ = CHR$(55) THEN GOTO start

help:
CLS
COLOR 0, 7
PRINT "     START           EXIT            *(HELP)*      SYSTEM      386   "
COLOR 7, 0
PRINT "                                     -------- "
PRINT "             "
COLOR 0, 7
PRINT "----HELP-WINDOW--------"
PRINT "-----------------------"
PRINT "CHR - MENU             "
PRINT "CHRISTIAN GELLERT 1996 "
PRINT "-----------------------"
PRINT "Steuerung :            "
PRINT "NUM - LOCK - COMMANDER "
PRINT "7 = LINKS              "
PRINT "9 = RECHTS             "
PRINT "8 = UP/DOWN SCROLL     "
PRINT "ENTER = ANWENDUNG START"
PRINT "ESCAPE= Sitzung beenden"
PRINT "-----------------------"
COLOR 7, 0
PRINT "    "
COLOR 0, 7
PRINT " Weiter versionsbezogene Informationen entnehmen sie der Datei INFO.CHR"
COLOR 7, 0
PRINT "  "
COLOR 0, 7
PRINT "DrÅcken sie T um das CHRY - PULL DOWN Lehrnprogramm zu starten         "
COLOR 7, 0
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(27) OR a$ = CHR$(55) OR a$ = CHR$(116)
IF a$ = CHR$(27) THEN GOTO ende
IF a$ = CHR$(57) THEN GOTO syste
IF a$ = CHR$(55) THEN GOTO ex
IF a$ = CHR$(116) THEN GOTO school

menu:
CLS
COLOR 0, 7
PRINT "   *(START)*         EXIT              HELP        SYSTEM      386   "
COLOR 7, 0
PRINT "   ---------"
PRINT "        "
COLOR 0, 7
PRINT "(DOS =       )"
PRINT " LIST=        "
COLOR 7, 0

DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(56) OR a$ = CHR$(13) OR a$ = CHR$(55) OR a$ = CHR$(27)
IF a$ = CHR$(57) THEN GOTO ex
IF a$ = CHR$(56) THEN GOTO menu2
IF a$ = CHR$(13) THEN GOTO uhr
IF a$ = CHR$(55) THEN GOTO 386
IF a$ = CHR$(27) THEN GOTO ende

menu2:
CLS
COLOR 0, 7
PRINT "   *(START)*         EXIT              HELP        SYSTEM      386   "
COLOR 7, 0
PRINT "   ---------"
PRINT "        "
COLOR 0, 7
PRINT " DOS =        "
PRINT "(LIST=       )"
COLOR 7, 0
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(56) OR a$ = CHR$(13) OR a$ = CHR$(55)
IF a$ = CHR$(57) THEN GOTO ex
IF a$ = CHR$(56) THEN GOTO menu
IF a$ = CHR$(13) THEN GOTO lin
IF a$ = CHR$(55) THEN GOTO 386

uhr:
SHELL "c:\command.com"
GOTO start

lin:
SHELL "list.com"
GOTO start


school:
CLS
COLOR 0, 7
PRINT "                              "
PRINT "      WILLKOMMEN IM           "
PRINT "  CHRY  -  PULL DOWN          "
PRINT "INTERACTIVE EDUCATION         "
PRINT "       PROGRAMM               "
PRINT "------------------------------"
PRINT " Sie kînnen durch ausprobieren"
PRINT " alle Tastenfunktionen selbst "
PRINT " erkunden .  (ESC = VERLASSEN)"
COLOR 7, 0
school2:
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(56) OR a$ = CHR$(13) OR a$ = CHR$(55) OR a$ = CHR$(27)
IF a$ = CHR$(57) THEN PRINT " CURSOR wandert nach RECHTS"
IF a$ = CHR$(56) THEN PRINT " CURSOR wandert nach OBEN / UNTEN "
IF a$ = CHR$(13) THEN PRINT " Anwendung wird geîffnet"
IF a$ = CHR$(55) THEN PRINT " CURSOR wandert nach LINKS"
IF a$ = CHR$(27) THEN GOTO start
GOTO school2


syste:
CLS
COLOR 0, 7
PRINT "     START           EXIT              HELP      *(SYSTEM)*    386   "
COLOR 7, 0
PRINT "                                                 ----------"
PRINT "        "
COLOR 0, 7
PRINT "CHRY         "
PRINT "ERRORCODE 1b "
PRINT "-------------"
PRINT "kein TASK in "
PRINT "Quelltext    "
PRINT "gefunden  !  "
COLOR 7, 0
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(55) OR a$ = CHR$(27)
IF a$ = CHR$(57) THEN GOTO 386
IF a$ = CHR$(55) THEN GOTO help
IF a$ = CHR$(27) THEN GOTO ende
GOTO ende




386 :
CLS
COLOR 0, 7
PRINT "     START           EXIT              HELP        SYSTEM    *(386)* "
COLOR 7, 0
PRINT "                                                             -------"
PRINT "        "
COLOR 0, 7
PRINT "UPDATE - MODUS    (legen sie die UPDATE DISK mit der DATEI UPDATE.BAT in A:"
PRINT "DrÅcken sie dann ENTER                                                     "
COLOR 7, 0
DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(57) OR a$ = CHR$(55) OR a$ = CHR$(27) OR a$ = CHR$(13)
IF a$ = CHR$(57) THEN GOTO start
IF a$ = CHR$(55) THEN GOTO syste
IF a$ = CHR$(27) THEN GOTO ende
IF a$ = CHR$(13) THEN GOTO update
GOTO ende


update:
SHELL "a:\update.bat"
GOTO start





ende:
SYSTEM





