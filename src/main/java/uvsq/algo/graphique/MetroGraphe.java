package uvsq.algo.graphique;

import uvsq.algo.Graphe;

import uvsq.algo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MetroGraphe extends JPanel implements MouseListener {

    private int cmp = 0 ;
    private String stationDepart ;
    private String stationArrivee ;
    private String itineraire ;
    private JButton button ;
    Graphe graph ;

    public MetroGraphe() {
        //setLayout(new GridLayout(3, 3));
        setSize(800, 800);
        graph = Itineraire.buildGraph();
        addButtons();
        //setOpaque(false);

    }


    public void addButtons()
    {
        String nomPrev ;
        for(Station s : graph.Sommets)
        {
            button = new JButton(s.Nom);
            add(button);
            button.addMouseListener(this);
        }
    }
   /* @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        Point p1 = button1.getLocation();
        p1.x += button1.getWidth() / 2;
        p1.y += button1.getHeight() / 2;
        Point p2 = button4.getLuvsq/algo/Graphe.java:200ocation();
        p2.x += button4.getWidth() / 2;
        p2.y += button4.getHeight() / 2;
        g.drawLine(p1.x, p1.y, p2.x, p2.y);

        Point p3 = button2.getLocation();
        p3.x += button2.getWidth() / 2;
        p3.y += button2.getHeight() / 2;
        Point p4 = button3.getLocation();
        p4.x += button3.getWidth() / 2;
        p4.y += button3.getHeight() / 2;
        g.setColor(Color.BLUE);
        g.drawLine(p3.x, p3.y, p4.x, p4.y);
    }*/
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        JButton myButton = (JButton)mouseEvent.getSource();
        if((cmp%2 == 0))
        {
            this.stationDepart =  myButton.getText();
            cmp++;
            System.out.println("First button clicked , cmp :" + cmp);

        }
        else if (cmp%2 == 1) {
            this.stationArrivee = myButton.getText();
            cmp++;
            itineraire = Itineraire.getItineraire(stationDepart, stationArrivee, 1);
            System.out.println("Second button clicked , cmp : " + cmp);
            JOptionPane.showMessageDialog(null, itineraire);
            System.out.println(itineraire);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
