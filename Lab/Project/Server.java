
/*
 * File name: Server.java
 * Author: Aayan Shah
 * Last modified : 22nd oct
 * 
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class Server {
    private double sysTime;
    protected double remainingTime;
    private LinkedList<Job> jobQueue;
    private int jobsProcessed;
    private double waitingTime = 0;

    // initialize all fields
    public Server() {
        sysTime = 0;
        remainingTime = 0;
        waitingTime = 0;
        jobsProcessed = 0;
        jobQueue = new LinkedList<Job>();
    }

    // adding job using queue offer method
    public void addJob(Job job) {
        jobQueue.offer(job);
        remainingTime += job.getProcessingTimeNeeded();
    }

    // process jobs until system reaches the time
    public void processTo(double time) {
        double timeLeft = time - sysTime;

        while (timeLeft > 0 && !jobQueue.isEmpty()) {
            // checking minimum to avoid overflow
            double runTime = Math.min(jobQueue.peek().getProcessingTimeRemaining(), timeLeft);

            Job oneJob = jobQueue.peek();

            oneJob.process(runTime, sysTime);
            if (oneJob.isFinished()) {
                jobQueue.poll(); // when job is finished, remove it
                jobsProcessed++;// increemnt the counter
                waitingTime = waitingTime + oneJob.timeInQueue();// increment waiting Time
            }

            // modify metrics accordingly
            timeLeft -= runTime;

            sysTime += runTime;
        }
        // checking after while loop to avoid timeleak
        if (jobQueue.isEmpty())
            sysTime = time;

    }

    // also modify the remainingTime field to access it quicker
    public double remainingWorkInQueue() {
        double t = 0;
        for (Job aJob : jobQueue) {
            t += aJob.getProcessingTimeRemaining();
        }
        remainingTime = t;
        return t;
    }

    // returns size of queue
    public int size() {

        return jobQueue.size();
    }

    public void draw(Graphics g, Color c, double loc, int numberOfServers) {
        double sep = (ServerFarmViz.HEIGHT - 20) / (numberOfServers + 2.0);
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(),
                (int) (72.0 * (sep * .5) / Toolkit.getDefaultToolkit().getScreenResolution())));
        g.drawString("Work: " + (remainingWorkInQueue() < 1000 ? remainingWorkInQueue() : ">1000"), 2,
                (int) (loc + .2 * sep));
        g.drawString("Jobs: " + (size() < 1000 ? size() : ">1000"), 5, (int) (loc + .55 * sep));
        g.setColor(c);
        g.fillRect((int) (3 * sep), (int) loc, (int) (.8 * remainingWorkInQueue()), (int) sep);
        g.drawOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
        if (remainingWorkInQueue() == 0)
            g.setColor(Color.GREEN.darker());
        else
            g.setColor(Color.RED.darker());
        g.fillOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
    }
}
