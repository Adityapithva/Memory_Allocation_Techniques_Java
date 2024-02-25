import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class Main{
    public static int nextId = 1;
    public static void add_process(int pre_run_process,Process[] p,int[] array_ram,Scanner sc){
        for(int i=0;i<pre_run_process;i++){
            System.out.println("Enter details of process "+(i+1)+" (size,start_address):");
            int size = sc.nextInt();
            int startaddress = sc.nextInt();
            int id = nextId++;
            p[i] = new Process(id,size,startaddress);
            for(int j=startaddress;j<startaddress+size;j++){
                array_ram[j] = id;
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
        int a_p = sc.nextInt();
        for (int i = 0; i < a_p; i++) {
            System.out.print("Enter size of process" + (i + 1) + ":");
            int size = sc.nextInt();
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
                        array_ram[k] = ep[epIndex].getid(); // Update RAM array
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
        int a_p = sc.nextInt();
        for(int i=0;i<a_p;i++){
            System.out.print("Enter size of process "+(i+1)+":");
            int size = sc.nextInt();
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
        int a_p = sc.nextInt();
        for(int i=0;i<a_p;i++){
            System.out.print("Enter size of process "+(i+1)+":");
            int size = sc.nextInt();
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
    public static void main(String[] args) {
        Process[] p = new Process[100];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of your RAM:");
        int size_of_ram = sc.nextInt();
        final int[] array_ram = new int[size_of_ram];
        E_process[] ep = new E_process[100];
        System.out.print("Enter no. of pre-running processes:");
        int pre_run_process = sc.nextInt();
        try{
            add_process(pre_run_process, p, array_ram, sc);
            print_ram(array_ram);
            while(true){
                System.out.println("\n1)Allocation in First Fit");
                System.out.println("2)Allocation in Best Fit");
                System.out.println("3)Allocation in Worst Fit");
                System.out.println("4)Exit");
                System.out.print("Enter your choice:");
                int choice = sc.nextInt();
                switch(choice){
                    case 1:
                        First_Fit(sc,array_ram,ep);
                        break;
                    case 2:
                        Best_Fit(sc, choice, array_ram, ep);
                        break;
                    case 3:
                    Worst_fit(sc, choice, array_ram, ep);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        }catch(Exception e){
            System.out.println("An error occured :"+e.getMessage());
        }finally{
            sc.close();
        }
    }
}