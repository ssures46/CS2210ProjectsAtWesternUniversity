//Author Name: Siva Suresh
//File Name: Record.java
//Date: Tuesday, February 18, 2025
/*
This class creates a class called record with attributs key and data.
*/
public class Record {
    //This line creates an private instance variable called key
    private String key;
    //This line creates an private instance variable called data
    private int data;
    public Record(String theKey, int theData)
    {
        //This line initializes the private instance variable key
        key= theKey;
        //This line initializes the private instance variable data.
        data= theData;
    }
    //This method returns the key atribute of the Record class.
    public String getKey()
    {
        return key;
    }
    //This method get the data attribute of the Record class.
    public int getData()
    {
        return data;
    }
    /*This method checks and returns true 
    if two record objects have the same key and data or otherwise returns false.
    */
    public boolean same(Record secondObject)
    {
        return key.equals(secondObject.getKey()) && data==secondObject.getData();
    }
}
