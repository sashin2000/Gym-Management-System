package com.GymManagementSystem;

import java.util.InputMismatchException;
import java.util.Scanner;


public class GymConsoleUI {

    private static final MyGymManager manager = new MyGymManager();
    private static int count = 0;


    public static void main(String[] args) {
        while(true) {
            System.out.println("Gym Management System");
            System.out.println("---------------------");

            System.out.println("1 ---> Add a new member");
            System.out.println("2 ---> Delete a member");
            System.out.println("3 ---> Print the list of members");
            System.out.println("4 ---> Sort according to name");
            System.out.println("5 ---> Save");
            System.out.println("6 ---> Visualize Members into table");
            System.out.println("7 ---> Exit");

            System.out.print("Please select an index from the management actions given above : ");


            try {
                Scanner inputSelection = new Scanner(System.in);
                int selection = inputSelection.nextInt();

                switch (selection) {
                    case 1:
                        System.out.println("\nAdd a new member");
                        System.out.println("-----------------");
                        addNewMember();
                        break;
                    case 2:
                        System.out.println("\nDelete a member");
                        System.out.println("---------------");
                        deleteMember();
                        break;
                    case 3:
                        System.out.println("\nPrint the list of members");
                        System.out.println("-------------------------");
                        manager.printMembers();
                        break;
                    case 4:
                        System.out.println("\nSort according to name");
                        System.out.println("----------------------");
                        manager.sortMembers();
                        break;
                    case 5:
                        System.out.println("\nSave");
                        System.out.println("----");
                        manager.save();
                        break;
                    case 6:
                        System.out.println("\nVisualize Members into table");
                        System.out.println("----------------------------");
                        TableCreatorForGraphicalUI.divideMemberTypes(manager.getMemberList());
                        GymGraphicalUI.display();
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("Invalid Selection. Please try again.\n");
                }

                if (selection == 7 || selection == 6){
                    System.out.println("Good Bye!");
                    break;
                }

            } catch (InputMismatchException e){
                System.out.println("Invalid Selection. Please try again.\n");
            }
        }
    }

    private static void addNewMember(){

        if(count < 100){

            while(true){

                Scanner input = new Scanner(System.in);
                Scanner inputLine = new Scanner(System.in);

                System.out.print("Enter membership number : ");
                String membershipNumber = input.next();


                System.out.print("Enter member name : ");
                String memberName = input.next();

                System.out.print("Enter membership start date (dd-mm-yyyy) : ");
                String membershipStartDate = input.next();

                System.out.print("Enter contact number : ");
                String contactNo = input.next();

                System.out.print("Enter height in metres : ");
                double height = input.nextDouble();

                System.out.print("Enter weight in kilograms : ");
                double weight = input.nextDouble();

                DefaultMember member = null;

                System.out.println("\n1 ---> Default member");
                System.out.println("2 ---> Student member");
                System.out.println("3 ---> Over 60 member");

                System.out.print("Which type of member do you want to enter ? ");
                int selection = input.nextInt();

                switch (selection){
                    case 1:

                        member = new DefaultMember(membershipNumber, memberName, membershipStartDate, contactNo, height, weight);
                        break;

                    case 2:

                        while (true){
                            System.out.print("Enter school name : ");
                            String schoolName = inputLine.nextLine();

                            System.out.print("State whether the member is engaging in physical activities in school as well (yes/no) : ");
                            String isEngagingInPhysicalActivitiesInSchool = input.next().toLowerCase();

                            if(!isEngagingInPhysicalActivitiesInSchool.equals("yes") && !isEngagingInPhysicalActivitiesInSchool.equals("no")){
                                System.out.println("Invalid input");
                                continue;
                            }
                            member = new StudentMember(membershipNumber, memberName, membershipStartDate, contactNo, height, weight, schoolName, isEngagingInPhysicalActivitiesInSchool);
                            break;
                        }
                        break;

                    case 3:

                        while (true){
                            System.out.print("Enter age : ");
                            int age = input.nextInt();

                            System.out.print("Is the member using any medications? (yes/no)");
                            String medications = input.next().toLowerCase();
                            if(!medications.equals("yes") && !medications.equals("no")){
                                System.out.println("Invalid input");
                                continue;
                            }
                            member = new Over60Member(membershipNumber, memberName, membershipStartDate, contactNo, height, weight, age, medications);
                            break;
                        }
                        break;

                    default:
                        System.out.println("Invalid selection. Please try again.");
                }

                if (member != null){
                    Boolean flag = manager.addMember(member);
                    if (flag){
                        count++;
                    }
                    break;
                }
            }
        }else{
            System.out.println("Sorry. There are no more slots available for a new member.");
        }


    }

    private static void deleteMember(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the membership number that you want to remove : ");
        String membershipNumber = input.next();
        Boolean flag = manager.deleteMember(membershipNumber);
        if (flag){
            count--;
        }
    }
}
