import java.util.*;

public class OrganizationManagement {
    static class Employee {
        int id;
        String name;
        String department;
        double salary;

        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name.trim(); // Trim spaces during initialization
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: $" + salary;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true; // Check if same object
            if (obj == null || getClass() != obj.getClass()) return false; // Check type

            Employee other = (Employee) obj; // Cast to Employee
            return name.equalsIgnoreCase(other.name.trim()); // Compare names case-insensitively
        }

        @Override
        public int hashCode() {
            return name.toLowerCase().trim().hashCode(); // Ensure consistency with equals
        }
    }

    public static void main(String[] args) {
        Map<Integer, Employee> employees = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Organization Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Search Employee by Name");
            System.out.println("5. Update Employee Details");
            System.out.println("6. Count Employees");
            System.out.println("7. Sort Employees");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Employee Salary: ");
                    double salary = scanner.nextDouble();

                    employees.put(id, new Employee(id, name, department, salary));
                    System.out.println("Employee added successfully!");
                    break;

                case 2:
                    // Display All Employees
                    if (employees.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        System.out.println("\n--- Employee List ---");
                        for (Employee employee : employees.values()) {
                            System.out.println(employee);
                        }
                    }
                    break;

                case 3:
                    // Search Employee by ID
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();
                    if (employees.containsKey(searchId)) {
                        System.out.println("Employee Found: " + employees.get(searchId));
                    } else {
                        System.out.println("Employee with ID " + searchId + " not found.");
                    }
                    break;

                case 4:
                    // Search Employee by Name
                    System.out.print("Enter Employee Name to search (partial or full): ");
                    scanner.nextLine(); // Consume newline
                    String searchName = scanner.nextLine().trim();
                    boolean found = false;
                    for (Employee employee : employees.values()) {
                        if (employee.name.equalsIgnoreCase(searchName)) {
                            System.out.println(employee);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No employees found with name \"" + searchName + "\".");
                    }
                    break;

                case 5:
                    // Update Employee Details
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = scanner.nextInt();
                    if (employees.containsKey(updateId)) {
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Name (leave blank to keep unchanged): ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Department (leave blank to keep unchanged): ");
                        String newDepartment = scanner.nextLine();
                        System.out.print("Enter New Salary (enter -1 to keep unchanged): ");
                        double newSalary = scanner.nextDouble();

                        Employee emp = employees.get(updateId);
                        if (!newName.isEmpty()) emp.name = newName.trim();
                        if (!newDepartment.isEmpty()) emp.department = newDepartment;
                        if (newSalary != -1) emp.salary = newSalary;

                        System.out.println("Employee details updated successfully!");
                    } else {
                        System.out.println("Employee with ID " + updateId + " not found.");
                    }
                    break;

                case 6:
                    // Count Employees
                    System.out.println("Total number of employees: " + employees.size());
                    break;

                case 7:
                    // Sort Employees
                    if (employees.isEmpty()) {
                        System.out.println("No employees to sort.");
                        break;
                    }
                    System.out.println("Sort by:");
                    System.out.println("1. ID");
                    System.out.println("2. Name");
                    System.out.println("3. Department");
                    System.out.println("4. Salary");
                    System.out.print("Choose an option: ");
                    int sortChoice = scanner.nextInt();

                    List<Employee> employeeList = new ArrayList<>(employees.values());
                    switch (sortChoice) {
                        case 1:
                            employeeList.sort(Comparator.comparingInt(emp -> emp.id));
                            break;
                        case 2:
                            employeeList.sort(Comparator.comparing(emp -> emp.name.toLowerCase()));
                            break;
                        case 3:
                            employeeList.sort(Comparator.comparing(emp -> emp.department.toLowerCase()));
                            break;
                        case 4:
                            employeeList.sort(Comparator.comparingDouble(emp -> emp.salary));
                            break;
                        default:
                            System.out.println("Invalid option. Returning to main menu.");
                            continue;
                    }
                    System.out.println("\n--- Sorted Employee List ---");
                    for (Employee employee : employeeList) {
                        System.out.println(employee);
                    }
                    break;

                case 8:
                    // Exit
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}