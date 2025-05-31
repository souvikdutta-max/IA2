// hard-level greedy algorithm problem // 
//Minimum Number of Arrows to Burst Balloons//

import java.util.Arrays;

public class MinArrowsToBurstBalloons {

    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;

      
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int currentEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            
            if (points[i][0] > currentEnd) {
                arrows++;
                currentEnd = points[i][1];
            }
        }

        return arrows;
    }

    public static void main(String[] args) {
        int[][] balloons = {{10,16}, {2,8}, {1,6}, {7,12}};
        System.out.println("Minimum arrows needed: " + findMinArrowShots(balloons));  // Output: 2
    }
}
