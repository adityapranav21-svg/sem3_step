import java.util.*;

class Player implements Comparable<Player> {
    private String name;
    private int matchesPlayed;
    private double battingAverage;
    private boolean injured;

    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    public String getName() { return name; }
    public int getMatchesPlayed() { return matchesPlayed; }
    public double getBattingAverage() { return battingAverage; }
    public boolean isInjured() { return injured; }

    // Eligibility checks
    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10; // threshold for experience
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 7 && !injured; // borderline rule
    }

    @Override
    public int compareTo(Player other) {
        return Double.compare(other.battingAverage, this.battingAverage);
    }

    static String draftAndRank(Player[] players) {
        List<Player> draftable = new ArrayList<>();
        for (Player p : players) {
            if (isDraftable(p.getMatchesPlayed()) || isDraftable(p.getMatchesPlayed(), p.isInjured())) {
                draftable.add(p);
            }
        }
        Player[] arr = draftable.toArray(new Player[0]);
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append((i+1) + ". " + arr[i].getName());
            if (i < arr.length - 1) sb.append(" | ");
        }
        return sb.toString();
    }
}

public class week5hw {

    // Problem 1: Score Multiplier
    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] *= 2;
        playerScores[viceCaptainIndex] *= 1.5;
    }

    // Problem 2: Duplicate Player Pick Checker
    static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // Problem 3: Top Performer Tracker
    static String findMinMaxSpread(int[] scores) {
        int min = scores[0], max = scores[0];
        for (int score : scores) {
            if (score < min) min = score;
            if (score > max) max = score;
        }
        int spread = max - min;
        return "Min: " + min + " | Max: " + max + " | Spread: " + spread;
    }

    // Problem 4: Match Day Grid Analyzer
    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int val : row) sum += val;
        return (double) sum / row.length;
    }

    static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < runsPerOver.length; i++) {
            double avg = rowAverage(runsPerOver[i]);
            String status = avg >= threshold ? "Power Surge" : "Normal";
            sb.append("Match " + i + ": " + status);
            if (i < runsPerOver.length - 1) sb.append(" | ");
        }
        return sb.toString();
    }

    // Main Demo
    public static void main(String[] args) {
        // Problem 1
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores)); // [40.0, 110.0, 30.0, 93.0]

        // Problem 2
        String[] lineup1 = {"Kohli", "Bumrah", "Kohli", "Rohit"};
        System.out.println(findDuplicatePick(lineup1));
        String[] lineup2 = {"Kohli", "Bumrah", "Rohit"};
        System.out.println(findDuplicatePick(lineup2));

        // Problem 3
        int[] weeklyScores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println(findMinMaxSpread(weeklyScores));

        // Problem 4
        int[][] runs = {
            {4, 6, 8},
            {10, 12, 14},
            {2, 3, 1}
        };
        System.out.println(classifyMatches(runs, 8));

        // Problem 5
        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };
        System.out.println(Player.draftAndRank(players)); // "1. Rahul | 2. Virat | 3. Dev"
    }
}
