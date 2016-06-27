/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine.CommandHandler;

import CommandEngine.CommandParams;
import CommandEngine.Commands;
import Common.InstanceInfo;
import Common.Utilities;
import IOSubsystem.CommandTransceiver;

/**
 *
 * @author Aron
 */
public class CAN_I_UPDATEHandler {
    public void Execute(CommandParams CmdInfo)
    {
        try
        {
        //On receiving a can I update, the system has to send a GO_AHEAD_UPDATE response back to the sender
            CommandParams ApproveUpdate=new CommandParams();
             ApproveUpdate.Command=Commands.GO_AHEAD_UPDATE;
              ApproveUpdate.SrcNode=InstanceInfo.LocalSystem;
              new CommandTransceiver().SendCommand(ApproveUpdate,CmdInfo.SrcNode.SystemNetworkName);
        }
        catch(Exception Ex)
        {
            Utilities.ShowError("Exception occured while sending Go Ahead Update command back to "+CmdInfo.SrcNode.SystemID);
        }
                
    }
    
}
