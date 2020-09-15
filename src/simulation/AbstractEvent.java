package simulation;

public abstract class AbstractEvent implements Event {

  private final double eventTime;
  /** Constructor for AbstractEvent. */
  public AbstractEvent(double time) {

    eventTime = time;
  }

  /** Returns the time (in ticks) at which this event will occur. */
  @Override
  public double time() {

    return eventTime;
  }

  /** Compares this object with the specified Event. */
  @Override
  public int compareTo(Event that) {

    if (this.time() < that.time()) return -1;
    else if (this.time() > that.time()) return 1;

    return 0;
  }
}
