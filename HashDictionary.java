//Author Name: Siva Suresh
//File Name: HashDictionary.java
//Date: Tuesday February 18, 2024
/*The purpose of this program is to demonstrate my knowledge of creating a 
hash table.
*/
public class HashDictionary implements DictionaryADT{
    //This line declares an array storing linked list of records.
    private records<Record> [] linkedListOfRecords;
    /*
    This method creates an hash table with size size.
    */
    public HashDictionary(int size)
    {
       //This line initializes an array storing linked list of records.
       linkedListOfRecords= new records[size];
    }
    /*
    This method returns the position of where to insert the record with key k
    in the hash table.
    */
    private int positionOfString(String k)
    {
        long pos= 0;
        int power= k.length()-1;
        for(int i= 0; i<k.length(); i++)
        {
            pos+= (long)(k.charAt(i))*(long)Math.pow(31, power)%linkedListOfRecords.length;
            power--;
        }
        long num= pos%linkedListOfRecords.length;
        return (int)num;
    }
    /*
    This method inserts a new record into the hash table or throws a 
    DictionaryException if a record with key already exists. 
    */
    public int put(Record pair) throws DictionaryException
    {
        int result= 0;
        records<Record> newRecord= new records<Record>(pair);
        int pos= positionOfString(pair.getKey());
        /*for (int i= 0; i<pair.getKey().length(); i++)
        {
            pos= (pos*31 + (int)pair.getKey().charAt(i))%linkedListOfRecords.length;
        }*/
        if (linkedListOfRecords[pos]== null)
        {
            linkedListOfRecords[pos]= newRecord;
            return result;
        }
        records<Record> currentRecord= linkedListOfRecords[pos];
        result= 1;
        if (get(pair.getKey())!= null)
        {
            throw new DictionaryException("A record with key " + pair.getKey() + " already exists");
        }
        while(currentRecord!= null)
        { 
            if (currentRecord.getNext()== null)
            {
                currentRecord.setNext(newRecord);
                return result;
            }
            else
            {
                currentRecord= currentRecord.getNext();
            }
        }
        return result;
    }
    /*
    This method removes a record with a particular key in the hash table or 
    otherwise throws a DictionaryException.
    */
    public void remove(String key) throws DictionaryException
    {
        int pos= 1;
        if (get(key)!= null)
        {
            /*for (int i= 0; i<key.length(); i++)
            
                pos= (pos*31 + (int)key.charAt(i))%linkedListOfRecords.length;
            }*/
            pos= positionOfString(key);
            while(linkedListOfRecords[pos]!= null)
            {
                if (linkedListOfRecords[pos].getNext()!= null && linkedListOfRecords[pos].getNext().getData().getKey().equals(key))
                {
                    records<Record>next= linkedListOfRecords[pos].getNext();
                    linkedListOfRecords[pos].setNext(linkedListOfRecords[pos].getNext().getNext());
                    next.setNext(null);
                    break;
                }
                else if (linkedListOfRecords[pos].getData().getKey().equals(key))
                {
                    records<Record> newRecord= linkedListOfRecords[pos].getNext();
                    linkedListOfRecords[pos].setNext(null);
                    linkedListOfRecords[pos]= newRecord;
                    break;
                }
            }
        }
        else
        {
            throw new DictionaryException("No Record object with key " + key + " is in the hash table");
        }
    }
    //This method returns the record from the hash table with a particular key.
    public Record get(String key)
    {
        int integerValueOfKey= 1;
        /*for (int i= 0; i<linkedListOfRecords.length; i++)
        {
            integerValueOfKey= (integerValueOfKey*31 + (int)linkedListOfRecords[i].getData().getKey().charAt(i))%linkedListOfRecords.length;
        }*/
        integerValueOfKey= positionOfString(key);
        records<Record> current= linkedListOfRecords[integerValueOfKey];
        while (current!= null)
        {
            if ((current.getData().getKey()).equals(key))
            {
                break;
            }
            else
            {
                current= current.getNext();
            }
        }
        if(current!= null)
        {
            return current.getData();
        }
        else
        {
            return null;
        }
    }
    /*
    This method returns the number of records present in the hash table.
    */
    public int numRecords()
    {
        int numberOfRecords= 0;
        
        for (int i= 0; i<linkedListOfRecords.length; i++)
        {
            records<Record> current= linkedListOfRecords[i];
            while(current!= null)
            {
                numberOfRecords++;
                current= current.getNext();
            }
        }
        return numberOfRecords;
    }
}
