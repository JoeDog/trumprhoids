package org.joedog.trumprhoids.view;

import java.awt.Color;
import java.awt.Graphics2D; 
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import java.net.URL;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import org.joedog.trumprhoids.control.*;
import org.joedog.trumprhoids.sprite.*;

public class View extends JPanel implements Viewable {
  private Game          control = null; 
  private BufferedImage stars   = null;
  private BufferedImage clone   = null;
  private Spaceship     ship    = new Spaceship();

  public View(Game control) {
    this.control = control;
    this.stars   = loadImage("/org/joedog/trumprhoids/images/stars.jpg");
    this.clone   = copyImage(this.stars);
    this.setBackground(Color.BLACK);
  }

  public void action() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        repaint();
      }
    });
  }
 
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.drawImage(this.stars, 0, 0, this);
    ArrayList<Actor> actors = (ArrayList<Actor>)control.getModelProperty("Actors");
    if (actors != null) { // assume nothing
      Iterator<Actor> itr = actors.iterator();
      while (itr.hasNext()) {
        Actor actor = itr.next();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (actor.getType() == Actor.SPACESHIP) {
          BufferedImage img = rotate(actor.getImage(), actor.getAngle());
          g2.drawImage(img, actor.getX(), actor.getY(), this);
        } else {
          g2.drawImage(actor.getImage(), actor.getX(), actor.getY(), this);
          System.out.printf("Asteroid: %d x %d\n", actor.getX(), actor.getY());
          actor.setLocation(actor.getX(), actor.getY()+1);
        }
      }
    }
  }

  public BufferedImage rotate(BufferedImage image, double angle) {
    double sin = Math.abs(Math.sin(Math.toRadians(angle)));
    double cos = Math.abs(Math.cos(Math.toRadians(angle)));

    int w = image.getWidth();
    int h = image.getHeight();

    int neww = (int)Math.floor(w*cos+h*sin);
    int newh = (int)Math.floor(h*cos+w*sin);

    BufferedImage dimg = new BufferedImage(neww, newh, image.getType());
    Graphics2D g = dimg.createGraphics();
    g.translate((neww-w)/2, (newh-h)/2);
    g.rotate(Math.toRadians(angle), w/2, h/2);
    g.drawRenderedImage(image, null);
    g.dispose();
    return dimg;
  }

  private static BufferedImage copyImage(BufferedImage image) {
    ColorModel model = image.getColorModel();
    return new BufferedImage(model, image.copyData(null), model.isAlphaPremultiplied(), null);
  }

  private BufferedImage loadImage(String path) {
    BufferedImage img = null;
    URL url = getClass().getResource(path);
    try {
      img = ImageIO.read(url);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }

  private static GraphicsConfiguration getDefaultConfiguration() {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    return gd.getDefaultConfiguration();
  }

  public void modelPropertyChange(PropertyChangeEvent e) {
    if (e.getNewValue() == null) return;
  }
}


