/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOSubsystem;

import CommandEngine.CommandParams;
import CommandEngine.CommandProcessor;
import Common.Configuration;
import Common.InstanceInfo;
import Common.Utilities;
import Serializer.BinarySerializer;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author Aron
 */
public class CommandTransceiver {
     BinarySerializer<CommandParams> CommandSerializer;
     
     public CommandTransceiver()
     {
         CommandSerializer=new BinarySerializer();
     }

    public void ReceiveCommands() throws Exception {
       
        CommandProcessor CmdEngine = new CommandProcessor();
        ServerSocket ServerReader = new ServerSocket(Configuration.COMMAND_PORT);

        try
        {
        while(InstanceInfo.ReceiveCommands)
        {
            Socket CommandReader = ServerReader.accept();
            InputStream CmdStream = CommandReader.getInputStream();
            byte[] EstimatedRawCommand = new byte[7000];
            int DataReadSize;
            DataReadSize = CmdStream.read(EstimatedRawCommand);
            byte[] RawCommand = Arrays.copyOfRange(EstimatedRawCommand, 0, DataReadSize);
            CommandParams CommandInfo = CommandSerializer.Deserialize(RawCommand);
            CmdEngine.Execute(CommandInfo);
        }
        }
        catch(Exception Ex)
        {
            Utilities.ShowError("Exception in receiving/processing command. Details - "+Ex.getMessage());
        }
        finally
        {
            ServerReader.close();
            Utilities.ShowStatus("Command Listener has been closed");
        }
    }
    
    public void SendCommand(CommandParams CmdInfo, String TargetSystem)throws Exception
    {
        Socket CommandWriter=new Socket(TargetSystem,Configuration.COMMAND_PORT);
        OutputStream WriteStream=CommandWriter.getOutputStream(); 
        byte[] CmdRAW=CommandSerializer.Serialize(CmdInfo);
        WriteStream.write(CmdRAW);
        WriteStream.close();
        CommandWriter.close();
    }
}
