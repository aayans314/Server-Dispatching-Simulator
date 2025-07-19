/*
 * 
 * Filename: LeastWorkDispatcher.java
 * Author: Aayan Shaj
 * Last modified : 23rd oct
 */

public class LeastWorkDispatcher extends JobDispatcher {
    public LeastWorkDispatcher(int k, boolean show) {
        super(k, show);
    }

    @Override
    public Server pickServer(Job j) {
        Server test = serversList.get(0);
        // just trying to get one with least remainingTime
        for (Server serv : serversList) {
            if (serv.remainingTime < test.remainingTime) {
                test = serv;
            }
        }
        return test;
    }

}
