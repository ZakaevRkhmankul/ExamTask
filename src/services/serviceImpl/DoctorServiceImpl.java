package services.serviceImpl;

import dao.DoctorDao;
import dao.daoImpl.DoctorDaoImpl;
import models.Doctor;
import models.Hospital;
import models.Patient;
import services.DoctorService;
import services.GenericService;
import services.HospitalService;

import java.util.List;
import java.util.Map;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {

    private final DoctorDaoImpl doctorDao = new DoctorDaoImpl();


    @Override
    public String add(Long hospitalId, Doctor doctor) {
        return doctorDao.add(hospitalId,doctor);
    }

    @Override
    public void removeById(Long id) {
        doctorDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        return doctorDao.updateById(id,doctor);
    }


    @Override
    public Doctor findDoctorById(Long id) {
        return doctorDao.findDoctorById(id);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return doctorDao.assignDoctorToDepartment(departmentId,doctorsId);
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return doctorDao.getAllDoctorsByHospitalId(id);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return null;
    }
}
