package university;

import facilities.Facility;
import facilities.buildings.Building;
import org.w3c.dom.ranges.Range;
import university.susu.Events;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.io.FileNotFoundException;

/** This class is made to keeps university information */
public class University {
  /** Those are the member variables I used here */
  private float budget;

  private int reputation = 0;
  private Estate estate;

  private HumanResource humanResource;

  private String[] typesOfBuilding = {"Hall", "Lab", "Theatre"};

  public ArrayList<Staff> staffMarket;

  private int unInstructed;

  private int year;

  /**
   * This is University constructor it initializes the budget and assign it to funding
   *
   * @param funding and takes an int funding as a parameter
   */
  public University(int funding) {

    budget = funding;
    estate = new Estate();
    humanResource = new HumanResource();
    this.staffMarket = new ArrayList<>(getHumanResource().getStaffList().size());
  }

  /**
   * This method is to build it takes the type of the building and the name at first it used the
   * estate method addFacility which add a facility to the method moreover I used an if statement to
   * check whether the facility is not null after that it checks that if the facility belongs to
   * Building or not if it is an instance of the building it checks if the budget is bigger than the
   * baseCost of the building So then we deduct the budget and raise by 100 the reputation
   *
   * @param type this is the type we used in the estate class which make sure that the facility is
   *     from the same type
   * @param name this assign the type of the facility to the specified name
   * @return this return the facility if the budget is deducted and if not it returns null
   */
  public Facility build(String type, String name) {
    Facility facility = estate.addFacility(type, name);

    if (facility != null) {
      if (facility instanceof Building) {

        if (budget >= ((Building) facility).getBaseCost()) {
          budget -= ((Building) facility).getBaseCost();
          reputation += 100;
          return facility;
        }
      }
    }
    return null;
  }

  /**
   * This method upgrade the given building. I used the first if statement to make sure that the
   * building is not in the maximumLevel and after that I used a for each loop to go through the
   * facilities and check if the facility is part of the buildings if yes then it checks if the
   * facility equals the building if correct it checks if the budget is larger than the upgrade cost
   * or equal if it applies the condition then it deduct the budget and raise the reputation by 50+
   *
   * @param building this building is used as a variable Building
   * @throws Exception the first exception is thrown if the building is at the maximum level and the
   *     second one if the budget condition is not correct then it throws a new Exception and the
   *     last one if the building is not from the building type
   */
  public void upgrade(Building building) throws Exception {
    if (building.getLevel() >= building.getmaxLevel()) {
      throw new Exception("Unable to upgrade: The building is already at the maximum level");
    }

    for (Facility facility : estate.getFacilities()) {
      if (facility instanceof Building)
        if (facility.equals(building)) {
          if (budget >= building.getUpgradeCost()) {
            budget -= building.getUpgradeCost();
            reputation += 50;
            building.increaseLevel();
            return;
          } else {
            throw new Exception("Not enough budget to upgrade the building");
          }
        }
    }

    throw new Exception("The building is not a part of the university");
  }

  /**
   * Accessor method to get the current budget
   *
   * @return the current budget
   */
  public float getBudget() {
    return budget;
  }

  /**
   * an accessor method to get the current reputation
   *
   * @return the current reputation
   */
  public int getReputation() {
    return reputation;
  }

  public Estate getEstate() {
    return this.estate;
  }

  /**
   * This is an accessor method for humanResource Class so i can use it within the EcsSim
   *
   * @return humanResource
   */
  public HumanResource getHumanResource() {
    return this.humanResource;
  }

  /**
   * I Used an algorithm to start a university with a low budget and low number of students it is
   * more into a private university strategy I did this by controlling the years for the first 13
   * years as the University starts from the year 0. as long as the budget is less than or equal
   * 2000 I build new buildings from all types if the budget is enough and within the first 13 years
   * in the conditions I checked if the budget is more than the maintenanceCase in addition to the
   * staffs salary if true I build So I gain students {Hall, Lab , Theatre} as we build the three
   * types than the maintenanceCase in addition to the * staffs salary if true i build So I gain
   * students {Hall, Lab , Theatre} of the buildings we get more students So more budget and more
   * building to build so after the 13 years I only build one type randomly and stop building once
   * the budget is equal or bigger than 100000 it builds one building then it stops building I did
   * this to prevent having a lot of students in one group so the staff won't leave the university
   * early and more over in the method I build a random type and assign it to "B" and a random
   * number from 1 to 100.
   *
   * @param year this parameter is how I controlled the years by passing it.
   */
  public void needTobuild(int year) {

    if (year <= 13 && budget <= 2000) {
      if (budget >= 100
          && budget > estate.getMaintenanceCost() + humanResource.getTotalSalary()
          && budget > 0) {
        build("Hall", "Electronics and Enggineering "); // if statement
        System.out.println("Hall");
      }

      if (budget >= 200
          && budget > estate.getMaintenanceCost() + humanResource.getTotalSalary()
          && budget > 0) {
        build("Theatre", "avenue");
        System.out.println("Theatre");
      }

      if (budget >= 300
          && budget > estate.getMaintenanceCost() + humanResource.getTotalSalary()
          && budget > 0) {
        build("Lab", " Zepler ");
        System.out.println("Lab");
      }
    } else if (budget > 300 && budget <= 100000) {
      Random buildingRandomizer = new Random();
      Random nameRandom = new Random();
      int typeToBuild = buildingRandomizer.nextInt(typesOfBuilding.length);
      build(typesOfBuilding[typeToBuild], "B" + (nameRandom.nextInt(100)));
      System.out.println("B" + (nameRandom.nextInt(100)));
    }
  }

