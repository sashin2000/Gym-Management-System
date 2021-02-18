package com.GymManagementSystem;


public class DefaultMember {

    private String  membershipNumber;
    private String memberName;
    private Date membershipStartDate;
    private String contactNo;
    private double height;
    private double weight;

    public DefaultMember(String membershipNumber, String memberName, String membershipStartDate, String contactNo, double height, double weight){
        setMembershipNumber(membershipNumber);
        setMemberName(memberName);
        setMembershipStartDate(membershipStartDate);
        setContactNo(contactNo);
        setHeight(height);
        setWeight(weight);
    }


    //implementation of getters and setters

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMembershipStartDate() {
        return membershipStartDate.displayDate();
    }

    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = new Date(membershipStartDate);
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        if (contactNo.length() != 10){
            throw new IllegalArgumentException("Invalid length in contact number.");
        }else{
            this.contactNo = contactNo;
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
