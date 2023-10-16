package FactoryPattern;

public class EmployeeFactory {
    public static Employee getEmp(String emp){
        if(emp.trim().equals("Web")){
            return new WebDev();
        }
        else if(emp.trim().equals("java")){
            return new Javadev();
        }
        else{
            return null;
        }
    }
    
}
