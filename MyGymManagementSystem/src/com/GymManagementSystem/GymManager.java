package com.GymManagementSystem;

import java.util.List;

public interface GymManager {
    // All the service requirements that are in contract between client and the program, are declared here.
    boolean addMember(DefaultMember member);
    boolean deleteMember(String membershipNumber);
    void printMembers();
    void sortMembers();
    void save();
    List<DefaultMember> getMemberList();
    String searchByMembershipNumber(String membershipNumber);
    String searchByMemberName(String memberName);
}
