package com.GymManagementSystem;

public class StudentMember extends DefaultMember{

    private String schoolName;
    private String isEngagingPhysicalActivitiesInSchool;

    public StudentMember(String membershipNumber, String memberName, String membershipStartDate, String contactNo, double height, double weight, String schoolName, String isEngagingPhysicalActivitiesInSchool) {
        super(membershipNumber, memberName, membershipStartDate, contactNo, height, weight);
        setSchoolName(schoolName);
        setIsEngagingPhysicalActivitiesInSchool(isEngagingPhysicalActivitiesInSchool);

    }

    //Implementation of getters and setters.
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getIsEngagingPhysicalActivitiesInSchool() {
        return isEngagingPhysicalActivitiesInSchool;
    }

    public void setIsEngagingPhysicalActivitiesInSchool(String isEngagingPhysicalActivitiesInSchool) {
        this.isEngagingPhysicalActivitiesInSchool = isEngagingPhysicalActivitiesInSchool;
    }
}
