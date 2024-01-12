package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.AppointmentBO;
import com.carclinic.car_clinic_auto_workshop.dao.DAOFactory;
import com.carclinic.car_clinic_auto_workshop.dao.custom.*;
import com.carclinic.car_clinic_auto_workshop.db.DBConnection;
import com.carclinic.car_clinic_auto_workshop.dto.*;
import com.carclinic.car_clinic_auto_workshop.entity.*;
import com.carclinic.car_clinic_auto_workshop.view.tdm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.carclinic.car_clinic_auto_workshop.constant.Query.SAVE_ORDER_DETAIL;
import static com.carclinic.car_clinic_auto_workshop.constant.Query.UPDATE_NEW_QTY;

public class AppointmentBOImpl implements AppointmentBO {

    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAOObject(DAOFactory.DAOTypes.APPOINTMENT);
    SlotDAO slotDAO = (SlotDAO) DAOFactory.getDaoFactory().getDAOObject(DAOFactory.DAOTypes.SLOT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAOObject(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAOObject(DAOFactory.DAOTypes.ORDER_DETAIL);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAOObject(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(AppointmentDTO dto) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            final boolean isSaveAppointment = saveAppointment(dto);
            if (isSaveAppointment) {
                final boolean isUpdateSlot = updateSlot(dto);
                if (isUpdateSlot) {
                   /* final boolean isSaveEmployee = saveEmployeeDetails(dto, connection);
                    if (isSaveEmployee) {*/
                        final boolean isSaveOrder = saveOrder(dto);
                        if (isSaveOrder) {
                            final boolean isSaveItemList = saveOrderDetailsList(dto);
                            if (isSaveItemList) {
                                final boolean isUpdateItemQty = updateItemList(dto);
                                if (isUpdateItemQty) {
                                    connection.commit();
                                }
                                return true;
                            }
                        }
                    }
                }
            //}
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean saveAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        return appointmentDAO.save(new AppointmentEntity(
                appointmentDTO.getAppointmentId(),
                appointmentDTO.getSlotId(),
                appointmentDTO.getVehicleId(),
                appointmentDTO.getVehicleModel(),
                appointmentDTO.getCustomerId(),
                appointmentDTO.getCustomerName(),
                appointmentDTO.getDate(),
                appointmentDTO.getTime(),
                appointmentDTO.getIssue(),
                appointmentDTO.getStatus()
        ));
    }

    @Override
    public boolean updateSlot(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        return slotDAO.update(new SlotEntity(appointmentDTO.getSlotId()));
    }

    @Override
    public boolean saveOrder(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {

        if (appointmentDTO.getOrderDTO() != null){
            return orderDAO.save(new OrderEntity(
                    appointmentDTO.getOrderDTO().getOrderId(),
                    appointmentDTO.getAppointmentId(),
                    appointmentDTO.getOrderDTO().getAmount(),
                    appointmentDTO.getOrderDTO().getDate(),
                    appointmentDTO.getOrderDTO().getTime()
            ));
        }
        return true;
    }

    @Override
    public boolean saveOrderDetailsList(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {

        if (appointmentDTO.getItemDTOList() != null && !appointmentDTO.getItemDTOList().isEmpty()) {
            for (ItemTM itemTM : appointmentDTO.getItemDTOList()) {
                return orderDetailDAO.save(new OrderDetailEntity(
                        appointmentDTO.getOrderDTO().getOrderId(),
                        itemTM.getItem_id(),
                        itemTM.getQty_on_hand()
                ));
            }
        }
        return true;
    }

    @Override
    public boolean updateItemList(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {

        if (appointmentDTO.getItemDTOList() != null && !appointmentDTO.getItemDTOList().isEmpty()) {
            for (ItemTM itemTM : appointmentDTO.getItemDTOList()) {

                return itemDAO.update(new ItemEntity(
                        itemTM.getQty_on_hand(),
                        itemTM.getItem_id())
                );
            }
        }
        return true;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existAppointment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteAppointment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewAppointmentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
