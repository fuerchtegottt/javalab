'                        Qbasic Black Jack for the PC V1.1
'
'This is the game of Black Jack.  The object of the game is to get a total
'of 21 in your hand, or as close as you can get to 21, without going over.
'Whoever gets closest to 21 is the winner.  In case of a tie, the dealer
'wins.  Face cards are worth ten points.  Aces are worth either one or eleven
'points.  All other cards are worth their face value.  To get more cards in
'your hand, you take a hit.  In this game, you do that by hitting the "H"
'button.  When you feel you have enough points, you stay, by hitting the "S"
'button.  The dealer will hit until he has seventeen or more.
'This game does use a deck of cards, not just randomly generated numbers, so
'you can keep track of the cards and you will know what cards are left.  When
'the deck runs out, it does get reshuffled.
'
'Original code by Jeff Lewis
'http://www.jefflewis.net/programming-qbasic-blakjak.html
'
' UPDATE 1.1
' - missing draw situation added 26.01.2022 by Christian Gellert
'
'
'
'
'
'

DECLARE SUB heart ()
DECLARE SUB diamond ()
DECLARE SUB club ()
DECLARE SUB spade ()
DECLARE SUB display ()
DECLARE SUB score ()

OPTION BASE 1          'starts the deck array at 1
DIM deck(52)           'dimensions the array
FOR card = 1 TO 52     'generates the deck
deck(card) = card
NEXT card
scoreplayer = 0
scoredealer = 0

reshuffle:

RANDOMIZE TIMER        'shuffles the deck
FOR ctr = 1 TO 50
a = INT(52 * RND) + 1
B = INT(52 * RND) + 1
SWAP deck(a), deck(B)
NEXT ctr

card = 0

playagain:
CLS
SCREEN 7    '40 x 25 text, 320 x 200 graphics, 16 colors
LINE (0, 0)-(320, 200), 2, BF      'green background
LINE (95, -1)-(191, 10), 15, BF    'box around "Black Jack"
LINE (95, -1)-(191, 10), 1, B      '     "
LINE (5, 165)-(265, 185), 15, BF   'box around "Total-  (h)it or ..."
LINE (5, 165)-(265, 185), 1, B     '     "
LINE (2, 94)-(41, 104), 15, BF     'box around "You:"
LINE (2, 94)-(41, 104), 1, B       '     "
LINE (2, 6)-(65, 16), 15, BF       'box around "Dealer:"
LINE (2, 6)-(65, 16), 1, B         '     "

COLOR 1, 2

LOCATE 1, 14                            '
PRINT "Black Jack"                      '
LOCATE 2, 2
PRINT "Dealer:"
LOCATE 13, 2
PRINT "You:"

CALL score

totaldealer = 0
totalplayer = 0
totaldealer2 = 0
totalplayer2 = 0
ace.yes.no.dealer = 0
ace.yes.no.player = 0

LOCATE 4, 2

x = 2
y = 4

LINE (5, 20)-(45, 70), 4, BF
LINE (5, 20)-(45, 70), 1, B
LINE (6, 21)-(44, 69), 15, B

card1 = 1

total = totaldealer
total2 = totaldealer2
ace.yes.no = ace.yes.no.dealer

cardoriginal = card

CALL display

totaldealer = total
totaldealer2 = total2
ace.yes.no.dealer = ace.yes.no

card1 = 0

x = 2
y = 4
cardx = 5
cardy = 20

total = totaldealer
total2 = totaldealer2
ace.yes.no = ace.yes.no.dealer

CALL display

totaldealer = total
totaldealer2 = total2
ace.yes.no.dealer = ace.yes.no
x = -4
y = 15
cardx = -43
cardy = 107

ctr = 0

hit:

ctr = ctr + 1

total = totalplayer
total2 = totalplayer2
ace.yes.no = ace.yes.no.player

CALL display

totalplayer = total
totalplayer2 = total2
ace.yes.no.player = ace.yes.no

IF totalplayer > 21 THEN
GOTO over
END IF

IF ctr = 1 THEN
GOTO hit
END IF

LOCATE 22, 2
PRINT "Total-"; totalplayer; "            "
IF ace.yes.no.player = 1 AND totalplayer2 <= 21 THEN
LOCATE 22, 2
PRINT "Total-"; totalplayer2; "or"; totalplayer; ""
END IF

IF ctr = 6 THEN
GOTO charlie
END IF

LOCATE 23, 2
PRINT "Do you want to (h)it or (s)tay?"

getkey:
h.s$ = INKEY$
SELECT CASE h.s$
CASE "h"
GOTO hit
CASE "s"
GOTO stay
END SELECT
GOTO getkey

stay:

LOCATE 23, 2
PRINT "                               "

IF ace.yes.no.player = 1 AND totalplayer2 <= 21 THEN
totalplayer = totalplayer2
END IF

x = -4
y = 4
cardx = -43
cardy = 20
realcard = card

card = cardoriginal

CALL display

cardvalue = 0
cardx = 53
x = 8

card = realcard

