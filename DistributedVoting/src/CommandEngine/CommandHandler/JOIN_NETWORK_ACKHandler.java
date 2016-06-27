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
import Voting.Systems.SystemInfo;

/**
 *
 * @author Aron
 */
public class JOIN_NETWORK_ACKHandler {
    
    public void Execute(CommandParams CmdInfo) {
        Utilities.ShowStatus("Join ACK received from " + CmdInfo.SrcNode.SystemID);
        if (Neighbors.NeighborList.size() > 0) {
            Utilities.ShowStatus("System repair under progress...");
            Utilities.ShowStatus("Leaving the current group...");
            Neighbors.DisconnectFromAll();
            Utilities.ShowStatus("Joining with the group of node " + CmdInfo.SrcNode.SystemID);
        }
        
        
        Neighbors.NeighborList.add(CmdInfo.SrcNode);
        for (SystemInfo Neighbor : CmdInfo.GroupMembers) {
            Neighbors.AddNeighbor(Neighbor);
        }
        
        InstanceInfo.SharedInfo.VN=CmdInfo.SharedInfo.VN;
        InstanceInfo.SharedInfo.RU=CmdInfo.SharedInfo.RU;
        InstanceInfo.SharedInfo.NODE_LIST=CmdInfo.SharedInfo.NODE_LIST;
       InstanceInfo.SharedInfo.UpdateDS();
       InstanceInfo.SharedInfo.DisplaySharedData();
        
    }
    
}
