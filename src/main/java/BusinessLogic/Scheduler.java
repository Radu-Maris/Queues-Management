package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers = new ArrayList<Server>();
    private Strategy strategy = new Strategy();

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        for (int i=0;i<maxNoServers;i++) {
            Server server = new Server();
            Thread thred = new Thread(server);
            servers.add(server);
            thred.start();
        }
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }
    public List<Server> getServers(){
        return servers;
    }
}
