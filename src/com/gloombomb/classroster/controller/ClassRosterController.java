package com.gloombomb.classroster.controller;

import com.gloombomb.classroster.dao.ClassRosterDao;
import com.gloombomb.classroster.dao.ClassRosterDaoFileImpl;
import com.gloombomb.classroster.dto.Student;
import com.gloombomb.classroster.ui.ClassRosterView;
import com.gloombomb.classroster.ui.UserIO;
import com.gloombomb.classroster.ui.UserIOConsoleImpl;

import java.util.List;

public class ClassRosterController {
    private ClassRosterView view = new ClassRosterView();
    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    io.print("REMOVE STUDENT");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
        }
        io.print("GOOD BYE");
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() {
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();

    }

    private void listStudents() {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
}
