/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedvoting;

import Common.Configuration;
import Common.InstanceInfo;
import Common.Utilities;
import View.*;
import Voting.Initializer;
import Voting.Systems.SystemInfo;
import Voting.Systems.SystemList;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 *
 * @author Aron
 */
public class DistributedVoting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].compareTo(Configuration.CONFIG_MODE) == 0) {
                new ConfigView().ShowView();
            } else {

                Utilities.ShowError("Invalid Switch");
            }
        } else {
            try {

                boolean ValidSystemFlag = false;
                String SystemName = Inet4Address.getLocalHost().getHostName();
                for (SystemInfo Node : new SystemList().GetParticipantSystems()) {
                    if (Node.SystemNetworkName.compareTo(SystemName) == 0) {
                        ValidSystemFlag = true;
                        break;
                    }
                }

                if (ValidSystemFlag) {
                    Initializer.Initialize();
                    new MainView().ShowView();
                } else {
                    Utilities.ShowError("This system is not configured among the participant systems. Run the application in \"ConfigMode\" to configure participants.");
                }
            } catch (Exception Ex) {
                Utilities.ShowError("Exception occured. Details - " + Ex.getMessage());
            }
        }
    }
}
