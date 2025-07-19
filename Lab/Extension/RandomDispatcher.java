/*
 * File name: RandomDispatcher.java
 * Author: Aayan Shah
 * Last modified : 23rd oct
 * 
 */

import java.util.Random;

public class RandomDispatcher extends JobDispatcher {

    public RandomDispatcher(int k, boolean showViz) {
        super(k, showViz);

    }

    // randomly selects A Server from the list
    @Override
    public Server pickServer(Job j) {
        Random ran = new Random();
        int ranser = ran.nextInt(serversList.size());
        return serversList.get(ranser);
    }

}
