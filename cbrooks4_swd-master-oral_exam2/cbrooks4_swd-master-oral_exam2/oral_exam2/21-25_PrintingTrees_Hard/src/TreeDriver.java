import java.util.Random;

public class TreeDriver {
    public static void main(String[] args){
        // The tree from the example given in the problem statement:
        Tree<Integer> testTree = new Tree<Integer>();
        int[] testNodes = new int[]{49, 83, 28, 97, 71, 40, 18, 99, 92, 72, 69, 44, 32, 19, 11};
        for(int i : testNodes){
            testTree.insertNode(i);
        }
        testTree.outputTree();

        // A very unbalanced tree
        Tree<Integer> testTree2 = new Tree<Integer>();
        int[] testNodes2 = new int[]{1,2,3,4,5,6,7,8};
        for(int i : testNodes2){
            testTree2.insertNode(i);
        }
//        testTree2.outputTree();

        // A random tree
        Random rand = new Random();
        Tree<Integer> testTree3 = new Tree<Integer>();
        for(int i =0; i < 10; i++){
            testTree.insertNode(rand.nextInt(100));
        }
//        testTree3.outputTree();
    }
}
