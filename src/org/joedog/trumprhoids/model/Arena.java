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

import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.event.ListDataEvent;

import org.joedog.trumprhoids.Constants;
import org.joedog.trumprhoids.sprite.*;
import org.joedog.util.NumberUtils;

public class Arena extends AbstractModel {
  private int    cols;
  private int    rows;
  private int    width;
  private int    height;
  private int    csize;
  private int    lives;
  private Actor  ship; 
  private String message;

  private static Arena  _instance = null;
  private static Object    mutex  = new Object();
  private ArrayList<Actor> actors = new ArrayList<Actor>(); 

  private Arena() {
    this.width  = 900;
    this.height = 600;
    this.csize  = (this.width  / 32);
    this.cols   = (this.width  / this.csize);
    this.rows   = (this.height / this.csize);
  }

  public synchronized static Arena getInstance() {
    if (_instance == null) {
      synchronized(mutex) {
        if (_instance == null) {
          _instance = new Arena();
        }
      }
    }
    return _instance;
  }

  public void save() {}

  public synchronized void newGame() {
    this.lives = 3;
    this.ship  = new Spaceship();
    Asteroid a = new Asteroid();
    Boulder  b = new Boulder();
    Rock     c = new Rock();
    a.setLocation(new Location(25, 25));
    b.setLocation(new Location(500, 50));
    c.setLocation(new Location(175, 75));
    this.ship.setLocation(new Location(450, 300));
    this.actors.add(a);
    this.actors.add(b);
    this.actors.add(c);
    this.actors.add(ship);
  }

  public synchronized void rotate(double angle) {
    this.ship.rotate(angle);
  }

  public ArrayList<Actor> getActors() {
    return this.actors;
  }

  /**
   * Status the status message and fires a property change
   * <p>
   * @param  String  the status message
   * @return void
   */
  public void setMessage(String message) {
    this.message = message;
    firePropertyChange(Constants.MESSAGE, "message", message);
  }

  /**
   * Returns a status message
   * <p>
   * @param  void
   * @return String  The status message
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * Returns the grid column count in integers
   * <p>
   * @param  none
   * @return int
   */
  public int getCols() {
    return this.cols;
  }

  /**
   * Returns the grid row count in integers
   * <p>
   * @param  none
   * @return int
   */
  public int getRows() {
    return this.rows;
  }

  /**
   * Returns the width of the grid in pixels
   * <p>
   * @param  none
   * @return int
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the grid in pixels
   * <p>
   * @param  none
   * @return int
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the cell size of the grid in pixels
   * <p>
   * @param  none
   * @return int
   */
  public int getCellSize() {
    return this.csize;
  }
}
