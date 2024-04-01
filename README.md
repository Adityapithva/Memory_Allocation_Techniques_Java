Memory Management Simulation

This Java program is a simple memory management system that demonstrates three different allocation strategies: First Fit, Best Fit, and Worst Fit. It allows users to allocate, release, and analyze memory fragmentation within a simulated RAM environment.

Features:
->Pre-Running Process initialization:Users can initialize a predefined number of processes with specified sizes and start addresses.
->Memory Allocation:
  First Fit:Allocates memory for new processes by finding the first available block that fits the process size.
  Best Fit: Finds the block of memory that best fits the size of the process being allocated.
  Worst Fit:Allocates memory to the largest available block that fits the process size.
->Memory Compaction:Compacts memory by moving all allocated processes to the beginning of memory to reduce fragmentation.
->Memory Release: Frees memory allocated to a specific process.
->Fragmentation Analysis: Analyzes memory fragmentation by calculating the number of free blocks and total free memory size.

Usage:
1)Setting Up RAM Size:Users input the size of the RAM at the beginning of the program execution.
2)Pre-Running Processes: Users initialize a set number of processes with sizes and start addresses.
3)Menu Interface: Users can select different operations from the menu:
  Allocation in First Fit
  Allocation in Best Fit
  Allocation in Worst Fit
  Memory Compaction
  Release Memory for Process
  Memory Fragmentation Analysis
  Exit

How To Run:
1)Compile the Java file: javac Main.java
2)Run the compiled file: java Main

