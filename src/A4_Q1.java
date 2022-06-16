// ----------------------------------------------
// Assignment 4
// Written by: Hichem Sehabi 40212336
// For COMP 248 Section C – Winter 2022
// ----------------------------------------------

/*
This program is meant to be a classroom management system. It gives the ability to the user to create a classroom object
which will hold information such as the instructors information, the students register to the classroom and their grades.
The user can add student, remove students, get a student's index, upgrade their grades, search students, compute their grade/score,
view their report card and view the class report card. The program keeps track of the number of students currently enrolled and checks whether 
it's full or not before allowing new students to be enrolled. 
*/

import java.util.Scanner;

public class A4_Q1 {
    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);   //create user input scanner object 
        String fname, lname, room, year;
        int size = 0, studId = 0, arrIdx = 0;   //size is the classroom maximum size, studID is the student ID of the user on which the operation is performed, arrIdx is the number of students currently enrolled
        byte code = -1;                         //task code 

        System.out.println("Welcome to the Simple Classroom Management System");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Enter instructor's particulars (FirstName, LastName, LectureRoom, Semester, MaxClassSize) as a single-line entry:");

        //take new object parameters from user input
        fname = keyIn.next();
        lname = keyIn.next();
        room = keyIn.next();
        year = keyIn.next();

        //take maximum class size from input while checking if its a valid integer.
        try {
          size = keyIn.nextInt();  
        }
         catch(Exception e) { 
            System.out.println("Error: Your input/entry for 'MaxClassSize' is NOT a valid integer. Kindly try again!");
            System.exit(0);
         }

        Comp248secU class1 = new Comp248secU(fname, lname, room, year, size);       //create new classroom object

        System.out.println("\nCode => Description");        //print menu codes description list 
        System.out.println("--------------------");
        System.out.print("103 => Enrol New Student"     
        + "\n106 => Find Student Position in Class List"
        + "\n109 => Rretrieve Student's Information"
        + "\n112 => Unenrol Student"
        + "\n115 => Update Student's Particulars"
        + "\n118 => Update Assignment Scores"
        + "\n121 => Update Other Scores"
        + "\n124 => Display Student's Report Card"
        + "\n127 => Display Class Report Card"
        + "\n0 ===> Exit"
        + "\n\nPlease enter a Code, from the aforementioned, that correspond to your task: "
        );

        //from this point, the code is inside a loop since it needs to be able to reprompt for a new task code after each operation
        while(true){
            
            try{
                code = keyIn.nextByte();        //input task code while checking if its a valid byte, catch error and exit program otherwise
            } catch(Exception e) {
                System.out.println("Error: Your input/entry is NOT a valid integer between -128 to 127. kindly retry again!");
                System.exit(0);
            } 

            if(code != 0 && (code < 103 || ((int)code-100)%3 != 0)) {           //check if the task code is in the list from above
                System.out.println("Thank you for patronizing our Simple Classroom Management System.");
                System.exit(0);             //exit the program if not valid
            }

            //compare entered code with codes present in the list to perform the correct actions
            switch(code) {
                case 103:   //add student 
                    System.out.println("\nEnrolling New Student...");
                    System.out.println("------------------------");
                    System.out.println("Enter student's particulars (FirstName, LastName, StudentID) as a single-line entry:");

                    //input new student info
                    fname = keyIn.next();       
                    lname = keyIn.next();
                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }
                    
                    //check if classroom is full
                    if(arrIdx >= size) {
                        System.out.println("Student with ID: " + studId + " CANNOT be added. Class is already full.");
                        System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                        continue;    
                    }

                    //call addStudent method
                    class1.addStudent(fname, lname, studId, arrIdx);
                    arrIdx++;       //arrIdx gets incremented each times a new student is added to keep track of how many students are in the class
                    System.out.println("Student with ID: " + studId + " added successfully.");
                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;       //sends the program back to the beginning of the loop, ready to recieve another task code input from the user
                case 106:   //get student index 
                    System.out.println("\nFInding Student's Position in Class List...");
                    System.out.println("-------------------------------------------");
                    System.out.println("Enter StudentID:");

                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }

                    int studidx = class1.getStudentIdx(studId);

                    if(studidx == -1) {
                        System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                        continue;
                    }
                    System.out.println("The position of student with ID: " + studId + ", in the class list, is: " + studidx);
                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                case 109:       //get student info
                    System.out.println(
                        "\nRetrieving Student's Information..." +
                        "\n-----------------------------------" + 
                        "\nEnter StudentID:"
                    );

                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }

                    String[] studInfo = class1.getStudentInfo(studId);

                    if(studInfo[0] == null) {
                        System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                        continue;
                    }

                    System.out.println(
                        "\nSudent's First Name = " + studInfo[0] + 
                        "\nStudent's Last Name = " + studInfo[1] + 
                        "\nStudent's ID = " + studInfo[2] + 
                        "\nScore in Assignment 1 = " + studInfo[3] + 
                        "\nScore in Assigmnent 2 = " + studInfo[4] +
                        "\nScore in Assignment 3 = " + studInfo[5] + 
                        "\nScore in Assignment 4 = " + studInfo[6] +
                        "\nCummulative Score in Labs = " + studInfo[7] + 
                        "\nMid-Term Test Score = " + studInfo[8] + 
                        "\nFinal Examination Score = " + studInfo[9]
                        );
 
                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                case 112: //delete student
                    System.out.println("\nUnenrolling New Student...\n------------------------\nEnter studentID:");
                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }

                    if(class1.delStudent(studId) == 1) {    //if task succeded, display success message
                        System.out.println("Successfully removed student with ID:" + studId);
                    }
                    
                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                case 115: //update student part
                    System.out.println(
                        "\nUpdating Student's Particulars..." + 
                        "\n---------------------------------" +
                        "\nEnter update to student's particulars (FirstName, LastName, StudentID) as a single-line entry:"
                    );

                    fname = keyIn.next();
                    lname = keyIn.next();
                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }
                
                    if(class1.updateStudentPart(fname, lname, studId) == 1) {
                        System.out.println("Successfully updated identification particulars for Student with ID: " + studId);
                    } else {
                        System.out.println("Unable to retrieve information for Student with ID: " + studId);
                    }

                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                case 118: //update assignment scores
                    System.out.println(
                        "\nUpdating Assignment Scores..." +
                        "\n-----------------------------" +
                        "\nEnter update to student's Assignment scores (Assignment1, Assignment2, Assignment3, Assignment4, StudentID) as a single-line entry:"
                    );

                    int ass1 = keyIn.nextInt();
                    int ass2 = keyIn.nextInt();
                    int ass3 = keyIn.nextInt();
                    int ass4 = keyIn.nextInt();
                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }

                    if(class1.updateAssgtScore(ass1, ass2, ass3, ass4, studId) == 1) {
                        System.out.println("Successfully updated Assignments' scores for Student with ID: " + studId);
                    } else {
                        System.out.println("Unable to retrieve information for Student with ID: " + studId);
                    }

                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                case 121:   //update other scores
                    System.out.println(
                        "\nUpdating Other Scores..." +
                        "\n-----------------------------" +
                        "\nEnter update to student's other scores (CummulativeLabs, MidtTerm, FinalExam, StudentID) as a single-line entry:"
                    );

                    int cummulativeLabs = keyIn.nextInt();
                    int midtTerm = keyIn.nextInt();
                    int finalExam = keyIn.nextInt();
                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }

                    if(class1.updateOtherScore(cummulativeLabs, midtTerm, finalExam, studId) != 0) {
                        System.out.println("Successfully updated Cummulative Labs, Mid-Term Test and Final Examination scores for Student with ID: " + studId);
                    } else {
                        System.out.println("Unable to retrieve information for Student with ID: " + studId);
                    }

                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                case 124: //get student info, score and grade
                    System.out.println(
                        "\nRetrieving Student's Information..." + 
                        "\n-----------------------------------" + 
                        "\nEnter StudentID:"
                    );

                    try{
                        studId = keyIn.nextInt();
                    } catch(Exception e) {
                        System.out.println("Error: Your input/entry is NOT a valid integer. kindly retry again!");
                        System.exit(0);
                    }

                    String[] studInfo1 = class1.getStudentInfo(studId); //student info is stored in string array

                    if(studInfo1[0] == null) {
                        System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                        continue;
                    }

                    double wgtScore = class1.computeWeightScore(studId);       //student weighted score

                    System.out.print(                                       //student info array is displayed
                        "\nSudent's First Name = " + studInfo1[0] + 
                        "\nStudent's Last Name = " + studInfo1[1] + 
                        "\nStudent's ID = " + studInfo1[2] + 
                        "\nScore in Assignment 1 = " + studInfo1[3] + 
                        "\nScore in Assigmnent 2 = " + studInfo1[4] +
                        "\nScore in Assignment 3 = " + studInfo1[5] + 
                        "\nScore in Assignment 4 = " + studInfo1[6] +
                        "\nCummulative Score in Labs = " + studInfo1[7] + 
                        "\nMid-Term Test Score = " + studInfo1[8] + 
                        "\nFinal Examination Score = " + studInfo1[9] +
                        "\n-----------------------------------------" + 
                        "\nStudent's Cummulative Weighted Score = "
                     );
                    System.out.printf("%.2f", wgtScore);        //print weighted score rounded with two decimal places
                    System.out.println("\nStudent's Final Letter Grade = " + Comp248secU.computeGrade(wgtScore));   //final grade

                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                
                case 127:       //class report card
                    class1.classReportCard();
                    System.out.println("Kindly continue by entering a Code, from the menu above, that corresponds to your task:");
                    continue;
                case 0:     //exit program
                    System.out.println("Thank you for using the Simple Classroom Management System");
                    System.exit(0);
            }   
        }
    }
}
