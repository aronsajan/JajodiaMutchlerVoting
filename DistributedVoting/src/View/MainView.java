/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import CommandEngine.CommandParams;
import CommandEngine.Commands;
import Common.InstanceInfo;
import Common.Utilities;
import IOSubsystem.CommandTransceiver;
import Voting.Neighbors;
import Voting.Systems.SystemInfo;
import Voting.Systems.SystemList;
import Voting.UpdateManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Aron
 */
public class MainView {

    public void ShowView() throws Exception {
        InputStreamReader IStream = new InputStreamReader(System.in);
        BufferedReader Reader = new BufferedReader(IStream);
        boolean ExitFlag = false;
        while (!ExitFlag) {
            System.out.println("1. View all participant systems");
            System.out.println("2. Establish/Repair connection to another group");
            System.out.println("3. View my neighborhood");
            System.out.println("4. Disconnect from another group");
            System.out.println("5. Make an update to the shared data");
            System.out.println("6. View voting parameters");
            System.out.println("7. Exit");
            System.out.print("Option : ");
            String Opt = Reader.readLine();
            try {
                int OptInt = Integer.parseInt(Opt);
                switch (OptInt) {
                    case 1:
                        Utilities.ShowSystems(InstanceInfo.AllParticipantSystems.GetParticipantSystems());
                        break;
                    case 2:
                        System.out.print("Target System ID in the group : ");
                        String TGetSystemID = Reader.readLine();

                        SystemInfo TargetSystem = InstanceInfo.AllParticipantSystems.GetSystem(TGetSystemID);
                        CommandParams JoinRepairCmd = new CommandParams();
                        JoinRepairCmd.Command = Commands.I_WANT_TO_JOIN;
                        JoinRepairCmd.SrcNode = InstanceInfo.LocalSystem;
                        new CommandTransceiver().SendCommand(JoinRepairCmd, TargetSystem.SystemNetworkName);
                        Utilities.ShowStatus("Join request sent to node " + TGetSystemID + " (" + TargetSystem.SystemNetworkName + "). Waiting for Join ACK");
                        break;

                    case 3:
                        Utilities.ShowSystems(Neighbors.NeighborList);
                        break;

                    case 4:
                        System.out.print("Target System ID in the group : ");
                        String DiscNodeID = Reader.readLine();
                        SystemInfo DiscNode = InstanceInfo.AllParticipantSystems.GetSystem(DiscNodeID);

                        CommandParams DisconnectCmd = new CommandParams();
                        DisconnectCmd.Command = Commands.DISCONNECT_ME;
                        DisconnectCmd.SrcNode = InstanceInfo.LocalSystem;
                        new CommandTransceiver().SendCommand(DisconnectCmd, DiscNode.SystemNetworkName);
                        Neighbors.DisconnectFromAll();

                        break;
                    case 5:
                        new UpdateManager().StartUpdateProcess();
                        break;
                    case 6:
                        InstanceInfo.SharedInfo.DisplaySharedData();
                        break;
                    case 7:

                        if (InstanceInfo.CmdListener.isAlive()) {
                            try {
                                CommandParams StopListenCmd = new CommandParams();
                                StopListenCmd.Command = Commands.STOP_COMMAND_LISTENER;
                                new CommandTransceiver().SendCommand(StopListenCmd, "127.0.0.1");
                            } catch (Exception Ex) {

                            }

                        }
                        ExitFlag = true;

                        break;

                }
            } catch (Exception Ex) {
                Utilities.ShowError("Invalid input");
            }

        }

    }

}
