package facilities.buildings;

import facilities.Facility;

import java.lang.Math;

/** A Class Hall that extends Facility and implements Building */
public class Hall extends Facility implements Building {

  /** The following are member variables used to do the instructions */
  private int level;

  private int upgradeCost;

  private int baseCost;

  private int capacity;

  private int baseCapacity;

  private int maxLevel;

  /**
   * Hall constructor which passes a String name as an argument
   *
   * @param name The Variable inherited from the Facility class
   */
  public Hall(String name) {
    super(name);
    level = 1;
    baseCapacity = 6;
    baseCost = 100;
    maxLevel = 4;
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

  /** This method increase the levels until it reaches the maximum level */
  @Override
  public void increaseLevel() {

    if (level < 4) {
      level++;
    }
  }

  /**
   * This method evaluate and return the upgrade cost based on the current level
   *
   * @return it returns the Upgrade cost or -1 if the level is at the maximum level
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
   * This method calculates the capacity of the Hall based on the current level for the Hall
   *
   * @return it returns the capacity of the building
   */
  @Override
  public int getCapacity() {

    capacity = (int) (baseCapacity * Math.pow(2, level - 1));

    return capacity;
  }

  /**
   * this accessor method is to getmaxlevel of the Hall
   *
   * @return it returns the maxLevel
   */
  @Override
  public int getmaxLevel() {
    return maxLevel;
  }

  /**
   * this accessor method is to getBaseCost of the Hall
   *
   * @return it returns the baseCost
   */
  @Override
  public int getBaseCost() {
    return baseCost;
  }
}
