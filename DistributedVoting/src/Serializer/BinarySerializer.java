/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Aron
 */
public class BinarySerializer<T> {
    public byte[] Serialize(Object Data)
    {
         byte[] BinaryStream=null;
        try
        {
            ByteArrayOutputStream MemoryStream=new ByteArrayOutputStream();
            ObjectOutputStream ObjectOutStream=new ObjectOutputStream(MemoryStream);
            ObjectOutStream.writeObject(Data);
            ObjectOutStream.close();
           BinaryStream = MemoryStream.toByteArray();
        }
        catch(Exception Ex)
        {
           
        }
        return BinaryStream;
    }
    
    public T Deserialize(byte[] Data)throws IOException,ClassNotFoundException
    {
        ByteArrayInputStream ByteStream=new ByteArrayInputStream(Data);
        ObjectInputStream ObjectInStream=new ObjectInputStream(ByteStream);
              Object DeserializedObject=  ObjectInStream.readObject();
              return (T) DeserializedObject;
    }    
}
