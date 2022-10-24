

import java.io.*;

public class ReachImmersion
{
    void main() throws IOException
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int noofvert = 0, noofgen=0; String str;
       
       
       System.out.println("Number of generators of subgroup H");
       noofgen = Integer.parseInt(br.readLine());
       
       String words[];
      words = new String [noofgen];
      System.out.println("Symbols for generators: a,b, x instead of a^-1, y instead of b^-1");
      
      for (int i=0; i<noofgen; i++)
      {
         System.out.println("Enter generator: Eg yaabbbxy");
       words[i]= br.readLine();
       words[i] = words[i].trim();
      }
        //adding the modulus of powers
       
       noofvert =0;
        //Calculating noofvert
       for (int i=0; i<noofgen;i++)
       {  
          str = words[i]; 
          
           noofvert = noofvert + str.length();
        
       }
       noofvert = noofvert - noofgen + 1;
       System.out.println("No of vertices in original graph: "+ noofvert);
       //Create arrays vert1, vert2, vert3 and vert4  
       
       int vert1[][], vert2[][], vert3[][], vert4[][];
       
       vert1 = new int[noofvert][noofvert]; vert3 = new int[noofvert][noofvert];
       vert2 = new int[noofvert][noofvert]; vert4 = new int[noofvert][noofvert];
       
       //fill with -1
       
        for (int i=0;i<noofvert;i++)
        { for(int j=0; j<noofvert;j++)
            {vert1[i][j] = -1; vert2[i][j] = -1;vert3[i][j] = -1; vert4[i][j] = -1;}
        }
        
      //filling vert1 and vert2  
      int tback=0; int tforw=1; 
      for (int i =0; i<noofgen; i++)
      {
          str = words[i]; 
          tback=0;
          
          if (str.length()==1)
          {  //word[i] is a singleton like "a" or "x" or "b"
              if (str.charAt(0)=='a' || str.charAt(0)=='x')
              {
                vert1[0][0] = 0;  
              }
              else
              {vert2[0][0] = 0;}
    
          }
          
          else
          {   //considering the first edge outside the j loop
             if (str.charAt(0) == 'a')
             {
                 vert1[0][tforw] = 0; tback = tforw; tforw = tforw + 1;
             }
             else if (str.charAt(0) == 'b')
             {
                 vert2[0][tforw] = 0; tback = tforw; tforw = tforw + 1;
             }
             else if (str.charAt(0) == 'x')
             {vert1[tforw][0] = 0; tback = tforw; tforw = tforw + 1;}
             
             else
             {vert2[tforw][0] = 0; tback = tforw; tforw = tforw + 1;}
             
              //word[i] has length greater than 1
             for (int j=1; j<str.length()-1;j++)
             {    char ch = str.charAt(j);
                  if (ch == 'a')
                  {vert1[tback][tforw] = 0; tback = tback + 1; tforw = tforw + 1;}
                  else if (ch == 'b')
                  {vert2[tback][tforw] = 0; tback = tback + 1; tforw = tforw + 1;}
                  else if (ch == 'x')
                  {vert1[tforw][tback] = 0; tback = tback + 1; tforw = tforw + 1;}
                  else
                  {vert2[tforw][tback] = 0; tback = tback + 1; tforw = tforw + 1;}
             }
             
             //considering the last edge
             
             int s = str.length()-1;
             if (str.charAt(s) == 'a')
             {vert1[tback][0] = 0; }
             else if (str.charAt(s) == 'b')
             {vert2[tback][0] = 0; }
             else if (str.charAt(s) == 'x')
             {vert1[0][tback] = 0;}
             else
             {vert2[0][tback] = 0;}
              
          }
         
            
      } //end i loop
      
      //now all the edges have been represented in vert1 and vert2
      
      //Pringting vert1 and vert2 after representing edges
      System.out.println("These are the original a edges in vert1");
      System.out.println("a Edges in vert1");
    for(int i=0;i<noofvert;i++)
    {
      for(int j=0;j<noofvert;j++)
      { if (vert1[i][j]==0)
        {System.out.print(" " + "("+i+","+j+")" + " ");}  
      }
      
    }
    System.out.println(" ");
    System.out.println("These are original b Edges in vert2");
    for(int i=0;i<noofvert;i++)
    {
      for(int j=0;j<noofvert;j++)
      {
        
          if (vert2[i][j]==0)
        {System.out.print(" " + "("+i+","+j+")"+ " ");}
      }
      
    }
      
      //Folding
      boolean flag1, flag2, flag3, flag4; 
      flag1=false; flag2 = false; flag3=false; flag4 = false; 
      int c1=-1, c2=-1,c3=-1, d1=-1, d2=-1 , d3=-1,e1=-1,e2=-1,e3=-1, f1=-1, f2=-1, f3=-1;
      
