package university;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/** This should manage the staff in the university */
public class HumanResource {

  /** This member HashMap is supposed to assign the every staff to his salary. */
  private HashMap<Staff, Float> staffSalary;

  /** This constructor is initializing the staff salary to a new hashMap */
  public HumanResource() {
    staffSalary = new HashMap<>();
  }

  /**
   * The staff salary is calculated randomly by a range from 10.5% and 9.5& of the staffs skill So I
   * used two formulas which calculate the percentage we used 9.5% as a minPercentage and we added
   * the new random bond float which will generate a random number between 9.5% and 10.5% and then
   * multiply it by the difference of the both maxPercent and minPercent
   */
  public void addStaff(Staff staff) {
    float maxPercent = 10.5f / 100; // check this
    float minPercent = 9.5f / 100; // check this

    float percentage = minPercent + new Random().nextFloat() * (maxPercent - minPercent);
    float salary = percentage * staff.getSkill();
    staffSalary.put(staff, salary);
  }

  /**
   * This iterates into the staff we have
   *
   * @return the staffSalary
   */
  public Iterator<Staff> getStaff() {
    return staffSalary.keySet().iterator();
  }

  /**
   * This method is made to get the TotalSalary for the hired staff, and then we assigned the
   * iterator float totalSalary to the staffSalary values then we initialized the sum to 0 we used a
   * condition that while there is still a staff salary it iterates to the next staff and so on then
   * it assigns and adds the totalSalary until the staff list fins
   *
   * @return the total sum of staffs salary
   */
  public float getTotalSalary() {

    Iterator<Float> totalSalary;
    totalSalary = staffSalary.values().iterator();
    float sum = 0;
    while (totalSalary.hasNext()) {
      sum += totalSalary.next();
    }

    return sum;
  }

  /**
   * This method is to get the StaffList and assign them to the salary for each staff.
   *
   * @return the staffSalary.
   */
  public HashMap<Staff, Float> getStaffList() {
    return staffSalary; // check tmw
  }
}
