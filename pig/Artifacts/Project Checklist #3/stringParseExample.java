import java.util.LinkedList;

public class stringParseExample {

    /* regex for any of these characters, to add new ones put them inside brackets */
    /* If you are trying to put a control character in a regex, like " you need to put a \ before it */
    private static String delimiters = "[ .,?!;:'\"]+";

    private static LinkedList<String> parseString(String input){
        LinkedList<String> parsedString = new LinkedList<String>();
        for (String currentWord: input.split(delimiters)){
            parsedString.add(currentWord);
        }
        return parsedString;
    }

    private static void printStringArray(String[] stringArray){
        for (String element: stringArray){
            System.out.print(element + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<String> wordList;
        wordList = parseString("Hello World! Nice to meet you.");
        System.out.println(wordList);
        /* Convert linked list to array */
        String wordArray[];
        wordArray = wordList.toArray(new String[wordList.size()]);
        printStringArray(wordArray);
    }
}
