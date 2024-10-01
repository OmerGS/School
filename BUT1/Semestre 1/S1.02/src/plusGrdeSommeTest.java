/**
* A class representing various algorithms to find the maximum sum subarray.
* This class includes methods implementing different algorithms and efficiency tests.
* 
* @author O.Gunes, R.Peron
**/
class plusGrdeSomme {

    /**
     * Represents a double precision floating-point variable initialized with the value 0.
     * The variable 'cpt' is used to count or accumulate numerical values in a program.
     */
    double cpt = 0;

    /**
    * Entry point of the code 
    */
    void principal(){
        testPlusGrdeSomme1();
        testPlusGrdeSomme1Efficacite();

        testPlusGrdeSomme2();
        testPlusGrdeSomme2Efficacite();

        testPlusGrdeSomme3();
        testPlusGrdeSomme3Efficacite();

        testPlusGrdeSomme4();
        testPlusGrdeSomme4Efficacite();
    }


/* ELEMENT IMPORTANT POUR LE BON FONCTIONNEMENT DU CODE */

    /**
    * Fills an already created array with randomly generated integers between min and max.
    * 
    * @param leTab - the array to be filled with randomly generated values
    * @param nbElem - the number of integers the array will contain
    * @param min - the minimum value of the integer
    * @param max - the maximum value of the integer
    */ 
	void remplirAleatoire(int[] leTab, int nbElem, int min, int max){
        if(tirerAleatoire(min,max) != -1){
            for(int i = 0; i < nbElem; i++){
                leTab[i] = tirerAleatoire(min,max);
            }
        }
	}

    /**
    * Returns a random integer between min and max. Checks that min is greater than zero and max is greater than min,
    * otherwise displays -1.
    * 
    * @param min - The value of the minimum integer
    * @param max - The value of the maximum integer
    * @return The random integer
    */ 
	int tirerAleatoire(int min, int max){
		int returnValue = -1;
		
		if(min <= max){
			returnValue = (int) (min + (Math.random() * (max - min)+0));
		} else {
			System.err.println("tirerAleatoire : " + "Valeur de min superieur a max");
		}
		return(returnValue);
	}
    

















/* PLUS GRANDE SOMME 1 EFFICACITE N^3*/

    /**
    * Method which must find the largest sequence in a given table,
    * if two sequences make the same sum we keep the one which is the smallest
    * 
    * @param tab - Integer Array
    * @return - A Integer contain 3 items
    *       - MaxSum
    *       - startIndex
    *       - endIndex
    */
    int[] plusGrdeSomme1(int[] tab) {
        int n = tab.length;
        int sumMax = tab[0];
        int longMax = 1;  // Initialiser la longueur maximale à 1
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentSum = 0;
                for (int k = i; k <= j; k++) {
                    currentSum += tab[k];
                    cpt++;
                }

                int currentLong = j - i + 1;

                if (currentSum > sumMax || (currentSum == sumMax && currentLong < longMax)) {
                    sumMax = currentSum;
                    longMax = currentLong;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }

        int[] returnArray = {sumMax, startIndex, endIndex};
        return returnArray;
    }

    /**
    * testMethode who test the plusGrdeSomme1 method with different array. 
    */
    void testPlusGrdeSomme1(){
        System.out.println("*** testplusGrdeSomme1 ***");
        System.out.println("Cas Normaux : \n");

        int[] tab = {-1,8,-4,5,6,-9,-7,0,12};
        testCasPlusGrdeSomme1(tab, 1, 4, 15);

        int[] tab1 = {1,2,3,4,-15,-20,10,20,30};
        testCasPlusGrdeSomme1(tab1, 6, 8, 60);

        int[] tab2 = {1,2,3,-80,3,5};
        testCasPlusGrdeSomme1(tab2, 4, 5, 8);

        int[] tab3 = {3,5,-80,1,2,5};
        testCasPlusGrdeSomme1(tab3, 3, 5, 8);
    }

