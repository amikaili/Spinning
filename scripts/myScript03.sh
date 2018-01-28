uname -a
# pwd

#condor_submit jobfile

# TODO: Infinite LOOP
TIMETOWAIT="6"
echo "sleeping for $TIMETOWAIT seconds"
/bin/sleep $TIMETOWAIT
echo "Woke!!!"

#condor_rm <job_id> #???

uname -a