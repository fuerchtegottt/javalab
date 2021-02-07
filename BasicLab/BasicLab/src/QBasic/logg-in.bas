anfang:
CLS
COLOR 0, 7
PRINT "                 Chry User Entry  V1.0                                          "
COLOR 7, 0
PRINT ""
PRINT ""
COLOR 0, 5
PRINT "     Name des Benutzers       LOGG IN Nr."
COLOR 7, 0
PRINT "     Christian Gellert           1 "
PRINT "     GAST                        2 "
PRINT ""
PRINT ""
PRINT ""
PRINT ""
INPUT " Please enter LOGG IN Nr. : ", logg%
IF logg% > 2 THEN GOTO fehler
IF logg% < 1 THEN GOTO fehler
IF logg% = 1 THEN GOTO master
IF logg% = 2 THEN GOTO slave

fehler:
CLS
COLOR 0, 7
PRINT "                 Chry User Entry  V1.0                                          "
COLOR 7, 0
PRINT ""
PRINT ""
COLOR 0, 5
PRINT "--------------------"
PRINT "-Eingabe verweigert-"
PRINT "-                  -"
PRINT "- Benutzer nicht   -"
PRINT "- identifizert     -"
PRINT "--------------------"
COLOR 7, 0
PRINT ""
PRINT ""
PRINT ""
SHELL "pause >nul"
GOTO anfang

master:
CLS
COLOR 0, 7
PRINT "                 Chry User Entry  V1.0                                          "
COLOR 7, 0
PRINT ""
PRINT ""
PRINT " SICHERHEITSABFRAGE"
PRINT ""
INPUT " Geben Sie bitte Ihren SYSOP PIN ein : ", pin$
IF NOT pin$ = "galahad" THEN GOTO fehler

CLS
username$ = "Christian"
COLOR 0, 7
PRINT "                 Chry User Entry  V1.0                                          "
COLOR 7, 0
PRINT ""
PRINT ""
PRINT " Willkommen Meister..."
PRINT ""
PRINT ""
PRINT ""
PRINT ""
SHELL "pause >nul"
SYSTEM


slave:
CLS
COLOR 0, 7
PRINT "                 Chry User Entry  V1.0                                          "
COLOR 7, 0
PRINT ""
PRINT ""
PRINT " Wilkommen Gast ..."
PRINT ""
PRINT ""
INPUT " Bitte geben Sie Ihren Namen an : ", username$
PRINT ""
PRINT " Ihr Name lautet ", username$
PRINT ""
PRINT ""
PRINT ""
SHELL "pause >nul"
SYSTEM











