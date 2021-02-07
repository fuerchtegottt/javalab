CLS
COLOR 0, 7
PRINT " Tastencode - Finder V1.1 by CHRY - Co                                      "
COLOR 7, 0
PRINT ""
PRINT " Geben Sie ~ENDE~ ein, um das Programm zu verlassen."
anfang:
PRINT ""
PRINT ""
PRINT " Welchen Tastatur-Code brauchen Sie ?"
INPUT "Bitte Taste eingeben :", key$
IF key$ = "ende" THEN GOTO ausg

PRINT ASC(key$)
GOTO anfang

ausg:
CLS
COLOR 0, 7
PRINT " Tastencode - Finder V1.1 by CHRY - Co                                      "
COLOR 7, 0
PRINT ""
PRINT " Vielen Dank, das Sie dieses Programm verwendet haben !"
PRINT ""
PRINT " Credits :"
PRINT " ALLES von Christian Gellert gemacht !"



