

public class TestTwoDimensArray {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int matrix[][] = new int[][] {
                new int[] {1,2,3,4},
                new int[] {5,6,7,8},
                new int[] {9,10,11,12}
        };
        int x = matrix[0].length;
        int y = matrix.length;
//        for(int i=0;i<x;i++) {
//            for(int j=0;j<y;j++) {
//                System.out.println(matrix[j][i]);
//            } 
//        }
        for(int i=0;i<y;i++) {
            for(int j=0;j<x;j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }
}
