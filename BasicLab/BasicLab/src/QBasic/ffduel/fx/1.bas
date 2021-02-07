GOTO fx4

fx1:
PRINT " 1. Effekt : Der Spieler wirft eine Rauchbombe"
SHELL "pause >nul"
CLS
    
    SCREEN 2
    SOUND 50, 40
    FOR r% = 1 TO 500 STEP 2
    CIRCLE (385, 123), r%' Gegnerstellung 9
    NEXT r%
SHELL "pause >nul"
CLS





fx2:
SCREEN 2
PRINT " 2. Effekt : Der Spieler benutzt den normalen Ring-Ray "
SHELL "pause >nul"
SOUND 500, 10
FOR c% = 1 TO 800 STEP 10
CIRCLE (c%, 90), 40
NEXT c%

fx3:
SCREEN 2
PRINT " 2. Effekt : Der Spieler benutzt den Super Ring-Ray "
SHELL "pause >nul"
SOUND 500, 10
FOR c% = 1 TO 800 STEP 10
CIRCLE (c%, 90), 40
CIRCLE (c%, c%), 40
CIRCLE (0, c%), 40
CIRCLE (c%, 180), 40
NEXT c%

fx4:
SCREEN 2
CLS
PRINT "Effekt : bisher unbekannt"
SHELL "pause >nul"
CLS
FOR v1! = 50 TO 500 STEP 8
LINE (v1!, 100)-(v2!, 40)
NEXT v1!





    






