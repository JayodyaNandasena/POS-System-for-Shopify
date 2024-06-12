package edu.shopify.bo.custom;

import dto.Employee;
import edu.shopify.bo.SuperBo;
import javafx.collections.ObservableList;

public interface EmployeeBo extends SuperBo {
    Boolean saveEmployee(Employee dto);
    Boolean validateLogin(String email, String password);
    ObservableList<Employee> getAllEmployees();
    Employee searchEmployee(String id);
    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(String id);
}
