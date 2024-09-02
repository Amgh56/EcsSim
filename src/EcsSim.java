import university.Staff;
import university.University;
import university.susu.Events;

import java.io.*;
import java.util.*;

/** This class is to simulate university operations and Generate the Staff from the market. */
public class EcsSim implements Serializable {
  /** Those are the member variables I used in this Class. */
  private University university;

  private int year;

  private ArrayList<Staff> staffMarket;

  public EcsSim(int funding) {
    this.university = new University(funding);
  }

  /**
   * This is how I run the code I put everything into the simulate method by order and some print
   * Statements to check if I am on the right track.
   */
  public void simulate() {

    try {
      System.out.println(
          "The current students are " + university.getEstate().getNumberOfStudents());
      System.out.println(
          "The current Budget is: " + university.getBudget()); // this is how I checked my code
      university.needTobuild(year);
      university.upgradeFacilities(year);
      System.out.println(
          university.getBudget() + " the budget is "); // this is how I checked my code

      int StudentsContribution = university.contributions();
      System.out.println(
          "The students contribution is: " + StudentsContribution); // this is how I checked my code

      university.hireStaff(staffMarket, year);

      university.groupsAllocation();

      university.staffInstructions();

      university.unInstructedStudents();
      university.increasingYearsOfTeaching();

      university.budgetDeduction(university.getEstate().getMaintenanceCost());

      university.budgetDeduction(university.getHumanResource().getTotalSalary());
      System.out.println(
          "After"
              + university.getHumanResource().getTotalSalary()); // this is how I checked my code

      university.staffLeaving();
      university.replenishStamina();

      System.out.println(
          "The current Budget is: " + university.getBudget()); // this is how I checked my code

      System.out.println(
          university.getReputation() + " this is the reputaion"); // this is how I checked my code

      System.out.println(
          university.getEstate().getFacilities().length); // this is how I checked my code

     // university.eventsDuringYear(university);

      //university.budgetThreaten(university);
      System.out.println("The current Budget is: " + university.getBudget());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Here is where we run the code for several years
   *
   * @param years how many years we want it to run;
   */
  public void simulate(int years) {
    try {
      for (year = 0; year <= years; year++) {

        Thread.sleep(500);
        System.out.println(" we are in the year: " + year);
        simulate();
      }

    } catch (InterruptedException e) {

    }
  }

  /**
   * In this method we are generating the staff from the market randomly from the staff.txt.
   *
   * @param numberOfStaff we have
   * @return staffMarket
   */
  public ArrayList<Staff> staffMarket(int numberOfStaff) {

    ArrayList<Staff> staffMarket = new ArrayList<>();

    for (int i = 0; i < numberOfStaff; i++) {

      staffMarket.add(new Staff(" StaffName" + i, new Random().nextInt()));
    }
    return staffMarket;
  }

  public static void main(String[] args) throws Exception {
    /*
     Right here we make sure that the number of command line arguments are correct
    */
    if (args.length != 3) {
      System.out.println(
          "Usage: java SimulationRunner <staffConfigFile> <initialFunding> <simulationYears>");
      return;
    }
    /*
     This Parse the command line so what it does is assign the 0 to staffConfigFile and 1 to
     initial Funding , 2 to simulationYears
    */
    String readStaffFile = args[0];
    int initialFunding = Integer.parseInt(args[1]);
    int simulationYears = Integer.parseInt(args[2]);

    EcsSim ecsSim = new EcsSim(initialFunding);
    System.out.println();
    try {
      /* In here we read the names from the file. */
      ecsSim.staffMarket = readStaffFile(readStaffFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      /*
       this is the arraylist for the staff in market and in the parenthesis is the name of the
       file of the staff
      */
      ArrayList<Staff> staffMarket = readStaffFile("staff.txt");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    /* This run the code to several years. */
    ecsSim.simulate(simulationYears);
  }

  /**
   * At first, it reads the staff.txt file It reads it using the BufferReader and then
   * ("\\s*\\(\\s*|\\s*\\)\\s*") this is to split the parameters in the staff.txt file. so then
   * string name and the int skill and add them to the staffMarket by making a new object staff
   *
   * @param filePath which is the staff.txt
   * @return the staff from the market
   * @throws IOException if the format is wrong or if it catches any other Exception *
   */
  public static ArrayList<Staff> readStaffFile(String filePath) throws IOException {

    ArrayList<Staff> staffMarket = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split("\\s*\\(\\s*|\\s*\\)\\s*");
        if (parts.length == 2) {
          String name = parts[0].trim();
          int skill = Integer.parseInt(parts[1].trim());
          staffMarket.add(new Staff(name, skill));
        } else {
          throw new IOException("Wrong format");
        }
        bufferedReader.readLine();
      }
    }
    return staffMarket;
  }
}
