#!/bin/bash VOLUMIO ALARM Controller V1.2 parameter1: [wd|we|aw] weekday
# or weekend or always parameter2: [music|news|off] play music or news
# or stop playback
#
execute_command( ) {
  if [ $2 == "music" ]
  then
    echo "play music.."
    wget -O/dev/null -q '192.168.2.32/api/v1/commands/?cmd=playplaylist&name=FluxFM'
  elif [ $2 == "news" ]
  then
    echo "play news.."
    wget -O/dev/null -q '192.168.2.32/api/v1/commands/?cmd=playplaylist&name=DLF'
  elif [ $2 == "off" ]
  then
    echo "stop playback.."
    wget -O/dev/null -q '192.168.2.32/api/v1/commands/?cmd=stop'
  fi
}
wget -O/dev/null -q '192.168.2.32/api/v1/commands/?cmd=volume&volume=85'
if [ $(/bin/date '+%u') -lt 6 ] && [ $1 == "wd" ]
  then
    execute_command "$1" "$2"
  elif [ $(/bin/date '+%u') -gt 5 ] && [ $1 == "we" ]
  then
    execute_command "$1" "$2" 
  elif [ $1 == "aw" ]
  then
    execute_command "$1" "$2"
  else
    echo "command suppressed.. out of schedule"
fi
