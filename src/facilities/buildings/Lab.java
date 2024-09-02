package facilities.buildings;

import facilities.Facility;

/** A Class Lab that extends Facility and implements Building */
public class Lab extends Facility implements Building {
  /** The following are member variables used to do the instructions */
  private int level;

  private int upgradeCost;

  private int baseCost;

  private int capacity;

  private int baseCapacity;

  private int maxLevel;

  /**
   * Lab constructor which passes a String name as an argument
   *
   * @param name The Variable inherited from the Facility class
   */
  public Lab(String name) {
    super(name);
    level = 1;
    baseCapacity = 5;
    baseCost = 300;
    maxLevel = 5;
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
    }
    return -1;
  }

  /**
   * This method is to calculate the capacity of the Lab based on the current level for the Level
   *
   * @return the capacity of the building
   */
  @Override
  public int getCapacity() {

    capacity = (int) (baseCapacity * Math.pow(2, level - 1));

    return capacity;
  }

  /**
   * this accessor method is to getmaxlevel of the Lab
   *
   * @return the maxLevel
   */
  @Override
  public int getmaxLevel() {
    return maxLevel;
  }

  /**
   * this accessor method is to getBaseCost of the Lab
   *
   * @return the baseCost
   */
  @Override
  public int getBaseCost() {
    return baseCost;
  }
}
