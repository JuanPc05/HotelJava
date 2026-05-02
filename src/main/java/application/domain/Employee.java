package application.domain;

public class Employee extends Person{

    private String position;
    private double salary;

    public Employee(){

        super();
    }

    public Employee(int id, String name, String lastName, String email, String password, Boolean state, String position, double salary) {
        super(id, name, lastName, email, password, state);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return """
           Employee Info:
           ----------------------
           Name: %s %s (ID: %d)
           Position: %s
           Salary: %.2f
           Status: %s
           """.formatted(getName(), getLastName(), getId(), position, salary, getState() ? "Active" : "Inactive");
    }
}
