package org.joedog.trumprhoids.sprite;

public class Photon extends Actor {

  public Photon(int x, int y, int direction) {
    super("/org/joedog/trumprhoids/images/photon.png");
    this.setName("Photon");
    this.setType(PHOTON);
    this.setSpeed(3);
    this.setDirection(direction);
    this.setLocation(x, y);
  }

  public void move() {
    int x = this.location.getX();
    int y = this.location.getY();
    x +=  Math.cos(this.direction.get()) * this.speed  + Math.sin(this.direction.get()) * this.strafe;
    y -= -Math.cos(this.direction.get()) * this.strafe + Math.sin(this.direction.get()) * this.speed;
    this.setLocation(x, y);
  }
}
