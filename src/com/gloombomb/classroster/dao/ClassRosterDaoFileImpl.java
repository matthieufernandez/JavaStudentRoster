package com.gloombomb.classroster.dao;

import com.gloombomb.classroster.dto.Student;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class ClassRosterDaoFileImpl implements ClassRosterDao {
    private Map<String, Student> students = new HashMap<>();

    private static final String ROSTER_FILE = "roster.txt";
    private static final String DELIMITER = "::";

    private int studentIdLength = 4;

    private Student unmarshallStudent(String studentAsText) {
        String[] studentTokens = studentAsText.split(DELIMITER);

        String studentId = studentTokens[0];

        Student studentFromFile = new Student(studentId);
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);

        return studentFromFile;
    }

    private void loadRoster() throws ClassRosterDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterDaoException("Could not load roster data :(", e);
        }

        String currentLine;
        Student currentStudent;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);

            students.put(currentStudent.getStudentId(), currentStudent);
        }

        scanner.close();
    }

    private String marshallStudent(Student aStudent) {
        String studentAsText = aStudent.getStudentId() + DELIMITER;
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort();

        return studentAsText;
    }

    private void writeRoster() throws ClassRosterDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassRosterDaoException("Could not save student data :(" + e);
        }

        String studentAsText;

        List<Student> studentList = this.getAllStudents();

        for (Student currentStudent : studentList) {
            studentAsText = marshallStudent(currentStudent);
            out.println(studentAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }

//    public int zeroPad(int num, int width) {
//        String numAsString = Integer.toString(num);
//        while (numAsString.length() < width) {
//            numAsString = "0" + numAsString;
//        }
//
//        return parseInt(numAsString);
//    }
}
