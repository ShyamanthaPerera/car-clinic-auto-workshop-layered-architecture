package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DBConnection;
import com.carclinic.car_clinic_auto_workshop.dto.SlotDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class SlotModel {

    public boolean saveSlot(SlotDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_SLOT);

        statement.setString(1, dto.getSlotId());
        statement.setString(2, dto.getStatus());
        statement.setString(3, dto.getSpace());
        statement.setString(4, dto.getChargingOutlet());

        return statement.executeUpdate() > 0;
    }

    public boolean updateSlot(SlotDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_SLOT);

        statement.setString(1, dto.getStatus());
        statement.setString(2, dto.getSpace());
        statement.setString(3, dto.getChargingOutlet());
        statement.setString(5, dto.getSlotId());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteSlot(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SLOT);

        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public SlotDTO searchSlot(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection ();
        PreparedStatement statement = connection.prepareStatement(SEARCH_SLOT);

        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        SlotDTO dto = null;

        if(resultSet.next()) {
            String slot_id = resultSet.getString(1);
            String status = resultSet.getString(2);
            String space = resultSet.getString(3);
            String charging_outlet = resultSet.getString(4);

            dto = new SlotDTO(slot_id, status, space, charging_outlet);
        }
        return dto;
    }

    public List<SlotDTO> getAllSlot() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_SLOT);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<SlotDTO> SlotDtoList = new ArrayList<>();

        while(resultSet.next()) {
            SlotDtoList.add(
                    new SlotDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return SlotDtoList;
    }

    public List<SlotDTO> getAllStatus() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_STATUS);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<SlotDTO> SlotDtoList = new ArrayList<>();

        while(resultSet.next()) {
            SlotDtoList.add(
                    new SlotDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return SlotDtoList;
    }


}
