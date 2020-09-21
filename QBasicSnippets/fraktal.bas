' ACHTUNG: für GW BASIC!!!

10 CLS

20 RANDOMIZE TIMER
30 DEF SEG = &HB800: SCREEN 1, 0: COLOR 3, 0: CLS
40 INPUT "X-Coordinate: x=", x1
50 INPUT "Y-Coordinate: y=", y1
60 GOSUB 640
70 PRINT "Hoehe bei "; x1; "/"; y1; " :"; z: INPUT "Z-Coordinate: z=", h: IF h < z THEN RUN
80 GOSUB 640
90 INPUT "Bildname (mit ext):", na$
100 GOSUB 490
110 FOR v% = 1 TO 600 STEP 2
120 a$ = INKEY$: IF a$ = CHR$(13) THEN BSAVE na$, 0, &H4000
130 e = h / v% * 10
140 FOR ho% = -160 TO 160
150 fa% = 1: z = 2: hc = e * ho% * .00935: xa = 0: ya = 0
160 xc = x1 + hc * co + e * si: yc = y1 - hc * si + e * co
170 x2 = ABS(xa): y2 = ABS(ya)
180 nx = x2 - y2 - xc
190 ya = xa * ya: ya = ya + ya - yc
200 xa = xn: z = z - .05
210 IF z < 0 THEN 240
220 IF x2 + y2 > 400 THEN 240
230 GOTO 170
240 IF ho% > -160 THEN z = za * .7 + z * .3
250 IF z < 0 THEN fa% = 0: z = -.0001
260 c = z - h: c = c * v% / h: za = z
270 GOSUB 320
280 ca = c: NEXT ho%
290 NEXT v%
300 BSAVE na$, 0, &H4000
310 GOTO 310
320 IF ABS(c) > 102 THEN c = 102 * SGN(c)
330 m1% = 161 + ho%: mo% = 160 + ho%: db% = 98 - c: d9% = 99 - c
340 LINE (m1%, 200)-(m1%, db%), 2
350 LINE (m%, 200)-(m0%, d9%), 3
360 IF c < 0 THEN PSET (m0%, d9%), fa%
370 IF c > ca + .5 THEN LINE (m0%, 99 - ca)-(m0%, d9%), 0
380 PSET (mo%, db%), fa%
390 RETURN
400 z = 2: xc = x1: yc = y1: xa = 0: ya = 0
410 x2 = ABS(xa): y2 = ABS(ya)
420 xn = x2 - y2 - xc
430 ya = xa * ya: ya = ya + ya - yc
440 xa = xn: z = z - .05
450 IF z < 0 THEN 480
460 IF x2 + y2 > 400 THEN 480
470 GOTO 410
480 RETURN
490 CLS
500 FOR i = 8 TO 192 STEP 4
510 j = (i / 192) ^ 6.6: j = j * 90
520 br = RND(1) + .7: br = br * i * .1
530 as=rnd(rnd(1))*520-100:hr=br*(2-rnd(1)*.8)*2

540 FOR ho = -br TO br
550 xb=ho+as:ifabs(xb-160) > 160 then 600
560 c1=br*br-ho*ho:c1=c1/hr:ifc1+j>90 then c1=90-j
570 yb = c1 * j * .006: yc = 90 - j
580 LINE (xb, yc - c1)-(xb, yc + yb), 2
590 PSET (xb, yc - yb), 0
600 NEXT ho
610 NEXT i
620 LINE (0, 98)-(320, 98), 2
630 RETURN
640 INPUT "blickwinkel : w= ", ku
650 ku = ku * 3.1416 / 180: si = SIN(ku): co = COS(ku)
660 RETURN





























