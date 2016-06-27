/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voting;

import CommandEngine.CommandParams;
import CommandEngine.Commands;
import Common.InstanceInfo;
import Common.Utilities;
import IOSubsystem.CommandTransceiver;
import Voting.Systems.SystemInfo;
import java.util.ArrayList;

/**
 *
 * @author Aron
 */
public class UpdateManager {

    public void StartUpdateProcess() {
        VotingSession Session = new VotingSession();
        Session.PrepareSession();
        Utilities.ShowStatus("Requesting for vote...");
        this.RequestVote();
        Session.StartVotingSession();
        this.ProcessVotes();
    }

    private void RequestVote() {
        try {
            CommandParams VoteCmd = new CommandParams();
            VoteCmd.Command = Commands.CAN_I_UPDATE;
            VoteCmd.SrcNode = InstanceInfo.LocalSystem;

            for (SystemInfo Neighbor : Neighbors.NeighborList) {
                new CommandTransceiver().SendCommand(VoteCmd, Neighbor.SystemNetworkName);
            }
        } catch (Exception Ex) {

            Utilities.ShowError("Exception occured while sending Can I Update command");

        }
    }

    /*
     On receiving the GO_AHEAD_UPDATE confirmation the system has to track the responses as well as count it.
     If the responses received makes a majority then do the update.
     If the responses received makes a tie with the RU count then check if DS_ID is in the set of systems responded, if so then do the update
     else do not perform update
     if response does not make a majority then do not perform update
                
     If update has been performed then send EXECUTE_UPDATE to the systems responded. The data carries the updated SHARED_INFO
     */
    private void ProcessVotes() {
        int VotesObtained = VotingSession.VotingResponse.size();
        if ((2 * (VotesObtained+1)) > InstanceInfo.SharedInfo.RU) {
            Utilities.ShowStatus("Votes form a majority. Updating data...");
            this.ExecuteUpdate();
            //Update data and send confirmation to all neighbors
        } else if ((2 * (VotesObtained+1)) == InstanceInfo.SharedInfo.RU) {
            Utilities.ShowStatus("Votes created a tie. Checking for DS response");
            boolean DS_Responded = false;
            for (SystemInfo RespondedNode : VotingSession.VotingResponse) {
                if (RespondedNode.SystemID.compareTo(InstanceInfo.SharedInfo.DS_ID) == 0) {
                    DS_Responded = true;
                    break;
                }
            }
            
            if(InstanceInfo.SharedInfo.DS_ID.compareTo(InstanceInfo.LocalSystem.SystemID)==0)
            {
                DS_Responded=true;
            }
            
            if (DS_Responded) {
                Utilities.ShowStatus("DS responded. Updating data...");
                this.ExecuteUpdate();
            } else {
                Utilities.ShowStatus("DS did not respond. Update process terminated");
                InstanceInfo.SharedInfo.DisplaySharedData();
            }
        } else {
            //Take no further action
            Utilities.ShowStatus("Votes obtained forms only a minority. Update process terminated");
             InstanceInfo.SharedInfo.DisplaySharedData();
        }
    }

    private void ExecuteUpdate() {
        try {
            ++InstanceInfo.SharedInfo.VN;
           
            ArrayList<SystemInfo> NodesToRemove=new ArrayList();
            for(SystemInfo Neighbor: InstanceInfo.SharedInfo.NODE_LIST)
            {
               if(Neighbor.SystemID.compareTo(InstanceInfo.LocalSystem.SystemID)==0)
               {
                   continue;
               }
                boolean NeighborResponded=false;
                for(SystemInfo RespondedNeighbor: VotingSession.VotingResponse)
                {
                    
                    if(RespondedNeighbor.SystemID.compareTo(Neighbor.SystemID)==0)
                    {
                        NeighborResponded=true;
                        break;
                    }
                }
                if(NeighborResponded==false)
                {
                    NodesToRemove.add(Neighbor);  
                }
            }
            
            for(SystemInfo Node: NodesToRemove)
            {
                System.out.println("NOT RESPONDED: "+Node.SystemID);
                InstanceInfo.SharedInfo.RemoveNode(Node);
            }
            
            Utilities.ShowStatus("Data version updated. Transmitting new data to all group members");
            CommandParams ExecUpdateCmd = new CommandParams();
            ExecUpdateCmd.Command = Commands.EXECUTE_UPDATE;
            ExecUpdateCmd.SharedInfo = InstanceInfo.SharedInfo;
            for (SystemInfo RespondedNode : VotingSession.VotingResponse) {
                new CommandTransceiver().SendCommand(ExecUpdateCmd, RespondedNode.SystemNetworkName);
            }
            
            Utilities.ShowStatus("Shared Data Updated");
            InstanceInfo.SharedInfo.DisplaySharedData();
            
        } catch (Exception Ex) {
            Utilities.ShowError("Exception occured while confirming update. Details - " + Ex.getMessage());
        }
    }

}
