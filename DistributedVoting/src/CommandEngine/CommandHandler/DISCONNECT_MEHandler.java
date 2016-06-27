/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine.CommandHandler;

import CommandEngine.CommandParams;
import CommandEngine.Commands;
import Common.Utilities;
import IOSubsystem.CommandTransceiver;
import Voting.Neighbors;
import Voting.Systems.SystemInfo;

/**
 *
 * @author Aron
 */
public class DISCONNECT_MEHandler {

    public void Execute(CommandParams CmdInfo) {
        Neighbors.RemoveNeghbor(CmdInfo.SrcNode);
        CommandParams CascadeDiscCmd = new CommandParams();
        CascadeDiscCmd.Command = Commands.CASCADE_DISCONNECT;
        CascadeDiscCmd.SrcNode = CmdInfo.SrcNode;

        try {
            for (SystemInfo Neighbor : Neighbors.NeighborList) {
                new CommandTransceiver().SendCommand(CascadeDiscCmd, Neighbor.SystemNetworkName);
            }
        } catch (Exception Ex) {
            Utilities.ShowError("Exception occured while cascading disconnect");
        }
    }

}