    /**
     * Tests the plusGrdeSomme1 method by providing an array, a starting index, an ending index, and an expected sum.
     * Calculates the maximum sum of the subsequence using the plusGrdeSomme1 method and compares the result
     * with the expected values for maximum sum, start index, and end index.
     *
     * @param tab - The array for testing the plusGrdeSomme1 method
     * @param startIndex - The starting index of the subsequence
     * @param endIndex - The ending index of the subsequence
     * @param sum - The expected sum of the subsequence
     */
    void testCasPlusGrdeSomme1(int[] tab, int startIndex, int endIndex, int sum){
        int[] tabAfterMethod = plusGrdeSomme1(tab);

        System.out.println("sum maximale de la sous-séquence = " + tabAfterMethod[0]);
        System.out.println("Indice de début de la sous-séquence = " + tabAfterMethod[1]);
        System.out.println("Indice de fin de la sous-séquence = " + tabAfterMethod[2]);

        if (tabAfterMethod[0] == sum && tabAfterMethod[1] == startIndex && tabAfterMethod[2] == endIndex){
            System.out.println("Reussi");
        } else {
            System.out.println("Echec");
        }

        System.out.println("\n ------------------- \n"); 
    }

    /**
    * Performs efficiency tests on the plusGrdeSomme1 method.
    *  
    * The method conducts multiple efficiency tests using arrays of different sizes (2^15, 2^17, 2^18, 2^13).
    * It prints the results of the efficiency tests, specifically the time taken for each test.
    * 
    * Note: The efficiency test involves repeatedly applying the plusGrdeSomme1 method on arrays of specified sizes.
    */
    void testPlusGrdeSomme1Efficacite(){
		System.out.println(" \n\n *** testplusGrdeSomme1Efficacite *** \n");
		/* 1er Test d'efficacité sur un tableau de taille 2^15*/
		int[] bigArray210 = new int [1];
		int n = (int) Math.pow(2,10);
		testEfficaciteplusGrdeSomme1Repetition(n, bigArray210);
		
		/* 2eme Test d'efficacité sur un tableau de taille 2^17*/
		int[] bigArray211 = new int [1];
		int n2 = (int) Math.pow(2,11);
		testEfficaciteplusGrdeSomme1Repetition(n2, bigArray211);
		
		/* 3eme Test d'efficacité sur un tableau de taille 2^18*/
		int[] bigArray212 = new int [1];
		int n3 = (int) Math.pow(2,12);
		testEfficaciteplusGrdeSomme1Repetition(n3, bigArray212);

        int[] bigArray213 = new int [1];
		int n4 = (int) Math.pow(2,13);
		testEfficaciteplusGrdeSomme1Repetition(n4, bigArray213);
	}

    /**
    * Performs repetitive efficiency tests on the plusGrdeSomme1 method with a specified array size.
    * 
    * The method initializes an array of size 'n' and conducts efficiency tests by filling it with random values
    * and then applying the plusGrdeSomme1 method. The time taken for the operation is measured in nanoseconds.
    * Additionally, the value of a counter 'cpt' is displayed, representing the number of operations performed.
    * 
    * @param n - The size of the array for the efficiency test
    * @param bigArray - The array used for testing the plusGrdeSomme1 method
    */
    void testEfficaciteplusGrdeSomme1Repetition(int n, int[] bigArray){
		cpt = 0;
		bigArray = new int[n];
		
		System.out.println("*** Tableau de Taille " + bigArray.length + " ***");
		
		remplirAleatoire(bigArray, n, 0, 500);
		bigArray[bigArray.length-1] = 502;
		double t1 = System.currentTimeMillis();
		plusGrdeSomme1(bigArray);
		double t2 = System.currentTimeMillis();
		double diffT = (t2 - t1); // en ms
		System.out.println(diffT + " ms");
    
        System.out.println("CPT : " + cpt);

		double k1 = cpt/Math.pow(n, 3);
		System.out.println("cpt/n^3 = " + k1 + "\n");
	}
























/* PLUS GRANDE SOMME 2 EFFICACITE n^2 */
    /**
    * Finds the subarray with the largest sum in the given array.*
    * @param tab The input array of integers.
    * @return An array containing the maximum sum, start index, and end index of the subarray.
    */
    int[] plusGrdeSomme2(int[] tab) {
        int n = tab.length;
        int sumMax = tab[0];
        int longMax = 1;
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;

            for (int j = i; j <= n - 1; j++) {
                currentSum = currentSum + tab[j];
                int currentLong = j - i;

                if (currentSum > sumMax || (currentSum == sumMax && currentLong < longMax)) {
                    sumMax = currentSum;
                    longMax = currentLong;
                    startIndex = i;
                    endIndex = j;
                }
                cpt++; // Assuming cpt is a counter variable
            }
        }

