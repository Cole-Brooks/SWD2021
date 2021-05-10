public class PerfectChecker{
    /**
     * isPerfect
     * @param num   the integer number which you would like to check
     * @return boolean  true if the integer is perfect, else false.
     */
    static public boolean isPerfect(int num){
        return isPerfect(num, true);
    }

    /**
     *
     * @param num   the integer number which you would like to check
     * @param verbose   a boolean value. True if you would like verbose logging, else false.
     * @return boolean  true if the integer is perfect, else false.
     */
    static public boolean isPerfect(int num, boolean verbose){
        System.out.println("Factors of " + String.valueOf(num) + ":");
        int sum = 0;
        for(int i = 1; i < num - 1; i++){
            if(num%i == 0){
                sum += i;
                System.out.print(String.valueOf(i) + " ");
            }
        }
        System.out.println();
        if(sum == num){
            if(verbose){System.out.println(String.valueOf(num) + " is Perfect!");}
            return true;
        }
        if(verbose){System.out.println(String.valueOf(num) + " isn't Perfect!");}
        return false;
    }
}