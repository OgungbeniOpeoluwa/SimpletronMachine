import java.util.Arrays;
import java.util.Scanner;

public class SimpletronMachineLanguage {
    String [] memory = new String[100];
    String accumulator = "+0000";

    int instructionLocation;

    public SimpletronMachineLanguage(){
        Arrays.fill(memory,"+0000");

    }
    public int getInstructionLocation(){
        return instructionLocation;
    }
    public void checkInput(String input){
        int memoryLocation = Integer.parseInt(input.substring(3));
        int operations = 0;
        if(input.contains("+") || input.contains("-")) {
             operations = Integer.parseInt(input.substring(1,3));
        }
        else{
            operations = Integer.parseInt(input.substring(0,3));
        }
       if(operations ==SimpletronOperations.READ.getValue()){
           read(memoryLocation);
       }
       else if (operations == SimpletronOperations.WRITE.getValue()) {
           write(memoryLocation);
       }
       else if (operations== SimpletronOperations.LOAD.getValue()) {
           load(memoryLocation);
       }else if (operations== SimpletronOperations.STORE.getValue()) {
           store(memoryLocation);
       }
       else if (operations == SimpletronOperations.ADD.getValue()) {
           add(memoryLocation);
       }
       else if(operations == SimpletronOperations.SUBTRACT.getValue()){
           subtract(memoryLocation);
       } else if (operations == SimpletronOperations.DIVIDE.getValue()) {
           divide(memoryLocation);
      
       } else if (operations == SimpletronOperations.MULTIPLY.getValue()) {
           multiply(memoryLocation);

       } else if (operations == SimpletronOperations.BRANCH.getValue()) {
           branch(memoryLocation);
       } else if (operations == SimpletronOperations.BRANCHZERO.getValue()) {
           branchZero(memoryLocation);
       }else if(operations == SimpletronOperations.BRANCHNEG.getValue()){
           branchNeg(memoryLocation);
       }
       else{
           halt();
       }

    }

    private void store(int memoryLocation) {
        this.memory[memoryLocation] = this.accumulator;
    }

    private void halt() {
        instructionLocation = 9999;
    }

    private void branchNeg(int memoryLocation) {
        if(Integer.parseInt(this.accumulator) < 0){
            this.instructionLocation = memoryLocation;

        }
    }

    private void branchZero(int memoryLocation) {
        if(Integer.parseInt(this.accumulator) == 0){
            this.instructionLocation = memoryLocation;
        }

    }

    private void branch(int memoryLocation) {
        this.instructionLocation = memoryLocation;
    }

    private void multiply(int memoryLocation) {
        String wordMemory = this.memory[memoryLocation];
        int multiply = Integer.parseInt(this.accumulator) * Integer.parseInt(wordMemory);
        
        String word = String.valueOf(multiply) ;

        word = checkIfFirstIndexAsMinusSign(word);

        int length = word.length();
        
        if(length < 5){
            word = padWordWithZeros(word);
        }
        if(length > 5){
            word = word.substring(0,5);
        }
        this.accumulator = word;
    
    }

    private static String checkIfFirstIndexAsMinusSign(String word) {
        if(word.charAt(0) != '-'){
            word = "+" + word;
        }
        return word;
    }

    private void divide(int memoryLocation) {
        String wordMemory = this.memory[memoryLocation];
        if(Integer.parseInt(wordMemory)== 0) {
            throw new ArithmeticException("zero cant be divided");}
        
        int divide = Integer.parseInt(this.accumulator) / Integer.parseInt(wordMemory);

        String word = String.valueOf(divide);

        word = checkIfFirstIndexAsMinusSign(word);
       
       
        int wordLength = word.length();

        if(wordLength < 5){
            word = padWordWithZeros(word);
        }
        if(wordLength > 5){
            word = word.substring(0,5);
        }
        this.accumulator = word;


    }

