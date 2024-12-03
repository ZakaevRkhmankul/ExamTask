package dao.daoImpl;

import dao.DoctorDao;
import dao.GenericDao;
import dao.HospitalDao;
import database.Database;
import models.Department;
import models.Doctor;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorDaoImpl implements DoctorDao, GenericDao<Doctor> {

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (!hospital.getId().equals(hospitalId)) {
                    throw new RuntimeException("Hospital with id " + hospitalId + " not found!");
                } else {
                    hospital.getDoctors().add(doctor);
                    return "Doctor added!";
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    @Override
    public void removeById(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Doctor doctor : hospital.getDoctors()) {
                    if (doctor.getId().equals(id)) {
                        System.out.println(hospital.getDoctors().remove(doctor));
                    } else {
                        throw new RuntimeException("Doctor with id " + id + " not found!");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Doctor newDoctor) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Doctor oldDoctor : hospital.getDoctors()) {
                    if (oldDoctor.getId().equals(id)) {
                        oldDoctor.setFirstName(newDoctor.getFirstName());
                        oldDoctor.setLastName(newDoctor.getLastName());
                        oldDoctor.setGender(newDoctor.getGender());
                        oldDoctor.setExperienceYear(newDoctor.getExperienceYear());
                    } else {
                        throw new RuntimeException("Doctor with id" + id + "not found!");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    @Override
    public Doctor findDoctorById(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Doctor doctor : hospital.getDoctors()) {
                    if (doctor.getId().equals(id)) {
                        return doctor;
                    } else {
                        throw new RuntimeException("Doctor with id " + id + " not found!");
                    }
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : Database.hospitals){
            for (Department department : hospital.getDepartments()){
                if (department.getId().equals( departmentId)){
                    for (Doctor doctor : hospital.getDoctors()) {
                       if (doctorsId.contains(doctor.getId())){
                           department.getDoctors().add(doctor);
                           return "ийгиликтуу";
                       }
                    }
                }
            }
        }
        return "";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId().equals(id)) {
                    return hospital.getDoctors();
                }else {
                    throw new RuntimeException("Hospital with id " + id + " nor found!");
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
