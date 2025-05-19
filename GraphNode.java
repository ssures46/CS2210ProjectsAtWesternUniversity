//Author Name: Sivaramchandar Suresh
//File Name: GraphNode.java
//Date: April 7, 2025
/*
The purpose
*/
public class GraphNode {
    /*
    This line creates an private instance variable called node.
    */
    private GraphNode node;
    /*
    This line creates an private instance variable called name.
    */
    private int name;
    /*
    This line creates an private instance variable called mark.
    */
    private boolean mark;
    public GraphNode(int name)
    {
        this.name=name;
        this.mark= false;
    }
    /*
    This method changes the value of the instance variable mark.
    */
    public void setMark(boolean mark)
    {
        mark= true;
    }
    /*
    This method returns the instance variable mark.
    */
    public boolean getMark()
    {
        return mark;
    }
    /*
    This method returns the instance variable name.
    */
    public int getName()
    {
        return name;
    }
}
