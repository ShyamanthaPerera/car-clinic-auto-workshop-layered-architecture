package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DbConnection;
import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;
import com.carclinic.car_clinic_auto_workshop.dto.VehicleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class VehicleModel {

    public boolean saveVehicle(VehicleDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SAVE_VEHICLE);

        statement.setString(1, dto.getVclId());
        statement.setString(2, dto.getCusId());
        statement.setString(3, dto.getVclCategory());
        statement.setString(4, dto.getManufacturer());
        statement.setString(5, dto.getModel());
        statement.setString(6, "system");
        statement.setDate(7,new java.sql.Date(new java.util.Date().getTime()));
        statement.setString(8,null);
        statement.setString(9,null);

        return statement.executeUpdate() > 0;
    }

    public boolean updateVehicle(VehicleDTO dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE);

        statement.setString(1, dto.getCusId());
        statement.setString(2, dto.getVclCategory());
        statement.setString(3, dto.getManufacturer());
        statement.setString(4, dto.getModel());
        statement.setString(5, dto.getVclId());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteVehicle(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE);

        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public VehicleDTO searchVehicle(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection ();
        PreparedStatement statement = connection.prepareStatement(SEARCH_VEHICLE);

        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        VehicleDTO dto = null;

        if(resultSet.next()) {
            String vcl_id = resultSet.getString(1);
            String cus_id = resultSet.getString(2);
            String category = resultSet.getString(4);
            String manufacturer = resultSet.getString(5);
            String model = resultSet.getString(6);

            dto = new VehicleDTO(vcl_id, cus_id, category, manufacturer, model);
        }
        return dto;
    }



    public List<VehicleDTO> getAllVehicle() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_VEHICLE);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<VehicleDTO> VehicleDtoList = new ArrayList<>();

        while(resultSet.next()) {
            VehicleDtoList.add(
                    new VehicleDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(11)
                    )
            );
        }
        return VehicleDtoList;
    }

    public List<VehicleDTO> getAllVehicleBySearch(String searchVal) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_VEHICLE_BY_SEARCH_VAL);

        for (int i = 1; i <= 5; i++) {
            statement.setString(i, "%" + searchVal + "%");
        }

        ResultSet resultSet = statement.executeQuery();

        ArrayList<VehicleDTO> vehicleDtoList = new ArrayList<>();

        while(resultSet.next()) {
            vehicleDtoList.add(
                    new VehicleDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return vehicleDtoList;
    }

    public String generateNextVehicleId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_VEHICLE_ID);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return splitVehicleId(resultSet.getString(1));
        }
        return splitVehicleId(null);
    }

    private String splitVehicleId(String currentVehicleId) {
        if(currentVehicleId != null) {
            String[] split = currentVehicleId.split("V0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "V00" + id;
        } else {
            return "V001";
        }
    }

}
