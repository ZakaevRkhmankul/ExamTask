package dao.daoImpl;

import dao.HospitalDao;
import database.Database;
import models.Hospital;
import models.Patient;

import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao {

    @Override
    public String addHospital(Hospital hospital) {
        Database.hospitals.add(hospital);
        return "Hospital successfully saved!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (!hospital.getId().equals(id)) {
                    throw new RuntimeException("Hospital with id " + id + " not found!");
                } else {
                    return hospital;
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return Database.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        for (Hospital h : Database.hospitals) {
            if (h.getId() == id) {
                return h.getPatients();

            }
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (!hospital.getId().equals(id)) {
                    throw new RuntimeException("Hospital with id " + id + " not found!");
                } else {
                    Database.hospitals.remove(hospital);
                    return "Hospital with id " + id + " successfully deleted";
                }
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (!hospital.getAddress().equalsIgnoreCase(address)) {
                    throw new RuntimeException("Hospital with address " + address + " not found!");
                } else {
                    return Map.of(address, hospital);
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
