import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {

  public static void main(String[] args) throws FileNotFoundException {

    File file = new File("input.txt");
    Scanner scan = new Scanner(file);

    int N = scan.nextInt();

    int[][] mL = new int[N][N]; //men's list
    int[][] wL = new int[N][N]; // women's list
    int[][] engagement = new int[N][2]; 

    setUpArrays(mL, wL, engagement, scan, file);
    printArr(mL);
    printArr(wL);
    printArr(engagement);
    System.out.println("\nOUTPUT:\n" + stableMarriage(mL, wL, engagement));
    printArr(engagement);

  }

  public static void setUpArrays(int[][] arr1, int[][] arr2, int[][] engagement, Scanner scan, File file) { // array
                                                                                                            // setups
                                                                                                            // for men's
                                                                                                            // array,
                                                                                                            // women's
                                                                                                            // array,
                                                                                                            // and
                                                                                                            // engagements

    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr1[i].length; j++) {
        arr1[i][j] = scan.nextInt();
      }
    }

    for (int i = 0; i < arr2.length; i++) {
      for (int j = 0; j < arr2[i].length; j++) {
        arr2[i][j] = scan.nextInt();
      }
    }

    for (int i = 0; i < engagement.length; i++) {
      for (int j = 0; j < engagement[i].length; j++) {
        engagement[i][j] = scan.nextInt();
      }
    }

  }

  public static int stableMarriage(int[][] m, int[][] w, int[][] engagements) {
    int i, j, womenIndex, currMan, women, potentialMan, swaps = 0;

    for (i = 0; i < m.length; i++) {
      System.out.println("i = " + i);
      for (j = 0; j < m[i].length; j++) {
        // System.out.println((i + 1) + " " + (j + 1));
        women = m[i][j];
        System.out.println("AT M" + (i + 1));

        if (!alreadyEngaged(i + 1, women, engagements)) {
          womenIndex = m[i][j] - 1; // ex: woman 2 will be located at women array index 1
          currMan = getMan(women, engagements);
          potentialMan = i + 1;
          if (ranking(w, womenIndex, currMan) > ranking(w, womenIndex, potentialMan)) { // ex: 1 is greater than 2
                                                                                        // ranking wise, so we don't
                                                                                        // continue
            System.out.println("Instablity: M"+potentialMan + ": W"+women);
            swapPartners(potentialMan, currMan, women, engagements);
            swaps++;
            i = 0;

            break; // moves to the next guy after swap
          }
          
          

        } else {
          System.out.println("Engaged");
          break; // if found engaged women before swap, moves on to next guy
        }
      }
    }
    return swaps;

  }

  public static int getMan(int women, int[][] engagements) {
    for (int i = 0; i < engagements.length; i++) {
      if (engagements[i][1] == women) {
        return engagements[i][0];
      }
    }
    return 0;
  }

  public static int getWoman(int man, int[][] engagements) {
    for (int i = 0; i < engagements.length; i++) {
      if (engagements[i][0] == man) {
        return engagements[i][1];
      }
    }
    return 0;
  }

  public static int ranking(int[][] w, int index, int man) {
    int rank = 1;
    for (int j = 0; j < w.length; j++) {

      // System.out.println("At Man: " + w[index][j]+ "\n" + (w[index][j] != man));
      if (w[index][j] == man) {
        break;
      }
      rank++;

    }

    return rank;

  }

  public static boolean alreadyEngaged(int man, int women, int[][] engagement) {
    for (int i = 0; i < engagement.length; i++) {

      if (engagement[i][0] == man && engagement[i][1] == women) {

        return true;
      }
    }

    return false;
  }

  public static void swapPartners(int potentialMan, int currMan, int women, int[][] engagements) {
    int tempWoman = getWoman(potentialMan, engagements);
    System.out.println("CURR MAN: " + currMan);

    for (int i = 0; i < engagements.length; i++) {
      if (engagements[i][0] == potentialMan) {
        engagements[i][1] = women;
      }

      if (engagements[i][0] == currMan) {
        engagements[i][1] = tempWoman;
      }
    }
  }

  public static void printArr(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
}
