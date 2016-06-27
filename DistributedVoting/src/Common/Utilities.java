/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import Voting.Systems.SystemInfo;
import java.util.ArrayList;

/**
 *
 * @author Aron
 */
public class Utilities {
     public static void ShowStatus(String Msg)
    {
        System.out.println("STATUS : "+Msg);
    }
     public static void ShowError(String Msg)
     {
         System.out.println("ERROR : "+Msg);
     }
     
     public static void ShowWarning(String Msg)
     {
         System.out.println("WARNING : "+Msg);
     }
     public static void ShowSystems(ArrayList<SystemInfo> Nodes)
     {
         int Count=1;
          System.out.println("No.\t System ID \t System Name");
          System.out.println("------------------------------------------------------------");
         for(SystemInfo Node:Nodes)
         {
             System.out.println(Count+".\t"+Node.SystemID+"\t"+Node.SystemNetworkName);
             ++Count;
         }
     }
     
}
