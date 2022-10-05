import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the AnagramList.txt");
        System.out.println("An anagrams are words that share the same letter, but in different order.");
        System.out.println("We shall give you a word at and all you have to do is give us a word that has the same letters");
        String filename = "AnagramList.txt";


        ArrayList<String> fileList = fileToList(filename);
       // System.out.println(fileList);

        String chosenWord = randomWord(fileList);
        System.out.println("Here is your word " + chosenWord);
        System.out.println("Enter q to quit");

        AnagramGame(chosenWord);

    }

    public static ArrayList<String> fileToList(String incomingFile) {
        ArrayList<String> grams = new ArrayList<>();
        String line;
        try {
            FileReader file = new FileReader(incomingFile);
            BufferedReader buffedRead = new BufferedReader(file);
            while ((line = buffedRead.readLine()) != null) {
                grams.add(line);
            }

        } catch (Exception e) {
            System.out.println("Sorry but the file you are looking for cannot be found");
        }
        return grams;
    }

    public static String randomWord(ArrayList<String> incomingList) {
        Random rand = new Random();
        int randNum = rand.nextInt(incomingList.size());
        return incomingList.get(randNum);
    }

    public static void AnagramGame(String chosenWord) {
        Scanner keys = new Scanner(System.in);

        // sorts chosenWord
        char chosen_sort[] = chosenWord.toCharArray();
        Arrays.sort(chosen_sort);

        int score = 0;

        ArrayList<String> input_words_array = new ArrayList<>();


        while (true) {
            String  userWord = keys.nextLine().toLowerCase(Locale.ROOT);

            // sorts userWord
            char userWord_sort[] = userWord.toCharArray();
            Arrays.sort(userWord_sort);

            if (Objects.equals(userWord,"q")) {
                break;

            } else if(Objects.equals(userWord, chosenWord)) {
                System.out.println("Sorry but that's the same word");
                input_words_array.add(userWord);


            } else if(Arrays.equals(userWord_sort, chosen_sort) && !input_words_array.contains(userWord)){
                System.out.println("Is an anagram!");
                input_words_array.add(userWord);
                score = score + 1;

            } else if(input_words_array.contains(userWord)){
                System.out.println("Sorry, you have already tried that word.");
               }


            else{
                System.out.println("Sorry, that is not an anagram!");
                input_words_array.add(userWord);
            }
        }


        System.out.println("Total distinct words:" + input_words_array.size());
        System.out.println("Total anagrams:" + score);

    }
}