    private  void subtract(int memoryLocation) {
        String worldInMemory = this.memory[memoryLocation];
        int subtract = Integer.parseInt(this.accumulator) - Integer.parseInt(worldInMemory);

        String word = String.valueOf(subtract) ;

        word = checkIfFirstIndexAsMinusSign(word);
        int wordLength = word.length();

        if(wordLength < 5){
            word = padWordWithZeros(word);
        } else if (wordLength > 5) {
            word = word.substring(0, 5);
        }

        this.accumulator = word;

    }

    private void add(int memoryLocation) {
        String wordInMemory = this.memory[memoryLocation];
        int sum = Integer.parseInt(this.accumulator) + Integer.parseInt(wordInMemory);

        String word = String.valueOf(sum);
        
        
        word = checkIfFirstIndexAsMinusSign(word);

        int wordLength = word.length();

        if(wordLength < 5){
            word = padWordWithZeros(word);
        } else if (wordLength > 5) {
            word = word.substring(0, 5);
        }

        this.accumulator = word;
    }

    private void load(int memoryLocation) {
        String word = this.memory[memoryLocation];
        this.accumulator = word;
    }

    private void write(int memoryLocation) {
        String word = this.memory[memoryLocation];
        System.out.println(word);
    }

    private void read(int memoryLocation) {
        Scanner sc = new Scanner(System.in);
        System.out.println( "Enter a number: ");
        String input = sc.nextLine();

        if (!(input.charAt(0) == '+') && !(input.charAt(0) == '-')){
            input = "+" + input;
        }

        int inputLength = input.length();

        if(inputLength < 5){
            input = padWordWithZeros(input);
        } else if (inputLength > 5) {
            System.out.println("Input too long. Max: 4 digits");
            read(memoryLocation);
        }
        memory[memoryLocation] = input;
    }
    
    
    
    private String padWordWithZeros(String word){
        char operator = word.charAt(0);
        word = word.substring(1);
        int wordLength = word.length();

        while (wordLength != 4){
            word = "0" + word;
            wordLength = word.length();
        }

        word = operator + word;
        return word;
    }

    public void loadInstruction(){
        int counter = 1;
        int count = 0;
        for(count = 0; count < 20; count++){
            String instruction = "+105" + count;
            memory[count] = instruction;
            if(count == 10){
                memory[count] = "+2050" ;
            }
            if(count > 10){
                instruction = "+305" + counter;
                memory[count] = instruction;
                counter++;
            }
        }
        memory[count++] = "+2170";
        memory[count++] = "+1170";
        memory[count] = "+4300";
    }

    public void loadInstruction2(){
        int count = 0;
        int counter = 1;
        for(count = 0; count < 14; count++){
            String instruction = "+105" + count;
            memory[count] = instruction;
            if(count == 7){
                memory[count] = "+2050" ;
            }
            if(count > 7){
                instruction = "+305" + counter;
                memory[count] = instruction;
                counter++;
            }
        }
        memory[count++] = "+2178";
        memory[count++] = "+1178";
        memory[count++] = "+105" + counter;
        memory[count++] = "+325" + counter;
        memory[count++] = "+2170";
        memory[count++] = "+1170";
        memory[count] = "+4300";
    }
    public  void loadInstruction3(){
        int count = 0;
        memory[count++] = "+1020";
        memory[count++] = "+1021";
        memory[count++] = "+1022";
        memory[count++] = "+1023";

        memory[count++] = "+2020";
        memory[count++] = "+3121";

        memory[count++] = "+4110";

        memory[count++] = "+2020";
        memory[count++] = "+3122";

        memory[count++] = "+41";

        memory[count++] = "+2021";
        memory[count++] = "+3122";

        memory[count++] = "+4116";

        memory[count++] = "+2022";
        memory[count] = "+2023";

        memory[count++] = "+41";

        memory[count++] = "+1122";
        memory[count++] = "+4300";

        memory[count++] = "+2023";
        memory[count++] = "+4300";
    }


    public void run(){
        loadInstruction3();
        System.out.println(Arrays.toString(memory));
        int count =0;
        while(instructionLocation < 9999){
           checkInput(memory[instructionLocation]);
           if(count < instructionLocation){
               count = instructionLocation;
               continue;
           }
           instructionLocation++;
          count++;
        }
    }
}
