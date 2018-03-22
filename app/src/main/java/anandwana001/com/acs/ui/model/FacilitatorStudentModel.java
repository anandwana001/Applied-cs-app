package anandwana001.com.acs.ui.model;

/**
 * Created by
 * dell on
 * 17-01-2018 at
 * 10:52 AM.
 */

public class FacilitatorStudentModel {

  private String name;
  private String student_id;

  public FacilitatorStudentModel() {
  }

  public FacilitatorStudentModel(String name, String student_id) {
    this.name = name;
    this.student_id = student_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStudent_id() {
    return student_id;
  }

  public void setStudent_id(String student_id) {
    this.student_id = student_id;
  }
}
