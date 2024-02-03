public class Process {
    private int id;
    private int size;
    private int startAddress;
    public Process(int id, int size, int startAddress) {
        this.id = id;
        this.size = size;
        this.startAddress = startAddress;
    }
    public int getId() {
        return id;
    }
    public int getSize() {
        return size;
    }
    public int getStartAddress() {
        return startAddress;
    }
}
