package org.joedog.trumprhoids.control;

import org.joedog.util.*;
import org.joedog.trumprhoids.model.*;

public class Game extends AbstractController {
  private Engine engine;
  private Thread thread;
  private Arena  arena;

  public Game() {
    this.arena = Arena.getInstance(); 
    this.addModel(arena);
    this.runModelMethod("newGame");
  }

  public void rotate(double angle) {
    this.arena.rotate(angle);
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public void addThread(Thread thread) {
    this.thread = thread;
  }

  public void setMessage(String message) {
    this.setModelProperty("Message", message);
  }

  public void start() {
    engine.start();

    while (engine.paused()) {
      Sleep.milliseconds(500);
    }
  }
}
