package tech.android.tcmp13.fullfragmentdemo.employees.model;

/**
 * Created by tcmp13-t on 12/21/2016.
 */
public class Employee {

    private String name;
    private String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!name.equals(employee.name)) return false;
        return department.equals(employee.department);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + department.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return name + " from: " + department;
    }
}
