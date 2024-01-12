package com.carclinic.car_clinic_auto_workshop.bo.custom.impl;

import com.carclinic.car_clinic_auto_workshop.bo.custom.CustomerBO;
import com.carclinic.car_clinic_auto_workshop.dao.DAOFactory;
import com.carclinic.car_clinic_auto_workshop.dao.custom.CustomerDAO;
import com.carclinic.car_clinic_auto_workshop.dto.CustomerDTO;
import com.carclinic.car_clinic_auto_workshop.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAOObject(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<CustomerEntity> customers = customerDAO.getAll();

        for (CustomerEntity customer:customers) {
            customerDTOS.add(new CustomerDTO(
                    customer.getCusId(),
                    customer.getCusName(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getTelNum()
            ));
        }
        return customerDTOS;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new CustomerEntity(
                customerDTO.getCusId(),
                customerDTO.getCusName(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getTelNum()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new CustomerEntity(
                customerDTO.getCusId(),
                customerDTO.getCusName(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getTelNum()
        ));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> search(String newValue) throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<CustomerEntity> customerEntities = customerDAO.search(newValue);

        for (CustomerEntity customer:customerEntities) {
            customerDTOS.add(new CustomerDTO(
                    customer.getCusId(),
                    customer.getCusName(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getTelNum()
            ));
        }
        return customerDTOS;
    }
}
