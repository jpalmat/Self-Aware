import java.util.*;

/**
 * n = 1 -> none
 * n = 2 -> none
 * n = 3 -> [2, 0, 0]
 * N = 4 -> {2, 0, 2, 0}, {1, 2, 1, 0}
 * N = 5 -> {2, 1, 2, 0, 0}
 * N = 6 -> None
 * N = 7 -> {3, 2, 1, 1, 0, 0, 0}
 * N = 8 -> {4, 2, 1, 0, 1, 0, 0, 0}
 * N = 9 -> {5, 2, 1, 0, 0, 1, 0, 0, 0}
 * N = 10 -> {6, 2, 1, 0, 0, 0, 1, 0, 0, 0}
 * N = 11 -> {7, 2, 1, 0, 0, 0, 0, 1, 0, 0, 0}
 * N = 12 -> {8, 2, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0}
 *
 * pseudocode
 *find all the summands of n
 */
public class Main {
    static Set<int[]> self_ware_arrays = new HashSet<>();

    public static void main (String[] args)
    {
        int n = 4;
        int arr[] = new int [n];
        if(n < 3)
            System.out.println("none");
        if(n==3){
            System.out.println(2+" "+0+" "+0);
        } else {
            findSummands(arr, 0, n, n, n);
        }
    }

    static void findSummands(int arr[], int index, int num, int reducedNum, int n) {
        if (reducedNum < 0)
            return;
        if (reducedNum == 0) {
            int[] a = new int[n];
            for (int i = 0; i < index; i++) {
                a[i] = arr[i];
            }
            recursiveSummands(n, a);
            return;
        }
        int prev = (index == 0) ? 1 : arr[index - 1];

        for (int k = prev; k <= num ; k++)
        {
            arr[index] = k;

            findSummands(arr, index + 1, num, reducedNum - k, n);
        }
    }
    public static void recursiveSummands(int n, int[] elements) {
        if(n == 1) {
            List l = new ArrayList();
            if(!self_ware_arrays.contains(elements) && validateArray(elements)){
                self_ware_arrays.add(elements);
                for (int a: elements) {
                    System.out.print(a + " ");
                }
                System.out.print('\n');
            }
        } else {
            for(int i = 0; i < n-1; i++) {
                recursiveSummands(n - 1, elements);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            recursiveSummands(n - 1, elements);
        }
    }

    private static boolean validateArray(int[] input) {
        boolean ok = false;
        for(int i = 0; i < input.length; i++) {
            if(!countNumbers(i, input[i], input))
                return false;
        }
        return true;
    }

    private static boolean countNumbers(int index, int indexCount, int[] input) {
        boolean ok = false;
        int count = 0;
        for (int a: input) {
            if(a==index)
                count++;
        }
        return count == indexCount;
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
}