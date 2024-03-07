package BusinessLogic;

import Model.Server;
import Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulationManager implements Runnable{
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers;
    public int numberofClients;
    public StringBuilder stringBuilder = new StringBuilder();
    public ArrayList<Task> listaTask = new ArrayList<Task>();
    public ArrayList<Task> listaToRemove = new ArrayList<Task>();
    private Scheduler scheduler;

    private FileWriter fileWriter;
    public SimulationManager(){
        try{
            fileWriter = new FileWriter("Maris_Radu_Ioan_log.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNRandomTasks(){
        Task task;
        Random r = new Random();
        for(int i=0;i<numberofClients;i++){
            int randomProcessingTime = r.nextInt(maxProcessingTime-minProcessingTime) + minProcessingTime;
            int randomArrivalTime = r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            task = new Task(randomArrivalTime,randomProcessingTime,i);
            listaTask.add(i,task);
        }
        Collections.sort(listaTask);
    }
    @Override
    public void run() {
        int currentTime = 0;
        generateNRandomTasks();
        scheduler = new Scheduler(numberOfServers,10);

        while(currentTime < timeLimit){
            for(int i=0;i<listaTask.size();i++) {
                if (currentTime==listaTask.get(i).getArrivalTime()){
                    scheduler.dispatchTask(listaTask.get(i));
                    listaToRemove.add(listaTask.get(i));
                }
            }
            listaTask.removeAll(listaToRemove);


            stringBuilder.append("Time : " + currentTime + "\n");
            stringBuilder.append("Waiting list:" + listaTask.toString() + "\n");
            int nrOfQueue=0;
            for (Server server: scheduler.getServers()){
                nrOfQueue++;
                stringBuilder.append("Queue" + nrOfQueue +": ");
                stringBuilder.append(server.toString());
                stringBuilder.append("\n");
            }
            currentTime++;
            try {
                Thread.sleep(100);  //change to 1000 for 1 sec
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try{
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finished");
        for (Server i: scheduler.getServers()){
            i.setRunning(false);
        }
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public int getNumberofClients() {
        return numberofClients;
    }

    public void setNumberofClients(int numberofClients) {
        this.numberofClients = numberofClients;
    }
}
