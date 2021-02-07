DECLARE SUB dosshell ()
DECLARE SUB clock ()
DECLARE SUB syswin ()
DECLARE SUB code ()
DECLARE SUB ssaver ()
DECLARE SUB appwin ()
DECLARE SUB ausg ()
DECLARE SUB desk ()
DECLARE SUB prefs ()

' Inhalt der CHROS.INI :
SCREEN 8



reboot:
'CALL code

anf1:
CALL prefs
CLS

anf2:
reboval! = 0

CALL desk

DO: a$ = INKEY$

LOOP UNTIL a$ = CHR$(115) OR a$ = CHR$(105) OR a$ = CHR$(97) OR a$ = CHR$(27)
IF a$ = CHR$(115) THEN CALL syswin '"SYSTEM"
IF a$ = CHR$(105) THEN CALL ausg '"INFO"
IF a$ = CHR$(97) THEN CALL appwin 'applications
IF a$ = CHR$(27) THEN CALL ausg

IF reboval! = 1 THEN GOTO reboot
GOTO anf1

SUB appwin
sappw:
CLS
CALL desk


LINE (100, 35)-(300, 150), 8, B

LOCATE 6, 15
PRINT "CALCULATOR          -C"
LOCATE 7, 15
PRINT "ClOCK               -L"
LOCATE 8, 15
PRINT "DOS SHELL           -D"
LOCATE 9, 15
PRINT "COMMAND LINE        -I"
LOCATE 10, 15
PRINT "SCREEN SAVERS >>    -S"

FOR mls! = 47 TO 150 STEP 8
LINE (100, mls!)-(300, mls!)
NEXT

DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(27) OR a$ = CHR$(115) OR a$ = CHR$(108) OR a$ = CHR$(100)
IF a$ = CHR$(27) THEN GOTO eoappwin
IF a$ = CHR$(115) THEN GOTO susaver
IF a$ = CHR$(108) THEN CALL clock
IF a$ = CHR$(100) THEN CALL dosshell

GOTO sappw
susaver:
CALL ssaver
CLS
CALL desk
GOTO sappw






eoappwin:
END SUB

SUB ausg
CLS
SYSTEM
END SUB

SUB clock

LINE (200, 150)-(350, 100), 8, BF
LINE (206, 156)-(356, 106), 15, BF
LOCATE 15, 29
PRINT "--CHR WATCH--"
LOCATE 17, 29
PRINT "TIME  "; TIME$
LOCATE 19, 29
PRINT "DATE  "; DATE$

DO: a$ = INKEY$
LOOP UNTIL a$ = CHR$(27)

END SUB

SUB code
CALL prefs
recode:
CLS
CALL desk
PRINT ""
PRINT ""
CALL desk
PRINT ""
PRINT "      CHRY SICHERHEITSABFRAGE :"
PRINT ""
PRINT " "
PRINT "      Bitte geben Sie ihren Log-In ein."
PRINT ""
CALL desk
LOCATE 20, 20
INPUT "      Log-In : ", codeword$
PRINT ""
IF NOT codeword$ = "galahad" THEN GOTO recode

END SUB

SUB desk
SCREEN 8
CALL prefs
FOR fr! = 1 TO 17 STEP 2
LINE (fr!, fr!)-(640, 200), 8, B
NEXT
LOCATE 2, 30
PRINT "CHR - OS V1.0"

LOCATE 1, 73
PRINT TIME$

LINE (17, 17)-(100, 35), 8, B
LINE (100, 35)-(250, 17), 8, B
LINE (250, 17)-(380, 35), 8, B
LINE (380, 35)-(440, 17), 8, B



LOCATE 4, 4
PRINT "(S)YSTEM"
LOCATE 4, 16
PRINT "(A)PPLICATIONS"
LOCATE 4, 33
PRINT "(P)REFERENCES"
LOCATE 4, 49
PRINT "(I)NFO"







END SUB

SUB dosshell
CLS
CALL desk
COLOR 1, 9
SHELL "command.com"
END SUB

SUB prefs
SCREEN 8
CLS
COLOR 8, 7

END SUB

SUB ssaver

LOCATE 10, 40
PRINT " FLYING LETTERS   -F"
LOCATE 11, 40
PRINT " BLANK SCREEN     -B"
LINE (300, 71)-(500, 87), 8, B
LINE (300, 79)-(500, 79)

DO: a$ = INKEY$

LOOP UNTIL a$ = CHR$(27) OR a$ = CHR$(102) OR a$ = CHR$(98)
IF a$ = CHR$(27) THEN GOTO eos
IF a$ = CHR$(102) THEN GOTO flylet
IF a$ = CHR$(98) THEN GOTO blanks








flylet:
SCREEN 0
RANDOMIZE TIMER
DO
  x = INT(RND * 60) + 1
  y = INT(RND * 24) + 1
  cf = INT(RND * 15)
  ch = INT(RND * 8)
  cf = 1
  COLOR cf, ch
  LOCATE y, x
  PRINT " CHR OS";
LOOP UNTIL INKEY$ = CHR$(27)
IF codec! = 1 THEN CALL code
GOTO eos:

blanks:
CLS
COLOR 0, 0
SHELL "pause > nul"
IF codec! = 1 THEN CALL code


SCREEN 8
eos:
END SUB

SUB syswin
LINE (18, 35)-(150, 60), 8, B
LOCATE 6, 5
PRINT "(R)EFRESH"
LOCATE 7, 5
PRINT "(S)HUT DOWN"
LINE (18, 47)-(150, 47)

DO: a$ = INKEY$

LOOP UNTIL a$ = CHR$(114) OR a$ = CHR$(115) OR a$ = CHR$(27)
IF a$ = CHR$(114) THEN reboval! = 1
IF a$ = CHR$(115) THEN CALL ausg
eosys:
END SUB

