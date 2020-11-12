package ProyectoU2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Ventana extends JPanel implements MouseListener {

    // ventana
    private JFrame ventana;
    // contenedor
    private Container contenedor;

    // declarar la figura
    private Punto[] figura;

    public Ventana(String titulo, Punto[] figura) {
        // inicializar la ventana
        ventana = new JFrame("");
        // definir tamaño a ventana
        ventana.setSize(800, 600);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        contenedor = ventana.getContentPane();
        contenedor.setSize(800, 600);
        // agregar la ventana en el contenedor
        contenedor.add(this, BorderLayout.CENTER);

        // definiendo la figura
        this.figura = figura;
        ventana.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        //dibujar figura 
        dibujar(g);

//        //escalamiento
//        g.setColor(Color.MAGENTA);
//        this.escalar(0.5, 0.5);
//        this.dibujar(g);
//        //traslacion
//        g.setColor(Color.RED);
//        this.traslacion(200, 0);
//        this.dibujar(g);        
//        
//        //rotacion(+)
//        g.setColor(Color.RED);
//        this.rotacionP(60);
//        this.dibujar(g);
//        
//        //rotacion(-)
//        g.setColor(Color.ORANGE);
//        this.rotacionN(40);
//        this.dibujar(g);
//        
//        //reflexion(x)
//        g.setColor(Color.BLUE);
//        this.reflexionX();
//        traslacion(300, 200);
//        escalar(0.2f, 0.2f);
//        this.dibujar(g);
        //reflexion(y)
//        g.setColor(Color.DARK_GRAY);
//        this.reflexionY();        
//        escalar(0.1f, 0.1f);
//        this.dibujar(g);
//        
//        //reflexion(x,y)
//        g.setColor(Color.MAGENTA);
//        this.reflexionXY();
//        escalar(.3f, .3f);
//        this.dibujar(g);
    }

    private void escalar(double fx, double fy) {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            //nueva x: ((a - tx) * fx) + tx
            //nueva y: ((a - ty) * fy) + ty
            punto.SetX((int) ((punto.GetX() - tx) * fx + tx));
            punto.SetY((int) ((punto.GetY() - ty) * fy + ty));
        }
    }

    private void traslacion(double xf, double yf) {
        for (Punto punto : figura) {
            punto.SetX((int) (punto.GetX() + xf));
            punto.SetY((int) (punto.GetY() + yf));
        }
    }

    private void rotacionP(double angulo) {
        int tx = figura[0].GetX(),
                ty = figura[0].GetY();
        for (Punto punto : figura) {
            //punto x: ()
            punto.SetX((int) ((punto.GetX() - tx) * Math.cos(Math.toRadians(angulo)) - (punto.GetY() - ty)
                    * Math.sin(Math.toRadians(angulo)) + tx));
            //punto y
            punto.SetY((int) ((punto.GetX() - ty) * Math.sin(Math.toRadians(angulo)) + (punto.GetY() - ty)
                    * Math.cos(Math.toRadians(angulo)) + ty));
        }
    }

    private void rotacionN(double angulo) {
        int tx = figura[0].GetX(),
                ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX((int) ((punto.GetX() - tx) * Math.cos(Math.toRadians(angulo)) + (punto.GetY() - ty)
                    * Math.sin(Math.toRadians(angulo)) + tx));
            punto.SetY((int) ((-1) * (punto.GetX() - tx) * Math.sin(Math.toRadians(angulo)) + (punto.GetY() - ty)
                    * Math.cos(Math.toRadians(angulo)) + ty));
        }
    }

    private void reflexionX() {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX(-(punto.GetX() - tx) + tx);
            punto.SetY((punto.GetY() - ty) + ty);
        }
    }

    private void reflexionY() {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX((punto.GetX() - tx) + tx);
            punto.SetY(-(punto.GetY() - ty) + ty);
        }
    }

    private void reflexionXY() {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX(-(punto.GetX() - tx) + tx);
            punto.SetY(-(punto.GetY() - ty) + ty);
        }
    }

    private void dibujar(Graphics g) {
        for (int i = 0; i < figura.length - 1; i++) {
            g.drawLine(figura[i].GetX(),
                    figura[i].GetY(),
                    figura[i + 1].GetX(),
                    figura[i + 1].GetY());
        }
    }

    private int x;
    private int y;
    @Override
    public void mouseClicked(MouseEvent me) {
        Graphics g = contenedor.getGraphics();        
        if (me.getButton() == 1) {            
            x = Integer.parseInt(JOptionPane.showInputDialog("Valor de x"));
            y = Integer.parseInt(JOptionPane.showInputDialog("Valor de y"));
            traslacion(x, y);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        rotacionP(Math.random() * 100);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        System.out.println("Released");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println("Estas dentro");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //System.exit(0);
    }
}
