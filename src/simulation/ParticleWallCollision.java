package simulation;

public class ParticleWallCollision extends Collision {
  /**
   * Constructor for Collision
   */
  Wall wall;

  public ParticleWallCollision(Particle p, Wall w, double t) {

    super(t, new Particle[] {p});
    wall = w;
  }

  @Override
  public void happen(ParticleEventHandler h) {

    if (super.isValid()) {
      Particle.collide(super.getParticles()[0], wall);
      h.reactTo(this);
    }
  }
}
