//Author Name: Sivaramchandar Suresh
//File Name: Maze.java
//Date: April 7, 2025
/*
This file creates a maze.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
public class Maze {
    private Stack<GraphNode> nodes;
    UndirectedGraph obj;
    private GraphNode entrance;
    private GraphNode exit;
    private int [] numberOfTokens;
    private boolean doFirstThreeLinesContainOnlyNumbers(String inputFile)
    {
        File myObj= new File(inputFile);
        boolean containsNumbersOnly= false;
        try
        {
            Scanner myReader= new Scanner(myObj);
            String currentLine= myReader.nextLine();
            for (int i= 0; i<3; i++)
            {
                if (Character.isDigit(currentLine.charAt(i)))
                {
                    containsNumbersOnly= true;
                }
                else
                {
                    containsNumbersOnly= false;
                    break;
                }
                currentLine= myReader.nextLine();
            }
            if (containsNumbersOnly==true)
            {
                throw new MazeException("The input of file is incorrect");
            }
        }
        catch(Exception e)
        {
            System.out.println("t");
        }
        return containsNumbersOnly;
    }
    private int numberOfRows(String inputFile)
    {
        int numberOfRows= 0;
        try 
        {
            File myObj= new File(inputFile);
            Scanner myReader= new Scanner(myObj);
            String line= "";
            for(int i= 0; i<6; i++)
            {
                line= myReader.nextLine();
            }
            while(line.equals("")==false)
            {
                numberOfRows++;
                line= myReader.nextLine();
            }
            myReader.close();
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return numberOfRows;
    }
    private boolean linesExceptFirstFourLinesHaveSameLength(String inputFile)
    {
        boolean doLinesHaveSameLength= false;
        
        try
        {
            String currentLine= "";
            File myObj= new File(inputFile);
            Scanner myReader= new Scanner(myObj);
            for (int i= 0; i<5; i++)
            {
                currentLine= myReader.nextLine();
            }
            while(currentLine.equals("")==false)
            {
                String nextLine= myReader.nextLine();
                if (currentLine.length()==nextLine.length())
                {
                    doLinesHaveSameLength= true;
                }
                else
                {
                    doLinesHaveSameLength= false;
                    break;
                }
                currentLine= nextLine;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return doLinesHaveSameLength;
    }
    private int numberOfColumns(String inputFile)
    {
        int numberOfColumns= 0;
        try
        {
            boolean doLinesHaveSameLength= linesExceptFirstFourLinesHaveSameLength(inputFile);
            File myObj= new File(inputFile);
            Scanner myReader= new Scanner(myObj);
            String line= "";
            for (int i= 0; i<6; i++)
            {
                line= myReader.nextLine();
            }
            while(doLinesHaveSameLength==true && line.equals("")==false)
            {
                numberOfColumns++;
                line= myReader.nextLine();
            }
            myReader.close();
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return numberOfColumns;
                    
    }
    public Maze(String inputFile) throws MazeException
    {
        int numberOfColumns= numberOfColumns(inputFile);
        int numberOfRows= numberOfRows(inputFile);
        GraphNode exit= null;
        
        //ArrayList<Integer> numberOfTokens= new ArrayList<Integer>();
        try
        {
            File myObj= new File(inputFile);
            Scanner myReader= new Scanner(myObj);
            String firstLine= myReader.nextLine();
            String secondLine= myReader.nextLine();
            String thirdLine= myReader.nextLine();
            String fourthLine= myReader.nextLine();
            int k= 0;
            int numberOfUpperCaseLetters= 0;
            while(k<fourthLine.length())
            {
                if (((int)fourthLine.charAt(k))>=65 && ((int)fourthLine.charAt(k++))<=90 && ((int)fourthLine.charAt(k++))==32)
                {
                    numberOfUpperCaseLetters++;
                }
                else
                {
                    throw new MazeException("The input of the file is incorrect");
                }
            }
            
            numberOfTokens= new int[90];
            for (int i= 0; i<numberOfTokens.length; i+=4)
            {
                if (i<fourthLine.length())
                {
                    numberOfTokens[(int)fourthLine.charAt(i)]= fourthLine.charAt(i+2);   
                }
                else
                {
                    break;
                }
            }
            
            String fifthLine= myReader.nextLine();
            obj= new UndirectedGraph((int)secondLine.charAt(0)*(int)thirdLine.charAt(0));
            exit= obj.getNode((int)secondLine.charAt(0)*(int)thirdLine.charAt(0)-1);
       //     exit= new GraphNode((int)secondLine.charAt(0)*(int)thirdLine.charAt(0));
           // obj= new UndirectedGraph((int)secondLine.charAt(0)*(int)thirdLine.charAt(0));
            if (fourthLine.equals(""))
            {
                myObj= null;
            }
            else
            {
                
                fifthLine= myReader.nextLine();
                if(linesExceptFirstFourLinesHaveSameLength(inputFile)==true && doFirstThreeLinesContainOnlyNumbers(inputFile)==true)
                {
                    String currentLine= fifthLine;
                    char[][]characters= new char[numberOfRows][numberOfColumns];
                    
                    for(int i= 0; i<characters.length; i++)
                    {
                        for (int j= 0; j<characters[0].length; j++)
                        {
                            characters[i][j]= currentLine.charAt(i);
                        }
                        currentLine= myReader.nextLine();
                    }
                    int name= 0;
                    obj= new UndirectedGraph((int)secondLine.charAt(0)*(int)thirdLine.charAt(0));
                    entrance= obj.getNode(0);
                    exit= obj.getNode((int)secondLine.charAt(0)*(int)thirdLine.charAt(0)-1);
                    int row= 0;
                    int column= 0;
                    //int row= 0;
                    while(row!=numberOfRows)
                    {
                        while(column!=characters[0].length-1)
                        {
                            try
                            {
                                if (characters[row][column+1]!=' ' && (int)characters[row][column+1]>=65 && (int)characters[row][column+1]<=90)
                                {
                                    obj.insertEdge(obj.getNode(name), obj.getNode(name+1), characters[row][column+1]);
                                    if(name==numberOfRows*numberOfColumns-2)
                                    {
                                        exit= obj.getNode(name);
                                    }
                                    name++;
                                    column+=2;
                                }
                                else if (characters[row][column+1]!=' ' && characters[row][column+1]=='c')
                                {
                                    obj.insertEdge(obj.getNode(name), obj.getNode(name+1), characters[row][column+1],"corridors");
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println("Error");
                            }
                        }
                        row+=2;
                        column=0;
                    }
                    row= 0;
                    column= 0;
                    name= 0;
                    while(row!=numberOfRows)
                    {
                        while(column!=numberOfColumns)
                        {
                            if (characters[row+1][column]!=' ')
                            {
                                try
                                {
                                    obj.insertEdge(obj.getNode(name),obj.getNode(name+numberOfColumns), characters[row+1][column]);
                                }
                                catch(Exception e)
                                {
                                    throw new MazeException("Cannot insert edge");
                                }
                            }
                            column+=2;
                            name++;
                        }
                        row+= 2;
                        column= 0;
                    }
                    
                }
                else
                {
                    throw new MazeException("The format of the input file is incorrect.");
                }
            }
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch(MazeException e)
        {
            throw new MazeException("A file named " + inputFile + " does not exist");
        }
        catch(Exception e)
        {
            System.out.println("E");
        }
        
    }
    
    public UndirectedGraph getGraph() throws MazeException{
        if(obj==null)
        {
            throw new MazeException("The graph is empty");
        }
        return obj;
    }
    /*private Iterator<GraphNode> DFS(GraphNode u) throws MazeException
    {
        Stack<GraphNode> nodes= new Stack<GraphNode>();
        //u.setMark(true);
        
        boolean isLabelled= false;
        nodes.push(u);
        u.setMark(true);
        try
        {
            if (obj.getNode(u.getName()+2)==null)
            {
                return nodes.iterator();
            }
            else
            {
                GraphEdge edge= (GraphEdge)obj.incidentEdges(u).next(); 
                while(edge!=null)
                {
                    if (isLabelled==false)
                    {
                        if (edge.firstEndpoint()==u && edge.secondEndpoint().getMark()==false)
                        {
                            Iterator<GraphNode> new= DFS(edge.secondEndpoint());
                        }
                    }
                    
                    edge= (GraphEdge)obj.incidentEdges(u).next();
                }
            }
        }
        catch(Exception e)
        {
            
        }
    }*/
    private int pos(String inputFile, int position)
    {
        File myObj= new File(inputFile);
        int []numberOfTokens= new int[90];
        try
        {
            Scanner myReader= new Scanner(myObj);
            String currentLine= "";
            for (int i= 0; i<3; i++)
            {
                currentLine= myReader.nextLine();
            }
            for(int i= 0; i<numberOfTokens.length; i+=4)
            {
                if (i<currentLine.length())
                {
                    numberOfTokens[(int)currentLine.charAt(i)]= currentLine.charAt(i+2);
                }
                else
                {
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("");
        }
        return numberOfTokens[position];
    }
    private Iterator<GraphNode> DFS(GraphNode u)
    {
        Iterator<GraphNode> result= null;
        Stack<GraphNode> nodes= new Stack<GraphNode>();
        nodes.push(u);
        u.setMark(true);
        try
        {
            if (u.getName()==exit.getName())
            {
                result= nodes.iterator();
                return result;
            }
            GraphEdge edgesIncidentOnU= ((GraphEdge)obj.incidentEdges(u).next());
            boolean areAllEdgesMarked= false;
            while (edgesIncidentOnU!=null)
            {
                if (edgesIncidentOnU.firstEndpoint().getName()==u.getName())
                {
                    if (edgesIncidentOnU.secondEndpoint().getMark()==false)
                    {
                        areAllEdgesMarked= false;
                        if (edgesIncidentOnU.getLabel()==null)
                        {
                            if (numberOfTokens[(int)edgesIncidentOnU.getType()]!=0)
                            {
                                numberOfTokens[(int)edgesIncidentOnU.getType()]= numberOfTokens[(int)edgesIncidentOnU.getType()]-1;
                                result= DFS(edgesIncidentOnU.secondEndpoint());
                            }
                            else
                            {
                                result= null;
                            }
                            //DFS(edgesIncidentOnU.secondEndpoint());
                        }
                        else
                        {
                            result= DFS(edgesIncidentOnU.secondEndpoint());
                            if (result!=null)
                            {
                                break;
                            }
                        }
                    }
                    else
                    {
                        return null;
                    }
                }
                edgesIncidentOnU= ((GraphEdge)obj.incidentEdges(u).next());
            }
            if (result==null)
            {
                return null;
            }
            return nodes.iterator();

        }
        catch(Exception e)
        {
            System.out.println("There are no incident edges on u");
        }
        return result;
    }
    public Iterator<GraphNode> solve()
    {
        try
        {
            Iterator<GraphNode> result= DFS(entrance);
            return result;
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return nodes.iterator();
    }
}
