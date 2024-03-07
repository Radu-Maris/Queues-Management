package Model;

public class Task implements Comparable{
    private int arrivalTime;
    private int serviceTime;
    private int taskId;

    public Task(int arrivalTime, int serviceTime, int taskId) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.taskId = taskId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public int compareTo(Object o) {

        if(this.arrivalTime<((Task)o).getArrivalTime()){
            return -1;
        }
        else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "(" + arrivalTime + "," + serviceTime + "," + taskId + ')';
    }
}
