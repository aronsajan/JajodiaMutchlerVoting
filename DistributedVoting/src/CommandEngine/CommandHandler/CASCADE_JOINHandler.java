/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine.CommandHandler;

import CommandEngine.CommandParams;
import Common.InstanceInfo;
import Common.Utilities;
import Voting.Neighbors;

/**
 *
 * @author Aron
 */
public class CASCADE_JOINHandler {
    
    public void Execute(CommandParams CmdInfo)
    {
      //UPDATE NEIGHBOR LIST
      Utilities.ShowStatus("Cascade join command received. Node requested for Join: "+CmdInfo.SrcNode.SystemID);
      Neighbors.AddNeighbor(CmdInfo.SrcNode);
    
      //UPDATES SHARED DATA
       InstanceInfo.SharedInfo.AddNode(CmdInfo.SrcNode);
       InstanceInfo.SharedInfo.DisplaySharedData();
       
    }
    
}
