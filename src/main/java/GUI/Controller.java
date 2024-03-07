package GUI;

import BusinessLogic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    public View view ;
    SimulationManager manager = new SimulationManager();
    public Controller(View view){
        this.view = view;
        view.addVerificaStartButton(new VerificaStart());
        view.addVerificaShowButton(new VerificaShow());
    }

    class VerificaStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                manager.setTimeLimit(Integer.parseInt(view.getTextField1().getText()));
                manager.setMaxProcessingTime(Integer.parseInt(view.getTextField2().getText()));
                manager.setMinProcessingTime(Integer.parseInt(view.getTextField3().getText()));
                manager.setMaxArrivalTime(Integer.parseInt(view.getTextField4().getText()));
                manager.setMinArrivalTime(Integer.parseInt(view.getTextField5().getText()));
                manager.setNumberOfServers(Integer.parseInt(view.getTextField6().getText()));
                manager.setNumberofClients(Integer.parseInt(view.getTextField7().getText()));

                Thread t = new Thread(manager);

                view.getTextArea1().setText(manager.stringBuilder.toString());

                t.start();
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    class VerificaShow implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                view.getTextArea1().setText(manager.stringBuilder.toString());
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
}
