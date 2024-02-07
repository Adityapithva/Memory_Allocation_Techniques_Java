import java.util.Scanner;
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
        // Print RAM after allocation
        print_ram(array_ram);
    }
    
    public static void main(String[] args) {
        Process[] p = new Process[100];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of your RAM:");
        int size_of_ram = sc.nextInt();
        int[] array_ram = new int[size_of_ram];
        E_process[] ep = new E_process[100];
        System.out.print("Enter no. of pre-running processes:");
        int pre_run_process = sc.nextInt();
        add_process(pre_run_process, p, array_ram, sc);
        print_ram(array_ram);
        while(true){
            System.out.println("1)Allocation in First Fit");
            System.out.println("2)Allocation in Best Fit");
            System.out.println("3)Allocation in Worst Fit");
            System.out.println("4)Exit");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    First_Fit(sc,array_ram,ep);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}