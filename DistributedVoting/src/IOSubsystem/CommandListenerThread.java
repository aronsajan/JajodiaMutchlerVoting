/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOSubsystem;

import Common.Configuration;
import Common.Utilities;

/**
 *
 * @author Aron
 */
public class CommandListenerThread extends Thread {
    
    public void run()
    {
        try
        {
            Thread.currentThread().setName("Command Listener Thread");
        CommandTransceiver CmdTransceiver=new CommandTransceiver();
         Utilities.ShowStatus("Command Listener Activated on port "+Configuration.COMMAND_PORT);
        CmdTransceiver.ReceiveCommands();
       
        }
        catch(Exception Ex)
        {
            Utilities.ShowError(Ex.getMessage());
            
        }
    }
}
