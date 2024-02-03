import java.util.Scanner;
public class Main{
    public static int id;
    public static void add_process(int pre_run_process,Process[] p,int[] array_ram,Scanner sc){
        for(int i=0;i<pre_run_process;i++){
            System.out.println("Enter details of process "+(i+1)+" (size,start_address):");
            int size = sc.nextInt();
            int startaddress = sc.nextInt();
            int id = i + 1;
            p[i] = new Process(id,size,startaddress);
            for(int j=startaddress;j<startaddress+size;j++){
                array_ram[j] = id;
            }
        }
    }
    public static void print_ram(int[] array_ram){
        System.out.print("--------------------RAM--------------------");
        for(int i=0;i<array_ram.length;i++){
            System.out.print("["+array_ram[i]+"]");
        }
    }
    public static void main(String[] args) {
        Process[] p = new Process[100];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of your RAM:");
        int size_of_ram = sc.nextInt();
        int[] array_ram = new int[size_of_ram];
        System.out.print("Enter no. of pre-running processes:");
        int pre_run_process = sc.nextInt();
        add_process(pre_run_process, p, array_ram, sc);
        print_ram(array_ram);
    }
}