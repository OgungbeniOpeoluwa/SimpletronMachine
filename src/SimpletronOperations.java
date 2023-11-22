public enum SimpletronOperations {
    READ(10), WRITE(11), LOAD(20), STORE(21), ADD(30), SUBTRACT(31),
    DIVIDE(32), MULTIPLY(33), BRANCH(40), BRANCHNEG(41), BRANCHZERO(42), HALT(43);
    private int value;

    SimpletronOperations(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
