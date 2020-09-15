package simulation;

public class TwoParticleCollision extends Collision {
  /**
   * Constructor for Collision
   */
  public TwoParticleCollision(Particle p1, Particle p2, double t) {
    super(t, new Particle[] {p1, p2});
  }

  @Override
  public void happen(ParticleEventHandler h) {
    if (super.isValid()) {
      Particle.collide(super.getParticles()[0], super.getParticles()[1]);
      h.reactTo(this);
    }
  }
}
