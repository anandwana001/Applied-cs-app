package anandwana001.com.acs.ui.model;

/**
 * Created by
 * dell on
 * 17-01-2018 at
 * 08:29 AM.
 */

public class StudentWorkshopModel {

  private String workshop_name;
  private String workshop_id;

  public StudentWorkshopModel() {
  }

  public StudentWorkshopModel(String workshop_name, String workshop_id) {
    this.workshop_name = workshop_name;
    this.workshop_id = workshop_id;
  }

  public String getWorkshop_name() {
    return workshop_name;
  }

  public void setWorkshop_name(String workshop_name) {
    this.workshop_name = workshop_name;
  }

  public String getWorkshop_id() {
    return workshop_id;
  }

  public void setWorkshop_id(String workshop_id) {
    this.workshop_id = workshop_id;
  }
}
