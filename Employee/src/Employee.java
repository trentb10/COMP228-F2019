public class Employee {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;
    private String position;
    private Date hireDate;
    private double annualSalary;

    public Employee(String firstName,
                    String lastName,
                    Date birthDate,
                    String address,
                    String position,
                    Date hireDate,
                    double annualSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.position = position;
        this.hireDate = hireDate;
        this.annualSalary = annualSalary;
    }

    // String Override

    @Override
    public String toString() {
        // Format annualSalary with two decimal places
        String strAnnualSalary = String.format("%,.2f", annualSalary);

        return    "First Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nBirth Date: " + birthDate +
                "\nAddress: " + address +
                "\nPosition: " + position +
                "\nHire Date: " + hireDate +
                "\nAnnual Salary: $" + strAnnualSalary;
    }
}

/*
    .
   _|________________
    |  .--.--.
    |  |  |  |
  `-`--'  '  `-

 */