package facilities.buildings;

import facilities.Facility;

/** A Class Theatre that extends Facility and implements Building */
public class Theatre extends Facility implements Building {
  /** The following are member variables used to do the instructions. */
  private int level;

  private int baseCost;
  private int upgradeCost;
  private int capacity;
  private int baseCapacity;
  private int maxLevel;

  /**
   * This is the Theatre constructor which passes a String name as an argument.
   *
   * @param name The Variable inherited from the Facility class.
   */
  public Theatre(String name) {
    super(name);
    level = 1;
    baseCapacity = 10;
    baseCost = 200;

    maxLevel = 6;
  }

  /**
   * the following methods are override from the building interface
   *
   * @return this return the current level of the building
   */
  @Override
  public int getLevel() {
    return level;
  }

  /** This method increase the levels until it reaches the maximum */
  @Override
  public void increaseLevel() {
    if (level < maxLevel) {
      level++;
    }
  }

  /**
   * This method evaluate and return the upgrade cost based on the current level
   *
   * @return the Upgrade cost or -1 if the level is at the maximum level
   */
  @Override
  public int getUpgradeCost() {

    if (level < maxLevel) {
      upgradeCost = baseCost * (level + 1);
      return upgradeCost;

    } else {
      return -1;
    }
  }

  /**
   * This method is to calculate the capacity of the Theatre based on the current level for the
   * Theatre using the formula given down
   *
   * @return it returns the capacity of the building
   */
  @Override
  public int getCapacity() {

    capacity = (int) (baseCapacity * Math.pow(2, level - 1));
    return capacity;
  }

  /**
   * this accessor method is to getmaxlevel of the Theatre
   *
   * @return the maxLevel
   */
  @Override
  public int getmaxLevel() {
    return maxLevel;
  }

  /**
   * this accessor method is to getBaseCost of the Theatre
   *
   * @return the baseCost
   */
  @Override
  public int getBaseCost() {
    return baseCost;
  }
}
