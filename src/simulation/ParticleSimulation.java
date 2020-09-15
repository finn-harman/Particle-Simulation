package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.management.MBeanParameterInfo;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler {

  private static final long FRAME_INTERVAL_MILLIS = 40;

  private final ParticlesModel model;
  private final ParticlesView screen;
  private MinPriorityQueue<Event> eventQueue = new MinPriorityQueue<>();
  private double clock = 0;
  /** Constructor. */
  public ParticleSimulation(String name, ParticlesModel m) {
    model = m;
    screen = new ParticlesView(name, model);
    eventQueue.add(new Tick(1));
    Iterable<Collision> collisions = model.predictAllCollisions(clock);

    for (Collision collision : collisions) {
      eventQueue.add(collision);
    }
  }

  /** Runs the simulation. */
  @Override
  public void run() {
    try {
      SwingUtilities.invokeAndWait(screen);
    } catch (InvocationTargetException | InterruptedException e) {
      e.printStackTrace();
    }
      while (!eventQueue.isEmpty()) {
      Event event = eventQueue.remove();

      if (event.isValid()) {
        model.moveParticles(event.time() - clock);
        clock = event.time();
        event.happen(this);
      }
    }
  }

  @Override
  public void reactTo(Tick tick) {

    try {
      Thread.sleep(FRAME_INTERVAL_MILLIS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    screen.update();
    eventQueue.add(new Tick(clock + 1));
  }

  @Override
  public void reactTo(Collision c) {

    for (Particle particle : c.getParticles()) {
      Iterable<Collision> collisions = model.predictCollisions(particle, c.time());
      for (Collision collision : collisions) {
        eventQueue.add(collision);
      }
    }
  }
}
