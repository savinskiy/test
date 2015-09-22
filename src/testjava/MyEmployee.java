/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjava;

/**
 *
 * @author DimaZ
 */
public class MyEmployee implements Employee {

    private int salary = 1000;
    private String firstName = "", lastName = "";
    private Employee manager;

    public MyEmployee() {
        this.firstName = "Default firstName";
        this.lastName = "Default lastName";
    }
    
    public MyEmployee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MyEmployee(String firstName, String lastName, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void increaseSalary(int value) {
        salary += value;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {
        final String NOMANAGER = "No Manager";
        if (manager == null) {
            return NOMANAGER;
        } else {
            return manager.getFullName();
        }
    }

    @Override
    public Employee getTopManager() {
        if (manager == null) {
            return this;
        }
        return manager.getTopManager();
    }
}
