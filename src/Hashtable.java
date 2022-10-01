
public class Hashtable {

    /*public int insert(int key, int[] hashtable) {
        int m = hashtable.length;
        int h = divHash(key, m);
        for (int i = 0; i < m; i++) {
            int position = probe
        }
    }*/

    // Function to print an array
    static void printArray(int arr[])
    {

        // Iterating and printing the array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // Function to implement the
    // quadratic probing
    static void hashing(int table[], int tsize, int arr[],
                        int N)
    {

        // Iterating through the array
        for (int i = 0; i < N; i++) {

            // Computing the hash value
            int hv = arr[i] % tsize;

            // Insert in the table if there
            // is no collision
            if (table[hv] == -1)
                table[hv] = arr[i];
            else {

                // If there is a collision
                // iterating through all
                // possible quadratic values
                for (int j = 0; j < tsize; j++) {

                    // Computing the new hash value
                    int t = (hv + j * j) % tsize;
                    if (table[t] == -1) {

                        // Break the loop after
                        // inserting the value
                        // in the table
                        table[t] = arr[i];
                        break;
                    }
                }
            }
        }

        printArray(table);
    }
}
