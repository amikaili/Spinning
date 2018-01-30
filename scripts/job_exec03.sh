#!/bin/sh

# TODO: Infinite LOOP
TIMETOWAIT="6"
while [ 1 ]
do
    echo  $(date -u)
    echo "sleeping for $TIMETOWAIT seconds"
    /bin/sleep $TIMETOWAIT
    echo "Woke!!!"
    
done

echo "All done!"


