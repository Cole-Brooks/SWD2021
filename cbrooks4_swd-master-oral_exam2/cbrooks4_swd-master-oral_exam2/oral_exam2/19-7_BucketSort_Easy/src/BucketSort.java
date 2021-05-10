import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * BucketSort:
 * A class that can sort an array of positive integers using the bucket sort method
 *
 * @author cbrooks4
 * @version 1.0
 * @since 4/2/2021
 *
 */
public class BucketSort {
    /**
     * sort:
     * @param unsorted  an unsorted list of integers
     * @return  the sorted list of elements containing all elements from unsorted in
     *          ascending order.
     */
    public static int[] sort(int[] unsorted){
        List<Integer> unsortedList = new ArrayList<Integer>();
        for(int num : unsorted){
            unsortedList.add(num);
        }
        unsortedList = listSort(unsortedList);
        // Create the int array to be returned
        for(int i = 0; i < unsorted.length; i++){
            unsorted[i] = unsortedList.get(i);
        }
        return unsorted;
    }
    /**
     * ListSort
     * @param unsorted  a List of Integers that will be sorted and returned
     * @return  the sorted list of integers
     */
    public static List<Integer> listSort(List<Integer> unsorted){
        // Create return List
        List<Integer> retList = new ArrayList<Integer>();

        // Create necessary buckets for bucket sorting
        // Note that bucket 10 is for storage, not for sorting
        List<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= 10; i++){
            buckets.add(new ArrayList<Integer>());
        }

        // Figure out how many digits the maximum number in the unsorted array is
        int max = Collections.max(unsorted);
        int digits = String.valueOf(max).length();

        // Actual Bucket Sort
        ArrayList<Integer> buffer;
        int bucket = 0;
        for(int i = 0; i < digits; i++){

            int place = (int) Math.pow(10,i);

            retList.clear();
            for(int num : unsorted){
                // if the number is less than the place, it has been sorted already
                if(num < place){
                    buckets.get(0).add(num);
                }
                else{
                    bucket = (int) Math.floor((double) num / place) % 10;
                    buckets.get(bucket).add(num);
                }
            }
            // assemble all buckets back into a list
            for(int j = 0; j < 10; j++){
                buffer = buckets.get(j);
                retList.addAll(buffer);
                buffer.clear();
            }

            unsorted.clear();
            unsorted.addAll(retList);
            for(int j = 0; j < buckets.size()-1; j++){
                buckets.get(j).clear();
            }

        }
        return retList;
    };
}