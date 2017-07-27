package org.joedog.trumprhoids.model;

public class Direction {
  private int  size   = 0;
  private int  index  = 0;
  private int  list[] = new int[] {
    Location.NORTH, Location.NORTHEAST, Location.EAST, Location.SOUTHEAST, 
    Location.SOUTH, Location.SOUTHWEST, Location.WEST, Location.NORTHWEST 
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

class Node {
  int  data;
  Node next;
  public Node(int data){
    this.data = data;
  }
}
