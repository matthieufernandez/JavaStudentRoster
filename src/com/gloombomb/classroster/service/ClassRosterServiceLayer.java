package com.gloombomb.classroster.service;

import com.gloombomb.classroster.dao.ClassRosterPersistenceException;
import com.gloombomb.classroster.dto.Student;

import java.util.List;

public interface ClassRosterServiceLayer {

    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;

    List<Student> getAllStudents() throws ClassRosterPersistenceException;

    Student getStudent(String studentId) throws ClassRosterPersistenceException;

    Student removeStudent(String studentId) throws ClassRosterPersistenceException;
}
