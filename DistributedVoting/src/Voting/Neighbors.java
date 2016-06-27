/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voting;

import Common.Utilities;
import Voting.Systems.SystemInfo;
import java.util.ArrayList;

/**
 *
 * @author Aron
 */
public class Neighbors {
    public static ArrayList<SystemInfo> NeighborList;
    public Neighbors()
    {
        NeighborList=new ArrayList<SystemInfo>();
    }
    
    public static void AddNeighbor(SystemInfo NewNeighbor)
    {
         boolean NodePresent=false;
                for(SystemInfo Node: Neighbors.NeighborList)
                {
                    if(Node.SystemID.compareTo(NewNeighbor.SystemID)==0)
                    {
                        NodePresent=true;
                        break;
                    }
                }
                if(!NodePresent)
                {
                    Neighbors.NeighborList.add(NewNeighbor);
                    Utilities.ShowStatus(NewNeighbor.SystemID+" added to Neighbors");
                }
                else
                {
                    Utilities.ShowWarning(NewNeighbor.SystemID+"("+NewNeighbor.SystemNetworkName+" already present in neighbor list. Add Neighbor operation failed)");
                }
    }
    public static void RemoveNeghbor(SystemInfo Node)
    {
        int Index=0;
        boolean FoundFlag=false;
        for(SystemInfo System: NeighborList)
        {
            if(System.SystemID.compareTo(Node.SystemID)==0)
            {
                FoundFlag=true;
                break;
            }
            ++Index;
        }

       if(FoundFlag)
       {
           NeighborList.remove(Index);
           Utilities.ShowStatus("Connection to node "+Node.SystemID+" has been lost");
       }
       else
       {
           Utilities.ShowWarning("System does not contain node "+Node.SystemID+" in Neighbor list. Neighbor removal operation failed");
       }
    }
    
    public static void DisconnectFromAll()
    {
        for(SystemInfo Neighbor:NeighborList)
        {
            Utilities.ShowStatus("Connection with "+Neighbor.SystemID+" has been lost");
        }
        NeighborList.clear();
    }
    
}
