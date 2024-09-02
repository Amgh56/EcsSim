package university;

/** This Class is made to for instructing students */
public class Staff {
  /** Those are the member variables I used for this class */
  private String name;

  public int skill;

  private int yearsOfTeaching;

  private int stamina;

  private int reputation;

  private String group;

  private int statisfied; // extension

  /**
   * A constructor Staff which initialize some variables
   *
   * @param name the name of the staff
   * @param skill the skill of the staff
   */
  public Staff(String name, int skill) {

    this.name = name;
    this.skill = skill;
    this.yearsOfTeaching = 0;
    this.stamina = 100;
    this.statisfied = 100; // extension
  }

  /**
   * @return the name and the skill by making the staff to string
   */
  public String toString() {

    return "Name:" + name + "Skill:" + skill;
  }

  /**
   * This method have three formulas the first one is to calculate the reputation the second one is
   * raise the skill by one but minimize it to 100 max So it cannot be above 100 the third one is to
   * calculate the stamina.
   *
   * @param numberOfStudents this indicates the number of students instructed by a staff
   * @return the reputation.
   */
  public int instruct(int numberOfStudents) {
    reputation = (skill * 100) / (100 + numberOfStudents);

    skill = Math.min(100, skill + 1);
    stamina = (int) (stamina - Math.ceil(numberOfStudents / (20 + skill) * 20));
    return reputation;
  }

  /**
   * this method is maid to replenish the staffs stamina I used an if statement which states that if
   * the staminaCap is bigger than the staff we add staff skill to the staff stamina 20.
   */
  public void replenishStamina() { // you need to check this?
    int staminaCap = 100;
    if (staminaCap > stamina + 20) {
      stamina += 20;
    }
  }

  /** This method is using a counter for the yearsOfTeaching, so it increases by 1 */
  public void increaseYearsOfTeaching() { // check this later ? maybe

    yearsOfTeaching++;
  }

  /**
   * @return staffs skill
   */
  public float getSkill() {
    return skill;
  }

  /**
   * @return the current the years of teaching for the staff
   */
  public int getYearsOfTeaching() {
    return yearsOfTeaching;
  }

  /**
   * @return staffs stamina
   */
  public int getStamina() {

    return stamina;
  }

  /**
   * This accessor method I used to set the groups
   *
   * @param group initializing the group and updating the initial group
   */
  public void setGroup(String group) {
    this.group = group;
  }
}
