/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voting;

import Common.InstanceInfo;
import Common.Utilities;
import Voting.Systems.SystemInfo;
import java.net.Inet4Address;

/**
 *
 * @author Aron
 */
public class Initializer {

    public static void Initialize() {
        try {
            new InstanceInfo();
            new Neighbors();
            String LocalSystemName = Inet4Address.getLocalHost().getHostName();
            for (SystemInfo System : InstanceInfo.AllParticipantSystems.GetParticipantSystems()) {
                if (System.SystemNetworkName.compareTo(LocalSystemName) == 0) {
                    InstanceInfo.LocalSystem = System;
                    break;
                }
            }
            InstanceInfo.SharedInfo.DS_ID=InstanceInfo.LocalSystem.SystemID;
            InstanceInfo.SharedInfo.AddNode(InstanceInfo.LocalSystem);
            Utilities.ShowStatus("DS : "+InstanceInfo.SharedInfo.GetDSNode().SystemID);
            InstanceInfo.CmdListener.start();
        } catch (Exception Ex) {
            Utilities.ShowError("The following exception has occured during system initialization - " + Ex.getMessage());
        }
    }

}
