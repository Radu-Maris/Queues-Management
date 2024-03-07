package Model;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private Thread thread;
    private boolean isRunning = true;

    public Server(){
        tasks = new ArrayBlockingQueue<Task>(100);
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public void run(){
        while(isRunning){
            try {
                if(tasks.size()>0) {
                    int sleep = tasks.peek().getServiceTime();
                    waitingPeriod.set(sleep);
                        tasks.peek().setServiceTime(waitingPeriod.intValue());
                        Thread.sleep(waitingPeriod.intValue()*100L);                                  //nu uita sa modifici la 1000
                    waitingPeriod.set(waitingPeriod.intValue()-tasks.peek().getServiceTime());
                    tasks.take();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Task i: tasks){
            stringBuilder.append(i.toString());
        }

        return stringBuilder.toString();
    }
}
