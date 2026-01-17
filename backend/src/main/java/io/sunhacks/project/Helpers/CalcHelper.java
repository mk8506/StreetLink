package io.sunhacks.project.Helpers;

public class CalcHelper {

    public float average(int[] nums) {
        float avg, sum = 0;
        int length = nums.length;
        for (int age : nums) {
            sum += age;
        }
        avg = sum / length;
        return avg;
    }
}
