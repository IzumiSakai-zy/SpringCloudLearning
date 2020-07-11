package com.entity;


/**
 * <p>
 * 
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-07-08
 */
public class Payment{
  private Integer id;
  private String serial;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public Payment(Integer id, String serial) {
    this.id = id;
    this.serial = serial;
  }

  public Payment() {
  }

  @Override
  public String toString() {
    return "Payment{" +
            "id=" + id +
            ", serial='" + serial + '\'' +
            '}';
  }
}
