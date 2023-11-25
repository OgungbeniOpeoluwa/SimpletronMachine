import java.util.Arrays;
import java.util.Scanner;

public class  ComputerSimulator {

    private int[] memory = new int [100];
    int instructionCounter = 00;
    public void firstDisplay(){
        System.out.println("""
                ** Welcome to Simpletron! **
                ** Please enter your program one instruction **
                ** (or data word) at a time. I will display **
                ** the location number and a question mark (?). **
                ** You then type the word for that location. **
                ** Type -99999 to stop entering your program. **""");
    }

    public void collectInstruction(){
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        while(input != -99999){
            input = scanner.nextInt();
            System.out.print(instructionCounter + " ? " + input);
            memory[instructionCounter] = input;
            instructionCounter++;
        }
        System.out.println(Arrays.toString(memory));
    }

    public static void main(String[] args) {
        ComputerSimulator computerSimulator = new ComputerSimulator();
        computerSimulator.collectInstruction();
    }
}
