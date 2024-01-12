package com.carclinic.car_clinic_auto_workshop.entity;

public class SlotEntity {

    private String slotId;
    private String status;
    private String space;
    private String chargingOutlet;

    public SlotEntity() {
    }

    public SlotEntity(String slotId, String status, String space, String chargingOutlet) {
        this.slotId = slotId;
        this.status = status;
        this.space = space;
        this.chargingOutlet = chargingOutlet;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getChargingOutlet() {
        return chargingOutlet;
    }

    public void setChargingOutlet(String chargingOutlet) {
        this.chargingOutlet = chargingOutlet;
    }

    @Override
    public String toString() {
        return "SlotEntity{" +
                "slotId='" + slotId + '\'' +
                ", status='" + status + '\'' +
                ", space='" + space + '\'' +
                ", chargingOutlet='" + chargingOutlet + '\'' +
                '}';
    }
}
