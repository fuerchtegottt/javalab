SCREEN 2
CLS
PRINT "Effekt : Energie-Dornenwald"
SHELL "pause >nul"
CLS

SOUND 40, 6



FOR v1! = 50 TO 500 STEP 8  ' bestimmung von V1 + Begrenzung der Prozedur

v2! = INT(RND * 70) + 5     ' Bestimmung von V2
v3! = v3! + 12              ' Bestimmung von V3
v4! = 200                  ' Bestimmung von V4

LINE (v1!, v2!)-(v3!, v4!)  ' Schreiben der LINE
NEXT v1!

