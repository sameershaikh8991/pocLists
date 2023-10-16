package FactoryPattern;

public class Dev {
    public static void main(String[] args) {

        Employee emp = EmployeeFactory.getEmp("Web");
        System.out.println(emp.getSalary());


          Employee emp1 = EmployeeFactory.getEmp("java");
        System.out.println(emp1.getSalary());
    }

}
