//Author Name: Sivaramchandar Suresh
//File Name: GraphEdge.java
//Date: April 7, 2025
public class GraphEdge {
    private GraphNode initialNode;
    private GraphNode endNode;
    private char type;
    private String label;
    public GraphEdge(GraphNode u, GraphNode v, char type)
    {
        initialNode= u;
        endNode= v;
        this.type= type;
        label= null;
    }
    /*
    This method in
    */
    public GraphEdge(GraphNode u, GraphNode v, char type, String label)
    {
        initialNode= u;
        endNode= v;
        this.type= type;
        this.label= label;
    }
    /*
    This method returns the instance variable type.
    */
    public char getType()
    {
    return type;
    }
    /*
    This method returns the instance variable initial node.
    */
    public GraphNode firstEndpoint()
    {
        return initialNode;
    }
    /*
    This method returns the instance variable called endNode.
    */
    public GraphNode secondEndpoint()
    {
        return endNode;
    }
    /*
    This method returns the instance variable called label.
    */
    public String getLabel()
    {
        return label;
    }
    /*
    This method changes the value of the instance variable label.
    */
    public void setLabel(String newLabel)
    {
        label= newLabel;
    }
}
