package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class Strategy {
    public void addTask(List<Server> servers, Task t){
        int minWaitingPeriod = 999999;
        int iteration = 0;
        int index_final = 0;
        for (Server i:servers) {
            if(i.getWaitingPeriod().get() < minWaitingPeriod){
                minWaitingPeriod = i.getWaitingPeriod().get();
                index_final = iteration;
            }
            iteration++;
        }
        servers.get(index_final).addTask(t);
    }
}