import java.util.*;

public class week4 {

    // L1. Two Sum
    static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // should not happen per problem statement
    }

    // L2. Best Time to Buy and Sell Stock
    static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) minPrice = price;
            int profit = price - minPrice;
            if (profit > maxProfit) maxProfit = profit;
        }
        return maxProfit;
    }

    // L3. Contains Duplicate
    static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // L4. Merge Two Sorted Arrays
    static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        int[] result = new int[arr1.length + arr2.length];
        int k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        return result;
    }

    // L5. Rotate Array
    static int[] rotateArray(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[(i + k) % n] = nums[i];
        }
        return newArr;
    }

    // Helper to print arrays
    static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- LeetCode Practice Suite ---");
            System.out.println("1. Two Sum");
            System.out.println("2. Best Time to Buy and Sell Stock");
            System.out.println("3. Contains Duplicate");
            System.out.println("4. Merge Two Sorted Arrays");
            System.out.println("5. Rotate Array");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    int[] nums1 = {2, 7, 11, 15};
                    int target1 = 9;
                    printArray(twoSum(nums1, target1)); // [0,1]
                    int[] nums2 = {3, 2, 4};
                    int target2 = 6;
                    printArray(twoSum(nums2, target2)); // [1,2]
                    break;
                case 2:
                    int[] prices1 = {7, 1, 5, 3, 6, 4};
                    System.out.println(maxProfit(prices1)); // 5
                    int[] prices2 = {7, 6, 4, 3, 1};
                    System.out.println(maxProfit(prices2)); // 0
                    break;
                case 3:
                    int[] dup1 = {1, 2, 3, 1};
                    System.out.println(containsDuplicate(dup1)); // true
                    int[] dup2 = {1, 2, 3, 4};
                    System.out.println(containsDuplicate(dup2)); // false
                    break;
                case 4:
                    int[] arr1 = {1, 3, 5};
                    int[] arr2 = {2, 4, 6};
                    printArray(mergeSortedArrays(arr1, arr2)); // [1,2,3,4,5,6]
                    int[] arr3 = {};
                    int[] arr4 = {1, 2, 3};
                    printArray(mergeSortedArrays(arr3, arr4)); // [1,2,3]
                    break;
                case 5:
                    int[] rot1 = {1, 2, 3, 4, 5, 6, 7};
                    printArray(rotateArray(rot1, 3)); // [5,6,7,1,2,3,4]
                    int[] rot2 = {1, 2};
                    printArray(rotateArray(rot2, 3)); // [2,1]
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

