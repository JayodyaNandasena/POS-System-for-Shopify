package edu.shopify.bo.custom.impl;

import dto.Employee;
import edu.shopify.bo.custom.EmployeeBo;
import edu.shopify.dao.DaoFactory;
import edu.shopify.dao.custom.EmployeeDao;
import edu.shopify.entity.EmployeeEntity;
import edu.shopify.util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class EmployeeBoImpl implements EmployeeBo {
    private EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    @Override
    public Boolean saveEmployee(Employee dto) {
        return employeeDao.add(new ModelMapper().map(dto, EmployeeEntity.class));
    }

    @Override
    public Boolean validateLogin(String email, String password) {
        return employeeDao.validateLogin(email, password);
    }

    @Override
    public ObservableList<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee searchEmployee(String id) {
        return new ModelMapper().map(employeeDao.searchById(id), Employee.class);
    }

    @Override
    public Boolean updateEmployee(Employee employee) {
        return employeeDao.update(new ModelMapper().map(employee, EmployeeEntity.class));
    }

    @Override
    public Boolean deleteEmployee(String id) {
        return employeeDao.delete(id);
    }
}
