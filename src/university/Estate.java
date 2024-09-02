package university;

import facilities.Facility;
import facilities.buildings.Building;
import facilities.buildings.Hall;
import facilities.buildings.Lab;
import facilities.buildings.Theatre;

import java.util.ArrayList;

/** This estate Class is supposed to manage the facilities */
public class Estate {

  /** Those are the member Variables and the Arraylist I used */
  private ArrayList<Facility> facilities;

  /** this is an Estate constructor which initialize the ArrayList */
  public Estate() {

    this.facilities = new ArrayList<>();
  }

  /**
   * This accessor method is to get the facilities
   *
   * @return the facilitate size
   */
  public Facility[] getFacilities() {
    return facilities.toArray(new Facility[facilities.size()]);
  }

  /**
   * This method is to addFacility it takes string type and string name as a parameter
   *
   * @param type of the facilities which is the Hall,Lab,Theatre
   * @param name of the new facility
   * @return it returns a facility if there is one and null if there is no facility
   */
  public Facility addFacility(String type, String name) {
    Facility newFacility = null;

    // this switch statement is to deal with different facilities the three (Hall,Lab,Theatre)
    switch (type) {
      case "Hall":
        newFacility = new Hall(name);
        facilities.add(newFacility);
        break;
      case "Lab":
        newFacility = new Lab(name);
        facilities.add(newFacility);
        break;

      case "Theatre":
        newFacility = new Theatre(name);
        facilities.add(newFacility);

        break;

      default: // Here if the type is not known it prints that the type is wrong
        System.out.println(" wrong type " + type);
    }
    return newFacility;
  }

  /**
   * This accessor method is supposed to get the maintenanceCost of the facilities So I wrote a for
   * each loop to loop into the facilities and the float formula is to get the 10% of the facilities
   * by casting into the Building interface, so it gives me all the facilities and calculate 10% of
   * the capacity by multiplying it by 0.10 and then assign it to the MaintenanceCost after that it
   * adds it to the totalCost variable
   *
   * @return the totalCost of the Maintenance
   */
  public float getMaintenanceCost() {
    float totalCost = 0.0f;
    for (Facility facility : facilities) {

      float maintenanceCost = ((Building) facility).getCapacity() * 0.10f;

      totalCost = totalCost + maintenanceCost;
    }
    return totalCost;
  }

  /**
   * This accessor method is to get the NumberOfStudents by looping into the facilities then check
   * if that the type Hall is an instance of facility or not if it is then it adds the capacity to
   * the variable hallCapacity and the same to the rest of the type of the facilities and after that
   * I used a formula as the instructions mentioned that the totalNumberOfStudents is the min of the
   * three facilities
   *
   * @return the totalNumberOfStudents.
   */
  public int getNumberOfStudents() {
    int hallCapacity = 0;
    int labCapacity = 0;
    int theatreCapacity = 0;

    int totalNumberOfStudents = 0;

    for (Facility facility : facilities) {

      if (facility instanceof Hall) {
        hallCapacity += ((Hall) facility).getCapacity();
      }
      if (facility instanceof Lab) {
        labCapacity += ((Lab) facility).getCapacity();
      }
      if (facility instanceof Theatre) {
        theatreCapacity += ((Theatre) facility).getCapacity();
      }

      totalNumberOfStudents += Math.min(hallCapacity, Math.min(labCapacity, theatreCapacity));
    }

    return totalNumberOfStudents;
  }
}