        // Return an array containing the maximum sum, start index, and end index
        int[] returnArray = {sumMax, startIndex, endIndex};
        return returnArray;
    }

    /**
    * testMethode who test the plusGrdeSomme2 method with different array. 
    */
    void testPlusGrdeSomme2(){
        System.out.println("*** testplusGrdeSomme1 ***");
        System.out.println("Cas Normaux : \n");

        int[] tab = {-1,8,-4,5,6,-9,-7,0,12};
        testCasPlusGrdeSomme2(tab, 1, 4, 15);

        int[] tab1 = {1,2,3,4,-15,-20,10,20,30};
        testCasPlusGrdeSomme2(tab1, 6, 8, 60);

        int[] tab2 = {1,2,3,-80,3,5};
        testCasPlusGrdeSomme2(tab2, 4, 5, 8);

        int[] tab3 = {3,5,-80,1,2,5};
        testCasPlusGrdeSomme2(tab3, 3, 5, 8);
    }

    /**
     * Tests the plusGrdeSomme2 method by providing an array, a starting index, an ending index, and an expected sum.
     * Calculates the maximum sum of the subsequence using the plusGrdeSomme2 method and compares the result
     * with the expected values for maximum sum, start index, and end index.
     *
     * @param tab - The array for testing the plusGrdeSomme2 method
     * @param startIndex - The starting index of the subsequence
     * @param endIndex - The ending index of the subsequence
     * @param sum - The expected sum of the subsequence
     */
    void testCasPlusGrdeSomme2(int[] tab, int startIndex, int endIndex, int sum){
        int[] tabAfterMethod = plusGrdeSomme2(tab);

        System.out.println("sum maximale de la sous-séquence = " + tabAfterMethod[0]);
        System.out.println("Indice de début de la sous-séquence = " + tabAfterMethod[1]);
        System.out.println("Indice de fin de la sous-séquence = " + tabAfterMethod[2]);

        if (tabAfterMethod[0] == sum && tabAfterMethod[1] == startIndex && tabAfterMethod[2] == endIndex){
            System.out.println("Reussi");
        } else {
            System.out.println("Echec");
        }

        System.out.println("\n ------------------- \n"); 
    }

    /**
    * Performs efficiency tests on the plusGrdeSomme2 method.
    *  
    * The method conducts multiple efficiency tests using arrays of different sizes (2^15, 2^17, 2^18, 2^13).
    * It prints the results of the efficiency tests, specifically the time taken for each test.
    * 
    * Note: The efficiency test involves repeatedly applying the plusGrdeSomme2 method on arrays of specified sizes.
    */
    void testPlusGrdeSomme2Efficacite(){
		System.out.println(" \n\n *** testplusGrdeSomme2Efficacite *** \n");
		/* 1er Test d'efficacité sur un tableau de taille 2^15*/
		int[] bigArray210 = new int [1];
		int n = (int) Math.pow(2,14);
		testEfficaciteplusGrdeSomme2Repetition(n, bigArray210);
		
		/* 2eme Test d'efficacité sur un tableau de taille 2^17*/
		int[] bigArray211 = new int [1];
		int n2 = (int) Math.pow(2,15);
		testEfficaciteplusGrdeSomme2Repetition(n2, bigArray211);
		
		/* 3eme Test d'efficacité sur un tableau de taille 2^18*/
		int[] bigArray212 = new int [1];
		int n3 = (int) Math.pow(2,16);
		testEfficaciteplusGrdeSomme2Repetition(n3, bigArray212);

        int[] bigArray213 = new int [1];
		int n4 = (int) Math.pow(2,17);
		testEfficaciteplusGrdeSomme2Repetition(n4, bigArray213);
	}

    /**
    * Performs repetitive efficiency tests on the plusGrdeSomme2 method with a specified array size.
    * 
    * The method initializes an array of size 'n' and conducts efficiency tests by filling it with random values
    * and then applying the plusGrdeSomme2 method. The time taken for the operation is measured in nanoseconds.
    * Additionally, the value of a counter 'cpt' is displayed, representing the number of operations performed.
    * 
    * @param n - The size of the array for the efficiency test
    * @param bigArray - The array used for testing the plusGrdeSomme1 method
    */
    void testEfficaciteplusGrdeSomme2Repetition(int n, int[] bigArray){
		cpt = 0;
		bigArray = new int[n];
		
		System.out.println("*** Tableau de Taille " + bigArray.length + " ***");
		
		remplirAleatoire(bigArray, n, 0, 500);
		bigArray[bigArray.length-1] = 502;
		double t1 = System.currentTimeMillis();
		plusGrdeSomme2(bigArray);
		double t2 = System.currentTimeMillis();
		double diffT = (t2 - t1); // en ms
		System.out.println(diffT + " ms");
    
        System.out.println("CPT : " + cpt);

		double k2 = cpt/Math.pow(n, 2);
		System.out.println("cpt/n^2 = " + k2 + "\n");
	}


