IF totaldealer >= 17 OR totaldealer2 >= 17 THEN
GOTO staydealer
END IF

dealercount = 0

hitdealer:

dealercount = dealercount + 1
LOCATE dealercount, 38
PRINT dealercount


total = totaldealer
total2 = totaldealer2
ace.yes.no = ace.yes.no.dealer

CALL display

totaldealer = total
totaldealer2 = total2
ace.yes.no.dealer = ace.yes.no

IF ace.yes.no.dealer = 1 AND totaldealer2 <= 21 THEN
  IF totaldealer2 < 17 THEN
  GOTO hitdealer
  END IF
ELSE
  IF totaldealer < 17 THEN
  GOTO hitdealer
  END IF
END IF

staydealer:

IF ace.yes.no.dealer = 1 AND totaldealer2 <= 21 THEN
totaldealer = totaldealer2
END IF

LINE (5, 78)-(145, 88), 15, BF     'box around "Dealer Total-"
LINE (5, 78)-(145, 88), 1, B       '     "

LOCATE 11, 2
PRINT "Dealer Total-"; totaldealer

IF totaldealer > 21 THEN
GOTO win
END IF
IF totalplayer > totaldealer THEN
GOTO win
END IF
IF totalplayer = totaldealer THEN
GOTO drawgame
END IF

LOCATE 23, 2
PRINT "The dealer won."
scoredealer = scoredealer + 1
GOTO again

win:
LOCATE 23, 2
PRINT "You won!"
scoreplayer = scoreplayer + 1
GOTO again

over:
LOCATE 22, 2
PRINT "Total-"; totalplayer; "            "
LOCATE 23, 2
PRINT "You busted.                    "
scoredealer = scoredealer + 1
GOTO again

drawgame:
LOCATE 23, 2
PRINT "draw"
GOTO again

charlie:
LOCATE 23, 2
PRINT "Five-Card Charlie.  You Won!   "
scoreplayer = scoreplayer + 1

again:
CALL score
LOCATE 22, 19
PRINT "Play again? y/n"
getkey2:
getkey$ = INKEY$
SELECT CASE getkey$
CASE "y"
GOTO again2
CASE "n"
GOTO quit
END SELECT
GOTO getkey2

again2:
IF card > 42 THEN
LOCATE 22, 2
PRINT " The cards have been shuffled.  "
LOCATE 23, 2
PRINT "  Press any key to continue."
DO
y.n$ = INKEY$
LOOP UNTIL y.n$ <> ""
GOTO reshuffle
END IF
GOTO playagain

quit:
LOCATE 12, 8
LINE (0, 0)-(320, 200), 2, BF
LINE (40, 80)-(281, 103), 15, BF
LINE (40, 80)-(281, 103), 1, B
PRINT "Hope to see you again soon!"
END

SUB club

SHARED x
SHARED y
SHARED card1
COLOR 1

IF card1 = 1 THEN
COLOR 0
END IF

LOCATE y + 2, x + 2
PRINT CHR$(5)

END SUB

SUB diamond

SHARED x
SHARED y
SHARED card1
COLOR 4

IF card1 = 1 THEN
COLOR 0
END IF

LOCATE y + 2, x + 2
PRINT CHR$(4)



END SUB

SUB display

COLOR 1, 15

SHARED deck()
SHARED playcard
SHARED card
SHARED cardx
SHARED cardy
SHARED card1
SHARED ace.yes.no
SHARED total
SHARED total2
SHARED x
SHARED y

card = card + 1
playcard = deck(card)
cardx = cardx + 48
x = x + 6

LOCATE y, x

IF card1 = 0 THEN
LINE (cardx, cardy)-(cardx + 40, cardy + 50), 15, BF
LINE (cardx, cardy)-(cardx + 40, cardy + 50), 1, B
END IF

IF playcard = 1 THEN
cardvalue = 0
PRINT "A"
CALL heart
IF ace.yes.no = 1 THEN
total = total + 1
total2 = total2 + 1
ELSE
total = total + 1
total2 = total2 + 11
END IF
ace.yes.no = 1
END IF

IF playcard = 2 THEN
cardvalue = 0
PRINT "A"
CALL diamond
IF ace.yes.no = 1 THEN
total = total + 1
total2 = total2 + 1
ELSE
total = total + 1
total2 = total2 + 11
END IF
ace.yes.no = 1
END IF

IF playcard = 3 THEN
cardvalue = 0
PRINT "A"
CALL club
IF ace.yes.no = 1 THEN
total = total + 1
total2 = total2 + 1
ELSE
total = total + 1
total2 = total2 + 11
END IF
ace.yes.no = 1
END IF

IF playcard = 4 THEN
cardvalue = 0
PRINT "A"
CALL spade
IF ace.yes.no = 1 THEN
total = total + 1
total2 = total2 + 1
ELSE
total = total + 1
total2 = total2 + 11
END IF
ace.yes.no = 1
END IF

IF playcard = 5 THEN
cardvalue = 2
CALL heart
END IF

IF playcard = 6 THEN
cardvalue = 2
CALL diamond
END IF

