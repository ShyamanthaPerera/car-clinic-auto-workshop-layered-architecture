package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DBConnection;
import com.carclinic.car_clinic_auto_workshop.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class ItemModel {

    public boolean saveItem(ItemDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_ITEM);

        statement.setString(1, dto.getItemId());
        statement.setString(2, dto.getModel());
        statement.setString(3, dto.getDescription());
        statement.setDouble(4, dto.getUnitPrice());
        statement.setInt(5, dto.getQtyOnHand());
        statement.setString(6, "system");
        statement.setDate(7,new java.sql.Date(new java.util.Date().getTime()));
        statement.setString(8,null);
        statement.setString(9,null);

        return statement.executeUpdate() > 0;
    }

    public boolean updateItem(ItemDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM);

        statement.setString(1, dto.getModel());
        statement.setString(2, dto.getDescription());
        statement.setDouble(3, dto.getUnitPrice());
        statement.setInt(4, dto.getQtyOnHand());
        statement.setString(5, dto.getItemId());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteItem(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ITEM);

        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public ItemDTO searchItem(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection ();
        PreparedStatement statement = connection.prepareStatement(SEARCH_ITEM);
        
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        ItemDTO dto = null;

        if(resultSet.next()) {
            String item_id = resultSet.getString(1);
            String item_model = resultSet.getString(2);
            String description = resultSet.getString(3);
            Double unit_price = resultSet.getDouble(4);
            int qty_on_hand = resultSet.getInt(5);

            dto = new ItemDTO(item_id, item_model, description, unit_price, qty_on_hand);
        }
        return dto;
    }

    public List<ItemDTO> getAllItem() throws SQLException {
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_ITEM);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<ItemDTO> ItemDtoList = new ArrayList<>();

        while(resultSet.next()) {
            ItemDtoList.add(
                    new ItemDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getInt(5)
                    )
            );
        }
        return ItemDtoList;
    }

    public List<ItemDTO> getAllItemsBySearch(String searchVal) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_ITEMS_BY_SEARCH_VAL);

        for (int i = 1; i <= 5; i++) {
            statement.setString(i, "%" + searchVal + "%");
        }

        ResultSet resultSet = statement.executeQuery();

        ArrayList<ItemDTO> itemDtoList = new ArrayList<>();

        while(resultSet.next()) {
            itemDtoList.add(
                    new ItemDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getInt(5)
                    )
            );
        }
        return itemDtoList;
    }

    public String generateNextItemsId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_ITEM_ID);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return splitItemId(resultSet.getString(1));
        }
        return splitItemId(null);
    }

    private String splitItemId(String currentItemId) {
        if(currentItemId != null) {
            String[] split = currentItemId.split("I0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "I00" + id;
        } else {
            return "I001";
        }
    }
}
