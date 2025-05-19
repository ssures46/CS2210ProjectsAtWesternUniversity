//Author Name: Siva Suresh
//File Name: records.java
//Date: Tuesday, February 18, 2025
/*
The purpose of this program is to create a linkedlist called records.
*/
public class records<T> {
    /*This line creates an private instance variable called next storing the
    next node that the current node points to
    */
    private records<T> next;
    /*
    This line creates a private instance variable called data, which stores the
    data of the current node in the records linked list.
    */
    private T data;
    /*
    This constructor method sets the data of the current node of the records 
    class to value. 
    */
    public records(T value)
    {
        next= null;
        data= value;
        //next= new records<T>(node);
    }
    /*
    This method initializes next and data to null.
    */
    public records()
    {
        next= null;
        data= null;
    }
    /*
    This method returns the node that the current node points to.
    */
    public records<T> getNext()
    {
        return next;
    }
    /*
    This method returns the data of the current node of the records linked list.
    */
    public T getData()
    {
        return data;
    }
    /*
    This method changes the node that the current node points to
    */
    public void setNext(records<T> node)
    {
        next= node;
    }
    /*
    This method modifies the data inside the current node.
    */
    public void setData(T value)
    {
        data= value;
    }
    
    
    
}
