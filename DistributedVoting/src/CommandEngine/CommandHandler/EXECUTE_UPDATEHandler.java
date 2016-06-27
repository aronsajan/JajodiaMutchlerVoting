/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine.CommandHandler;

import CommandEngine.CommandParams;
import Common.InstanceInfo;
import Common.Utilities;

/**
 *
 * @author Aron
 */
public class EXECUTE_UPDATEHandler {
    
    public void Execute(CommandParams CmdInfo)
    {
        InstanceInfo.SharedInfo=CmdInfo.SharedInfo;
        Utilities.ShowStatus("Shared Data Updated");
        InstanceInfo.SharedInfo.DisplaySharedData();
    }
    
}
