
import java.util.Scanner;
//Name: Jeremy Rogers
//Course: Programming Fundamentals II
//Professor: Inetha Sheffield
public class FinalExamProgram { //public class for Final Exam Program
    public static void main(String[] args) {

        //Delimiter to limit the exception errors
        Scanner input = new Scanner(System.in).useDelimiter("\n");;

        //prompt user for number of courses
        System.out.println("Enter the number of courses: ");
        int numCourses = input.nextInt();

        //for loop from prompt user for number of desired courses
        for (int i = 1; i <= numCourses; i++) {
            System.out.println("Enter the name of course " + i + ": ");
            String courseName = input.next();
            System.out.println("Enter the section for course " + i + ": ");
            String section = input.next();
            System.out.println("Enter the number of students in " + courseName + ": ");
            int numStudents = input.nextInt();

            //student names and grades
            String[] studentNames = new String[numStudents];
            int[] quizGrades = new int[numStudents];
            int[] testGrades = new int[numStudents];
            int[] programGrades = new int[numStudents];
            int[] projectGrades = new int[numStudents];

            //for loop for desired number of students
            for (int j = 0; j < numStudents; j++) {
                System.out.println("Enter the first and last name of student " + (j+1) + " in " + courseName + ": ");
                String firstName = input.next();
                String lastName = input.next();
                studentNames[j] = firstName + " " + lastName;

                System.out.println("Enter " + firstName + "'s quiz grade in " + courseName + ": ");
                quizGrades[j] = input.nextInt();
                System.out.println("Enter " + firstName + "'s test grade in " + courseName + ": ");
                testGrades[j] = input.nextInt();
                System.out.println("Enter " + firstName + "'s program grade in " + courseName + ": ");
                programGrades[j] = input.nextInt();
                System.out.println("Enter " + firstName + "'s project grade in " + courseName + ": ");
                projectGrades[j]  = input.nextInt();
            }

            //printing course names
            System.out.println(courseName + " Grades:");
            double[] studentAverages = calculateAverages(quizGrades, testGrades, programGrades, projectGrades);
            char[] studentGrades = calculateLetterGrades(studentAverages);
            int aboveSeventy = calculateAboveSeventy(studentAverages);
            double courseAverage = calculateCourseAverage(studentAverages);
            double highestAverage = calculateHighestAverage(studentAverages);
            double lowestAverage = calculateLowestAverage(studentAverages);

            for (int j = 0; j < numStudents; j++) {
                double scholarshipAmount = scholarships(studentAverages[j]);

                System.out.println(studentNames[j] + ": " + String.format("%.2f", studentAverages[j]) + " (" + studentGrades[j] + ") " + "$" + String.format("%.2f", scholarshipAmount));
            }

            System.out.println("Extra Credit: ");
            for (int j = 0; j < numStudents; j++) {
                double newAverage = studentAverages[j] + input.nextDouble();
                if (newAverage > 100) {
                    newAverage = 100;
                }
                double scholarshipAmount = scholarships(newAverage);
                System.out.println(studentNames[j] + ": " + String.format("%.2f", newAverage) + " (" + calculateNewLetterGrade(newAverage) + ") " + "$" + String.format("%.2f", scholarshipAmount));
            }

            System.out.println("Course Average: " + String.format("%.2f", courseAverage));
            System.out.println("Num Students Above 70: " + aboveSeventy);
            System.out.println("Lowest Student Average: " + String.format("%.2f", lowestAverage));
            System.out.println("Highest Student Average: " + String.format("%.2f", highestAverage));
        }
    }


    public static double[] calculateAverages(int[] quizGrades, int[] testGrades, int[] programGrades, int[] projectGrades) {
        //method for calculating grade average
        double[] averages = new double[quizGrades.length];
        for (int i = 0; i < averages.length; i++) {
            double quizPercent = (double) quizGrades[i] * 0.15;
            double testPercent = (double) testGrades[i] * 0.20;
            double programPercent = (double) programGrades[i] * 0.25;
            double projectPercent = (double) projectGrades[i] * 0.40;

            double studentAverage = quizPercent + testPercent + programPercent + projectPercent;
            averages[i] = studentAverage;
        }
        return averages;
    }

    public static char[] calculateLetterGrades(double[] studentAverages) {
        //method for determining student letter grade
        char[] grades = new char[studentAverages.length];
        for (int i = 0; i < grades.length; i++) {
            if (studentAverages[i] >= 90) {
                grades[i] = 'A';
            } else if (studentAverages[i] >= 80) {
                grades[i] = 'B';
            } else if (studentAverages[i] >= 70) {
                grades[i] = 'C';
            } else if (studentAverages[i] >= 60) {
                grades[i] = 'D';
            } else {
                grades[i] = 'F';
            }
        }
        return grades;
    }

    public static char calculateNewLetterGrade(double newAverage) {
        //calculation of new letter grade
        if (newAverage >= 90) {
            return 'A';
        } else if (newAverage >= 80) {
            return 'B';
        } else if (newAverage >= 70) {
            return 'C';
        } else if (newAverage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static int calculateAboveSeventy(double[] studentAverages) {
        //method for students that made a 70 or above
        int count = 0;
        for (int i = 0; i < studentAverages.length; i++) {
            if (studentAverages[i] >= 70) {
                count++;
            }
        }
        return count;
    }

    public static double calculateCourseAverage(double[] studentAverages) {
        //the calculated average of all students in the course
        double total = 0;
        for (int i = 0; i < studentAverages.length; i++) {
            total += studentAverages[i];
        }
        return total / studentAverages.length;
    }

    public static double calculateHighestAverage(double[] studentAverages) {
        //method for determining the highest student average
        double highest = Double.MIN_VALUE;
        for (int i = 0; i < studentAverages.length; i++) {
            if (studentAverages[i] > highest) {
                highest = studentAverages[i];
            }
        }
        return highest;
    }

    public static double calculateLowestAverage(double[] studentAverages) {
        //method for determining the lowest student average
        double lowest = Double.MAX_VALUE;
        for (int i = 0; i < studentAverages.length; i++) {
            if (studentAverages[i] < lowest) {
                lowest = studentAverages[i];
            }
        }
        return lowest;
    }

    public static double scholarships(double studentAverage) {
        //method for calculating the scholarships
        if (studentAverage >= 95) {
            return 750.0;
        } else if (studentAverage >= 90) {
            return 500.0;
        } else if (studentAverage >= 85) {
            return 250.0;
        } else {
            return 0.0;
        }
    }
}


