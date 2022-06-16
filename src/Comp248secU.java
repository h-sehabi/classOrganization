public class Comp248secU {
    private static int classSize, idx, studNumber = 0;
    private static String semesterYear, lectureRoom, instructorFname, instructorLname;
    private String[] studFname, studLname;
    private int[] studID;
    private double[] assgt1, assgt2, assgt3, assgt4, labs, midTerm, finalExam;

    public Comp248secU(String fname, String lname, String room, String semYr, int size) {   //constructor method gets execuded each time a new Comp248secU object is created
        //initializes variables with classroom info
        instructorFname = fname;
        instructorLname = lname;
        lectureRoom = room;
        semesterYear = semYr;

        if(size < 0) {      //check if size is a positive integer
            System.out.println("Error: Your input/entry for 'MaxClassSize' is NOT a valid integer. Kindly try again!");
            System.exit(0);
        }    
        classSize = size;       //initialize classSize with the desired maximum size

        //initialize arrays with a size of the maximum class size
        studFname = new String[classSize];
        studLname = new String[classSize];
        studID = new int[classSize];
        assgt1 = new double[classSize];
        assgt2 = new double[classSize];
        assgt3 = new double[classSize];
        assgt4 = new double[classSize];
        labs = new double[classSize];
        midTerm = new double[classSize];
        finalExam = new double[classSize];
    }

    public void addStudent(String fname, String lname, int studId, int arrIdx) {
        studNumber++;   //increment student counter
        studFname[arrIdx] = fname;  //update arrays with student info
        studLname[arrIdx] = lname;
        studID[arrIdx] = studId;
    }

    public int getStudentIdx(int studId) {
        for(int idx = 0; idx < classSize; idx++ ) {     //search through each studID array elements until a match with the desired studID is found and return index of the match
            if(studID[idx] == studId) return idx;
        }
        System.out.println("Student with ID: " + studId + " does NOT exist");   
        return -1;  //return -1 if no match is found
    }

    public String[] getStudentInfo(int studId) {
        idx = getStudentIdx(studId);
        if(idx != -1) {     //if a match is student id match is found, create string array to hold student info to get passed to driver class
            String[] studInfo = {
                studFname[idx],
                studLname[idx],
                studID[idx]+"",
                assgt1[idx]+"",
                assgt2[idx]+"",
                assgt3[idx]+"",
                assgt4[idx]+"",
                labs[idx]+"",
                midTerm[idx]+"",
                finalExam[idx]+""
            };

            return studInfo;
        }
        System.out.println("Unable to retrieve information for Student with ID: " + studId);    //no student ID match is found
        String[] studInfo = {};
        return studInfo;    //return empty array
    }

    public int delStudent(int studId) {
        idx = getStudentIdx(studId);

        if(idx == -1) return 0;    //return 0 if failed

        studFname[idx] = null; //clear array entries of the desired user
        studLname[idx] = null;
        studID[idx] = 0;

        studNumber--;   //decrement student counter

        return 1; //return 1 if successful operation
    }

    public int updateStudentPart (String fname, String lname, int studId) {
        idx = getStudentIdx(studId);

        if(idx == -1) return 0;

        studFname[idx] = fname; 
        studLname[idx] = lname;

        return 1;
    }

    public int updateAssgtScore(double a1, double a2, double a3, double a4, int studId) {
        idx = getStudentIdx(studId);

        if(idx == -1) return 0;

        assgt1[idx] = a1;       //update student scores in arrays at the index associated to the student ID
        assgt2[idx] = a2;
        assgt3[idx] = a3;
        assgt4[idx] = a4;

        return 1;
    }

    public int updateOtherScore(double lab, double test, double exam, int studId) {
        idx = getStudentIdx(studId);

        if(idx == -1) return 0;

        labs[idx] = lab;
        midTerm[idx] = test;
        finalExam[idx] = exam;

        return 1;
    }

    public double computeWeightScore(int studId) {
        idx = getStudentIdx(studId);

        //calculate the weight score from the student's grades
        double weightScore = ((assgt1[idx]/20.0)*0.02 + (assgt2[idx]/20.0)*0.03 + (assgt3[idx]/20.0)*0.05 + (assgt4[idx]/20.0)*0.08 + (labs[idx]/12.0)*0.12 + (midTerm[idx]/30.0)*0.3 + (finalExam[idx]/40.0)*0.4)*100.0;
        return weightScore;
    }

    public static char computeGrade(double wgtScore) {
        char grade;
        
        //match the weight score to a grade according to a chart
        if(wgtScore >= 88) grade = 'A';
        else if(wgtScore >= 80) grade = 'B';
        else if(wgtScore >= 67) grade = 'C';
        else if(wgtScore >= 60) grade = 'D';
        else grade = 'F';

        return grade;
    }

    public static double findMin(double[] dataArr) {
        double temp = 100;

        /*loop goes through each value of the passed on array, if the current value is smaller then the predefined 
        tem value (100), the temp value gets replaced with said value which becomes the new current minimum. this procedures 
        happens for every element in the array resulting in the smalled value of the array being stored in temp which is the array's 
        smalled value
        */
        for(idx = 0; idx < studNumber; idx++) { 
            if(dataArr[idx] < temp) temp = dataArr[idx];
        }

        return temp;
    }

    public static double findMax(double[] dataArr) {
        double temp = 0;

        for(idx = 0; idx < studNumber; idx++) {     //same principle applied to findMin method applies here to find the greatest value
            if(dataArr[idx] > temp) temp = dataArr[idx];
        }

        return temp;
    }

    public static double findAvg(double[] dataArr) {
        double sum = 0;
        
        for(idx = 0; idx < studNumber; idx++) { //each element of the array gets added to a sum variable
            sum += dataArr[idx];
        }

        return sum/(double)studNumber;      //sum variable is divided by the number of students currently enrolled to find the class average
    }

    public double[] getClassMin() {
        double[] min = new double[7];       

        min[0] = findMin(assgt1);
        min[1] = findMin(assgt2);
        min[2] = findMin(assgt3);
        min[3] = findMin(assgt4);
        min[4] = findMin(labs);
        min[5] = findMin(midTerm);
        min[6] = findMin(finalExam);

        return min;         //return array with minimum value of each grade array
    }

    public double[] getClassMax() {
        double[] max = new double[7];

        max[0] = findMax(assgt1);
        max[1] = findMax(assgt2);
        max[2] = findMax(assgt3);
        max[3] = findMax(assgt4);
        max[4] = findMax(labs);
        max[5] = findMax(midTerm);
        max[6] = findMax(finalExam);

        return max; //return array with maximum value of each grade array
    }

    public double[] getClassAvg() {
        double[] avg = new double[7];
        
        avg[0] = findAvg(assgt1);
        avg[1] = findAvg(assgt2);
        avg[2] = findAvg(assgt3);
        avg[3] = findAvg(assgt4);
        avg[4] = findAvg(labs);
        avg[5] = findAvg(midTerm);
        avg[6] = findAvg(finalExam);

        return avg; //return array with average value of each grade array
    }

    public void classReportCard() {
        double[] min = getClassMin();
        double[] max = getClassMax();
        double[] avg = getClassAvg();

        System.out.println(
            "Displaying Class Report Card..." + 
            "\n------------------------------" +
            "\n-----------------------------------------------------------------------------------------------------------------" +
            "\nFirst Name\tLast Name\tStud. ID\tA1\tA2\tA3\tA4\tLabs\tTest\tExam\tWgt.\t*" +
            "\n-----------------------------------------------------------------------------------------------------------------" 
        );

        for(idx = 0; idx < studNumber; idx++) {     //print every students grades
            double wgt = computeWeightScore(studID[idx]);
            System.out.print(
                studFname[idx] + 
                "\t\t" + studLname[idx] + 
                "\t\t" + studID[idx] + 
                "\t" + assgt1[idx] +
                "\t" + assgt2[idx] +
                "\t" + assgt3[idx] +
                "\t" + assgt4[idx] +
                "\t" + labs[idx] +
                "\t" + midTerm[idx] +
                "\t" + finalExam[idx] + "\t"
            );
            System.out.printf("%.2f", + wgt);
            System.out.println("\t" + computeGrade(wgt)); 
        
        }

        System.out.print(
            "\n-----------------------------------------------------------------------------------------------------------------" +
            "\n\t\tMinimum Score in Class:\t\t"
        );

        for(int i = 0; i<7; i++) {      //print each min value
            System.out.printf("%.2f\t", min[i]);
        }

        System.out.print("\n\t\tMaximum Score in Class:\t\t");

        for(int i = 0; i<7; i++) {      //print each max value
            System.out.printf("%.2f\t", max[i]);
        }

        System.out.print("\n\t\tAverage Score in Class:\t\t");

        for(int i = 0; i<7; i++) {      //print each average value
            System.out.printf("%.2f\t", avg[i]);
        }

        System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
    }
    
}
