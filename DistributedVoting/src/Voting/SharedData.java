/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voting;

import Common.InstanceInfo;
import Voting.Systems.SystemInfo;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Aron
 */
public class SharedData implements Serializable {

    public int VN;
    public int RU;
    public String DS_ID;
    private SystemInfo DS_NODE;
    public ArrayList<SystemInfo> NODE_LIST;

    public SharedData() {
        this.VN = 0;
        this.RU = 0;
        NODE_LIST = new ArrayList();
    }

    public void DisplaySharedData() {
        System.out.println("\nVOTING PARAMETERS\n------------------------------------------");
        System.out.println("VN: " + VN + "  RU: " + RU + "  DS: " + DS_ID);
        System.out.println("------------------------------------------\n");
    }

    public void UpdateDS() {
        SystemInfo DS;
        if (NODE_LIST.size() > 0) {
            DS_ID = NODE_LIST.get(0).SystemID;
        }
        for (SystemInfo Node : NODE_LIST) {
            if (Node.SystemID.compareTo(DS_ID) >= 0) {
                DS_ID = Node.SystemID;
                DS_NODE = Node;
            }
        }
    }

    public void AddNode(SystemInfo NewNode) {
        boolean NodePresent = false;
        for (SystemInfo Node : NODE_LIST) {
            if (Node.SystemID.compareTo(NewNode.SystemID) == 0) {
                NodePresent = true;
                break;
            }
        }
        if (!NodePresent) {
            NODE_LIST.add(NewNode);
            this.UpdateDS();
        }

        RU = NODE_LIST.size();

    }

    public void RemoveNode(SystemInfo Node) {
        int RemoveIndex = 0;
        for (SystemInfo Neighbor : this.NODE_LIST) {
            if (Neighbor.SystemID.compareTo(Node.SystemID) == 0) {
                break;
            }
            ++RemoveIndex;
        }
        this.NODE_LIST.remove(RemoveIndex);
        this.RU = this.NODE_LIST.size();
        this.UpdateDS();
    }

    public SystemInfo GetDSNode() {
        return this.DS_NODE;
    }
}
