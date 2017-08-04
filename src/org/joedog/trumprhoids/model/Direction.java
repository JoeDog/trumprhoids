package org.joedog.trumprhoids.model;

public class Direction {
  public static final int NORTH       = 90;
  public static final int NORTHEAST   = 315;
  public static final int EAST        = 120;
  public static final int SOUTHEAST   = 270;
  public static final int SOUTH       = 320;
  public static final int SOUTHWEST   = 30;
  public static final int WEST        = 180;
  public static final int NORTHWEST   = 135;
  public static final int RIGHT       = 90;
  public static final int LEFT        = -90;
  public static final int HALF_RIGHT  = 45;
  public static final int HALF_LEFT   = -45;
  public static final int FULL_CIRCLE = 360;
  public static final int FORWARD     = 0;

  private int  size   = 0;
  private int  index  = 0;
  private int  list[] = new int[] {
    NORTH, NORTHEAST, EAST, SOUTHEAST,
    SOUTH, SOUTHWEST, WEST, NORTHWEST
  };

  public Direction() {
  }

  public Direction(int direction) {
    this.set(direction);
  }

  public int get() {
    return this.list[index];
  }

  public void set(int location) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == location) {
        this.index = i;
      }
    }
  }

  public void increment() {
    if (this.index == list.length-1) {
      index = 0;
    } else {
      index++;
    }
  }

  public void decrement() {
    if (index == 0) {
      this.index = list.length-1;
    } else {
      index--;
    }
  }

  public int getSize(){
    return size;
  }
}
