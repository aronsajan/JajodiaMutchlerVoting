/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine;

import CommandEngine.CommandHandler.*;
import Common.InstanceInfo;
import Voting.Neighbors;

/**
 *
 * @author Aron
 */
public class CommandProcessor {

    public void Execute(CommandParams CmdInfo) {
        switch (CmdInfo.Command) {
            case Commands.I_WANT_TO_JOIN:

                new I_WANT_TO_JOINHandler().Execute(CmdInfo);

                break;

            case Commands.CASCADE_JOIN:

                new CASCADE_JOINHandler().Execute(CmdInfo);

                break;

            case Commands.JOIN_NETWORK_ACK:

                new JOIN_NETWORK_ACKHandler().Execute(CmdInfo);

                break;
            case Commands.DISCONNECT_ME:

                new DISCONNECT_MEHandler().Execute(CmdInfo);

                break;
            case Commands.CASCADE_DISCONNECT:

                new CASCADE_DISCONNECTHandler().Execute(CmdInfo);

                break;

            case Commands.CAN_I_UPDATE:

                new CAN_I_UPDATEHandler().Execute(CmdInfo);

                break;
            case Commands.GO_AHEAD_UPDATE:

                new GO_AHEAD_UPDATEHandler().Execute(CmdInfo);

                break;

            case Commands.EXECUTE_UPDATE:
                new EXECUTE_UPDATEHandler().Execute(CmdInfo);
                
                break;

            case Commands.STOP_COMMAND_LISTENER:
                InstanceInfo.ReceiveCommands = false;
                break;

        }

    }
}
