#!/usr/bin/bash

nm=$(basename $(pwd))
an=$(basename $(pwd))$(expr substr $(date '+%y%m%d%H%M') 1 10)

#echo $nm $an
cd ..
tar cJf ${an}.txz --exclude='*/target/*' ${nm}

#dn=$(basename $(pwd))
#mp=${dn}$(date +%y%m%d%H%M)
#an=${mp}.tar.xz
#~/.kde/share/config/
#~/.kde/share/apps/

#tar -C "/home/f/.kde/share/" -cf "kde$(date +%y%m%d%H%M).tar" config/ apps/
#tar -C "/home/f/.kde/share/" -cJf "kde$(date +%y%m%d%H%M).txz" config/ apps/
#tar -C "~/.kde/share/" -cJf "kde$(date +%y%m%d%H%M).txz" config/ apps/

#tar -C "/home/f/.kde/share/" -cJf "kde$(date +%y%m%d%H%M).txz" config/ apps/kate/metainfos apps/konsole/ apps/kwallet/

myDir="/home/f"

#tar -C "${myDir}/.config/google-chrome/Default/" -cJf "chrome$(date +%y%m%d%H%M).txz" "Current Tabs" Bookmarks
#tar -C "${myDir}/.config/google-chrome/Default/" -cJf "chrome$(expr substr $(date +%y%m%d%H%M) 2 10).txz" "Current Tabs" Bookmarks

#cp ../google-chrome/Default/Current\ Tabs Current\ Tabs6
#cp ../../.mozilla/firefox/ao6blc78.default/sessions/backup.session .

# $(expr substr $(date '+%y%m%d%H%M') 2 10)
