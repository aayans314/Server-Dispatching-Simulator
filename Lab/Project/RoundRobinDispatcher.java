/*
 * File name: RoundRobinDispatcher.java
 * Author: Aayan Shah
 * Last modified : 23rd oct
 * 
 */
public class RoundRobinDispatcher extends JobDispatcher {
    private int count =0;

    public RoundRobinDispatcher(int k, boolean showViz){
        super(k,showViz);
    }

    @Override
    public Server pickServer(Job j) {

        //my implementation of a round robin system
        if(count>=serversList.size()){
            count =0;
        }

        Server workServer = serversList.get(count);
        count++;
        return workServer;

    }

    
}
