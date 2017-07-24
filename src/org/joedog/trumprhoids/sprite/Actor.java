package org.joedog.trumprhoids.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.net.URL;
import java.io.IOException;

import javax.swing.JPanel;
import javax.imageio.ImageIO;


import org.joedog.trumprhoids.model.Arena;
import org.joedog.trumprhoids.model.Location;

public class Actor {
  public static final int SPACESHIP = 0;
  public static final int ASTEROID  = 1;
  public static final int TRUMP     = 2;
  protected int      width; 
  protected int      height;
  private   int      dw;
  private   int      dh;
  private   int      ypad;
  private   int      type;
  private   String   name       = null;
  private   String   url        = null;
  private   Arena    arena      = null; 
  private   Location location   = new Location(0, 0);
  private   int      direction  = Location.SOUTH;
  private   int      speed      = 1;
  private   int      strafe     = 1;
  private   double   angle      = 0.0;
  private   BufferedImage image = null;

  public Actor() {

  }

  public Actor(String url) {
    this.url    = url;
    this.image  = loadImage(this.url);
    this.width  = this.image.getWidth();
    this.height = this.image.getHeight();
    this.dw     = this.width;
    this.dh     = this.height;
  }

  public Actor(int width, int height) {
    this.width  = width;
    this.dw     = width;
    this.height = height;
    this.dh     = height;
  }

  public void move() {
    int x = this.location.getX();
    int y = this.location.getY();
    x +=  Math.cos(this.direction) * this.speed  + Math.sin(this.direction) * this.strafe;
    y -= -Math.cos(this.direction) * this.strafe + Math.sin(this.direction) * this.speed;
    this.setLocation(x, y);
  }

  public void rotate(double angle) {
    double tmp = this.angle;
    this.angle += angle;
    if (this.angle > 360) {
      this.angle = 0.0;  
    }
    if (this.angle < 0) {
      this.angle += (360+this.angle);
    }
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public double getDirection() {
    return this.direction;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public double getAngle() {
    return this.angle;
  }

  public void setType(int type) {
    this.type   = type;
  }

  public int getType() {
    return this.type;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setImageUrl(String url) {
    this.url   = url;
    this.image = loadImage(this.url);
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void resetWidth() {
    this.width = this.dw;
  }

  public int getWidth() {
    return this.width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void resetHeight() {
    this.height = dh;
  }

  public int getHeight() {
    return this.height;
  }

  public Location getLocation() {
    return this.location;
  }

  public int getX() {
    return this.location.getX();
  }

  public int getY() {
    return this.location.getY();
  }

  public synchronized void setX(int x) {
    this.location.setX(x);
  }

  public synchronized void setY(int y) {
    this.location.setY(y);
  }

  public synchronized void setLocation(int x, int y) {
    this.location.setX(x);
    this.location.setY(y);
  }

  public synchronized void setLocation(Location location) {
    if (location == null) {
      // WTF?
      return;
    }
    this.location.setX(location.getX());
    this.location.setY(location.getY());
  }

  public BufferedImage getImage() {
    return this.image;
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
}
