package com.GymManagementSystem;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyGymManagerTest {

    MyGymManager manager = new MyGymManager();

    @Test
    public void addMember() {
        DefaultMember member = new DefaultMember("M020", "Sashin", "02-02-2020", "0710810231", 5, 50);
        assertEquals("Checking whether member has been successfully added",true, manager.addMember(member));

    }

    @Test
    public void deleteMember() {
        assertEquals("Checks whether the member with the given membership number has been successfully deleted",true, manager.deleteMember("M002"));
    }

    @Test
    public void getMemberList() {
        assertArrayEquals(new ArrayList<DefaultMember>(),manager.getMemberList());
    }

    private void assertArrayEquals(List<DefaultMember> memberList, List<DefaultMember> memberList1) {

    }

    @Test
    public void searchByMembershipNumber() {
        String str1 = manager.searchByMembershipNumber("M001");

        String str2 = "Membership No : M001\n";
        str2 += "Member Type : DefaultMember\n";
        str2 += "Member Name : Shiny\n";
        str2 += "Membership start date : 02-03-2020\n";
        str2 += "Contact No : 0766784325\n";
        str2 += "Height : 5.0 m\n";
        str2 += "Weight : 60.0 kg\n";

        assertEquals("Checks whether it is returning the correct information according to the membership number",true, str1.equals(str2));
    }

    @Test
    public void searchByMemberName() {
        String str1 = manager.searchByMemberName("Shiny");

        String str2 = "Membership No : M001\n";
        str2 += "Member Type : DefaultMember\n";
        str2 += "Member Name : Shiny\n";
        str2 += "Membership start date : 02-03-2020\n";
        str2 += "Contact No : 0766784325\n";
        str2 += "Height : 5.0 m\n";
        str2 += "Weight : 60.0 kg\n\n";

        assertEquals("Checks whether it is returning the correct information according to the member name",true, str1.equals(str2));
    }
}