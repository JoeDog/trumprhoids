package org.joedog.trumprhoids.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.net.URL;
import java.io.IOException;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

import org.joedog.trumprhoids.model.Location;
import org.joedog.trumprhoids.model.Direction;

public abstract class Actor {
  public static final int SPACESHIP = 0;
  public static final int ASTEROID  = 1;
  public static final int TRUMP     = 2;
  public static final int PHOTON    = 3;
  protected int       width; 
  protected int       height;
  private   int       dw;
  private   int       dh;
  private   int       ypad;
  private   int       type;
  private   String    name       = null;
  private   String    url        = null;
  protected Location  location   = new Location(0, 0);
  protected Direction direction  = new Direction(Direction.NORTH);
  protected int       speed      = 1;
  protected int       strafe     = 1;
  protected double    angle      = 0.0;
  private   BufferedImage image  = null;

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

  public void rotate(int direction) {
    if (direction == Direction.RIGHT) {
      this.direction.increment();
    }
    if (direction == Direction.LEFT) {
      this.direction.decrement();
    }
  }

  public void setDirection(int direction) {
    this.direction.set(direction);
  }

  public int getDirection() {
    return this.direction.get();
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

  public void setSpeed(int speed) {
    this.speed  = speed;
    this.strafe = speed;
  }

  public void setSpeed(int speed, int strafe) {
    this.speed  = speed;
    this.strafe = strafe;
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

  /*public BufferedImage getImage() {
    return this.image;
  }*/

  public BufferedImage getImage() {
    int w = this.image.getWidth();
    int h = this.image.getHeight();
    int d = 0;
    BufferedImage newImage = new BufferedImage(width, height, this.image.getType());
    Graphics2D g2 = newImage.createGraphics();
    switch (this.direction.get()) {
      case Direction.NORTH:
        d = 0;
        break;
      case Direction.NORTHEAST:
        d = 45;
        break;
      case Direction.EAST:
        d = 90;
        break;
      case Direction.SOUTHEAST:
        d = 135;
        break;
      case Direction.SOUTH:
        d = 180;
        break;
      case Direction.SOUTHWEST:
        d = 225;
        break;
      case Direction.WEST:
        d = 270;
        break;
      case Direction.NORTHWEST:
        d = 315;
        break;
    }
    g2.rotate(Math.toRadians(d), w/2, h/2);
    g2.drawImage(this.image,null,0,0);
    return newImage;
  }

  public abstract void move();

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
