/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommandEngine;

import Voting.SharedData;
import Voting.Systems.SystemInfo;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Aron
 */
public class CommandParams implements Serializable{
   
    public String Command;
    public SystemInfo SrcNode;
    public ArrayList<SystemInfo> GroupMembers;
    public SharedData SharedInfo; 
}
