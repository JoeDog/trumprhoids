package org.joedog.trumprhoids.sprite;

public class Asteroid extends Actor {

  public Asteroid() {
    super("/org/joedog/trumprhoids/images/asteroid.png");
    this.setName("Asteroid");
    this.setType(ASTEROID);
  }

  public void move() {
    int x = this.location.getX();
    int y = this.location.getY();
    x +=  Math.cos(this.direction.get()) * this.speed  + Math.sin(this.direction.get()) * this.strafe;
    y -= -Math.cos(this.direction.get()) * this.strafe + Math.sin(this.direction.get()) * this.speed;
    this.setLocation(x, y);
  }
}
