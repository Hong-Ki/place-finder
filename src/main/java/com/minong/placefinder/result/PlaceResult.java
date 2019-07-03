package com.minong.placefinder.result;

import com.google.gson.annotations.SerializedName;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PlaceResult {
  private String id;
  private String phone;
  private String x;
  private String y;
  private String shortcuts;

  @SerializedName("category_name")
  private String categoryName;
  @SerializedName("address_name")
  private String addressName;
  @SerializedName("road_address_name")
  private String roadAddressName;
  @SerializedName("place_name")
  private String placeName;

  @Builder
  public PlaceResult(String id, String phone, String placeName, String addressName, String roadAdress, String x,
      String y) {
    this.id = id;
    this.phone = phone;
    this.placeName = placeName;
    this.addressName = addressName;
    this.roadAddressName = roadAdress;
    this.x = x;
    this.y = y;
  }

}