/*
file name:      ServerFarmSimulation.java
Authors:        Ike Lage
last modified:  03/07/2024
*/

public class ServerFarmSimulation {

    public static void main(String[] args) {

        // You can explore how these change your results if you want!
        // How often a new job arrives at the server farm, on average
        int meanArrivalTime = 5;
        // How long a job takes to process, on average
        int meanProcessingTime = 100;

        // Debugging settings
        int numServers = 34; // Numbers of servers in the farm
        int numJobs = 10000000; // Number of jobs to process
        boolean showViz = false; // Set to true to see the visualization, and false to run your experiments
        // to speed up the display, you can decrease the sleep time in the ServerFarmViz class.

        // Main experiment settings
        /**
         * int numServers = 34 ; //Numbers of servers in the farm
         * int numJobs = 10000000 ; //Number of jobs to process
         * boolean showViz = false ; //Set to true to see the visualization, and false
         * to run your experiments
         */


         
        System.out.println("mean processing time: "+meanProcessingTime);
        System.out.println("mean arrival time: "+meanArrivalTime);
          for(int j=15;j<25;j++){ //Edit here to show range of servers sizes
          String dispatcherType="shortest";
            numServers=(j+1);
         

        // Initialize the job maker with the mean arrival and processing time
        JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);

        // Create a dispatcher of the appropriate type
        
        JobDispatcher dispatcher = null;
        if (dispatcherType == "random") {
            dispatcher = new RandomDispatcher(numServers, showViz);
        } else if (dispatcherType == "round") {
            dispatcher = new RoundRobinDispatcher(numServers, showViz);
        } else if (dispatcherType == "shortest") {
            dispatcher = new ShortestQueueDispatcher(numServers, showViz);
        } else if (dispatcherType == "least") {
            dispatcher = new LeastWorkDispatcher(numServers, showViz);
        }

        // Have the dispatched handle the specified number of jobs
       
        for (int i = 0; i < numJobs; i++) {            
            dispatcher.handleJob(jobMaker.getMyJob());// Swap between getNextJob() getMyJob() for my result or actual result
        }
        dispatcher.finishUp(); // Finish all of the remaining jobs in Server queues

        // Print out the mean processing time
        System.out.println("Servers: "+numServers+" Dispatcher: " + dispatcherType + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());
    }
    }

    public static String shuffle(int i){
        if (i==0) return "random";
        else if(i==1) return "round";
        else if(i==2) return "least";
        else return "shortest";   


    }
}