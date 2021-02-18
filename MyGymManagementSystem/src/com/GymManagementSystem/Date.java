package com.GymManagementSystem;

public class Date {
    private int year;
    private int month;
    private int day;

    private boolean isValidated;

    Date(String strDate){

        isValidated = validateDate(strDate);     //validating the date whether it is with the proper format and with correct value ranges.

        if (isValidated){
            arrangeDate(strDate);
        }

    }

    private static boolean validateDate(String strDate){
        try {
            String[] separateStrDate = strDate.split("-", 3);
            int day = Integer.parseInt(separateStrDate[0]);
            int month = Integer.parseInt(separateStrDate[1]);
            int year = Integer.parseInt(separateStrDate[2]);

            String strMonth = findMonth(month);

            if( day < 1 || month < 1 || year < 1900){
                throw new IllegalArgumentException("Invalid date format.");
            }


            if(!(month <= 12 && month > 0)){
                throw new IllegalArgumentException("Invalid month has been entered.");

            } else {
                switch (month){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (!(0 < day && day <= 31)){
                            throw new IllegalArgumentException("Invalid day has been entered for " + strMonth);
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (!(0 < day && day <= 30)){
                            throw new IllegalArgumentException("Invalid day has been entered for " + strMonth);
                        }
                        break;
                    case 2:

                        if (isLeapYear(year)){
                            if (!(0 < day && day <= 29)){
                                throw new IllegalArgumentException("There must be maximum 29 days for " + strMonth + " in " + year);
                            }
                        }else {
                            if (!(0 < day && day <= 28)){
                                throw new IllegalArgumentException("There must be maximum 28 days for " + strMonth + " in " + year);
                            }
                        }
                }
            }
            return true;

        } catch (NumberFormatException e){
            throw new NumberFormatException("Invalid date format.");
        }
    }

    //if the date is validated, this method is called and arrange the date in a proper manner.
    private void arrangeDate(String strDate){
        String [] arrangedDate = strDate.split("-");
        this.day = Integer.parseInt(arrangedDate[0]);
        this.month = Integer.parseInt(arrangedDate[1]);
        this.year = Integer.parseInt(arrangedDate[2]);
    }

    // This method checks whether it is a leap year or not.
    private static boolean isLeapYear(int year){
        if(year % 4 == 0){
            if (year % 100 == 0){
                if (year % 400 == 0){
                    return true;
                }else{
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static String findMonth(int month){
        String strMonth = "";
        switch (month){
            case 1:
                strMonth = "January";
                break;
            case 2:
                strMonth = "February";
                break;
            case 3:
                strMonth = "March";
                break;
            case 4:
                strMonth = "April";
                break;
            case 5:
                strMonth = "May";
                break;
            case 6:
                strMonth = "June";
                break;
            case 7:
                strMonth = "July";
                break;
            case 8:
                strMonth = "August";
                break;
            case 9:
                strMonth = "September";
                break;
            case 10:
                strMonth = "October";
                break;
            case 11:
                strMonth = "November";
                break;
            case 12:
                strMonth = "December";
                break;
        }

        return strMonth;
    }

    // Returns the date in a proper formatting
    public String displayDate(){
        return String.format("%02d",this.day) + "-" + String.format("%02d",this.month) + "-" + this.year;
    }
}
