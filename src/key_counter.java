public class key_counter {
    public static String fileName;            //String for file path
    public static void main(String[] args) {
        //File path
         fileName = "input_text.txt";
        KeyCounter kC = new KeyCounter(fileName);
        kC.printTotals();
    } //End main method
} //End class key_counter