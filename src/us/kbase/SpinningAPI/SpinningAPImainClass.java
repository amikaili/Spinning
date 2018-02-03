
package us.kbase.SpinningAPI;

	
	// import condor.*;
	// import birdbath.*;
	// import birdbath.Transaction;
	// import birdbath.ClassAd.*;
	// import birdbath.Schedd.*;
	// import java.net.URL;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.*;
import java.rmi.RemoteException;
import java.util.Map;

// import us.kbase.narrativejobservice.sdkjobs.DockerRunner;
	
	// import javax.xml.rpc.ServiceException;
    
	public class SpinningAPImainClass
	{
	        private static Map<String, String> config = null;
		    public static final String CFG_PROP_SELF_EXTERNAL_URL = "https://ci.kbase.us/services/njs_wrapper";
		    
		    // TODO: main method for the Java Universe:
		    
	    	public static void main(String[] args) {
	    		
	            System.out.println("\n\nHello JobSubmitStub:\n");
	            try {
	            	
	                submitJavaUniverse( args );
	                
	            } catch (Exception ex) {
	            	
	            } finally {
	            	
	            }
	                
	    	}
            
		    
		    // main method for the Vanilla Universe:
		    
		    /*
	    	public static void main(String[] args) {
	    		
	            System.out.println("\n\nHello JobSubmitStub:\n");
	            String cmd = "";
	            
	            if( ! ( args.length > 0 ) ) {
	            	cmd = "uname -a";
	            	                    	
	            } else {
	            	
	                for ( String str: args ){
	            	    cmd = cmd + " " + str;
	                }
	                
		    		System.out.println( "\nJobSubmitStub::Command is: " + cmd + "\n");

	                
                    try{
			    		if( cmd.contains( "0.1" ) ) {
		                	
								submitPrintTime( );

		                } else if( cmd.contains( "0.2" ) ) {
		                	    
		                	    submitSleepWallclock( );
		                	    
		                } else if( cmd.contains( "0.3" ) ) {
		                	
		                	    submitInfiniteLoop( );
		                	    
		                } else if( cmd.contains( "0.4" ) ) {
		                	
		                	    submitHalfDozenJobs( );
		                	    
		                } else {
			                executeTheCLIcommand( cmd );	                	
		                }
                    } catch (IOException e) {
						e.printStackTrace();                    	
                    }
	                
	            }
	            
	                System.out.println("\n\nJobSubmitStub finished.");
	    	}
	    	*/
	    	
	    	public static void submitJavaUniverse( String[] args ) throws IOException {
	    		// Got here because the Java Universe example submit file called this executable
	    		// todo: Emulate like what localmethodrunner does in the old execution system today
	    		//     endup calling docker run on this condor image...
	    		
	    		// todo: parse args to get job id and uri to njsw server
	            if (args.length != 2) {
	                System.err.println("Usage: <program> <job_id> <job_service_url>");
	                for (int i = 0; i < args.length; i++)
	                    System.err.println("\tArgument[" + i + "]: " + args[i]);
	                System.exit(1);
	            }
	            final String jobId = args[0];
	            String jobSrvUrl = args[1];	    		
	    		
	    		System.out.println( "\nJobSubmitStub::submitJavaUniverse called with args:\n");
	    		System.out.println( "jobId: " + jobId + "\n" );
	    		System.out.println( "jobSrvUrl: " + jobSrvUrl + "\n\n" );

	            String[] hostnameAndIP = getHostnameAndIP();
	            
	            String clientGroup = System.getenv("AWE_CLIENTGROUP");
	            if (clientGroup == null)
	                clientGroup = "<unknown>";
	            
	            // Calling Docker run
	            /*
	            new DockerRunner(dockerURI).run(imageName, modMeth.getModule(), inputFile, token, log,
	                    outputFile, false, refDataDir, null, callbackUrl, jobId, additionalBinds,
	                    cancellationChecker, envVars);
	    		*/
	            
	    		System.out.println( "\nJobSubmitStub::submitJavaUniverse JOB WELL... DONE!\n\n");

	    	}
	    	
	    	
	        public static String[] getHostnameAndIP() {
	            String hostname = null;
	            String ip = null;
	            try {
	                InetAddress ia = InetAddress.getLocalHost();
	                ip = ia.getHostAddress();
	                hostname = ia.getHostName();
	            } catch (Throwable ignore) {}
	            if (hostname == null) {
	                try {
	                    hostname = System.getenv("HOSTNAME");
	                    if (hostname != null && hostname.isEmpty())
	                        hostname = null;
	                } catch (Throwable ignore) {}
	            }
	            if (ip == null && hostname != null) {
	                try {
	                    ip = InetAddress.getByName(hostname).getHostAddress();
	                } catch (Throwable ignore) {}
	            }
	            return new String[] {hostname == null ? "unknown" : hostname,
	                    ip == null ? "unknown" : ip};
	        }	    	
	    	
	    	////////// Vanilla Universe methods //////////////////////////////////////////////
	    	public static void submitPrintTime( ) throws IOException {
	    		
            	// Execute task 0.1 in the Vanilla Universe: submit a test job that prints the current timestamp to an output file and exits

	    		System.out.println( "\nJobSubmitStub::submitPrintTime:\n");
	    		
	    		// XXX: Hardcoded path to the script to execute:
	    		String[] cmdScript = new String[]{"/bin/bash", "/home/submitter/submit/Spinning/scripts/myScript01.sh"};
	    		
	    		Process p = Runtime.getRuntime().exec(cmdScript);
	    		
                BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream() ));
                String line = reader.readLine();
                System.out.println( line );
                line = reader.readLine();
                while ( line != null ) {    
                    System.out.println( line );
                    line = reader.readLine();
                }	    		
	    		
	    	}
	    	
	    	public static void submitSleepWallclock( ) throws IOException {
	    		
            	// Execute task 0.2 in the Vanilla Universe:: submit a job similar to 1, but in an infinite loop, sleeping 1 second between prints. Set a wallclock limit of 10 minutes

	    		System.out.println( "\nJobSubmitStub::submitSleepWallclock:\n");
	    		
	    		// XXX: Hardcoded path to the script to execute:
	    		String[] cmdScript = new String[]{"/bin/bash", "/home/submitter/submit/Spinning/scripts/myScript02.sh"};
	    		
	    		Process p = Runtime.getRuntime().exec(cmdScript);
	    		
                BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream() ));
                String line = reader.readLine();
                System.out.println( line );
                line = reader.readLine();
                while ( line != null ) {    
                    System.out.println( line );
                    line = reader.readLine();
                }	    		
	    		
	    	}
	    	
	    	public static void submitInfiniteLoop( ) throws IOException {
	    		
            	// Execute task 0.3 in the Vanilla Universe:: same as 0.2, but with an unlimited wallclock. Kill this job using the api.

	    		System.out.println( "\nJobSubmitStub::submitHalfDozenJobs:\n");
	    		
	    		// XXX: Hardcoded path to the script to execute:
	    		String[] cmdScript = new String[]{"/bin/bash", "/home/submitter/submit/Spinning/scripts/myScript03.sh"};
	    		
	    		Process p = Runtime.getRuntime().exec(cmdScript);
	    		
                BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream() ));
                String line = reader.readLine();
                System.out.println( line );
                line = reader.readLine();
                while ( line != null ) {    
                    System.out.println( line );
                    line = reader.readLine();
                }	    			    		
	    		
	    		
	    		
	    	}
	    	
	    	public static void submitHalfDozenJobs( ) throws IOException {
	    		
            	// Execute task 0.4 in the Vanilla Universe:: submit 5 jobs using different user accounts, docker01 has a few already set up

	    		System.out.println( "\nJobSubmitStub::submitHalfDozenJobs:\n");
	    		
	    		// XXX: Hardcoded path to the script to execute:
	    		String[] cmdScript = new String[]{"/bin/bash", "/home/submitter/submit/Spinning/scripts/myScript04.sh"};
	    		
	    		Process p = Runtime.getRuntime().exec(cmdScript);
	    		
                BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream() ));
                String line = reader.readLine();
                System.out.println( line );
                line = reader.readLine();
                while ( line != null ) {    
                    System.out.println( line );
                    line = reader.readLine();
                }	    			    		
	    		
	    		
	    		
	    	}
	    	
	    	
	    	public static void executeTheCLIcommand(String cmd) {
	            System.out.println( "\nJobSubmitStub: executing command " + cmd + "\n" );        
	            try { // Execute command submitted as args                  
	                    Process p = Runtime.getRuntime().exec( cmd );
	                    // Could use the ProcessBuilder with more functionality
	                    
	                    BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream() ));
	                    String line = reader.readLine();
	                    System.out.println( line );
	     
	                    // p.waitFor();
	                    
	                    line = reader.readLine();

	                    while ( line != null ) {    
	                        System.out.println( line );
	                        line = reader.readLine();
	                    }
	                }
	                catch(IOException e1) {
	                	System.out.print( e1 );
	                }
	                finally{
	                	// reader.close();
	                }
	    		
	    	}
	    	/*
	    	public static void initTestJab( ) {

	    	}
	    	*/

	    	
	        
	    	
	        /*
	        public static void main(String[] arguments)
	                throws MalformedURLException, RemoteException, ServiceException
	        {
	               URL scheddLocation = new URL(arguments[0]);
	               String owner = arguments[1];
	               String dagFileLocation = arguments[2];

	                // Get a handle on a schedd we can make SOAP call on.
	                CondorScheddLocator scheddLocator = new CondorScheddLocator();
	                CondorScheddPortType schedd =
	                        scheddLocator.getcondorSchedd(scheddLocation);
	                
	                // Begin a transaction, allow for 60 seconds between calls
	                TransactionAndStatus transactionAndStatus = schedd.beginTransaction(60);
	                
	                Transaction transaction = transactionAndStatus.getTransaction();
	                // Get a new cluster for the DAG job.
	                IntAndStatus clusterIdAndStatus = schedd.newCluster(transaction);
	                int clusterId = clusterIdAndStatus.getInteger();
	                
	                // Get a new Job ID (aka a ProcId) for the DAG Job.
	                IntAndStatus jobIdAndStatus = schedd.newJob(transaction, clusterId);
	                int jobId = jobIdAndStatus.getInteger();
	                
	                // Build the DAG's ClassAd.
	                ClassAdStructAttr[] jobAd = buildJobAd(owner,
	                                             dagFileLocation,
	                                             clusterId,
	                                             jobId);
	                
	                // Submit the DAG's ClassAd.
	                schedd.submit(transaction, clusterId, jobId, jobAd);
	                
	                // Commit the transaction.
	                schedd.commitTransaction(transaction);

	                // Ask the Schedd to kick off the DAG immediately.
	                schedd.requestReschedule();
	        }
	        */
			// TODO: generate condor client library;
			//  SEE http://communitygrids.blogspot.com/2006/
			   /*
		        private static ClassAdStructAttr[] buildJobAd(String owner,
		                                                 String dagFileLocation,
		                                                 int clusterId,
		                                                 int jobId)
		        {
		                String dagOutputFileLocation = dagFileLocation + ".dagman.out";
		                String dagLogFileLocation = dagFileLocation + ".dagman.log";
		                String stdOutLocation = dagFileLocation + ".stdout";
		                String stdErrLocation = dagFileLocation + ".stderr";
		                String dagmanLockFile = dagFileLocation + ".lock";
		                String workingDirectory =
		                        dagFileLocation.substring(0, dagFileLocation.lastIndexOf("/"));
		                
		                ClassAdStructAttr[] jobAd =
		                {
		                 createStringAttribute("Owner", owner),
		                 createStringAttribute("Iwd", workingDirectory),
		                 createIntAttribute("JobUniverse", 7), // Scheduler Universe
		                 createStringAttribute("Cmd", "/usr/bin/condor_dagman"),
		                 createIntAttribute("JobStatus", 1), // Idle
		                 createStringAttribute("Env",
		                                       "_CONDOR_MAX_DAGMAN_LOG=0;" +
		                                       "_CONDOR_DAGMAN_LOG=" + dagOutputFileLocation),
		                 createIntAttribute("JobNotification", 0), // Never
		                 createStringAttribute("UserLog", dagLogFileLocation),
		                 createStringAttribute("RemoveKillSig", "SIGUSR1"),
		                 createStringAttribute("Out", stdOutLocation),
		                 createStringAttribute("Err", stdErrLocation),
		                 createStringAttribute("ShouldTransferFiles", "NO"), // Using shared FS
		                 createExpressionAttribute("Requirements", "TRUE"),
		                 createExpressionAttribute("OnExitRemove",
		                                           "(ExitSignal =?= 11 || " +
		                                           " (ExitCode =!= UNDEFINED && " +
		                                           "  ExitCode >=0 && ExitCode <= 2))"),
		                 createStringAttribute("Arguments",
		                                       "-f -l . -Debug 3 " +
		                                       "-AutoRescue 1 -DoRescueFrom 0 " +
		                                       "-Allowversionmismatch " + // Often safe
		                                       "-Lockfile " + dagmanLockFile + " " +
		                                       "-Dag " + dagFileLocation),
		                 createIntAttribute("ClusterId", clusterId),
		                 createIntAttribute("ProcId", jobId)};

		                return jobAd;
		        }

		        private static ClassAdStructAttr createStringAttribute(String name,
		                                                          String value)
		        {
		                return createAttribute(name, value, ClassAdAttrType.value3);
		        }

		        private static ClassAdStructAttr createIntAttribute(String name,
		                                                       int value)
		        {
		                return createAttribute(name,
		                             String.valueOf(value),
		                             ClassAdAttrType.value1);
		        }

		        private static ClassAdStructAttr createExpressionAttribute(String name,
		                                                              String value)
		        {
		                return createAttribute(name, value, ClassAdAttrType.value4);
		        }

		        private static ClassAdStructAttr createAttribute(String name,
		                                                    String value,
		                                                    ClassAdAttrType type)
		        {
		                ClassAdStructAttr attribute = new ClassAdStructAttr();
		                attribute.setName(name);
		                attribute.setValue(value);
		                attribute.setType(type);
		                
		                return attribute;
		        }
		        
		        */
			
	        
	        
	} // class SpinningAPImainClass
