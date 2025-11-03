import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*

    Since its introduction in Java 8, the Stream API has become a staple of Java development.
    The basic operations like iterating, filtering, mapping sequences of elements are deceptively simple to use.

    2 main types of stream operations: Intermediate	(Lazy) and Terminal (Executes pipeline)

 */

record Employee(String name, String department, double salary, int age) {
    @Override
    public String toString() {
        return name + " (" + department + ", $" + salary + ")";
    }
}

public class StreamExample {


    public static void main(String... args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 80000, 30),
                new Employee("Bob", "HR", 50000, 45),
                new Employee("Charlie", "IT", 95000, 35),
                new Employee("David", "Finance", 70000, 40),
                new Employee("Eve", "HR", 55000, 28)
        );

        // Filtering Elements
        List<Employee> highEarners = employees.stream()
                .filter(e -> e.salary() > 70000)
                .toList();

        highEarners.forEach(System.out::println);


        // Mapping and Transforming Data
        List<String> names = employees.stream()
                .map(Employee::name)
                .map(String::toUpperCase)
                .toList();
        System.out.println(names);

        // Reducing to a Single Value
        double totalSalaries = employees.stream()
                .map(Employee::salary)
                .reduce(0.0, Double::sum);
        System.out.println("Total Salaries: $" + totalSalaries);

        // Grouping Data
        Map<String, List<Employee>> byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::department));
        byDepartment.forEach((dept, list) -> System.out.println(dept + " -> " + list));

        // Average / Summary Statistics
        double avgSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::salary));
        System.out.println("Average Salary: $" + avgSalary);

        // Sorting
        List<Employee> sortedBySalary = employees.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .toList();
        sortedBySalary.forEach(System.out::println);

        // Finding Elements
        Optional<Employee> oldest = employees.stream()
                .max(Comparator.comparing(Employee::age));
        oldest.ifPresent(System.out::println);

        // Matching Conditions
        boolean allAbove25 = employees.stream()
                .allMatch(e -> e.age() > 30);
        System.out.println("All above 25: " + allAbove25);

        boolean anyInFinance = employees.stream()
                .anyMatch(e -> e.department().equals("Finance"));
        System.out.println("Any in Finance: " + anyInFinance);

        // Joining Strings
        String allNames = employees.stream()
                .map(Employee::name)
                .collect(Collectors.joining(", "));
        System.out.println("Employees: " + allNames);
    }
}
