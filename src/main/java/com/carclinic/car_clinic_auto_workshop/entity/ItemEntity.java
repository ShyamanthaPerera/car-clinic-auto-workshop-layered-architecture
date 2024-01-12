package com.carclinic.car_clinic_auto_workshop.entity;

public class ItemEntity {

    private String itemId;
    private String model;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;

    public ItemEntity() {
    }

    public ItemEntity(String itemId, String model, String description, Double unitPrice, Integer qtyOnHand) {
        this.itemId = itemId;
        this.model = model;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(Integer qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "itemId='" + itemId + '\'' +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
