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

