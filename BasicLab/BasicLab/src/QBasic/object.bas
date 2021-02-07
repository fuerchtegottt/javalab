DECLARE SUB castle01 ()
DECLARE SUB house01 ()
DECLARE SUB player ()
DECLARE SUB message ()
DECLARE SUB sun ()
DECLARE SUB mount05 ()
DECLARE SUB mount04 ()
DECLARE SUB mount03 ()
DECLARE SUB mount02 ()
DECLARE SUB mount01 ()
DECLARE SUB tree02 ()
DECLARE SUB tree03 ()
DECLARE SUB tree05 ()
DECLARE SUB tree06 ()
DECLARE SUB horizon ()
DECLARE SUB tree01 ()
DECLARE SUB tree04 ()
CLS


SCREEN 8
CLS
COLOR 7, 1  ' Tag
'COLOR 7, 0 ' Nacht
CALL sun
CALL horizon
'CALL mount02
'CALL mount04
CALL mount01
'CALL mount03
CALL mount05
'CALL tree04
'CALL tree05
'CALL house01
CALL castle01
mess! = 1
'CALL message

SHELL "pause >nul"

SUB castle01
LINE (50, 100)-(640, 200), 8, BF
LINE (300, 200)-(450, 140), 6, BF
FOR ccir! = 1 TO 75 STEP 1
CIRCLE (375, 140), ccir!, 6
LINE (450, 140)-(300, 140)
LINE (375, 200)-(375, 109)
LINE (150, 100)-(200, 80), 8, BF
LINE (50, 100)-(100, 80), 8, BF
LINE (250, 100)-(300, 80), 8, BF
LINE (350, 100)-(400, 80), 8, BF
LINE (450, 100)-(500, 80), 8, BF
LINE (550, 100)-(600, 80), 8, BF
FOR va1! = 1 TO 10 STEP 1
CIRCLE (470, 140), va1!, 15
NEXT
FOR va2! = 1 TO 10 STEP 1
CIRCLE (280, 140), va2!, 15
NEXT


NEXT





END SUB

SUB cave01
END SUB

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

SUB house01
LINE (100, 100)-(300, 180), 4, BF
LINE (120, 130)-(180, 160), 2, BF
LINE (120, 145)-(180, 145)
LINE (150, 130)-(150, 160)
LINE (300, 180)-(250, 150), 2, BF


 





END SUB

SUB loca
SHARED location$
PRINT "LOC :", location
END SUB

SUB message
SHARED mess!
IF mess! = 1 THEN GOTO ocean
'IF mess! = 2 THEN GOTO sun
'IF mess! = 3 THEN GOTO enemy

ocean:
LINE (0, 0)-(400, 30), 7, B
PRINT ""
PRINT "  Sie haben das Meer erreicht. Ohne Boot"
PRINT "  kînnen Sie die Insel nicht verlassen "
LINE (0, 0)-(400, 30), 7, B





END SUB

SUB mount01
FOR m! = 10 TO 160 STEP 3
LINE (100, 60)-(m!, 100), 7
NEXT

END SUB

SUB mount02
FOR m! = 10 TO 310 STEP 2
LINE (200, 50)-(m!, 100), 8
NEXT

END SUB

SUB mount03
FOR m! = 260 TO 340 STEP 2
LINE (300, 49)-(m!, 100), 7
NEXT

END SUB

SUB mount04
FOR m! = 300 TO 460 STEP 3
LINE (400, 65)-(m!, 100), 8
NEXT

END SUB

SUB mount05
FOR m! = 410 TO 590 STEP 2
LINE (500, 45)-(m!, 100), 7
NEXT

END SUB

SUB player
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

END SUB

SUB sun
FOR sc! = 1 TO 130 STEP 2
CIRCLE (640, 0), sc!, 14
NEXT
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