     while(true)
     {
      for (int i =0;i<noofvert;i++)
      {
        //i acting as the base of any fold
        c1=-1; c2=-1; d1=-1; d2=-1;c3=-1;d3=-1;
        
        for(int j=0;j<noofvert;j++)
        {
          if(vert1[i][j]!= -1 && c1!=-1)
          {
             //know that this is the second edge in the ith row
             c2=j;
          }
          if(vert1[i][j]!= -1 && c1==-1)
          {
              c1=j;
          }
          if(c1!= -1 && c2!= -1 && vert1[i][c1]!=-1 && vert1[i][c2]!=-1)
          {flag1= true; c3=i; break;}
          
          if(vert2[i][j]!= -1 && d1!= -1)
          {d2=j;}
          if(vert2[i][j]!= -1  && d1==-1)
          {d1=j;}
          if(d1!=-1 && d2!=-1 && vert2[i][d1]!=-1 && vert2[i][d2]!=-1)
          {
            flag2= true; d3=i; break;  
          }
          
        
        }//end j loop
        
        if(flag1==true || flag2 == true)
        {break;//breaks out of i loop
         
        }
        
        
      }//end i loop
      
      if(flag1==false&& flag2==false)
      {
        //check if you can fold the other way
        
        for(int i =0; i<noofvert;i++)
        {
          //i acting as the base of any fold
        e1=-1; e2=-1; f1=-1; f2=-1; e3=-1; f3=-1;
        
        for(int j=0;j<noofvert;j++)
        {
          if(vert1[j][i]!= -1 && e1!=-1)
          {
             //know that this is the second edge in the ith row
             e2=j;
          }
          if(vert1[j][i]!= -1 && e1==-1)
          {
              e1=j;
          }
          if(e1!= -1 && e2!= -1 && vert1[e1][i]!=-1 && vert1[e2][i]!=-1)
          {flag3= true; e3 = i; break;}
          
          if(vert2[j][i]!= -1 && f1!= -1)
          {f2=j;}
          if(vert2[j][i]!= -1  && f1==-1)
          {f1=j;}
          if(f1!=-1 && f2!=-1  && vert2[f1][i]!=-1 && vert2[f2][i]!=-1)
          {
            flag4= true; f3=i; break;  
          }
          
        
        }//end j loop
        
        if(flag3==true || flag4 == true)
        {break;}
        
        
       }//end i loop
      }//end if checking if flag1==false or flag2==false
      
      if(flag1==false &&flag2==false&&flag3==false&&flag4==false)
      {
        //reached an immersion no fold possible
        break;
      }
      
      else
      {
         //atleast one of the flags is true
         if(flag1==true && c1!=-1&& c2!=-1 && c3!=-1)
         {
            
            if  (vert1[c3][c1]==0 && vert1[c3][c2]==0)
            
            { 
            
            //c1 is not zero so we will collapse c1
            
            int copy = c1;
            if(c1==0)
            {  //swapping c1 and c2
                c1=c2; c2= copy;
            
            }
            for(int k=0;k<noofvert;k++)
            {
              if (k==c1)
              {
                  //checking if there is an a loop at c1 or b loop at c1
               if(vert1[c1][c1]==0)
               {vert1[c2][c2]=0; vert1[c1][c1]=-1;}
               if(vert2[c1][c1]==0)
               {vert2[c2][c2]=0; vert2[c1][c1]=-1;}
               continue;
              }
              
              if (vert1[c1][k] ==0)
              {vert1[c2][k]=0; vert1[c1][k]=-1;}
              
              if(vert1[k][c1]==0)
              {vert1[k][c2]=0;vert1[k][c1]=-1;}
              
              if(vert2[c1][k]==0)
              {vert2[c2][k]=0; vert2[c1][k] =-1;}
              
              if (vert2[k][c1]==0)
              {vert2[k][c2] =0; vert2[k][c1]=-1;}
            }
            flag1=false; flag2=false; flag3=false;flag4=false;
            
           }//end if condition vert1[c3][c1] = 0 and vert1[c3][c2]=0 
        
         }//end flag1==true condition
         else if(flag2==true && d1!=-1 && d2!=-1 && d3!=-1)
         {
              int copy = d1;
            if(d1==0)
            {  //swapping d1 and d2
                d1=d2; d2= copy;
            
            }
            
            if(vert2[d3][d1]==0 && vert2[d3][d2]==0) 
            {
                
            for(int k=0;k<noofvert;k++)
            {
                if (k==d1)
              {
                  //checking if there is an a loop at c1 or b loop at c1
               if(vert1[d1][d1]==0)
               {vert1[d2][d2]=0; vert1[d1][d1]=-1;}
               if(vert2[d1][d1]==0)
               {vert2[d2][d2]=0; vert2[d1][d1]=-1;}
               continue;
              }
              if (vert1[d1][k] ==0)
              {vert1[d2][k]=0; vert1[d1][k] =-1;}
              
              if(vert1[k][d1]==0)
              {vert1[k][d2]=0; vert1[k][d1]=-1;}
              
              if(vert2[d1][k]==0)
              {vert2[d2][k]=0; vert2[d1][k]=-1;}
              
              if (vert2[k][d1]==0)
              {vert2[k][d2] =0; vert2[k][d1]=-1;}
            }
            flag1=false; flag2=false; flag3=false;flag4=false;
            
           }//end if condition checking vert2[d3][d1]=0 and vert2[d3][d2]=0
           
         }//end flag2==true condition
         
         else if(flag3==true && e1!=-1 && e2!=-1 && e3!=-1)
         {
              
            int copy = e1;
            if(e1==0)
            {  //swapping e1 and e2
                e1=e2; e2= copy;
            
            }
            if(vert1[e1][e3]==0 && vert1[e2][e3]==0)
            {
                
            for(int k=0;k<noofvert;k++)
            {
                if (k==e1)
              {
                  //checking if there is an a loop at c1 or b loop at c1
               if(vert1[e1][e1]==0)
               {vert1[e2][e2]=0; vert1[e1][e1]=-1;}
               if(vert2[e1][e1]==0)
               {vert2[e2][e2]=0; vert2[e1][e1]=-1;}
               continue;
              }
              if (vert1[e1][k] ==0)
              {vert1[e2][k]=0; vert1[e1][k] =-1;}
              
              if(vert1[k][e1]==0)
              {vert1[k][e2]=0; vert1[k][e1]=-1;}
              
              if(vert2[e1][k]==0)
              {vert2[e2][k]=0; vert2[e1][k]=-1;}
              
              if (vert2[k][e1]==0)
              {vert2[k][e2] =0; vert2[k][e1]=-1;}
            }
            flag1=false; flag2=false; flag3=false;flag4=false;
            
           }//end if condition checking vert1[e1][e3]=0 and vert1[e2][e3]=0
 
         }//end flag3 == true condition
         
         else if (flag4==true && f1!=-1 && f2!=-1 && f3!=-1)
         {
           int copy = f1;
            if(f1==0)
            {  //swapping e1 and e2
                f1=f2; f2= copy;
            
            }
            if(vert2[f1][f3]==0 && vert2[f2][f3]==0)
            {
            for(int k=0;k<noofvert;k++)
            {
                if (k==f1)
              {
                  //checking if there is an a loop at c1 or b loop at c1
               if(vert1[f1][f1]==0)
               {vert1[f2][f2]=0; vert1[f1][f1]=-1;}
               if(vert2[f1][f1]==0)
               {vert2[f2][f2]=0; vert2[f1][f1]=-1;}
               continue;
              }
              if (vert1[f1][k] ==0)
              {vert1[f2][k]=0; vert1[f1][k] =-1;}
              
              if(vert1[k][f1]==0)
              {vert1[k][f2]=0; vert1[k][f1]=-1;}
              
              if(vert2[f1][k]==0)
              {vert2[f2][k]=0; vert2[f1][k]=-1;}
              
              if (vert2[k][f1]==0)
              {vert2[k][f2] =0; vert2[k][f1]=-1;}
            }
            flag1=false; flag2=false; flag3=false;flag4=false;   
           }//end if condition for vert2[f1][f3]=0 and vert2[f2][f3]=0
         }
         
         //Printing after folding
         System.out.println(" ");
         System.out.println("");
         System.out.println("After folding, a Edges in vert1");
    for(int i=0;i<noofvert;i++)
    {
      for(int j=0;j<noofvert;j++)
      { if (vert1[i][j]==0)
        {System.out.print(" " + "("+i+","+j+")" + " ");}  
      }
      
    }
    
    System.out.println("");
    
    System.out.println("After folding, b Edges in vert2");
    for(int i=0;i<noofvert;i++)
    {
      for(int j=0;j<noofvert;j++)
      {
        
          if (vert2[i][j]==0)
        {System.out.print(" " + "("+i+","+j+")" + " ");}
      }
      
    }
    System.out.println("");     
         
          
          
      }//end else 
      
    }//end while
    
    
    //Printing:
    System.out.println("Immersion:");
    System.out.println("a Edges in vert1");
    int fcounta=0, fcountb=0;
    for(int i=0;i<noofvert;i++)
    {
      for(int j=0;j<noofvert;j++)
      { if (vert1[i][j]==0)
        {System.out.println("("+i+","+j+")"); fcounta=fcounta + 1;}  
      }
      
    }
    System.out.println("No of a edges in immersion: "+fcounta);
    
    System.out.println("b Edges in vert2");
    for(int i=0;i<noofvert;i++)
    {
      for(int j=0;j<noofvert;j++)
      {
        
          if (vert2[i][j]==0)
        {System.out.println("("+i+","+j+")"); fcountb = fcountb + 1;}
      }
      
    }
     System.out.println("No of b edges in immersion: "+fcountb);
    
    }
}


