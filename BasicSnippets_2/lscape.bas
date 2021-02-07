DECLARE SUB tree02 ()
DECLARE SUB tree03 ()
DECLARE SUB tree05 ()
DECLARE SUB tree06 ()
DECLARE SUB horizon ()
DECLARE SUB tree01 ()
DECLARE SUB tree04 ()
SCREEN 2
CLS
PRINT ""
PRINT " Dies ist die der erste ~Prototyp~ des Kartenmodus."
PRINT " Die Testlandschaft besteht aus 3x3 Feldern mit B„umen"
PRINT " drauf. (Karte liegt irgendwo im WW rum)."
PRINT ""
PRINT " Die Steuerung erfolgt ber den NUM-Block."
SHELL "pause >nul"
location$ = "1a" ' Startposition
GOTO einsa

GOTO verteiler

verteiler:
CLS
SCREEN 8
'IF location$ = "1a" THEN GOTO einsa
'IF location$ = "1b" THEN GOTO einsb
'IF location$ = "1c" THEN GOTO einsc
'IF location$ = "2a" THEN GOTO zweia
'IF location$ = "2b" THEN GOTO zweib
'IF location$ = "2c" THEN GOTO zweic
'IF location$ = "3a" THEN GOTO dreia
'IF location$ = "3b" THEN GOTO dreib
'IF location$ = "3c" THEN GOTO dreic

einsa:
SCREEN 8
CLS
CALL horizon
CALL tree01
CALL tree04
SHELL "pause >nul"

einsb:
SCREEN 8
CLS
CALL horizon
CALL tree03
CALL tree06
SHELL "pause >nul"

einsc:
SCREEN 8
CLS
CALL horizon
CALL tree02
CALL tree05
SHELL "pause >nul"

zweia:
SCREEN 8
CLS
CALL horizon
CALL tree01
CALL tree03
SHELL "pause >nul"

zweib:
SCREEN 8
CLS
CALL horizon
CALL tree01
CALL tree05
SHELL "pause >nul"

zweic:
SCREEN 8
CLS
CALL horizon
CALL tree06
CALL tree04
SHELL "pause >nul"

dreia:
SCREEN 8
CLS
CALL horizon
CALL tree01
CALL tree02
SHELL "pause >nul"

dreib:
SCREEN 8
CLS
CALL horizon
CALL tree02
CALL tree03
SHELL "pause >nul"

dreic:
SCREEN 8
CLS
CALL horizon
CALL tree03
CALL tree04

SHELL "pause >nul"

SUB horizon
SHARED ctr!
IF ctr! = 0 THEN GOTO hori1
IF ctr! = 1 THEN GOTO hori2

hori1:
ctr! = 1
LINE (-1, 101)-(640, 103), 2, B
LINE (-1, 103)-(640, 105), 2, BF
LINE (-1, 105)-(640, 108), 2, B
LINE (-1, 108)-(640, 113), 2, BF
LINE (-1, 113)-(640, 120), 2, B
LINE (-1, 120)-(640, 130), 2, BF
LINE (-1, 130)-(640, 145), 2, B
LINE (-1, 145)-(640, 165), 2, BF
LINE (-1, 165)-(640, 190), 2, B
LINE (-1, 190)-(640, 250), 2, BF
GOTO zuv


hori2:
ctr! = 0
LINE (-1, 101)-(640, 103), 2, BF
LINE (-1, 103)-(640, 105), 2, B
LINE (-1, 105)-(640, 108), 2, BF
LINE (-1, 108)-(640, 113), 2, B
LINE (-1, 113)-(640, 120), 2, BF
LINE (-1, 120)-(640, 130), 2, B
LINE (-1, 130)-(640, 145), 2, BF
LINE (-1, 145)-(640, 165), 2, B
LINE (-1, 165)-(640, 190), 2, BF
LINE (-1, 190)-(640, 250), 2, B
GOTO zuv


zuv:
END SUB

SUB loca
SHARED location$
PRINT "LOC :", location
END SUB

SUB tree01
LINE (80, 80)-(100, 150), 6, BF
FOR ger% = 0 TO 60 STEP 1
CIRCLE (89, 80), ger%, 2
NEXT
END SUB

SUB tree02
FOR ghj% = 0 TO 70 STEP 1
CIRCLE (200, 200), ghj%, 2
NEXT
END SUB

SUB tree03
LINE (500, 60)-(515, 130), 6, BF
FOR asw% = 0 TO 55 STEP 1
CIRCLE (506, 55), asw%, 2
NEXT
END SUB

SUB tree04
LINE (320, 80)-(340, 160), 6, BF
FOR ger% = 0 TO 70 STEP 1
CIRCLE (325, 80), ger%, 2
NEXT
END SUB

SUB tree05
LINE (140, 120)-(160, 240), 6, BF
FOR ger% = 0 TO 60 STEP 1
CIRCLE (152, 120), ger%, 2
NEXT
END SUB

SUB tree06
LINE (190, 45)-(200, 110), 6, BF
FOR ger% = 0 TO 50 STEP 1
CIRCLE (194, 45), ger%, 2
NEXT
END SUB

SUB tree07
LINE (387, 70)-(398, 110), 6, BF
FOR ger% = 0 TO 50 STEP 1
CIRCLE (390, 70), ger%, 2
NEXT
END SUB

SUB tree08
LINE (450, 130)-(459, 190), 6, BF
FOR ger% = 0 TO 50 STEP 1
CIRCLE (453, 130), ger%, 2
NEXT
END SUB

