package com.gloombomb.classroster.ui;

import com.gloombomb.classroster.dto.Student;

import java.util.List;

public class ClassRosterView {

    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select an option.", 1, 5);
    }

    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }

    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                    currentStudent.getStudentId(),
                    currentStudent.getFirstName(),
                    currentStudent.getLastName());

            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public String getStudentIdChoice() {
        return io.readString("Please enter student id.");
    }

    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print(" ");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveResult (Student studentRecord) {
        if(studentRecord != null) {
            io.print("Student successfully removed!");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }



    public void displayCreateStudentBanner() {
        io.print("===CREATE STUDENT===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("Student successfully created. Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display a Student ===");
    }

    public void displayRemoveStudentBanner () {
        io.print("=== Remove Student ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("!!! UNKNOWN COMMAND !!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayRemoveStudentSuccessMessage() {
        io.print("Successfully removed student.");
    }
}
