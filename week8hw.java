import java.util.*;
import java.time.LocalDate;

// ---------- Problem 1: Canteen Billing ----------
abstract class Customer {
    double amount;
    Customer(double amount) { this.amount = amount; }
    abstract double finalAmount();
}

class Student extends Customer {
    Student(double amount) { super(amount); }
    double finalAmount() { return amount * 0.9; } // 10% discount
}

class Staff extends Customer {
    Staff(double amount) { super(amount); }
    double finalAmount() { return amount * 0.95; } // 5% discount
}

class Guest extends Customer {
    Guest(double amount) { super(amount); }
    double finalAmount() { return amount + 10; } // ₹10 service charge
}

// ---------- Problem 2: Parking Charges ----------
abstract class Vehicle {
    int hours;
    Vehicle(int hours) { this.hours = hours; }
    abstract double charge();
}

class Bike extends Vehicle {
    Bike(int hours) { super(hours); }
    double charge() { return hours * 10; }
}

class Car extends Vehicle {
    Car(int hours) { super(hours); }
    double charge() { return 30 + (hours - 1) * 20; }
}

class Truck extends Vehicle {
    Truck(int hours) { super(hours); }
    double charge() { return Math.max(100, hours * 50); }
}

// ---------- Problem 3: Hostel Electricity Bill ----------
abstract class Room {
    int units;
    Room(int units) { this.units = units; }
    abstract double bill();
}

class SingleRoom extends Room {
    SingleRoom(int units) { super(units); }
    double bill() { return units * 8; }
}

class SharedRoom extends Room {
    int occupants;
    SharedRoom(int units, int occupants) { super(units); this.occupants = occupants; }
    double bill() { return (units * 6.0) / occupants; }
}

class AcRoom extends Room {
    AcRoom(int units) { super(units); }
    double bill() { return units * 10 + 200; }
}

// ---------- Problem 4: Festival Bonus ----------
abstract class Employee {
    String name;
    double salary;
    Employee(String name, double salary) { this.name = name; this.salary = salary; }
    abstract double bonus();
}

class FullTime extends Employee {
    FullTime(String name, double salary) { super(name, salary); }
    double bonus() { return salary * 0.10; }
}

class PartTime extends Employee {
    PartTime(String name, double salary) { super(name, salary); }
    double bonus() { return salary * 0.05; }
}

class Intern extends Employee {
    Intern(String name, double salary) { super(name, salary); }
    double bonus() { return 2000; }
}

// ---------- Problem 5: Streaming Plan ----------
abstract class Plan {
    String name;
    LocalDate startDate;
    Plan(String name, LocalDate startDate) { this.name = name; this.startDate = startDate; }
    abstract LocalDate renewalDate();
}

class BasicPlan extends Plan {
    BasicPlan(String name, LocalDate startDate) { super(name, startDate); }
    LocalDate renewalDate() { return startDate.plusDays(30); }
}

class StandardPlan extends Plan {
    StandardPlan(String name, LocalDate startDate) { super(name, startDate); }
    LocalDate renewalDate() { return startDate.plusDays(90); }
}

class PremiumPlan extends Plan {
    PremiumPlan(String name, LocalDate startDate) { super(name, startDate); }
    LocalDate renewalDate() { return startDate.plusDays(365); }
}

// ---------- Main Demo ----------
public class week8hw {
    public static void main(String[] args) {
        // Problem 1: Canteen Billing
        Customer[] bills = {
            new Student(200),
            new Staff(300),
            new Guest(150)
        };
        double totalBill = 0;
        for (Customer c : bills) {
            double amt = c.finalAmount();
            System.out.printf("%s: %.2f%n", c.getClass().getSimpleName().toUpperCase(), amt);
            totalBill += amt;
        }
        System.out.printf("Total: %.2f%n%n", totalBill);

        // Problem 2: Parking Charges
        Vehicle[] vehicles = {
            new Bike(3),
            new Car(4),
            new Truck(1),
            new Car(1)
        };
        double totalCharge = 0;
        for (Vehicle v : vehicles) {
            double fee = v.charge();
            System.out.printf("%s: %.2f%n", v.getClass().getSimpleName().toUpperCase(), fee);
            totalCharge += fee;
        }
        System.out.printf("Total: %.2f%n%n", totalCharge);

        // Problem 3: Hostel Electricity Bill
        Room[] rooms = {
            new SingleRoom(120),
            new SharedRoom(150,3),
            new AcRoom(100)
        };
        double totalBillRooms = 0;
        for (Room r : rooms) {
            double b = r.bill();
            System.out.printf("%s: %.2f%n", r.getClass().getSimpleName().toUpperCase(), b);
            totalBillRooms += b;
        }
        System.out.printf("Total: %.2f%n%n", totalBillRooms);

        // Problem 4: Festival Bonus
        Employee[] employees = {
            new FullTime("Asha",50000),
            new PartTime("Ravi",30000),
            new Intern("Neha",15000)
        };
        double totalBonus = 0;
        for (Employee e : employees) {
            double b = e.bonus();
            System.out.printf("%s: %.2f%n", e.name, b);
            totalBonus += b;
        }
        System.out.printf("Total Bonus: %.2f%n%n", totalBonus);

        // Problem 5: Streaming Plan
        Plan[] plans = {
            new BasicPlan("Asha", LocalDate.of(2024,1,15)),
            new StandardPlan("Ravi", LocalDate.of(2024,2,1)),
            new PremiumPlan("Neha", LocalDate.of(2024,3,10)),
            new BasicPlan("Kiran", LocalDate.of(2024,12,20))
        };
        for (Plan p : plans) {
            System.out.println(p.name + ": " + p.renewalDate());
        }
    }
}
