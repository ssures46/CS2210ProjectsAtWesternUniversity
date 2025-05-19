//Author Name: Sivaramchandar Suresh
//File Name: GraphNodes.java
//Date: April 7, 2025
/*

*/
public class GraphNodes<T> {
    private GraphNodes<T> next;
    private T data;
    public GraphNodes()
    {
        next= null;
        data= null;
    }
    public GraphNodes(T data)
    {
        this.data= data;
    }
    public GraphNodes<T> getNext()
    {
        return next;
    }
    public void setNext(GraphNodes<T> newNext)
    {
        next= newNext;
    }
    public T getData()
    {
        return data;
    }
    public void setData(T newData)
    {
        data= newData;
    }
}
