import java.util.Random;

/**
 * This class is part of the first obligatory practice tasks for the Algorithms and Datastructures Course
 *
 * @Author Daniel Evensen
 * @Credz Trym / Stackoverflow for RunTime algoritme / metode (endret fra metoden vist i presentasjon)
 */
public class stockSales {
    private static int[] stocks = {-1, 3, -9, 2, 2, -1, 2, -1, -5};

    public static void main(String[] args) {
        runTimeForProgram();
        System.out.println(highestProfitFromSalesOfStocks(stocks));
        System.out.println("");

        runTime(1000);
        runTime(10000);
        runTime(100000);
        runTime(1000000);
    }


    public static String highestProfitFromSalesOfStocks(int[] stocks) {
        int profit = 0; //1
        int stockValue; //1
        int buyDay = 0; //1
        int sellDay = 0; //1

        /**
         * Here we can see that we are running a loop inside a loop
         * This will result in a complexity of Theta (n * n) = Theta (n^2) if we look away from other operations
         * and simply look at the relative complexity containing n.
         */
        for (int i = 0; i < stocks.length; i++) { //2n + 1
            stockValue = 0; //1n
            for (int j = i+1; j < stocks.length; j++) { //4n + 1
                stockValue += stocks[j]; //1n + 1n + 1n= 3n
                if (stockValue >= profit) { // 1n
                    profit = stockValue; // 1n
                    buyDay = i + 1; // 2n
                    sellDay = j + 1; // 2n
                }

            }

        //Verste: 4 + (2n+1)*(4n+1+3n+n+n+2n+2n)+1n = 4+26n^2+13n+2n+1+n = 26n^2 +16n + 5
        //Beste: 4 + (2n+1)*(4n+1+3n) + n = 4 + 8n^2 + 2n + 6n^2 + 4n + 1 + 3n + n = 14n^2 + 10n + 5
        }
        String result = "Highest profit " + profit + ", gained from buying at day " + buyDay + ", and selling at day " + sellDay; // 1
        return result; // 1

        //Verste: 4 + (2n+1)*(4n+1+3n+n+n+2n+2n)+1n = 4 + 26n^2+13n+2n+1+n = 4 + 26n^2 +16n +1 +2 = 26n^2 + 16n + 7
        //Beste: 4 + (2n+1)*(4n+1+3n) + n = 4 + 8n^2 + 2n + 6n^2 + 4n + 1 + 3n + n = 4 + 14n^2 + 10n + 1 + 2 = 14n^2 + 10n + 7

    }

    public static int[] createArrayWithNElements(int number) {
        int[] nSizeArray = new int[number];
        Random random = new Random();
        for (int i = 0; i < nSizeArray.length; i++) {
            nSizeArray[i] = random.nextInt(20)-10;
        }

       return nSizeArray;
    }

    public static void runTimeForProgram() {
        final long timeStart = System.currentTimeMillis();

        highestProfitFromSalesOfStocks(stocks);
        final long timeEnd = System.currentTimeMillis();

        long totalTime = timeEnd - timeStart;
        System.out.println("Miliseconds per round with main program: " + totalTime);
    }

    public static void runTime(int n) {
        final long timeStart = System.currentTimeMillis();

        highestProfitFromSalesOfStocks(createArrayWithNElements(n));
        final long timeEnd = System.currentTimeMillis();

        long totalTime = timeEnd - timeStart;
        System.out.println("Miliseconds per round with n = " + n + ": " + totalTime);
    }
}

