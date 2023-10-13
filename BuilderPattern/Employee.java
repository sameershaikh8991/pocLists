package BuilderPattern;

public class Employee {
    private String firstName;
    private String lastName;
    private int age;
    private String department;
    private double salary;

    private Employee(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.department = builder.department;
        this.salary = builder.salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", department=" + department + ", salary=" + salary + "]";
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;
        private String department;
        private double salary;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}