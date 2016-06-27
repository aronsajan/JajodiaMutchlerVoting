/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voting.Systems;

import Common.Configuration;
import Common.Utilities;
import IOSubsystem.FileManager;
import Serializer.BinarySerializer;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Aron
 */
public class SystemList implements Serializable{

    private ArrayList<SystemInfo> SysList;

    public SystemList() {
        SysList = GetParticipantSystems();
    }

    public void AddSystem(String SystemName, String SystemID) throws Exception {
        this.SysList.add(new SystemInfo(SystemName, SystemID));
        this.SaveSystemList();
    }

    private void SaveSystemList() {
        try {
            byte[] SysListRAW = new BinarySerializer<SystemList>().Serialize(this);
            FileManager.WriteToFile(SysListRAW, Configuration.ParticipantSystemsFile);
        } catch (Exception Ex) {
            Utilities.ShowError("Saving participant system information failed");
        }
    }

    public ArrayList<SystemInfo> GetParticipantSystems() {
        try {
            if (new File(Configuration.ParticipantSystemsFile).exists()) {
                byte[] SysListRAW = FileManager.ReadFile(Configuration.ParticipantSystemsFile);
                SysList = new BinarySerializer<SystemList>().Deserialize(SysListRAW).SysList;
            }
            else
            {
                SysList=new ArrayList();
            }
            return SysList;
        } catch (Exception Ex) {
            Utilities.ShowError("Retrieving participant system information failed");
        }
        return null;
    }

    public SystemInfo GetSystem(String SystemID) throws Exception {
        for (SystemInfo Sys : this.SysList) {
            if (Sys.SystemID.toUpperCase().compareTo(SystemID.toUpperCase()) == 0) {
                return Sys;
            }
        }
        throw new Exception("System not found among the participant nodes");
    }
}
