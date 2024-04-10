public class Process {
    private Short id;
    private Short size;
    private Short startAddress;
    public Process(Short id, Short size, Short startAddress) {
        this.id = id;
        this.size = size;
        this.startAddress = startAddress;
    }
    public Short getId() {
        return id;
    }
    public Short getSize() {
        return size;
    }
    public Short getStartAddress() {
        return startAddress;
    }
}
