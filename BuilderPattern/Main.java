package BuilderPattern;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee.Builder("John", "Doe")
                .setAge(30)
                .setDepartment("Engineering")
                .setSalary(60000)
                .build();

        Employee employee2 = new Employee.Builder("Jane", "Smith")
                .setDepartment("Marketing")
                .setSalary(55000)
                .build();

        System.out.println(employee1);
        System.out.println(employee2);
    }
}
