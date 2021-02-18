package com.GymManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class MyGymManager implements GymManager{
    //Array list has been used for store the instances of members.
    private static List<DefaultMember> memberList = SaveAndRetrieveMembers.retrieve();

    //Implementation of methods in interface GymManager

    /**
     * addMember()      ---> adding members to memberList
     * deleteMember()   ---> deleting members from memberList
     * printMembers()   ---> printing details of members from memberList
     * sortMembers()    ---> sorting members by name (ascending order)
     * save()           ---> saving members in memberList to mongoDB database
     */

    @Override
    public boolean addMember(DefaultMember member) {
        if (checkFromMemberId(member.getMembershipNumber()) != -1){
            System.out.println("Membership number already exists.");
            return false;
        }else{
            if (getMemberList().size() >= 100){
                System.out.println("No more slots available for new members.");
                return false;
            }else{
                getMemberList().add(member);
                System.out.println("Member added successfully\n");
                System.out.println("\nNo. of reserved slots : " + getMemberList().size());     //Prints reserved slots in gym.
                System.out.println("No. of free slots available : " + (100 - getMemberList().size()));     //Prints free slots available in gym.
                return true;
            }
        }

    }

    @Override
    public boolean deleteMember(String membershipNumber) {
        int indexOfMemberInstance = checkFromMemberId(membershipNumber);
        if (indexOfMemberInstance == -1){
            System.out.println("Member under given membership number does not exist.\n");
            return false;
        }else{
            if (memberList.get(indexOfMemberInstance) instanceof Over60Member){
                System.out.println("Member Type : Over60Member");
            }else if(memberList.get(indexOfMemberInstance) instanceof StudentMember){
                System.out.println("Member Type : StudentMember");
            }else{
                System.out.println("Member Type : DefaultMember");
            }
            getMemberList().remove(memberList.get(indexOfMemberInstance));
            System.out.println("Member successfully removed.\n");
            System.out.println("\nNo. of reserved slots : " + getMemberList().size());     //Prints reserved slots in gym.
            System.out.println("No. of free slots available : " + (100 - getMemberList().size()));     //Prints free slots available in gym.
            return true;
        }

    }

    @Override
    public void printMembers() {
        if (memberList.size() == 0){
            System.out.println("There are no members to display.");
        }else{
            for (DefaultMember member: memberList) {
                System.out.print("Membership No : " + member.getMembershipNumber() + "\t");
                if (member instanceof Over60Member){
                    System.out.print("Member Type : Over60Member\t\t");
                }else if(member instanceof StudentMember){
                    System.out.print("Member Type : StudentMember\t\t");
                }else{
                    System.out.print("Member Type : DefaultMember\t\t");
                }
                System.out.print("Member Name : " + member.getMemberName() + "\t\t");
                System.out.println("Membership start date : " + member.getMembershipStartDate());
            }
        }

    }

    /**
     * Here it is sorting according to the bubble sorting algorithm
     * It takes a copy of the memberList into another ArrayList and sorting the copied ArrayList named "sortedMemberList"
     */

    @Override
    public void sortMembers() {
        List<DefaultMember> sortedMemberList = new ArrayList<>();
        sortedMemberList.addAll(memberList);        //Making a copy of memberList

        for (int i = 0; i < sortedMemberList.size() - 1 ; i++) {
            for (int j = 0; j < sortedMemberList.size() - 1 ; j++) {
                if (sortedMemberList.get(j).getMemberName().compareTo(sortedMemberList.get(j+1).getMemberName()) >= 0){
                    DefaultMember tempMember = sortedMemberList.get(j);
                    sortedMemberList.remove(j);
                    sortedMemberList.add(j+1,tempMember);
                }
            }
        }

        if (sortedMemberList.size() == 0){
            System.out.println("There are no members to display.");
        }else{
            for (DefaultMember member: sortedMemberList) {
                System.out.print("Membership No : " + member.getMembershipNumber() + "\t");
                if (member instanceof Over60Member){
                    System.out.print("Member Type : Over60Member\t\t");
                }else if(member instanceof StudentMember){
                    System.out.print("Member Type : StudentMember\t\t");
                }else{
                    System.out.print("Member Type : DefaultMember\t\t");
                }
                System.out.print("Member Name : " + member.getMemberName() + "\t\t");
                System.out.println("Membership start date : " + member.getMembershipStartDate());
            }
        }
    }

    @Override
    public void save() {
        SaveAndRetrieveMembers.save(memberList);
        System.out.println("Saved Successfully\n");
    }

    @Override
    public List<DefaultMember> getMemberList() {
        return memberList;
    }

    @Override
    public String searchByMembershipNumber(String membershipNumber) {
        DefaultMember tempMember = null;
        for (DefaultMember member: memberList) {
            if (member.getMembershipNumber().equals(membershipNumber)){
                tempMember = member;
                break;
            }
        }

        if (tempMember == null){
            return "Membership number you provided doesn't exist.";
        }

        String str = "";

        str += "Membership No : " + tempMember.getMembershipNumber() + "\n";
        if (tempMember instanceof Over60Member){
            str += "Member Type : Over60Member\n";
        }else if(tempMember instanceof StudentMember){
            str +="Member Type : StudentMember\n";
        }else{
            str += "Member Type : DefaultMember\n";
        }
        str += "Member Name : " + tempMember.getMemberName() + "\n";
        str += "Membership start date : " + tempMember.getMembershipStartDate() + "\n";
        str += "Contact No : " + tempMember.getContactNo() + "\n";
        str += "Height : " + tempMember.getHeight() + " m\n";
        str += "Weight : " + tempMember.getWeight() + " kg\n";


        return str;
    }

    @Override
    public String searchByMemberName(String memberName) {
        List<DefaultMember> tempMember = new ArrayList<DefaultMember>();
        for (DefaultMember member: memberList) {
            if (member.getMemberName().toLowerCase().equals(memberName.toLowerCase())){
                tempMember.add(member);
            }
        }

        if (tempMember.size() == 0){
            return "Member name you provided doesn't exist.";
        }

        String str = "";

        for (DefaultMember member: tempMember) {
            str += "Membership No : " + member.getMembershipNumber() + "\n";
            if (member instanceof Over60Member){
                str += "Member Type : Over60Member\n";
            }else if(member instanceof StudentMember){
                str +="Member Type : StudentMember\n";
            }else{
                str += "Member Type : DefaultMember\n";
            }
            str += "Member Name : " + member.getMemberName() + "\n";
            str += "Membership start date : " + member.getMembershipStartDate() + "\n";
            str += "Contact No : " + member.getContactNo() + "\n";
            str += "Height : " + member.getHeight() + " m\n";
            str += "Weight : " + member.getWeight() + " kg\n\n";
        }

        tempMember.get(0).getMembershipNumber();
        return str;
    }

    /**
     *
     * @param memberId
     * @return
     *
     * this method is used for check whether the user given member id is already exist in member list
     * if the member id exists, it returns the index of the relevant member instance.
     * else it returns -1
     */

    private static int checkFromMemberId(String memberId){
        for (int i = 0; i < memberList.size(); i++) {
            if (memberId.equals(memberList.get(i).getMembershipNumber())){
                return i;
            }
        }
        return -1;
    }

}
