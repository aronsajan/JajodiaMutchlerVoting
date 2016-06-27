/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Common.Utilities;
import Voting.Systems.SystemList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Aron
 */
public class ConfigView {

    public void ShowView() {
        try {
            InputStreamReader IStream = new InputStreamReader(System.in);
            BufferedReader Reader = new BufferedReader(IStream);
            System.out.println("Configure participants\n----------------------------------\n");
            SystemList Participants = new SystemList();
            String UserOpt = "Y";
            do {
                System.out.print("System ID : ");
                String SysID = Reader.readLine();
                System.out.print("System Name : ");
                String SysName = Reader.readLine();
                Participants.AddSystem(SysName, SysID);
                System.out.print("Do you want to continue (Y/N) : ");
                UserOpt = Reader.readLine().toUpperCase();

            } while (UserOpt.compareTo("Y") == 0);
        } catch (Exception Ex) {
            Utilities.ShowError("An exception has been encountered while configuring the participant systems. Details - " + Ex.getMessage());
        }
    }
}
