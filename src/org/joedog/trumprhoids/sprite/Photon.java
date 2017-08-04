package org.joedog.trumprhoids.sprite;

public class Photon extends Actor {
  private final int MAX_SPEED = 4;
  private final double ACCEL_FACTOR = 0.01;
  private long  start; 

  public Photon(int x, int y, int direction) {
    super("/org/joedog/trumprhoids/images/photon.png");
    this.setName("Photon");
    this.setType(PHOTON);
    this.setSpeed(4);
    this.setDirection(direction);
    this.setLocation(x, y);
    this.start = System.currentTimeMillis();
  }

  public void move() {
    long end   = System.currentTimeMillis();
    long delta = end - start;
    double timeElapsed = delta / 1000.0;
    float dx = (float)this.location.getX(); 
    float dy = (float)this.location.getY();
    dx = (float) Math.cos(Math.toRadians(this.direction.get()));
    dy = (float) Math.sin(Math.toRadians(this.direction.get()));
    dx = dx / 2;
    dy = dy / 2;
    //if (direction.length() > 0) {
      //direction = direction.normalise();
      // NORMALIZE!!
    //}
    float vx = dx * this.speed;
    float vy = dy * this.speed;
    float px = (float)this.location.getX() + (float)(vx * timeElapsed);
    float py = (float)this.location.getY() + (float)(vy * timeElapsed);
    this.setLocation(Math.round(px), Math.round(py));
  }

  /*public void move() {
    int x = this.location.getX();
    int y = this.location.getY();
    x +=  Math.cos(Math.toRadians(this.direction.get())) * this.speed  + Math.sin(Math.toRadians(this.direction.get())) * this.strafe;
    y -= -Math.cos(Math.toRadians(this.direction.get())) * this.strafe + Math.sin(Math.toRadians(this.direction.get())) * this.speed;
    this.setLocation(x, y);
  } */
}
