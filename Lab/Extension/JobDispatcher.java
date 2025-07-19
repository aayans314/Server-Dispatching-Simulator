/*
 * File name: JobDispatcher.java
 * Author: Aayan Shah
 * Last modified : 23rd oct
 * 
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public abstract class JobDispatcher {
    protected ArrayList<Server> serversList = new ArrayList<>();
    private double systemTime;
    private ServerFarmViz serverFarmViz;
    private LinkedList<Job> jobsHandled;

    // add k servers to our serverlist
    public JobDispatcher(int k, boolean showViz) {
        for (int i = 0; i < k; i++) {
            serversList.add(new Server());
        }
        systemTime = 0;
        jobsHandled = new LinkedList<>();
        serverFarmViz = new ServerFarmViz(this, showViz);
    }

    // return systemtime
    public double getTime() {
        return systemTime;
    }

    /**
     * @return serverlist
     */
    public ArrayList<Server> getServerList() {
        return serversList;

    }

    // process all of the servers to the given time and update
    public void advanceTimeTo(double time) {

        for (Server aServer : serversList) {
            aServer.processTo(time);
        }
        systemTime = time;
    }

    // add job to handled list, pick right server from JobDispatcher>pickServer()
    // and add the job
    public void handleJob(Job job) {

        jobsHandled.add(job);
        this.advanceTimeTo(job.getArrivalTime());
        serverFarmViz.repaint();
        Server pickedServer = pickServer(job);
        pickedServer.addJob(job);
        serverFarmViz.repaint();

    }

    // advance to time when processing all job in all servers is complete

    public void finishUp() {
        double finalTime = 0;
        for (Server server : serversList) {
            finalTime = Math.max(finalTime, server.remainingWorkInQueue());
        }
        this.advanceTimeTo(finalTime + systemTime);
    }

    /**
     * 
     * @return jobsHandled.size
     */
    public int getNumJobsHandled() {
        return jobsHandled.size();
    }

    /**
     * 
     * @return average waiting time for all jobs
     */
    public double getAverageWaitingTime() {
        double requiredtime = 0;
        for (Job testjob : jobsHandled) {
            requiredtime += testjob.timeInQueue();
        }
        return requiredtime / jobsHandled.size();
    }

    // implemented in individual dispatchers
    public abstract Server pickServer(Job j);

    public void draw(Graphics g) {
        double sep = (ServerFarmViz.HEIGHT - 20) / (getServerList().size() + 2.0);
        g.drawString("Time: " + getTime(), (int) sep, ServerFarmViz.HEIGHT - 20);
        g.drawString("Jobs handled: " + getNumJobsHandled(), (int) sep, ServerFarmViz.HEIGHT - 10);
        for (int i = 0; i < getServerList().size(); i++) {
            ((Server) getServerList().get(i)).draw(g, (i % 2 == 0) ? Color.GRAY : Color.DARK_GRAY, (i + 1) * sep,
                    getServerList().size());
        }
    }
}