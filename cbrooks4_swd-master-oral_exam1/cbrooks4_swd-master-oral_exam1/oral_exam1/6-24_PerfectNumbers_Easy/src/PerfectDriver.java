import java.util.ArrayList;

public class PerfectDriver{
    public static void main(String[] args){
        ArrayList<Integer> perfectArray = new ArrayList<>();

        int numToCheck = 1000;

        for(int i = 0; i <=numToCheck; i++){
            if(PerfectChecker.isPerfect(i)){
                perfectArray.add(i);
            }
        }
        System.out.println("Perfect numbers between 1 and " + String.valueOf(numToCheck) + ":" + perfectArray);
    }
}