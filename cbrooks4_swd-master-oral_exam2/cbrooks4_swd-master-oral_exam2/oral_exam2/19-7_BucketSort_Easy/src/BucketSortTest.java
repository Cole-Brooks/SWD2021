import java.util.Arrays;

/**
 * BucketSortTest
 * a class that will test the functionality of the Complex class
 *
 * @author cbrooks4
 * @version 1.0
 * @since 4/2/2021
 */

class BucketSortTest {
    public static void main(String[] args){
        // entirely backwards, 1 digit list
        int[] unsorted = new int[]{6,5,4,3,2,1};
        // entirely sorted list
        int[] unsorted2 = new int[]{11,22,33,44};
        // 3 digit list, unsorted
        int[] unsorted3 = new int[]{100, 300, 200, 500, 550, 225, 378};
        // mixed digit list, unsorted
        int[] unsorted4 = new int[]{24, 2, 39, 49, 30, 20, 10, 22, 40, 20, 225, 12, 12, 2301, 24005};

        System.out.println(Arrays.toString(BucketSort.sort(unsorted4)));
    }
}