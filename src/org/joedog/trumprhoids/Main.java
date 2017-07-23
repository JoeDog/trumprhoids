package org.joedog.trumprhoids;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.joedog.trumprhoids.view.*;
import org.joedog.trumprhoids.control.*;

public class Main  extends JPanel implements KeyListener {
  private static final  long serialVersionUID = -2666347118493388423L;
  private static Game   control = null;
  private static Engine engine  = null;
  private static View   view    = null;

  public Main(View panel) {
    super(new BorderLayout());
    panel.addKeyListener(this);
  }

  private static void createAndShowGui(View view) {
    final JFrame frame = new JFrame(org.joedog.trumprhoids.Version.name);
    JComponent   panel = new Main(view);
    panel.add(view, BorderLayout.CENTER);
    view.setFocusable(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(panel);
    frame.setPreferredSize(new Dimension(900, 600));
    frame.setSize(900, 600);
    frame.pack();
    frame.setLocation(10, 10);
    frame.setVisible(true);
  }

  public void keyPressed(KeyEvent e) { }
  public void keyReleased(KeyEvent e) { }
  public void keyTyped(KeyEvent e) {
    System.out.println(e.getKeyChar());  
    if (e.getKeyChar() == 'j') {
      control.rotate(-3.25);
    }
    if (e.getKeyChar() == 'l') {
      control.rotate(3.25);
    }
  }
 
  public static void main(String [] args) {
    if (control == null) {
      control = new Game();
    }

    if (view == null) {
      view = new View(control);
    }

    if (engine == null) {
      engine = new Engine(view);
    }

    control.setEngine(engine);
    control.addView(view);

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGui(view);
      }
    });

    while (true) {
      GameThread thread = new GameThread(control);
      control.addThread(thread);
      thread.start();
      while (thread.isAlive()) ;
    }
  }
}
