package simulation;

public abstract class Collision extends AbstractEvent {

  private Particle[] particles;
  private int[] hits;
  /** Constructor for Collision */
  public Collision(double t, Particle[] ps) {

    super(t);
    particles = ps;
    hits = new int[particles.length];

    for (int i = 0; i < particles.length; i++) {
      hits[i] = particles[i].collisions();
    }
  }

  /** Returns true if this Collision is (still) valid. */
  @Override
  public boolean isValid() {

    for (int i = 0; i < particles.length; i++) {
      if (hits[i] != particles[i].collisions()) {
        return false;
      }
    }

    return true;
  }

  /** Returns an array containing the Particles involved in this Collision. */
  public Particle[] getParticles() {

    return particles;
  }
}
