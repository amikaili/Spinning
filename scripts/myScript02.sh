
uname -a

#condor_submit jobfile

# TODO: Set wallclock limit: 
# See condor_q to get time spent running"
# http://www.cl.cam.ac.uk/manuals/condor-V6_8_3-Manual/condor_q.html
# goes in the loop:
#  'RUN_TIME':
#      condor_q -long | grep RUN_TIME # ???
#  if RUN_TIME g.t. wallclock => condor_rm <job_id>
# 
#

# TODO: Infinite LOOP
TIMETOWAIT="6"
echo "sleeping for $TIMETOWAIT seconds"
/bin/sleep $TIMETOWAIT
echo "Woke!!!"

uname -a

