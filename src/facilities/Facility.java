package facilities;

/** A class Facility whic is the basis of the buildings classes Hall,Lab,Theatre */
public class Facility {

  /** member Variable String name */
  private String name;

  /**
   * A Constructor that holds an argument the member variable
   *
   * @param name This constructor initialize the member variable (name) with the give name
   */
  public Facility(String name) {

    this.name = name;
  }

  /**
   * Accessor method to get which will get me the name of the facility
   *
   * @return statement will return the name of the facility
   */
  public String getName() {

    return name;
  }
}
