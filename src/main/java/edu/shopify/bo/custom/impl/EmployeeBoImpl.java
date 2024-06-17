package edu.shopify.bo.custom.impl;

import edu.shopify.dto.Employee;
import edu.shopify.bo.custom.EmployeeBo;
import edu.shopify.dao.DaoFactory;
import edu.shopify.dao.custom.EmployeeDao;
import edu.shopify.entity.EmployeeEntity;
import edu.shopify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EmployeeBoImpl implements EmployeeBo {
    private EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);

    public EmployeeBoImpl() throws Exception {
    }

    @Override
    public Boolean saveEmployee(Employee dto) throws Exception {
//        dto.setPassword(encryptPassword(dto.getPassword()));
        return employeeDao.add(new ModelMapper().map(dto, EmployeeEntity.class));
    }

    @Override
    public Boolean validateLogin(String email, String password) throws Exception {
//        System.out.println("Login encrypt = "+encryptPassword(password));
//        return employeeDao.validateLogin(email, encryptPassword(password));
        return employeeDao.validateLogin(email, password);
    }

    @Override
    public ObservableList<Employee> getAllEmployees() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        for (EmployeeEntity employeeEntity : employeeDao.getAll()) {
            employeeList.add(new ModelMapper().map(employeeEntity,Employee.class));
        }

        return employeeList;
    }

    @Override
    public Employee searchEmployee(String id) throws Exception {
        EmployeeEntity employee = employeeDao.searchById(id);
        if (employee != null)
            return new ModelMapper().map(employeeDao.searchById(id), Employee.class);
        return null;


        //employee.setPassword(decryptPassword(employee.getPassword()));
    }

    @Override
    public Boolean updateEmployee(Employee employee) {
        return employeeDao.update(new ModelMapper().map(employee, EmployeeEntity.class));
    }

    @Override
    public Boolean deleteEmployee(String id) {
        return employeeDao.delete(id);
    }
/*
    private static final String ALGORITHM = "AES";
    private static final String KEY = "encryptedpassword";

    private SecretKeySpec getKeySpec() {
        return new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    }

    public String encryptPassword(String password) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKeySpec());
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKeySpec());
        byte[] decryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        return new String(cipher.doFinal(decryptedBytes));
    }
    */
}
