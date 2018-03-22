package anandwana001.com.acs.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 * dell on
 * 17-01-2018 at
 * 07:56 AM.
 */

public class FacilitatorWorkshopModel implements Parcelable {

  private String workshop_name;
  private String mentor_id;
  private String workshop_id;


  public String getWorkshop_name() {
    return workshop_name;
  }

  public void setWorkshop_name(String workshop_name) {
    this.workshop_name = workshop_name;
  }

  public String getMentor_id() {
    return mentor_id;
  }

  public void setMentor_id(String mentor_id) {
    this.mentor_id = mentor_id;
  }

  public String getWorkshop_id() {
    return workshop_id;
  }

  public void setWorkshop_id(String workshop_id) {
    this.workshop_id = workshop_id;
  }

  public static Creator<FacilitatorWorkshopModel> getCREATOR() {
    return CREATOR;
  }

  public FacilitatorWorkshopModel() {
  }

  public FacilitatorWorkshopModel(String workshop_name, String mentor_id, String workshop_id) {
    this.workshop_name = workshop_name;
    this.mentor_id = mentor_id;
    this.workshop_id = workshop_id;
  }

  protected FacilitatorWorkshopModel(Parcel in) {
    workshop_name = in.readString();
    mentor_id = in.readString();
    workshop_id = in.readString();
  }

  public static final Creator<FacilitatorWorkshopModel> CREATOR = new Creator<FacilitatorWorkshopModel>() {
    @Override
    public FacilitatorWorkshopModel createFromParcel(Parcel in) {
      return new FacilitatorWorkshopModel(in);
    }

    @Override
    public FacilitatorWorkshopModel[] newArray(int size) {
      return new FacilitatorWorkshopModel[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(workshop_name);
    parcel.writeString(mentor_id);
    parcel.writeString(workshop_id);
  }
}
