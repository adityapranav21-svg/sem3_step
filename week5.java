import java.util.*;

class Candidate implements Comparable<Candidate> {
    private String name;
    private double cgpa;
    private int codingScore;

    public Candidate(String name, double cgpa, int codingScore) {
        this.name = name;
        this.cgpa = cgpa;
        this.codingScore = codingScore;
    }

    public String getName() { return name; }
    public double getCgpa() { return cgpa; }
    public int getCodingScore() { return codingScore; }

    // Composite score: CGPA*10 + codingScore
    public double compositeScore() {
        return cgpa * 10 + codingScore;
    }

    @Override
    public int compareTo(Candidate other) {
        return Double.compare(other.compositeScore(), this.compositeScore());
    }

    // Eligibility checks
    static boolean isEligible(double cgpa) {
        return cgpa >= 7.5; // threshold
    }

    static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= 7.0 && codingScore >= 60; // borderline rule
    }

    static String shortlistAndRank(Candidate[] candidates) {
        List<Candidate> shortlist = new ArrayList<>();
        for (Candidate c : candidates) {
            if (isEligible(c.getCgpa()) || isEligible(c.getCgpa(), c.getCodingScore())) {
                shortlist.add(c);
            }
        }
        Candidate[] arr = shortlist.toArray(new Candidate[0]);
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append((i+1) + ". " + arr[i].getName() + " (" + arr[i].compositeScore() + ")");
            if (i < arr.length - 1) sb.append(" | ");
        }
        return sb.toString();
    }
}

public class week5 {

    // Problem 1: Curve Scores
    static void curveScores(int[] scores, int bonus) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus;
        }
    }

    // Problem 2: Duplicate Team Name Finder
    static String findDuplicateTeam(String[] teamNames) {
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // Problem 3: Top-3 Podium Finder
    static int[] findTopThreeScores(int[] scores) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
        for (int score : scores) {
            if (score >= first) {
                third = second;
                second = first;
                first = score;
            } else if (score >= second) {
                third = second;
                second = score;
            } else if (score >= third) {
                third = score;
            }
        }
        return new int[]{first, second, third};
    }

    // Problem 4: Seating Grid Optimizer
    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int val : row) sum += val;
        return (double) sum / row.length;
    }

    static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seatingScores.length; i++) {
            double avg = rowAverage(seatingScores[i]);
            String status = avg >= threshold ? "Buzzing Zone" : "Quiet Zone";
            sb.append("Row " + i + ": " + status);
            if (i < seatingScores.length - 1) sb.append(" | ");
        }
        return sb.toString();
    }

    // Main Demo
    public static void main(String[] args) {
        // Problem 1
        int[] scores = {70, 85, 60};
        curveScores(scores, 10);
        System.out.println(Arrays.toString(scores)); // [80,95,70]

        // Problem 2
        String[] teams1 = {"ByteForce", "CodeCrafters", "ByteForce"};
        System.out.println(findDuplicateTeam(teams1));
        String[] teams2 = {"ByteForce", "CodeCrafters", "NullPointers"};
        System.out.println(findDuplicateTeam(teams2));

        // Problem 3
        int[] podiumScores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println(Arrays.toString(findTopThreeScores(podiumScores))); // [90,90,82]

        // Problem 4
        int[][] seating = {
            {40, 50, 45},
            {85, 90, 95},
            {30, 20, 25}
        };
        System.out.println(classifyRows(seating, 60));

        // Problem 5
        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };
        System.out.println(Candidate.shortlistAndRank(candidates));
    }
}

