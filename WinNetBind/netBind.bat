@echo off
echo --Netzlaufwerke verbinden--
net use V: /delete
net use W: /delete
pause
net use W: \\192.168.2.103\share
net use V: \\fritz.nas\FRITZ.NAS /USER:smb ###PWD#HERE!###
pause