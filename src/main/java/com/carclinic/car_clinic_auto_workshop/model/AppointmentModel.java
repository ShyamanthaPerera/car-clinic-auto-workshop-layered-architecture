package com.carclinic.car_clinic_auto_workshop.model;

import com.carclinic.car_clinic_auto_workshop.db.DBConnection;
import com.carclinic.car_clinic_auto_workshop.dto.AppointmentDTO;
import com.carclinic.car_clinic_auto_workshop.dto.EmployeeDTO;
import com.carclinic.car_clinic_auto_workshop.view.tdm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.*;

public class AppointmentModel {


    public boolean save(AppointmentDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            final boolean isSaveAppointment = saveAppointment(dto, connection);
            if (isSaveAppointment) {
                final boolean isUpdateSlot = updateSlot(dto, connection);
                if (isUpdateSlot) {
                    final boolean isSaveEmployee = saveEmployeeDetails(dto, connection);
                    if (isSaveEmployee) {
                        final boolean isSaveOrder = saveOrder(dto, connection);
                        if (isSaveOrder) {
                            final boolean isSaveItemList = saveOrderDetailsList(dto, connection);
                            if (isSaveItemList) {
                                final boolean isUpdateItemQty = updateItemList(dto, connection);
                                if (isUpdateItemQty) {
                                    connection.commit();
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    public boolean saveAppointment(AppointmentDTO dto, Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(SAVE_APPOINTMENT);

        statement.setString(1, dto.getAppointmentId());
        statement.setString(2, dto.getSlotId());
        statement.setString(3, dto.getVehicleId());
        statement.setString(4, dto.getVehicleModel());
        statement.setString(5, dto.getCustomerId());
        statement.setString(6, dto.getCustomerName());
        statement.setString(7, dto.getDate());
        statement.setString(8, dto.getTime());
        statement.setString(9, dto.getIssue());
        statement.setString(10, dto.getStatus());
        statement.setString(11, "system");
        statement.setDate(12, new java.sql.Date(new java.util.Date().getTime()));
        statement.setString(13, null);
        statement.setString(14, null);

        return statement.executeUpdate() > 0;
    }

    public boolean updateSlot(AppointmentDTO dto, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_SLOT_STATUS);

        statement.setString(1, "BUSY");
        statement.setString(2, dto.getSlotId());

        return statement.executeUpdate() > 0;
    }

    public boolean saveEmployeeDetails(AppointmentDTO dto, Connection connection) throws SQLException {
        if (dto.getEmployeeDTOList() != null && !dto.getEmployeeDTOList().isEmpty()) {
            for (EmployeeDTO employeeDTO : dto.getEmployeeDTOList()) {
                PreparedStatement statement = connection.prepareStatement(SAVE_EMPLOYEE_APPOINTMENT);
                statement.setString(1, dto.getAppointmentId());
                statement.setString(2, employeeDTO.getEmpId());
                statement.setString(3, "system");
                statement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
                statement.setString(5, null);
                statement.setString(6, null);

                final boolean isEmployeeAdd = statement.executeUpdate() > 0;
            }
        }
        return true;
    }

    public boolean saveOrder(AppointmentDTO dto, Connection connection) throws SQLException {

        if (dto.getOrderDTO() != null) {
            PreparedStatement statement = connection.prepareStatement(SAVE_ORDER);

            statement.setString(1, dto.getOrderDTO().getOrderId());
            statement.setString(2, dto.getAppointmentId());
            statement.setString(3, dto.getOrderDTO().getAmount());
            statement.setString(4, dto.getOrderDTO().getDate());
            statement.setString(5, dto.getOrderDTO().getTime());
            statement.setString(6, "system");
            statement.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
            statement.setString(8, null);
            statement.setString(9, null);
            return statement.executeUpdate() > 0;
        }
        return true;
    }

    public boolean saveOrderDetailsList(AppointmentDTO dto, Connection connection) throws SQLException {

        if (dto.getItemDTOList() != null && !dto.getItemDTOList().isEmpty()) {
            for (ItemTM itemTM : dto.getItemDTOList()) {
                PreparedStatement statement = connection.prepareStatement(SAVE_ORDER_DETAIL);


                statement.setString(1, dto.getOrderDTO().getOrderId());
                statement.setString(2, itemTM.getItem_id());
                statement.setInt(3, itemTM.getQty_on_hand());
                statement.setString(4, "system");
                statement.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
                statement.setString(6, null);
                statement.setString(7, null);

                final boolean isSaveItemList = statement.executeUpdate() > 0;
            }
        }
        return true;
    }

    public boolean updateItemList(AppointmentDTO dto, Connection connection) throws SQLException {

        if (dto.getItemDTOList() != null && !dto.getItemDTOList().isEmpty()) {
            for (ItemTM itemTM : dto.getItemDTOList()) {
                PreparedStatement statement = connection.prepareStatement(UPDATE_NEW_QTY);

                statement.setInt(1, itemTM.getQty_on_hand());
                statement.setString(2, itemTM.getItem_id());

                final boolean isUpdateItemList = statement.executeUpdate() > 0;
            }
        }
        return true;
    }


    public boolean updateAppointment(AppointmentDTO dto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT);

        statement.setString(1, dto.getCustomerId());
        statement.setString(2, dto.getVehicleId());
        statement.setString(3, dto.getSlotId());
        statement.setString(4, dto.getDate());
        statement.setString(5, dto.getTime());
        statement.setString(6, dto.getIssue());
        statement.setString(7, dto.getAppointmentId());

        return statement.executeUpdate() > 0;
    }

    public boolean deleteAppointment(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_APPOINTMENT);

        statement.setString(1, id);

        return statement.executeUpdate() > 0;
    }

    public AppointmentDTO searchAppointment(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SEARCH_APPOINTMENT);

        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        AppointmentDTO dto = null;

        if (resultSet.next()) {
            String appointmentId = resultSet.getString(1);
            String slotId = resultSet.getString(2);
            String vehicleId = resultSet.getString(3);
            String vehicleModel = resultSet.getString(4);
            String customerId = resultSet.getString(5);
            String customerName = resultSet.getString(6);
            String date = resultSet.getString(7);
            String time = resultSet.getString(8);
            String issue = resultSet.getString(9);
            String status = resultSet.getString(10);

            dto = new AppointmentDTO(appointmentId, slotId, vehicleId, vehicleModel, customerId, customerName, date, time, issue, status);
        }
        return dto;
    }


    public List<AppointmentDTO> getAllAppointment() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(LOAD_ALL_APPOINTMENT);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<AppointmentDTO> AppointmentDtoList = new ArrayList<>();

        while (resultSet.next()) {
            AppointmentDtoList.add(
                    new AppointmentDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10)
                    )
            );
        }
        return AppointmentDtoList;
    }

    public String generateNextAppointmentId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_APPOINTMENT_ID);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return splitAppointmentId(resultSet.getString(1));
        }
        return splitAppointmentId(null);
    }

    private String splitAppointmentId(String currentAppointmentId) {
        if (currentAppointmentId != null) {
            String[] split = currentAppointmentId.split("A0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "A00" + id;
        } else {
            return "A001";
        }
    }


}
