package edu.shopify.bo.custom;

import edu.shopify.dto.Employee;
import edu.shopify.bo.SuperBo;
import javafx.collections.ObservableList;

public interface EmployeeBo extends SuperBo {
    Boolean saveEmployee(Employee dto) throws Exception;
    Boolean validateLogin(String email, String password) throws Exception;
    ObservableList<Employee> getAllEmployees();
    Employee searchEmployee(String id) throws Exception;
    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(String id);
    Boolean readdEmployee(String id);
}
