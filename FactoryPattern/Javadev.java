package FactoryPattern;

public class Javadev implements Employee {

    @Override
    public int getSalary() {
        System.out.println("java dev");
        return 80000;
    }

}
