
package IOSubsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author Aron
 */
public class FileManager {

    public static byte[] ReadFile(String Filename) throws Exception {
        File FInfo = new File(Filename);
        int FileLen = (int) FInfo.length();
        FileInputStream Reader = new FileInputStream(Filename);
        byte[] ReadBuffer = new byte[FileLen];
        Reader.read(ReadBuffer, 0, FileLen);
        return ReadBuffer;
    }

    public static void WriteToFile(byte[] Data, String Filename) throws Exception {
        FileOutputStream Writer = new FileOutputStream(Filename);
        Writer.write(Data);
        Writer.close();
    }

}
