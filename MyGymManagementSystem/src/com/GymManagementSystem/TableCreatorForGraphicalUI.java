package com.GymManagementSystem;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class TableCreatorForGraphicalUI {

    private static List<DefaultMember> defaultMember = new ArrayList<DefaultMember>();
    private static List<StudentMember> studentMember = new ArrayList<StudentMember>();
    private static List<Over60Member> over60Member = new ArrayList<Over60Member>();

    /**
     * Here it is divided the members according to their member type in order to store them separately in the database collections
     * @param memberList
     */

    public static void divideMemberTypes(List<DefaultMember> memberList){
        for (DefaultMember member: memberList){
            if (member instanceof StudentMember){
                studentMember.add((StudentMember) member);
            }else if(member instanceof Over60Member){
                over60Member.add((Over60Member) member);
            }else{
                defaultMember.add(member);
            }
        }
    }

    /**
     * Here it the data is being populated into relevant tables in GUI
     * @return
     */

    public static Label defaultMemberTable(){

        String table = "\n   MembershipNo\t\tMemberName\t\tMembershipStartDate\t\tContactNo\t\tHeight (m)\tWeight (kg)\n" +
                "   ---------------------------------------------------------------------------------------------------\n";

        for (DefaultMember member: defaultMember) {
            table += "\t" + member.getMembershipNumber() +"\t\t|\t"+ member.getMemberName() + "\t\t|\t\t" + member.getMembershipStartDate() + "\t\t|\t" + member.getContactNo() + "\t|\t" + member.getHeight() + "\t\t|\t" + member.getWeight() + "\n";
        }

        Label defaultMemberTable = new Label(table);
        return defaultMemberTable;
    }

    public static Label studentMemberTable(){

        String table = "\n   MembershipNo\t\tMemberName\t\tMembershipStartDate\t\tContactNo\t\tHeight (m)\tWeight (kg)\t\tSchool\t\t\tIsEngagingInSports\n" +
                "   -----------------------------------------------------------------------------------------------------------------------------------------------\n";

        for (StudentMember member: studentMember) {
            table += "\t" + member.getMembershipNumber() +"\t\t|\t"+ member.getMemberName() + "\t\t|\t\t" + member.getMembershipStartDate() + "\t\t|\t" + member.getContactNo() + "\t|\t" + member.getHeight() + "\t\t|\t" + member.getWeight() + "\t\t|\t" + member.getSchoolName() + "\t|\t" + member.getIsEngagingPhysicalActivitiesInSchool() + "\n";
        }

        Label studentMemberTable = new Label(table);
        return studentMemberTable;
    }

    public static Label over60MemberTable(){

        String table = "\n   MembershipNo\t\tMemberName\t\tMembershipStartDate\t\tContactNo\t\tHeight (m)\tWeight (kg)\tAge\t\tIsTakingMedications\n" +
                "   ------------------------------------------------------------------------------------------------------------------------------------\n";

        for (Over60Member member: over60Member) {
            table += "\t" + member.getMembershipNumber() +"\t\t|\t"+ member.getMemberName() + "\t\t|\t\t" + member.getMembershipStartDate() + "\t\t|\t" + member.getContactNo() + "\t|\t" + member.getHeight() + "\t\t|\t" + member.getWeight() + "\t\t|\t" + member.getAge() + "\t\t|\t" + member.getMedications() + "\n";
        }

        Label over60Member = new Label(table);
        return over60Member;
    }

}
