import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class Main{
    public static int nextId = 1;
    public static void add_process(int pre_run_process,Process[] p,int[] array_ram,Scanner sc){
        for(int i=0;i<pre_run_process;i++){
            System.out.println("Enter details of process "+(i+1)+" (size,start_address):");
            int size = checkNegativeInput(sc);
            int startaddress = checkNegativeInput(sc);
            int id = nextId++;
            p[i] = new Process(id,size,startaddress);
            for(int j=startaddress;j<startaddress+size;j++){
                if(array_ram[j] != 0){
                    System.out.println("This space is already occupied by another process...!");
                    break;
                }else{
                    array_ram[j] = id;
                }
            }
        }
    }
    public static void print_ram(int[] array_ram){
        System.out.println("--------------------RAM--------------------");
        for(int i=0;i<array_ram.length;i++){
            System.out.print("["+array_ram[i]+"]");
        }
    }
    public static void First_Fit(Scanner sc, int[] array_ram, E_process[] ep) {
        System.out.println("Enter number of processes you want to add:");
        int a_p = checkNegativeInput(sc);
        for (int i = 0; i < a_p; i++) {
            System.out.print("Enter size of process" + (i + 1) + ":");
            int size = checkNegativeInput(sc);
            int id = nextId++;
            ep[i] = new E_process(size, id);
        }
        int epIndex = 0;
        for (int j = 0; j < array_ram.length; j++) {
            if (array_ram[j] == 0) {
                int count = 0;
                int startAddress = j;
                while (epIndex < a_p && j < array_ram.length && array_ram[j] == 0 && count < ep[epIndex].getsize()) {
                    count++;
                    j++;
                }
                if (epIndex < a_p && count >= ep[epIndex].getsize()) {
                    for (int k = startAddress; k < startAddress + ep[epIndex].getsize(); k++) {
                        array_ram[k] = ep[epIndex].getid(); 
                    }
                    epIndex++;
                } else {
                    if (epIndex < a_p && ep[epIndex] != null) {
                        System.out.println("Memory allocation failed for process " + ep[epIndex].getid() + ". No suitable block available");
                    }
                }
            }
        }
        print_ram(array_ram);
    }
    public static void Best_Fit(Scanner sc,int id,int[] array_ram,E_process[] ep){
        System.out.print("Enter number of processes you want to add:");
        int a_p = checkNegativeInput(sc);
        for(int i=0;i<a_p;i++){
            System.out.print("Enter size of process "+(i+1)+":");
            int size = checkNegativeInput(sc);
            id = nextId++;
            ep[i] = new E_process(size,id);
        }
        Map<Integer,Integer>available_blocks = new HashMap<>();
        for(int i = 0; i<array_ram.length;i++){
            if(array_ram[i] == 0){
                int blockSize = 0;
                int start = i;
                while(i<array_ram.length && array_ram[i] == 0){
                    blockSize++;
                    i++;
                }
                if(blockSize > 0){
                    available_blocks.put(start,blockSize);
                }
            }
        }
        for(int i=0;i<a_p;i++){
            int processSize = ep[i].getsize();
            int allocateStart = allocateBestFit(available_blocks, processSize);
            if(allocateStart != -1){
                for(int j=allocateStart;j<allocateStart+processSize;j++){
                    array_ram[j] = ep[i].getid();
                }
                available_blocks.remove(allocateStart);
            }else{
                System.out.println("Memory allocation failed for process "+ep[i].getid()+". No suitable block is available");
            }
        }
        print_ram(array_ram);
    }
    private static int allocateBestFit(Map<Integer,Integer>available_blocks,int processSize){
        int bestFitStart = 0;
        int bestFitSize = Integer.MAX_VALUE;
        for(Map.Entry<Integer,Integer> entry : available_blocks.entrySet()){
            int blockStart = entry.getKey();
            int blockSize = entry.getValue();
            if(blockSize >= processSize && blockSize < bestFitSize){
                bestFitStart = blockStart;
                bestFitSize = blockSize;
            }
        }
        return bestFitStart;
    }
    public static void Worst_fit(Scanner sc,int id,int[] array_ram,E_process[] ep){
        System.out.print("Enter number of processes you want to add:");
        int a_p = checkNegativeInput(sc);
        for(int i=0;i<a_p;i++){
            System.out.print("Enter size of process "+(i+1)+":");
            int size = checkNegativeInput(sc);
            id = nextId++;
            ep[i] = new E_process(size, id);
        }Map<Integer,Integer> available_blocks = new HashMap<Integer,Integer>();
        for(int j=0;j<array_ram.length;j++){
            if(array_ram[j] == 0){
                int blockSize = 0;
                int start = j;
                while(j<array_ram.length && array_ram[j] == 0){
                    blockSize++;
                    j++;
                }
                if(blockSize > 0){
                    available_blocks.put(start,blockSize);
                }
            }
        }
        for(int i = 0;i<a_p;i++){
            int processSize = ep[i].getsize();
            int allocateStart = allocateWorstFit(available_blocks, processSize);
            if(allocateStart != -1){
                for(int j = allocateStart;j<allocateStart+processSize;j++){
                    array_ram[j] = ep[i].getid();
                }
                available_blocks.remove(allocateStart);
            }else{
                System.out.print("Memory allocation failed for process "+ep[i].getid()+". No suitable block available");
            }
        }
        print_ram(array_ram);
    }
    private static int allocateWorstFit(Map<Integer,Integer> available_blocks,int processSize){
        int worstFitStart = -1;
        int worstFitSize = -1;
        for(Map.Entry<Integer,Integer> entry : available_blocks.entrySet()){
            int blockStart = entry.getKey();
            int blockSize = entry.getValue();
            if(blockSize >= processSize && blockSize > worstFitSize) {
                worstFitStart = blockStart;
                worstFitSize = blockSize;
            }
        }
        return worstFitStart;
    }
    public static void compact_memory(int[] array_ram){
        int id = 0;
        for(int i=0;i<array_ram.length;i++){
            if(array_ram[i]!=0){
                array_ram[id++] = array_ram[i];
            }
        }
        for(int i=id;i<array_ram.length;i++){
            array_ram[i] = 0;
        }
    }
    public static void release_memory(int processId,int[] array_ram){
        boolean success = false;
        for(int i=0;i<array_ram.length;i++){
            if(array_ram[i] == processId){
                array_ram[i] = 0;
                success = true;
            }
        }
        if(success){
            System.out.println("Memory released successfully...!");
        }else{
            System.out.println("Could not find process with id " + processId);
        }
    }
    public static void analyzeFragmentation(int[] array_ram){
        int freeBlocks = 0;
        int totalFreeSize = 0;
        int currentBlockSize = 0;
        boolean inFreeBlock = false;
        for(int i=0;i<array_ram.length;i++){
            if(array_ram[i] == 0){
                if(!inFreeBlock){
                    inFreeBlock = true;
                    freeBlocks++;
                }
                currentBlockSize++;
            }else{
                if(inFreeBlock){
                    totalFreeSize+=currentBlockSize;
                    currentBlockSize = 0;
                    inFreeBlock = false;
                }
            }
        }
        if (inFreeBlock) {
            totalFreeSize += currentBlockSize;
        }
        System.out.println("Memory Fragmentation Analysis:");
        System.out.println("Number of free blocks: " + freeBlocks);
        System.out.println("Total free memory size: " + totalFreeSize);
    }
    public static int checkRam(Scanner sc){
        int temp = sc.nextInt();
        while(temp <= 0){
            System.out.print("Ram size must be greater than 0,please try again:");
            temp = sc.nextInt();
        }
        return temp;
    }
    public static int checkNegativeInput(Scanner sc) {
        int temp = sc.nextInt();
        while (temp < 0) {
            System.out.print("Input value is negative, please enter a non-negative value: ");
            temp = sc.nextInt();
        }
        return temp;
    }
    
    public static void main(String[] args) {
        Process[] p = new Process[100];
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            try {
                System.out.print("Enter size of your RAM:");
                int size_of_ram = checkRam(sc);
                final int[] array_ram = new int[size_of_ram];
                E_process[] ep = new E_process[100];
                System.out.print("Enter no. of pre-running processes:");
                int pre_run_process = checkNegativeInput(sc);
                add_process(pre_run_process, p, array_ram, sc);
                print_ram(array_ram);
    
                while (true) {
                    System.out.println("\n1)Allocation in First Fit");
                    System.out.println("2)Allocation in Best Fit");
                    System.out.println("3)Allocation in Worst Fit");
                    System.out.println("4)Compact Memory");
                    System.out.println("5)Release Memory for Process");
                    System.out.println("6)Memory Fragmentation Analysis");
                    System.out.println("7)Exit");
                    System.out.print("Enter your choice:");
                    int choice = checkNegativeInput(sc);
                    
                    switch (choice) {
                        case 1:
                            First_Fit(sc, array_ram, ep);
                            break;
                        case 2:
                            Best_Fit(sc, choice, array_ram, ep);
                            break;
                        case 3:
                            Worst_fit(sc, choice, array_ram, ep);
                            break;
                        case 4:
                            compact_memory(array_ram);
                            print_ram(array_ram);
                            break;
                        case 5:
                            System.out.print("Enter process id to release memory:");
                            int processId = checkNegativeInput(sc);
                            release_memory(processId, array_ram);
                            print_ram(array_ram);
                            break;
                        case 6:
                            analyzeFragmentation(array_ram);
                            break;
                        case 7:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    
                    if (exit) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Do you want to retry the operation? (yes/no)");
                String retryChoice = sc.next();
                if (!retryChoice.equalsIgnoreCase("yes")) {
                    exit = true;
                }
            }
        }
        
        sc.close();
    }
    
}