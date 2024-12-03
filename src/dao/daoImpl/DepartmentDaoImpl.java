package dao.daoImpl;

import dao.DepartmentDao;
import dao.GenericDao;
import database.Database;
import models.Department;
import models.Hospital;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId() != id) {
                    throw new RuntimeException(id + " айдисиндеги больница жок!");
                } else {
                    return hospital.getDepartments();
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Department department : hospital.getDepartments()) {
                    if (department.getDepartmentName().equalsIgnoreCase(name)) {
                        return department;
                    } else {
                        throw new RuntimeException("Болумду туура эмес берип жатасыз!");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (!hospital.getId().equals(hospitalId)) {
                    throw new RuntimeException("Hospital with id " + hospitalId + " not found!");
                } else {
                    hospital.getDepartments().add(department);
                    return "Department added!";
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
                for (Department department : hospital.getDepartments()) {
                    if (department.getId() == id) {
                        hospital.getDepartments().remove(department);
                        System.out.println("Болум ийгиликтуу очурулду!");
                    }else {
                        throw new RuntimeException(id + " айдисинде болум катталган эмес!");
                    }
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Department newDepartment) {
        try {


            for (Hospital hospital : Database.hospitals) {
                for (Department oldDepartment : hospital.getDepartments()) {
                    if (oldDepartment.getId() == id) {
                        oldDepartment.setDepartmentName(newDepartment.getDepartmentName());
                        System.out.println("Болум ийгиликтуу озгортулду!");
                    } else {
                        throw new RuntimeException("Болум табылган жок!");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
