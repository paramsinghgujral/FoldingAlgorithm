
//This program takes the a edges and b edges in the immersion as input and computes the basis


//The Breadth first seach part of this program has been taken from the GeeksforGeeks page for Breadth First Search Java Implementation
//Link to GeeksforGeeks page: https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
import java.io.*;
import java.util.*;
 
// This class represents a directed graph using adjacency list
// representation
class FindBasis
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
    private int EdgesBFS[][];
    private String GenStr;
    // Constructor
    FindBasis(int v)
    {
        V = v;
        EdgesBFS = new int[v][v];
        //filling EdgesBFS with -1 in every cell
        for(int i=0;i<v;i++)
        {  for(int j = 0; j<v;j++)
            {EdgesBFS[i][j]= -1;}
        }
        
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
 
    // prints BFS traversal from a given source s
    
    
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");   
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                    
                    //Identifying the edge (s,n) in EdgesBFS
                    EdgesBFS [s][n] = 0;
                    
                }
            }
        }
    }
 
    // Driver method to
    public static void main(String args[]) throws IOException
    {
        int vert1[][], vert2[][], vert3[][], vert4[][],noofvert;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Enter number of vertices in original graph");
        noofvert = Integer.parseInt(br.readLine());
        
        
        vert1 = new int [noofvert][noofvert];
        vert2 = new int [noofvert][noofvert];
        
        vert3 = new int [noofvert][noofvert];
        vert4 = new int [noofvert][noofvert];
        
        //filling vert1 and vert2 and vert3 and vert4 with -1 indicating no active edges
        for (int i=0;i<noofvert;i++)
        { for (int j=0; j<noofvert;j++)
            {vert1[i][j] = -1; vert2[i][j] = -1;vert3[i][j] = -1; vert4[i][j] = -1;}
        }
        
      //take input of generators of the subgroup H
      
        
      FindBasis g = new FindBasis(noofvert);
      
        System.out.println("Number of a edges in vert1 of the immersion");
        int counta = Integer.parseInt(br.readLine()); 
        for(int i=0;i<counta;i++)
        {
          System.out.println("Enter coordinates of a edge in vert 1: Eg: 2,3 ");
          String aedge = br.readLine();
          aedge = aedge.trim();
          for(int j =0; j<aedge.length();j++)
          {
             if(aedge.charAt(j)==',')
             {
                 int aedge1 = Integer.parseInt(aedge.substring(0,j));
                 int aedge2 = Integer.parseInt(aedge.substring(j+1,aedge.length()));
                 vert1[aedge1][aedge2] = 0;
                 g.addEdge(aedge1,aedge2);
                 g.addEdge(aedge2, aedge1);
                 break;
             }
          }
          
        }
        
         System.out.println("Number of b edges in vert2 of the immersion");
        int countb = Integer.parseInt(br.readLine()); 
        
        for (int i=0; i<countb;i++)
        {
            System.out.println("Enter coordinates of b edge in vert 2: Eg: 2,3 ");
            String bedge = br.readLine();
            bedge = bedge.trim();
            
            for(int j=0; j<bedge.length(); j++)
            {
              if(bedge.charAt(j)==',')
              {
                 int bedge1 = Integer.parseInt(bedge.substring(0,j));
                 int bedge2 = Integer.parseInt(bedge.substring(j+1,bedge.length()));
                 vert2[bedge1][bedge2] = 0;
                 g.addEdge(bedge1,bedge2);
                 g.addEdge(bedge2,bedge1);
                 break;
              }
            }

        }
        
        
        
                           
        g.BFS(0); 
        
        
        
        //checking that each zero in EdgesBFS is paired with a non zero in either vert1 or vert2 or vert3 or vert 4
        
        for(int i=0;i<noofvert;i++)
        {
          for(int j=0;j<noofvert;j++)
          {
             if (g.EdgesBFS[i][j] != -1)  
             {
                 if (vert1[i][j] == -1 && vert2[i][j]==-1)
                 {
                     //so this must mean there is an edge from j to i
                     if(vert1[j][i]!=-1)
                     { vert1[j][i] = -1; 
                       vert3[i][j] = 0;
                     }
                     else if(vert2[j][i]!=-1)
                     {
                       vert2[j][i] = -1;
                       vert4[i][j] = 0;
                     }
                 }
             }
          }
        
        }
        //Creating the generators for each edge not identified in EdgesBFS
        
        
        String StrGenerators = "";
        
        for (int i = 0; i <noofvert;i++)
        {
          for (int j = 0; j< noofvert;j++)
          { 
              
              String StrLeft = "", StrRight = "";
              int count=0;
              
              if(vert1[i][j]!=-1)
              count = count + 1;
              
              if(vert2[i][j]!=-1)
              count = count + 1;
              
              if (vert3[i][j]!=-1)
              count = count + 1;
              
              if (vert4[i][j] != -1)
              count = count + 1;
              
              if (g.EdgesBFS[i][j]!= -1)
              count = count - 1;
              
              if (count == 0)
              {  //either all are -1 or only one edge that has been used in tree
                  continue;
              }
              
              else
              {
              
            
             //need to cacluate the generator so calculating the path from 0 vertex to i and from j to 0 vertex along maximal tree
                int RightMost; int LeftMost;
                RightMost = i; LeftMost = j;
                
              
                
                //In this while loop we will calculate StrLeft
                while (true)
                {
                   if (RightMost == 0)
                   {break;}
                   
                   for (int k = 0; k < noofvert; k++)
                   { 
                      if (g.EdgesBFS[k][RightMost] != -1)
                      {
                          //know that k connects to i in maximial tree 
                          if (vert1[k][RightMost] != -1)
                          {StrLeft ="a^(1)"+ StrLeft ;}
                          else if (vert2[k][RightMost]!= -1)
                          {StrLeft ="b^(1)" + StrLeft;}
                          else if (vert3[k][RightMost]!= -1)
                          {StrLeft ="a^(-1)" + StrLeft;}
                          else 
                          {StrLeft ="b^(-1)" + StrLeft;}
                          
                        RightMost = k; break; //after the break we go outside the k loop  
                      }
                   }
                   
                }
                //this while loop computes LeftMost by going from j to 0
                while (true)
                {
                   if (LeftMost == 0)
                   {break;}
                   
                   for (int k = 0; k < noofvert; k++)
                   { 
                      if (g.EdgesBFS[k][LeftMost] != -1)
                      {
                          //know that k connects to i in maximial tree 
                          if (vert1[k][LeftMost] != -1)
                          {StrRight = StrRight + "a^(-1)";}
                          else if(vert2[k][LeftMost] != -1)
                          {StrRight = StrRight + "b^(-1)";}
                          else if(vert3[k][LeftMost] != -1)
                          {StrRight = StrRight + "a^1";}
                          else
                          {StrRight = StrRight + "b^1";}
                          
                        LeftMost = k; break; //after the break we go outside the k loop  
                      }
                   }
                   
                }
                
                //We are looking at edges from i to j. There are only two possible edges a and b. We know there is 
                //at least one edge from i to j  is not in the maximal tree
                //StrLeft contains the path from 0 to i. StrRight conatins the path from j to 0. 
                
                if (g.EdgesBFS[i][j] != -1)
                {
                     //so one of the edges is in the maximal tree but we also know one edge isnt.
                     
                     if (vert1[i][j] != -1)
                     {  //so the a edge is used in tree
                        if (vert2[i][j]!= -1)
                        {StrGenerators = StrGenerators + "$" + (StrLeft + "b" + StrRight);}
                        if (vert3[i][j]!= -1)
                        {StrGenerators = StrGenerators + "$" + (StrLeft + "a^(-1)" + StrRight);}
                        if (vert4[i][j]!= -1)
                        {StrGenerators = StrGenerators + "$" + (StrLeft + "b^(-1)" + StrRight);}
                     }
                     else
                     {
                        if (vert2[i][j]!= -1)
                        {
                        //so b edge used in tree
                        if (vert3[i][j]!= -1)
                        {StrGenerators = StrGenerators + "$" + (StrLeft + "a^(-1)" + StrRight);}
                        if (vert4[i][j]!= -1)
                        {StrGenerators = StrGenerators + "$" + (StrLeft + "b^(-1)" + StrRight);}
                        
                        }
                        
                        else
                        {
                          if (vert3[i][j]!= -1)
                          {
                            //so the a^-1 edge used in tree
                            if (vert4[i][j]!= -1)
                            {StrGenerators = StrGenerators + "$" + (StrLeft + "b^(-1)" + StrRight);}
                          }
                        }
                     }
                     
                }
                else
                {
                     if (vert1[i][j]!=-1)
                     {
                         //there is the a edge and it is not in max tree
                         StrGenerators = StrGenerators + " $ " + (StrLeft + "a" + StrRight);
                     }
                     if (vert2[i][j]!=-1)
                     {   //there is the b edge and it is not in max tree
                         StrGenerators = StrGenerators + " $ " + (StrLeft + "b" + StrRight);
                     }
                     if (vert3[i][j]!= -1)
                     {StrGenerators = StrGenerators + "$" + (StrLeft + "a^(-1)" + StrRight);}
                     if (vert4[i][j]!= -1)
                     {StrGenerators = StrGenerators + "$" + (StrLeft + "b^(-1)" + StrRight);}
                }
              
            
            
            }//close else
          }//close j loop
        
        
        }//close i loop
        System.out.println("");
        System.out.println("Basis elements are: " + StrGenerators);
        
       
        
        
    }
}





