package com.example.demo;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public final class EmployeeServiceImpl implements EmployeeService {

	EmployeeServiceImpl() {
		employees.put(1, new Employee(1, "Josh", "dev"));
		employees.put(2, new Employee(2, "Rev", "qa"));
		employees.put(3, new Employee(3, "Kaustuv", "dev"));
		employees.put(4, new Employee(4, "Sam", "Hr"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployee(java.lang.Integer)
	 */
	@Cacheable(value = "employeeCache", key = "#id", sync = true)
	@Override
	public Employee getEmployee(Integer id) {
		return employees.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#addEmployees(com.example.demo.Employee)
	 */
	@Override
	public Employee addEmployees(Employee newEmp) {
		return employees.put(newEmp.getId(), newEmp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#deleteEmployee(java.lang.Integer)
	 */
	public Employee deleteEmployee(Integer id) {
		Employee deletedEmployee = employees.remove(id);
		return deletedEmployee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#updateEmployee(java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	public Employee updateEmployee(Integer id, String name, String department) {
		Employee modEmp = employees.get(id);
		if (StringUtils.hasLength(name))
			modEmp.setName(name);
		if (StringUtils.hasLength(department))
			modEmp.setDepartment(department);
		return employees.put(id, modEmp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployees()
	 */
	@Cacheable(value = "employeeCache", sync = true)
	public Collection<Employee> getEmployees() {
		return employees.values();
	}
}