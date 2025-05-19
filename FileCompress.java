//Author Name: Siva Suresh
//File Name: FileCompress.java
//Date: Saturday, February 22, 2025
/*
This file creates an class called FileCompress to compress files.
*/
public class FileCompress {
    private HashDictionary h1;
    //This method creates an hash table of 9
    public FileCompress()
    {
        h1= new HashDictionary(9001);
    }
    /*
    This method 
    */
    public CompressedFile compressor(String fileName) {
        CompressedFile outputFile = new CompressedFile(fileName);
        MyReader inputFile = new MyReader(fileName);
       //inputFile.toString();
        for (int i = 0; i < 256; i++) {
            //String key= Character.toString((char)inputFile.read());
            Record pair = new Record(Character.toString((char) i), i);
            try {
                h1.put(pair);
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        int nc = 256;
        StringBuilder s = new StringBuilder("");
        //      newString.append((char)code);
        String sPrime = "";
        int code= inputFile.read();
        if(code!= -1)
        {
            s.append((char)code);
        }
        while (true)
        {
            while (code != -1 && h1.get(s.toString()) != null) {
                code = inputFile.read();
                s.append((char)code);
            }
            if (h1.get(s.toString()) == null) {
                sPrime= s.substring(0, s.length() - 1);
            } else {
                sPrime= s.toString();
                inputFile.close();
                outputFile.flush();
                outputFile.close();
                return outputFile;
            }
            Record d= h1.get(sPrime);
            if (d != null) {
                int k = h1.get(sPrime).getData();
                outputFile.output(k);
            }
            if (code==-1)
            {
                inputFile.close();
                outputFile.flush();
                outputFile.close();
                return outputFile;
            }
            if (h1.numRecords() < 4096) {
                Record dPrime = new Record(s.toString(), nc);
                try {
                    h1.put(dPrime);
                } catch (Exception e) {
                    System.out.println("Cannot insert record with key " + dPrime.getKey() + " since key " + dPrime.getKey() + " already exists.");
                }
            }
            s = s.delete(0, s.length() - 1);
            nc++;
        }
        
        //xFileCompress inputFile= new FileCompress()
        
    }
}
