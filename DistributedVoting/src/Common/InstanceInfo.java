/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import IOSubsystem.CommandListenerThread;
import Voting.SharedData;
import Voting.Systems.SystemInfo;
import Voting.Systems.SystemList;


/**
 *
 * @author Aron
 */
public class InstanceInfo {
    public static CommandListenerThread CmdListener;
    public static SystemInfo LocalSystem;
    public static SystemList AllParticipantSystems;
    public static SharedData SharedInfo;
    public static boolean ReceiveCommands;
    
    public InstanceInfo()
    {
        ReceiveCommands=true;
        CmdListener=new CommandListenerThread();
        AllParticipantSystems=new SystemList();
        SharedInfo=new SharedData();
    }
}