/* PLUS GRANDE SOMME 3 EFFICACITE nlog2n */
    
    /**
     * Finds the maximum sum subarray using the divide-and-conquer approach.
     * 
     * This method calls the sumMaxArray(int[], int, int) method to find the maximum sum subarray
     * for the given array 'tab'. The subarray is identified between the indices 0 and tab.length - 1.
     * 
     * @param tab - The input array for finding the maximum sum subarray
     * @return An array containing three elements: [maximum sum, start index, end index] of the maximum sum subarray
     */
    int[] plusGrdeSomme3(int[] tab) {
        return sumMaxArray(tab, 0, tab.length - 1);
    }

    /**
    * Finds the maximum sum subarray within a specified range using the divide-and-conquer approach.
    * 
    * This method recursively divides the given array 'tab' into subarrays, calculates the maximum sum subarrays 
    * for the left, right, and cross portions, and compares the results to determine the maximum sum subarray
    * within the specified range [low, high].
    * 
    * @param tab - The input array for finding the maximum sum subarray
    * @param low - The starting index of the current subarray
    * @param high - The ending index of the current subarray
    * @return An array containing three elements: [maximum sum, start index, end index] of the maximum sum subarray
    */
    int[] sumMaxArray(int[] tab, int low, int high) {
        if (low == high) {
            return new int[]{tab[low], low, high};
        }

        int mid = (low + high) / 2;

        int[] leftResult = sumMaxArray(tab, low, mid);
        int[] rightResult = sumMaxArray(tab, mid + 1, high);
        int[] crossResult = sumMaxTrans(tab, low, mid, high);

        // Comparer les résultats pour déterminer le maximum
        if (leftResult[0] >= rightResult[0] && leftResult[0] >= crossResult[0]) {
            return leftResult;
        } else if (rightResult[0] >= leftResult[0] && rightResult[0] >= crossResult[0]) {
            return rightResult;
        } else {
            return crossResult;
        }
    }

    /**
     * Finds the maximum sum subarray that crosses the midpoint of the array.
     * 
     * This method calculates the maximum sum subarray that includes elements from both the left and right halves
     * of the array 'tab'. It uses a linear approach by iterating over the elements around the midpoint and
     * updating the maximum sum subarray accordingly.
     * 
     * @param tab - The input array for finding the maximum sum subarray
     * @param low - The starting index of the current subarray
     * @param mid - The midpoint index of the current subarray
     * @param high - The ending index of the current subarray
     * @return An array containing three elements: [maximum sum, start index, end index] of the maximum sum subarray
     */
    int[] sumMaxTrans(int[] tab, int low, int mid, int high) {
        int leftSum = 0;
        int sum = 0;
        int maxLeft = 0;

        for (int i = mid; i >= low; i--) {
            sum += tab[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
            cpt++;
        }

        int rightSum = 0;
        sum = 0;
        int maxRight = 0;

        for (int i = mid + 1; i <= high; i++) {
            sum += tab[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new int[]{leftSum + rightSum, maxLeft, maxRight};
    }


    /**
    * Tests the plusGrdeSomme3 method with various normal cases.
    * 
    * This method conducts normal test cases for the plusGrdeSomme3 method, providing different input arrays
    * and expected results. It prints the results of each test case.
    */
    void testPlusGrdeSomme3(){
        System.out.println("*** testplusGrdeSomme3 ***");
        System.out.println("Cas Normaux : \n");

        int[] tab = {-1,8,-4,5,6,-9,-7,0,12};
        testCasPlusGrdeSomme3(tab, 1, 4, 15);

        int[] tab1 = {1,2,3,4,-15,-20,10,20,30};
        testCasPlusGrdeSomme3(tab1, 6, 8, 60);

        int[] tab2 = {1,2,3,-80,3,5};
        testCasPlusGrdeSomme3(tab2, 4, 5, 8);

        int[] tab3 = {3,5,-80,1,2,5};
        testCasPlusGrdeSomme3(tab3, 3, 5, 8);
        //Doit donner echec car la somme max avec la plus petite sequence est celui de 0 à 1
    }

    /**
     * Tests the plusGrdeSomme3 method with specific input parameters.
     * 
     * This method tests the plusGrdeSomme3 method by providing an input array, a start index, an end index,
     * and an expected sum. It then compares the result with the expected values for maximum sum, start index,
     * and end index, and prints whether the test was successful or failed.
     * 
     * @param tab - The input array for testing the plusGrdeSomme3 method
     * @param startIndex - The expected start index of the maximum sum subarray
     * @param endIndex - The expected end index of the maximum sum subarray
     * @param sum - The expected sum of the maximum sum subarray
     */
    void testCasPlusGrdeSomme3(int[] tab, int startIndex, int endIndex, int sum){
        int[] tabAfterMethod = plusGrdeSomme3(tab);

        System.out.println("Somme maximale de la sous-séquence = " + tabAfterMethod[0]);
        System.out.println("Indice de début de la sous-séquence = " + tabAfterMethod[1]);
        System.out.println("Indice de fin de la sous-séquence = " + tabAfterMethod[2]);

        if (tabAfterMethod[0] == sum && tabAfterMethod[1] == startIndex && tabAfterMethod[2] == endIndex){
            System.out.println("Reussi");
        } else {
            System.out.println("Echec");
        }

        System.out.println("\n ------------------- \n"); 
    }
    
    /**
    * Performs efficiency tests on the plusGrdeSomme3 method.
    * 
    * This method conducts multiple efficiency tests using arrays of different sizes (2^10, 2^11, 2^12, 2^13).
    * It prints the results of the efficiency tests, specifically the time taken for each test.
    * 
    * Note: The efficiency test involves repeatedly applying the plusGrdeSomme3 method on arrays of specified sizes.
    */
    void testPlusGrdeSomme3Efficacite(){
		System.out.println(" \n\n *** testplusGrdeSomme1Efficacite *** \n");
		/* 1er Test d'efficacité sur un tableau de taille 2^10*/
		int[] bigArray210 = new int [1];
		int n = (int) Math.pow(2,12);
		testEfficaciteplusGrdeSomme3Repetition(n, bigArray210);
		
		/* 2eme Test d'efficacité sur un tableau de taille 2^11*/
		int[] bigArray211 = new int [1];
		int n2 = (int) Math.pow(2,14);
		testEfficaciteplusGrdeSomme3Repetition(n2, bigArray211);
		
		/* 3eme Test d'efficacité sur un tableau de taille 2^12*/
		int[] bigArray212 = new int [1];
		int n3 = (int) Math.pow(2,16);
		testEfficaciteplusGrdeSomme3Repetition(n3, bigArray212);

        int[] bigArray213 = new int [1];
		int n4 = (int) Math.pow(2,18);
		testEfficaciteplusGrdeSomme3Repetition(n4, bigArray213);
	}

    /**
    * Performs repetitive efficiency tests on the plusGrdeSomme3 method with a specified array size.
    * 
    * This method initializes an array of size 'n' and conducts efficiency tests by filling it with random values
    * and then applying the plusGrdeSomme3 method. The time taken for the operation is measured in nanoseconds.
    * Additionally, the value of a counter 'cpt' is displayed, representing the number of operations performed.
    * The method also calculates and displays the normalized counter value using the formula cpt/nlog2n.
    * 
    * @param n - The size of the array for the efficiency test
    * @param bigArray - The array used for testing the plusGrdeSomme3 method
    */
    void testEfficaciteplusGrdeSomme3Repetition(int n, int[] bigArray){
		cpt = 0;
		bigArray = new int[n];
		
		System.out.println("*** Tableau de Taille " + bigArray.length + " ***");
		
		remplirAleatoire(bigArray, n, 0, 500);
		bigArray[bigArray.length-1] = 502;
		double t1 = System.nanoTime();
		plusGrdeSomme3(bigArray);
		double t2 = System.nanoTime();
		double diffT = (t2 - t1); // en nanosecondes
		System.out.println(diffT + " ns");
    
        System.out.println("CPT : " + cpt);

        double nlog2n = n*(Math.log10(n) / Math.log10(2));
		double k3 = (double) (cpt/nlog2n);
		System.out.println("cpt/nlog2n = " + k3 + "\n");
	}





















/* PLUS GRANDE SOMME 4 EFFICACITE N */
    /**
    * Finds the maximum sum subarray using the optimized Kadane's algorithm.
    * 
    * This method employs Kadane's algorithm to efficiently find the maximum sum subarray in a given array 'tab'.
    * It iterates through the array, keeping track of the current sum and subarray length, and updates the
    * maximum sum subarray information when necessary. The result is returned as an array with three elements:
    * [maximum sum, start index, end index].
    * 
    * @param tab - The input array for finding the maximum sum subarray
    * @return An array containing three elements: [maximum sum, start index, end index] of the maximum sum subarray
    */
    int[] plusGrdeSomme4(int[] tab) {
        int startIndex = 0;
        int endIndex = 0;
        int startIndexTemp = 0;
        int sumMax = tab[0];
        int currentSum = tab[0];
        int longMax = 1;
        int currentLong = 1;
        int[] returnArray = new int[3];

        for (int i = 1; i < tab.length; i++) {
            if (currentSum < 0) {
                currentSum = tab[i];
                startIndexTemp = i;
                currentLong = 1;
            } else {
                currentSum += tab[i];
                currentLong++;
            }

            if (currentSum > sumMax || (currentSum == sumMax && currentLong < longMax)) {
                sumMax = currentSum;
                startIndex = startIndexTemp;
                endIndex = i;
                longMax = currentLong;  // Mettre à jour la longueur maximale
            }
            cpt++;
        }

        returnArray[0] = sumMax;
        returnArray[1] = startIndex;
        returnArray[2] = endIndex;

        return returnArray;
    }

    /**
    * Tests the plusGrdeSomme4 method with various normal cases.
    * 
    * This method conducts normal test cases for the plusGrdeSomme4 method, providing different input arrays
    * and expected results. It prints the results of each test case.
    */
    void testPlusGrdeSomme4(){
        System.out.println("*** testplusGrdeSomme4 ***");
        System.out.println("Cas Normaux : \n");

        int[] tab = {-1,8,-4,5,6,-9,-7,0,12};
        testCasplusGrdeSomme4(tab, 1, 4, 15);

        int[] tab1 = {1,2,3,4,-15,-20,10,20,30};
        testCasplusGrdeSomme4(tab1, 6, 8, 60);

        int[] tab2 = {1,2,3,-80,3,5};
        testCasplusGrdeSomme4(tab2, 4, 5, 8);

        int[] tab3 = {3,5,-80,1,2,5};
        testCasplusGrdeSomme4(tab3, 3, 5, 8);
        //Doit donner echec car la somme max avec la plus petite sequence est celui de 0 à 1
    }

    /**
    * Tests the plusGrdeSomme4 method with specific input parameters.
    * 
    * This method tests the plusGrdeSomme4 method by providing an input array, a start index, an end index,
    * and an expected sum. It then compares the result with the expected values for maximum sum, start index,
    * and end index, and prints whether the test was successful or failed.
    * 
    * @param tab - The input array for testing the plusGrdeSomme4 method
    * @param startIndex - The expected start index of the maximum sum subarray
    * @param endIndex - The expected end index of the maximum sum subarray
    * @param sum - The expected sum of the maximum sum subarray
    */
    void testCasplusGrdeSomme4(int[] tab, int startIndex, int endIndex, int sum){
        int[] tabAfterMethod = plusGrdeSomme4(tab);

        System.out.println("Somme maximale de la sous-séquence = " + tabAfterMethod[0]);
        System.out.println("Indice de début de la sous-séquence = " + tabAfterMethod[1]);
        System.out.println("Indice de fin de la sous-séquence = " + tabAfterMethod[2]);

        if (tabAfterMethod[0] == sum && tabAfterMethod[1] == startIndex && tabAfterMethod[2] == endIndex){
            System.out.println("Reussi");
        } else {
            System.out.println("Echec");
        }

        System.out.println("\n ------------------- \n"); 
    }

    /**
    * Performs efficiency tests on the plusGrdeSomme4 method.
    * 
    * This method conducts multiple efficiency tests using arrays of different sizes (2^15, 2^18, 2^20, 2^22).
    * It prints the results of the efficiency tests, specifically the time taken for each test.
    * 
    * Note: The efficiency test involves repeatedly applying the plusGrdeSomme4 method on arrays of specified sizes.
     */
    void testPlusGrdeSomme4Efficacite(){
		System.out.println(" \n\n *** testplusGrdeSomme4Efficacite *** \n");
		/* 1er Test d'efficacité sur un tableau de taille 2^15*/
		int[] bigArray210 = new int [1];
		int n = (int) Math.pow(2,15);
		testEfficaciteplusGrdeSomme4Repetition(n, bigArray210);
		
		/* 2eme Test d'efficacité sur un tableau de taille 2^17*/
		int[] bigArray211 = new int [1];
		int n2 = (int) Math.pow(2,18);
		testEfficaciteplusGrdeSomme4Repetition(n2, bigArray211);
		
		/* 3eme Test d'efficacité sur un tableau de taille 2^18*/
		int[] bigArray212 = new int [1];
		int n3 = (int) Math.pow(2,20);
		testEfficaciteplusGrdeSomme4Repetition(n3, bigArray212);

        int[] bigArray222 = new int [1];
		int n4 = (int) Math.pow(2,22);
		testEfficaciteplusGrdeSomme4Repetition(n4, bigArray222);
	}

    /**
    * Performs repetitive efficiency tests on the plusGrdeSomme4 method with a specified array size.
    * 
    * This method initializes an array of size 'n' and conducts efficiency tests by filling it with random values
    * and then applying the plusGrdeSomme4 method. The time taken for the operation is measured in nanoseconds.
    * Additionally, the value of a counter 'cpt' is displayed, representing the number of operations performed.
    * The method also calculates and displays the normalized counter value using the formula cpt/n.
    * 
    * @param n - The size of the array for the efficiency test
    * @param bigArray - The array used for testing the plusGrdeSomme4 method
    */
    void testEfficaciteplusGrdeSomme4Repetition(int n, int[] bigArray){
		cpt = 0;
		bigArray = new int[n];
		
		System.out.println("*** Tableau de Taille " + bigArray.length + " ***");
		
		remplirAleatoire(bigArray, n, -500, 500);
		bigArray[bigArray.length-1] = 502;
		double t1 = System.nanoTime();
		plusGrdeSomme4(bigArray);
		double t2 = System.nanoTime();
		double diffT = (t2 - t1); // en nanosecondes
		System.out.println(diffT + " ns");
    
        System.out.println("CPT : " + cpt);

		double k4 = cpt/n;
		System.out.println("cpt/n = " + k4 + "\n");
	}




}


