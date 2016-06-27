/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine.CommandHandler;

import CommandEngine.CommandParams;
import Voting.Neighbors;

/**
 *
 * @author Aron
 */
public class CASCADE_DISCONNECTHandler {
    
    public void Execute(CommandParams CmdInfo)
    {
        Neighbors.RemoveNeghbor(CmdInfo.SrcNode);
    }
    
}
