package org.joedog.trumprhoids.model;
/**
 * Copyright (C) 2017
 * Jeffrey Fulmer - <jeff@joedog.org>, et al.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *--
 */

import java.util.ArrayList;

/**
 * This class borrows heavily from JGameGrid
 * by Aegidius Plüss
 * http://www.aplu.ch/home/apluhomex.jsp?site=45
 */
public class Location implements Comparable, Cloneable {
  private int x;
  private int y;
  private boolean highlight = false;

  public static final int FORWARD     = 0;
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

  public Location() {
    this.x = 0;
    this.y = 0;
  }

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Location(Location location) {
    this.x = location.x;
    this.y = location.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public Location getAdjacentLocation(double direction, int distance, double epsilon) {
    if (distance < 0) {
      direction = 180.0D + direction;
      distance = -distance;
    }
    direction %= 360.0D;
    if (direction < 0.0D) {
      direction = 360.0D + direction;
    }

    int xNew = (int)Math.floor(x + 0.5 + (distance + epsilon) * Math.cos(direction / 180 * Math.PI));
    int yNew = (int)Math.floor(y + 0.5 + (distance + epsilon) * Math.sin(direction / 180 * Math.PI));
    //System.out.println("["+xNew+","+yNew+"]");
    return (new Location(xNew, yNew));
  }

  public Location getNeighboringLocation(double direction) {
    return getAdjacentLocation(direction, 1, 0.3);
  }

  public int getDirectionToward(Location target) {
    int dx = target.getX() - getX();
    int dy = target.getY() - getY();
    int angle = (int)Math.toDegrees(Math.atan2(-dy, dx));
    int compassAngle = (RIGHT - angle);
    compassAngle += HALF_RIGHT / 2;
    if (compassAngle < 0)
      compassAngle += FULL_CIRCLE;
    
    int tmp = (compassAngle / HALF_RIGHT) * HALF_RIGHT;
    return direction(tmp);
  }

  public void highlight(boolean highlight) {
    //System.out.println("HIGHLIGHT: "+highlight);
    this.highlight = highlight;
  }

  public boolean isHighlighted() {
    return this.highlight;
  }

  private int direction(int angle) {
     switch (angle) {
       case 0:
         return NORTH;
       case 45:
         return NORTHEAST;
       case 90:
         return EAST;
       case 135:
         return SOUTHEAST;
       case 180:
         return SOUTH;
       case 225:
         return SOUTHWEST;
       case 270:
         return WEST;
       case 315:
         return NORTHWEST;
       default:
         return NORTH;
    } 
  }

  public boolean equals(Object other) {
    if (!(other instanceof Location))
      return false;

    Location otherLoc = (Location)other;
    return getX() == otherLoc.getX() && getY() == otherLoc.getY();
  }


  public int compareTo(Object other) {
    Location there = (Location)other;
    if (getX() < there.getX()) {
      return -1;
    }
    if (getX() > there.getX()) {
      return 1;
    }
    if (getY() < there.getY()) {
      return -1;
    }
    if (getY() > there.getY()) {
      return 1;
    }
    return 0;
  }


  public Location clone() {
    return new Location(this);
  }

  public String toString() {
    return "["+this.getX()+", "+this.getY()+"]";
  }
}