IF playcard = 7 THEN
cardvalue = 2
CALL club
END IF

IF playcard = 8 THEN
cardvalue = 2
CALL spade
END IF

IF playcard = 9 THEN
cardvalue = 3
CALL heart
END IF

IF playcard = 10 THEN
cardvalue = 3
CALL diamond
END IF

IF playcard = 11 THEN
cardvalue = 3
CALL club
END IF

IF playcard = 12 THEN
cardvalue = 3
CALL spade
END IF

IF playcard = 13 THEN
cardvalue = 4
CALL heart
END IF

IF playcard = 14 THEN
cardvalue = 4
CALL diamond
END IF

IF playcard = 15 THEN
cardvalue = 4
CALL club
END IF

IF playcard = 16 THEN
cardvalue = 4
CALL spade
END IF

IF playcard = 17 THEN
cardvalue = 5
CALL heart
END IF

IF playcard = 18 THEN
cardvalue = 5
CALL diamond
END IF

IF playcard = 19 THEN
cardvalue = 5
CALL club
END IF

IF playcard = 20 THEN
cardvalue = 5
CALL spade
END IF

IF playcard = 21 THEN
cardvalue = 6
CALL heart
END IF

IF playcard = 22 THEN
cardvalue = 6
CALL diamond
END IF

IF playcard = 23 THEN
cardvalue = 6
CALL club
END IF

IF playcard = 24 THEN
cardvalue = 6
CALL spade
END IF

IF playcard = 25 THEN
cardvalue = 7
CALL heart
END IF

IF playcard = 26 THEN
cardvalue = 7
CALL diamond
END IF

IF playcard = 27 THEN
cardvalue = 7
CALL club
END IF

IF playcard = 28 THEN
cardvalue = 7
CALL spade
END IF

IF playcard = 29 THEN
cardvalue = 8
CALL heart
END IF

IF playcard = 30 THEN
cardvalue = 8
CALL diamond
END IF

IF playcard = 31 THEN
cardvalue = 8
CALL club
END IF

IF playcard = 32 THEN
cardvalue = 8
CALL spade
END IF

IF playcard = 33 THEN
cardvalue = 9
CALL heart
END IF

IF playcard = 34 THEN
cardvalue = 9
CALL diamond
END IF

IF playcard = 35 THEN
cardvalue = 9
CALL club
END IF

IF playcard = 36 THEN
cardvalue = 9
CALL spade
END IF

IF playcard = 37 THEN
cardvalue = 10
CALL heart
END IF

IF playcard = 38 THEN
cardvalue = 10
CALL diamond
END IF

IF playcard = 39 THEN
cardvalue = 10
CALL club
END IF

IF playcard = 40 THEN
cardvalue = 10
CALL spade
END IF

IF playcard = 41 THEN
cardvalue = 10
CALL heart
END IF

IF playcard = 42 THEN
cardvalue = 10
CALL diamond
END IF

IF playcard = 43 THEN
cardvalue = 10
CALL club
END IF

IF playcard = 44 THEN
cardvalue = 10
CALL spade
END IF

IF playcard = 45 THEN
cardvalue = 10
CALL heart
END IF

IF playcard = 46 THEN
cardvalue = 10
CALL diamond
END IF

IF playcard = 47 THEN
cardvalue = 10
CALL club
END IF

IF playcard = 48 THEN
cardvalue = 10
CALL spade
END IF

IF playcard = 49 THEN
cardvalue = 10
CALL heart
END IF

IF playcard = 50 THEN
cardvalue = 10
CALL diamond
END IF

IF playcard = 51 THEN
cardvalue = 10
CALL club
END IF

IF playcard = 52 THEN
cardvalue = 10
CALL spade
END IF

LOCATE y, x

PRINT cardvalue

LOCATE y, x

IF cardvalue = 0 THEN
PRINT " A "
END IF

IF playcard > 48 AND playcard < 53 THEN
PRINT " K "
END IF

IF playcard > 44 AND playcard < 49 THEN
PRINT " Q "
END IF

IF playcard > 40 AND playcard < 45 THEN
PRINT " J "
END IF

COLOR 1

total = total + cardvalue
total2 = total2 + cardvalue

END SUB

SUB heart

SHARED x
SHARED y
SHARED card1

COLOR 4

IF card1 = 1 THEN
COLOR 0
END IF

LOCATE y + 2, x + 2
PRINT CHR$(3)



END SUB

SUB score
SHARED scoredealer
SHARED scoreplayer
LINE (154, 77)-(312, 97), 15, BF   'box around "Score: ..."
LINE (154, 77)-(312, 97), 1, B     '     "
LOCATE 11, 22
PRINT "Score: Dealer-"; scoredealer
LOCATE 12, 29
PRINT "You-   "; scoreplayer
END SUB

SUB spade

SHARED x
SHARED y
SHARED card1

COLOR 1

IF card1 = 1 THEN
COLOR 0
END IF

LOCATE y + 2, x + 2
PRINT CHR$(6)

END SUB

