/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voting.Systems;

import java.io.Serializable;

/**
 *
 * @author Aron
 */
public class SystemInfo implements Serializable
{
    public String SystemNetworkName;
    public String SystemID;
    
    public SystemInfo(String name, String id)
    {
        this.SystemNetworkName=name;
        this.SystemID=id;
    }
    
}
