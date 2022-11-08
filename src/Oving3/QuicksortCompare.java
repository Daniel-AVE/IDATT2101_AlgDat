package Oving3;// Java program to implement
// dual pivot QuickSort
import java.util.*;

/**
 * The type Quicksort compare.
 */
public class QuicksortCompare{
    static Random rn = new Random();
    static final int size = 100 * 1000000;
    static final int boundSize = size + 1;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
// Driver code
    public static void main(String[] args)
    {

        int[] arr = { 24, 8, 42, 75, 29, 77, 38, 57 };

        dualPivotQuickSort(arr, 0, 7);
        System.out.print("Sorted array: ");
        for (int i = 0; i < 8; i++)
            System.out.print(arr[i] + " ");

        System.out.println();

        int[] arr2 = { 24, 8, 42, 75, 29, 77, 38, 57 };

        quicksort(arr2, 0, 7);

        System.out.print("Sorted array (Quicksort): ");
        for (int i = 0; i < 8; i++) {
            System.out.print(arr2[i] + " ");
        }



        int[] randomDataArray = randomDataArray(boundSize, size);
        int[] randomDataArray2 = randomDataArray(boundSize, size);
        int sum = sumOfArray(randomDataArray);
        int sum2 = sumOfArray(randomDataArray2);

        System.out.println("\n\nRuntime single pivot and double pivot quicksort with random data: ");
        runTimeSinglePivot(randomDataArray, 0, size);
        runTimeDoublePivot(randomDataArray2, 0, size);

        int sumAfterSortingSinglePivot = sumOfArray(randomDataArray);
        int sumAfterSortingDualPivot = sumOfArray(randomDataArray2);

        System.out.println(arraySortedCorrectly(randomDataArray) + "\n" + arraySortedCorrectly(randomDataArray2));

        if (sumAfterSortingSinglePivot == sum && sumAfterSortingDualPivot == sum2) {
            System.out.println("Sum check test completed successfully. No change in sum");
        } else {
            System.out.println("Sum check test failed. Sum changed after sorting");
        }




        int[] arrayWithDuplicates = randomDataArray(boundSize, 10);
        int[] arrayWithDuplicates2 = randomDataArray(boundSize, 10);
        sum = sumOfArray(arrayWithDuplicates);
        sum2 = sumOfArray(arrayWithDuplicates2);

        System.out.println("\nRuntime single pivot and double pivot quicksort with many duplicates");
        runTimeSinglePivot(arrayWithDuplicates, 0, size);
        runTimeDoublePivot(arrayWithDuplicates2, 0, size);

        sumAfterSortingSinglePivot = sumOfArray(arrayWithDuplicates);
        sumAfterSortingDualPivot = sumOfArray(arrayWithDuplicates2);

        System.out.println(arraySortedCorrectly(arrayWithDuplicates) + "\n" + arraySortedCorrectly(arrayWithDuplicates2));


        if (sumAfterSortingSinglePivot == sum && sumAfterSortingDualPivot == sum2) {
            System.out.println("Sum check test completed successfully. No change in sum");
        } else {
            System.out.println("Sum check test failed. Sum changed after sorting");
        }

        int[] sortedArray = sortedArray(boundSize);
        dualPivotQuickSort(sortedArray, 0, size);
        sum = sumOfArray(sortedArray);
        sum2 = sumOfArray(sortedArray);

        System.out.println("\nRuntime single pivot and double pivot quicksort with already sorted array");
        runTimeSinglePivot(sortedArray, 0, size);
        sumAfterSortingSinglePivot = sumOfArray(sortedArray);
        System.out.println(arraySortedCorrectly(sortedArray));

        runTimeDoublePivot(sortedArray, 0, size);
        sumAfterSortingDualPivot = sumOfArray(sortedArray);
        System.out.println(arraySortedCorrectly(sortedArray));

        if (sumAfterSortingSinglePivot == sum && sumAfterSortingDualPivot == sum2) {
            System.out.println("Sum check test completed successfully. No change in sum");
        } else {
            System.out.println("Sum check test failed. Sum changed after sorting");
        }

    }


