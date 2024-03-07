package org.example;
import GUI.Controller;
import GUI.View;
public class App 
{
    public static void main( String[] args ) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setVisible(true);
    }
}
