enemy$ = "Strohpuppe"




SCREEN 0
CLS
COLOR 0, 7
PRINT "     Final Fantasy Duell                                                        "
COLOR 7, 0
PRINT ""
PRINT " Dies ist die Vorabversion des Status-Menues. Es erscheint vor"
PRINT " jeder Runde, und zeigt alle relevanten Daten ber den Spieler"
PRINT " und den Gegner."
SHELL "pause > nul"
CLS

location$ = "Zombie-See"

status:
SCREEN 2
CLS
PRINT ""
PRINT "       SPIELER                                        ", enemy$
PRINT "________________________________________________________________________________"
PRINT ""
PRINT " Hitpoints :", hp1!; "                           Hitpoints :", hp2!;
PRINT "________________________________________________________________________________"
PRINT ""
PRINT " Magicpts  :", mp1!; "                           Magicpts  :", mp2!;
PRINT "________________________________________________________________________________"
PRINT ""
PRINT " Shieldpts :", sp1!; "                           Shieldpts :", sp2!;
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



