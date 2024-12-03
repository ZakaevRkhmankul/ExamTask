package services.serviceImpl;

import dao.HospitalDao;
import dao.daoImpl.HospitalDaoImpl;
import models.Hospital;
import models.Patient;
import services.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService{

    HospitalDao hospitalDao = new HospitalDaoImpl();

    @Override
    public String addHospital(Hospital hospital) {
        return hospitalDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDao.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {

         return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalDao.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitalDao.getAllHospitalByAddress(address);
    }
}
