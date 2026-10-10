import java.util.*;

class PiggyBank {
    private double savings;
    private final String id;

    public PiggyBank(String id) {
        this.id = id;
        this.savings = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            savings += amount;
            System.out.println("Deposited: " + amount + " | Savings = " + savings);
        } else {
            System.out.println("Deposit rejected: amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > savings) {
            System.out.println("Withdraw rejected: insufficient savings.");
        } else {
            savings -= amount;
            System.out.println("Withdrawn: " + amount + " | Savings = " + savings);
        }
    }

    public double getSavings() {
        return savings;
    }
}

class Scorecard {
    private boolean[] results;
    private int recordedCount;

    public Scorecard(int totalQuestions) {
        results = new boolean[totalQuestions];
        recordedCount = 0;
    }

    public void recordAnswer(boolean correct) {
        if (recordedCount < results.length) {
            results[recordedCount++] = correct;
        } else {
            System.out.println("Cannot record more answers than total questions.");
        }
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < recordedCount; i++) {
            if (results[i]) score++;
        }
        return score;
    }
}

class NameTag {
    private final String firstName;
    private final String lastInitial;

    public NameTag(String fullName) {
        String[] parts = fullName.split(" ");
        this.firstName = parts[0];
        this.lastInitial = parts[1].substring(0, 1);
    }

    public String getNickname() {
        return firstName + " " + lastInitial + ".";
    }
}

class Locker {
    private String code;
    private final int lockerNumber;

    public Locker(int lockerNumber, String initialCode) {
        this.lockerNumber = lockerNumber;
        this.code = initialCode;
    }

    public void changeCode(String currentCode, String newCode) {
        if (this.code.equals(currentCode)) {
            this.code = newCode;
            System.out.println("Code changed successfully.");
        } else {
            System.out.println("Code change rejected: incorrect current code.");
        }
    }
}

class AttendanceSheet {
    private String[] presentStudents;
    private int count;

    public AttendanceSheet(int maxSize) {
        presentStudents = new String[maxSize];
        count = 0;
    }

    public void markPresent(String name) {
        if (!isPresent(name)) {
            presentStudents[count++] = name;
        }
    }

    public int getPresentCount() {
        return count;
    }

    public boolean isPresent(String name) {
        for (int i = 0; i < count; i++) {
            if (presentStudents[i].equals(name)) return true;
        }
        return false;
    }
}

public class week7 {
    public static void main(String[] args) {
        // Problem 1: PiggyBank
        PiggyBank pb = new PiggyBank("PB-1");
        pb.deposit(100);
        pb.withdraw(30);
        pb.withdraw(500);

        // Problem 2: Scorecard
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);
        System.out.println("Final Score: " + sc.getScore());

        // Problem 3: NameTag
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("Nickname: " + tag.getNickname());

        // Problem 4: Locker
        Locker l = new Locker(101, "1234");
        l.changeCode("1234", "5678");
        l.changeCode("0000", "9999");

        // Problem 5: AttendanceSheet
        AttendanceSheet sheet = new AttendanceSheet(30);
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana");
        System.out.println("Present count: " + sheet.getPresentCount());
        System.out.println("Is Ben present? " + sheet.isPresent("Ben"));
        System.out.println("Is Chen present? " + sheet.isPresent("Chen"));
    }
}

