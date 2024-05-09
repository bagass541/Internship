package com.bagas.services;

import com.bagas.entities.Employee;
import com.bagas.entities.Position;
import com.bagas.exceptions.EmployeeNotFoundException;
import com.bagas.exceptions.PositionNotFoundException;
import com.bagas.repositories.interfaces.EmployeeRepository;
import com.bagas.repositories.interfaces.PositionRepository;

import java.util.List;

import static com.bagas.constants.ExceptionMessageConstants.EMPLOYEE_NOT_FOUND_MESSAGE;
import static com.bagas.constants.ExceptionMessageConstants.POSITION_NOT_FOUND_MESSAGE;


public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));
    }

    public Position getPositionById(Long id) throws PositionNotFoundException {
        return positionRepository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(POSITION_NOT_FOUND_MESSAGE));
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void createPosition(Position position) {
        positionRepository.save(position);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.update(employee);
    }

    public void updatePosition(Position position) {
        positionRepository.update(position);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void deletePositionById(Long id) {
        positionRepository.deleteById(id);
    }
}
