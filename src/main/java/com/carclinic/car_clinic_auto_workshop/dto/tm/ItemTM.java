package com.carclinic.car_clinic_auto_workshop.dto.tm;

import javafx.scene.layout.HBox;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemTM {
    private String item_id;
    private String model;
    private String description;
    private Double unit_price;
    private int qty_on_hand;
    private double totalPrice;
    private HBox btn;


//    public ItemTM(String item_id, String model,String description, Double unit_price, Integer qtyOnHand) {
//        this.item_id=item_id;
//        this.model=model;
//        this.description=description;
//        this.unit_price=unit_price;
//        this.qty_on_hand=qtyOnHand;
//    }

    public ItemTM(String itemId, String model, String description, Double unitPrice, Integer qtyOnHand, HBox hbox) {
        this.item_id=itemId;
        this.model=model;
        this.description=description;
        this.unit_price=unitPrice;
        this.qty_on_hand=qtyOnHand;
        this.btn=hbox;
    }
}