  /**
   * This method were used to upgrade the facilities I used an algorithm that we upgrade after the
   * year 13 So I won't lose money if I start with a low budget and moreover I upgrade if I have
   * more than one or equal one buildings I upgrade random buildings and if I don't have a budget or
   * the building is at it maxLevel I throw an Exception the building is in the max level. I don't
   * upgrade unless my budget is more than the upgradeCost. my approach is not to upgrade in the
   * first years since the buildings are new and the upgradeCost is costly So I preferred to upgrade
   * after I finish building the firs couple years.
   *
   * @param year This year's argument is to make control of the years of the simulation.
   */
  public void upgradeFacilities(int year) {
    Random rand = new Random();
    if (year > 13) {

      System.out.println("Upgrade Happens after year 13:");
    } else {
      if (getEstate().getFacilities().length >= 1) {

        Building building =
            (Building)
                getEstate().getFacilities()[rand.nextInt(getEstate().getFacilities().length)];

        for (Facility facility : getEstate().getFacilities()) {
          if (building.equals(facility)) {

            if (budget > building.getUpgradeCost() && budget > 0) {
              System.out.println(
                  " this is the length of the building: " + getEstate().getFacilities().length);

              try {
                upgrade(building);
                System.out.println("upgrade:" + ((Facility) building).getName()); // check this

              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
    }
  }

  /**
   * In this method I initialized a variable contributionStudents and assign it to 10 after that I
   * used a formula and I multiplied the variable with the numberOfStudents I called it from the
   * estate class.
   *
   * @return totalContribution
   */
  public int contributions() {

    int contributionOfstudents = 10;
    int totalContribution = contributionOfstudents * estate.getNumberOfStudents();

    budget += totalContribution;

    System.out.println(totalContribution);

    return totalContribution;
  }

  /**
   * I used here an algorithm that loop into the staffMarket and find the required specifications
   * but for the first two years we don't hire. and after that we choose the staff according to the
   * conditions it chooses one randomly if the conditions is applied we hire one evey year ("except
   * for the first two years") So after I hire the staff I add the staff to the humanResource and
   * remove him from the market.
   *
   * @param staffMarket this is to read the staffMarket ArrayList from the EcsSim class
   * @return hired if hired and null if no one is hired.
   */
  public Staff hireStaff(ArrayList<Staff> staffMarket, int year) {

    if (year == 1 || year == 2) {
      System.out.println(" No hiring for this year:" + year);
    } else
      for (Staff staff : staffMarket) {
        if (staff != null && budget > humanResource.getTotalSalary() && budget > 0) {

          Staff hired = staffMarket.get(new Random().nextInt(staffMarket.size()));

          humanResource.addStaff(hired);
          staffMarket.remove(hired);

          System.out.println("The staff is hired: " + hired);
          return hired;
        }
      }
    return null;
  }

  /**
   * In this method my approach was to do an arrayList with a 30 group So I can instruct students by
   * groups every year a new staff is hired and new Group is enrolled.
   *
   * @return groups
   */
  public ArrayList<String> groupsAllocation() { // important Check

    ArrayList<String> groups = new ArrayList<>();

    for (int i = 1; i <= 30; i++) {

      String name = "Group: " + i;

      groups.add(name);
    }

    return groups;
  }

  /**
   * In this method the approach is related to the previous method So I used an arraylist with a
   * staff Iterators into the humanResource.getStaffList().keySet() and then I shuffle the Arrays So
   * I get a random staff instructing a group and moreover a for loop to loop into the staff list
   * and checks if that the groups arrayList is not empty and moreover I did two new formulas one to
   * divide students by the number of staff I have and assign it to the variable instructions this
   * ensures that every group have the same amount of students and that they are being instructed by
   * one staff . and the other formula was to confirm how many student is not instructed So I used
   * the mod to see how much is the reminder from their division and later on I set the group
   * Allocation method and passed the variable instructions into the instruct method and also
   * increase the reputation when students are instructed.
   */
  public void staffInstructions() { // check important
    ArrayList<Staff> inStructionList = new ArrayList<>(humanResource.getStaffList().keySet());
    Collections.shuffle(inStructionList);

    ArrayList<String> groups = groupsAllocation();

    for (Staff staff : inStructionList) {

      if (!groups.isEmpty()) {
        String GroupAllocation = groups.remove(0);

        int instructions = getEstate().getNumberOfStudents() / humanResource.getStaffList().size();

        unInstructed = getEstate().getNumberOfStudents() % humanResource.getStaffList().size();

        System.out.println(humanResource.getStaffList().size() + " This is the number of staff");

        staff.setGroup(GroupAllocation);

        staff.instruct(instructions);

        increaseReputation(instructions);

        Staff hired = staff;
        System.out.println(
            staff + " is instructing " + instructions + " students in " + GroupAllocation);

      } else {
        System.out.println("There is no Students to Instruct");
      }
      {
      }
    }
  }

  /**
   * @param instructions this is how i used this in the previous method
   */
  public void increaseReputation(int instructions) {
    this.reputation += instructions;
  }

  /**
   * @param deduction by this method i the end of year payments and make sure that the budget is not
   *     less than 0.
   */
  public void budgetDeduction(float deduction) {
    if (budget > 0) this.budget -= deduction;
  }

  /** Here I make sure for any hired staff I increase the year Of teaching. */
  public void increasingYearsOfTeaching() {

    for (Staff staff : humanResource.getStaffList().keySet()) {
      staff.increaseYearsOfTeaching();

      System.out.println(
          staff.getYearsOfTeaching() + " years of teaching "); // checking years of teaching
    }
  }

  /** reputation Deduction for unInstructed students. */
  public void unInstructedStudents() {

    if (unInstructed > 0) {
      ReputaionDeduction(unInstructed);

      System.out.println(
          "Deducted:" + unInstructed + " reputation points for uninstructed students");
    }
  }

  /**
   * in this method we need to put two content for in which case the staff leave so in the case that
   * the staff reach 30 years or his stamina is less than the random number from 0 to 100 if true
   * the staff need to leave, and we remove him from the humanResource.
   */
  public void staffLeaving() {

    int maxYearsTeaching = 30;
    Random random = new Random();

    Iterator<Staff> staffIterator = humanResource.getStaffList().keySet().iterator();

    while (staffIterator.hasNext()) {
      Staff staff = staffIterator.next();

      if (staff.getYearsOfTeaching() == maxYearsTeaching
          || staff.getStamina() < random.nextInt(100)) {
        System.out.println(staff + " is leaving ");
        staffIterator.remove();

      } else {
        System.out.println("The staff will stay he passed the conditions");
      }
    }
  }

  /** Here for all the remaining staff we replenish their stamina */
  public void replenishStamina() {
    for (Staff staff : humanResource.getStaffList().keySet()) {
      staff.replenishStamina();
    }
  }

  /**
   * @param deduction to Deduct the reputation
   */
  public void ReputaionDeduction(int deduction) {

    this.reputation -= deduction;
  }
}

  // This is extensions
  /*
  So this method is to manage universities events and if the
  event have many students then the budget raises and the reputation points also
  and deduct the budgetDeduction i used the switch case to implement the logic.

   */

  /**
   * @Override public void eventsDuringYear(University university) { float eventMaintenanceCost =
   * estate.getNumberOfStudents() * 0.60f; int runningEvent = new Random().nextInt(5); switch
   * (runningEvent) { case 1: System.out.println("It is the Fresher Week Event! "); budget += 1000;
   * reputation += 10; university.budgetDeduction(eventMaintenanceCost);
   *
   * <p>break; case 2: System.out.println("It is Graduation week"); budget -= 50; reputation += 100;
   * break; case 3: System.out.println("We Have a workShop for the Staff "); budget += 100;
   * reputation += 10; for (Staff staff : humanResource.getStaffList().keySet()) { if
   * (staff.getSkill() > 70 && 100 > staff.getSkill()) { staff.skill += 5; break; } }
   *
   * <p>case 4: System.out.println("Qs university ranking get a bad ranking ");
   *
   * <p>budget -= 100;
   *
   * <p>reputation -= 20; break;
   *
   * <p>case 5: System.out.println("Halloween party ");
   *
   * <p>int eventExpectedCapacity = Math.min(estate.getNumberOfStudents(), 10000); int ticketPrice =
   * 2 * eventExpectedCapacity; float consumedBudget = eventExpectedCapacity * 0.50f;
   *
   * <p>if (estate.getNumberOfStudents() >= eventExpectedCapacity && eventExpectedCapacity > 0) {
   * budget += ticketPrice; reputation += 10; budget -= consumedBudget;
   *
   * <p>System.out.println( "The Halloween party was perfect this is the budget after it " +
   * ticketPrice); System.out.println("This is the the budget we spend to run this party" +
   * consumedBudget); } } }
   *
   * <p>/* for this method is the budgetThreaten we need to escape from the budget threaten every
   * year
   */
 /**
  * @Override public void budgetThreaten(University university) {
  *
  * <p>float randomPercentage = new Random().nextInt(20) / 100f; int budgetDeduction = (int)
  * (university.getBudget() * randomPercentage);
  *
  * <p>university.budgetDeduction(budgetDeduction); } }
  */
