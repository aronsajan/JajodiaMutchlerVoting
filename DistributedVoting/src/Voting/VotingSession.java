/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voting;

import Common.Configuration;
import Common.Utilities;
import Voting.Systems.SystemInfo;
import java.util.ArrayList;

/**
 *
 * @author Aron
 */
public class VotingSession {

    public static ArrayList<SystemInfo> VotingResponse;
    public static boolean SessionActive;
    public VotingSession() {
        VotingResponse = new ArrayList();
        SessionActive=false;
    }

    public void PrepareSession()
    {
        SessionActive=true;
        this.ResetVotingSession();
    }
    
    public void StartVotingSession() {

        try {
            Thread.sleep(Configuration.VOTING_PERIOD);
        } catch (Exception Ex) {
            Utilities.ShowError("Exception occured in session maintenance. Details - " + Ex.getMessage());
        }
        SessionActive=false;
    }

    public void ResetVotingSession() {
        VotingResponse.clear();
    }

}
