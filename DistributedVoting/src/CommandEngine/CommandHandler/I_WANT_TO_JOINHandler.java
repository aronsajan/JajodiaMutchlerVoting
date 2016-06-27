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
import Voting.Neighbors;
import Voting.Systems.SystemInfo;

/**
 *
 * @author Aron
 */
public class I_WANT_TO_JOINHandler {

    public void Execute(CommandParams CmdInfo) {
        Utilities.ShowStatus("Request to join the network received from node " + CmdInfo.SrcNode.SystemID + " (" + CmdInfo.SrcNode.SystemNetworkName + ")");

                //Cascading this information to all neighbors
        CommandParams Cmd = new CommandParams();
        Cmd.Command = Commands.CASCADE_JOIN;
        Cmd.SrcNode = CmdInfo.SrcNode;
        for (SystemInfo Node : Neighbors.NeighborList) {
            try {
                new CommandTransceiver().SendCommand(Cmd, Node.SystemNetworkName);
            } catch (Exception Ex) {
                Utilities.ShowError("CASCADE_JOIN command failed to system " + Node.SystemID);
            }
        }

        //UPDATES SHARED DATA
       
        InstanceInfo.SharedInfo.AddNode(CmdInfo.SrcNode);

                //Sending neigbor list and shared data to new node
        try {
            CommandParams JoinACKCmd = new CommandParams();
            JoinACKCmd.Command = Commands.JOIN_NETWORK_ACK;
            JoinACKCmd.SrcNode = InstanceInfo.LocalSystem;
            JoinACKCmd.GroupMembers = Neighbors.NeighborList;
            JoinACKCmd.SharedInfo = InstanceInfo.SharedInfo;

            new CommandTransceiver().SendCommand(JoinACKCmd, CmdInfo.SrcNode.SystemNetworkName);
            InstanceInfo.SharedInfo.DisplaySharedData();
            Neighbors.NeighborList.add(CmdInfo.SrcNode);
        } catch (Exception Ex) {
            Utilities.ShowError("Join ACK command to new node " + CmdInfo.SrcNode.SystemID + " failed");
        }

    }

}
