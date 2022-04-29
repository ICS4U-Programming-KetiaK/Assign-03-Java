import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
* This program takes a number and checks if it is a
*  palindrome or not. If not, it adds the reverse of the
*  number to itself and checks again.
*
* @author Ketia Gaelle Kaze
* @version 1.0
* @since 2022-04-11
*/

class Recursion {

    /**
    * Default empty constructor.
    */
    Recursion() { }
    /**
    * Creating function to find the palindrome depth of
    * each number in the input.txt.
    *
    * @param number as long
    * @param depthInt as int
    *
    * @return returnArray as long array
    */

    public static long[] depth(long number, int depthInt) {
        String stringNum = Long.toString(number);
        String newString = "";

        // create a reverse string of the number
        for (int index = 0; index < stringNum.length(); index++) {
            char character = stringNum.charAt(index);
            newString = character + newString;
        }

        long reverseNum = Long.parseLong(newString);

        // check if the number is a palindrome
        if (!newString.equals(stringNum)) {
            // calls function again if the number is not a palindrome
            return depth(number + reverseNum, depthInt + 1);
            // create an array with the depth and the palindrome of the number
        } else {
            long[] resultArray = {0, 1};
            resultArray[0] = Long.valueOf(depthInt);
            resultArray[1] = reverseNum;
            return resultArray;
        }
    }

    /**
    * Creating main function.
    *
    * @param args nothing passed in
    * @throws IOException if no file is passed in
    */

    public static void main(String[] args) throws IOException {
        // declaring variables
        List<String> listOfStrings = new ArrayList<String>();
        BufferedWriter writer;
        int initialDepth = 0;
        String[] stringArray;
        String[] arrayDepth;
        String string = "";
        long[] depthArray = {0, 1};
        List<String> depthString = new ArrayList<String>();

        try {
            // Read input from their text files into a list
            listOfStrings = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            // Transfer the list of strings to an array
            stringArray = listOfStrings.toArray(new String[0]);
            for (int counter1 = 0; counter1 < stringArray.length; counter1++) {

                try {
                    // Handles errors for negative integers and non-integers
                    if (Integer.parseInt(stringArray[counter1]) < 0) {
                        string = "The input should be a positive integer.";
                    } else if (Integer.parseInt(stringArray[counter1]) >= 0) {
                        depthArray = depth(Long.parseLong(
                            stringArray[counter1]), initialDepth);
                        string = stringArray[counter1] + " is a depth of "
                            + depthArray[0] + " with a palindrome of "
                                + depthArray[1];
                    }

                } catch (NumberFormatException Exception) {
                    string = "The input should be an integer.";
                }

                depthString.add(string);
            }

            // convert reversed strings to another array
            arrayDepth = depthString.toArray(new String[0]);

            // build a string containing the elements of the array
            StringBuilder builder = new StringBuilder();
            for (int counter2 = 0; counter2 < arrayDepth.length; counter2++) {
                builder.append(arrayDepth[counter2]);
                builder.append("\n");
            }

            // create a new file to display the output
            writer = new BufferedWriter(new FileWriter("/home/ubuntu/"
                + "environment/Assign/Assign-03/"
                  + "Assign-03-Java/output.txt"));
            writer.write(builder.toString());
            writer.close();
            System.out.println("Assigned result to the output file");

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