    /**
     * Swap.
     *
     * @param arr the arr
     * @param i   the
     * @param j   the j
     */
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Dual pivot quick sort.
     *
     * @param arr  the arr
     * @param low  the low
     * @param high the high
     */
    static void dualPivotQuickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {

            // piv[] stores left pivot and right pivot.
            // piv[0] means left pivot and
            // piv[1] means right pivot
            int[] piv;
            piv = partition(arr, low, high);

            dualPivotQuickSort(arr, low, piv[0] - 1);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1);
            dualPivotQuickSort(arr, piv[1] + 1, high);
        }
    }

    /**
     * Partition int [ ].
     *
     * @param arr  the arr
     * @param low  the low
     * @param high the high
     * @return the int [ ]
     */
    static int[] partition(int[] arr, int low, int high)
    {
        swap(arr, low, low + (high - low)/3);
        swap(arr, high, high - (high - low)/3);
        if (arr[low] > arr[high])
            swap(arr, low, high);

        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
                p = arr[low], q = arr[high];

        while (k <= g)
        {

            // If elements are less than the left pivot
            if (arr[k] < p)
            {
                swap(arr, k, j);
                j++;
            }

            // If elements are greater than or equal
            // to the right pivot
            else if (arr[k] >= q)
            {
                while (arr[g] > q && k < g)
                    g--;

                swap(arr, k, g);
                g--;

                if (arr[k] < p)
                {
                    swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // Bring pivots to their appropriate positions.
        swap(arr, low, j);
        swap(arr, high, g);

        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[] { j, g };
    }


    // This code is contributed by Gourish Sadhu

    // The code under here is retrieved from the book Algorithm and Datastructures, page 59 Quicksort method.

    /**
     * Bytt.
     *
     * @param t the t
     * @param i the
     * @param j the j
     */
    public static void bytt(int[] t, int i, int j) {
        int k = t[j];
        t[j] = t[i];
        t[i] = k;
    }

    private static int median3sort(int[] t, int v, int h) {
        int m = (v + h) / 2;
        if (t[v] > t[m]) {
            bytt(t, v, m);
        }
        if (t[m] > t[h]) {
            bytt (t, m, h);
            if (t[v] > t[m]) {
                bytt(t, v, m);
            }
        }
        return m;
    }

    /**
     * Splitt int.
     *
     * @param t the t
     * @param v the v
     * @param h the h
     * @return the int
     */
    public static int splitt(int[] t, int v, int h) {
        int iv, ih;
        int m = median3sort(t,v,h);
        int dv = t[m];
        bytt(t, m, h - 1);
        for (iv = v, ih = h - 1;;) {
            while (t[++iv] < dv);
            while (t[--ih] > dv);
            if (iv >= ih) {
                break;
            }
            bytt(t, iv, ih);
        }
        bytt(t, iv, h - 1);
        return iv;
    }

    /**
     * Quicksort.
     *
     * @param t the t
     * @param v the v
     * @param h the h
     */
    public static void quicksort(int[] t, int v, int h) {
        if (h - v > 2) {
            int delepos = splitt(t, v, h);
            quicksort(t, v, delepos - 1);
            quicksort(t, delepos + 1, h);
        } else {
            median3sort(t, v, h);
        }
    }

    /**
     * Random data array int [ ].
     *
     * @param n     the n
     * @param bound the bound
     * @return the int [ ]
     */
    public static int[] randomDataArray(int n, int bound) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = rn.nextInt(bound);
        }
        return array;
    }

    /**
     * Sorted array int [ ].
     *
     * @param n the n
     * @return the int [ ]
     */
    public static int[] sortedArray(int n) {
        int count = 1;
        int[] sortedArray = new int[n];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = count;
            count++;
        }
        return sortedArray;
    }

    /**
     * Sum of array int.
     *
     * @param array the array
     * @return the int
     */
    public static int sumOfArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];

        }
        return sum;
    }

    /**
     * Array sorted correctly string.
     *
     * @param array the array
     * @return the string
     */
    public static String arraySortedCorrectly(int[] array) {
        String result;
        for (int i = 1; i < array.length; i++) {
           if (array[i] < array[i-1]) {
               result = "Test failed. Array not sorted correctly";
               return result;
           }
        }
        result = "Test completed successfully. Array sorted correctly";
        return result;
    }

    /**
     * Run time single pivot.
     *
     * @param t the t
     * @param v the v
     * @param h the h
     */
    public static void runTimeSinglePivot(int[] t, int v, int h) {
        final long timeStart = System.currentTimeMillis();
        quicksort(t, v, h);
        final long timeEnd = System.currentTimeMillis();

        long totalTime = timeEnd - timeStart;
        System.out.println("Runtime in milliseconds with single pivot quicksort:  " + totalTime);
    }

    /**
     * Run time double pivot.
     *
     * @param t the t
     * @param v the v
     * @param h the h
     */
    public static void runTimeDoublePivot(int[] t, int v, int h) {
        final long timeStart = System.currentTimeMillis();
        dualPivotQuickSort(t, v, h);
        final long timeEnd = System.currentTimeMillis();

        long totalTime = timeEnd - timeStart;
        System.out.println("Runtime in milliseconds with dual pivot quicksort:  " + totalTime);
    }
}

// This code is contributed by Gourish Sadhu