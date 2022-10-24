The first program (ReachImmersion) takes the generators of the subgroup H as input. It will create a graph with petals for each generator. (I call this the original graph.) It will calculate first the number of vertices in the original graph and print this number. Then it performs all the folds to get to an immersion. It prints out the a and b edges of the immersion.

All through this program stores a edges in an array called vert1[][] and b edges in an array called vert2[][]. The dimensions of these two arrays are [#no of vertices in the original graph][ #no of vertices in the original graph ]. The first program prints the a and b edges of the immersion as (i,j) coordinates of vert1 and vert2 respectively.

The second program (FindBasis) will take the immersion as input and print the basis of the fundamental group of the immersion. It computes the basis by identifying the maximal tree in the graph. For each edge not in the maximal tree it draws a loop in the maximal tree that passes the edge. Each loop becomes a basis element.  
The first and second programs are not linked in any way so the second program will ask you to enter some details calculated by the first program. 

The second program will first ask for the number of vertices in the original graph. (This is the number calculated by the first program.) It will then create vert1 and vert2 of the same size. Then the program will ask for the number of a edges in vert1 of the immersion. (This is also printed out by the first program.) It will then ask you to enter the coordinates of the a edges of vert1. (These are the coordinates of vert1 printed by the first program at the end.) It will ask for identical information for the b edges in vert2.

 
BlueJ:

This is the link to download BlueJ. https://www.bluej.org/

To run the first program:
Please go back to the earlier window which has the brown box with the name of the class "ReachImmersion". Please right click on that box and select the option "new ReachImmersion()." 

A dialog box will appear asking for name of instance. Please click ok. A red box will appear at the bottom. Please right click on that red box and select "void main()."

The program is running and it will take you to the terminal window and ask for the input. Once the program has printed the outputs, if you want to rerun this same program again you need to right click on the redbox and select "void main." 

To run the second program:
Right click on the brown box called FindBasis and choose the option "void main(String [] args)." It will take you to the terminal window for the input. To rerun this program again, right click on the brown box and select “void main(String [] args)”

If you wish to stop the program while running you need to right click on the bar at the right bottom and choose “Reset Java Virtual Machine.” 








