/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine.CommandHandler;

import CommandEngine.CommandParams;
import Common.Utilities;
import Voting.VotingSession;

/**
 *
 * @author Aron 
 */
public class GO_AHEAD_UPDATEHandler {
    
    public void Execute(CommandParams CmdInfo)
    {
        if(VotingSession.SessionActive)
        {
            Utilities.ShowStatus("Approval Response From : "+CmdInfo.SrcNode.SystemID);
            VotingSession.VotingResponse.add(CmdInfo.SrcNode);
        }
    }
    
}
