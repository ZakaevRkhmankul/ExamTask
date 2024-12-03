package dao.daoImpl;

import dao.GenericDao;
import dao.PatientDao;
import database.Database;
import models.Hospital;
import models.Patient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PatientDaoImpl implements PatientDao, GenericDao<Patient> {
    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId() == hospitalId) {
                    hospital.getPatients().add(patient);
                    return "Оорулуу катталды!";
                } else {
                    throw new RuntimeException(hospitalId + " айдисиндеги оорукана жок!");
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
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getId() == id) {
                        System.out.println(hospital.getPatients().remove(patient));
                    }else {
                        throw new RuntimeException("Айдиси "+id+" болгон оорулуу табылган жок");
                    }
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Patient newpPatient) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getId() == id) {
                        patient.setFirstName(newpPatient.getFirstName());
                        patient.setLastName(newpPatient.getLastName());
                        patient.setAge(newpPatient.getAge());
                        return "Оорулуу тууралуу маалыматтар ийгиликтуу озгортулду";
                    } else {
                        throw new RuntimeException("Айдиси " + id + " болгон оорулуу табылбады");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId() == id) {
                    hospital.getPatients().addAll(patients);
                } else {
                    throw new RuntimeException("Айдиси " + id + " болгон оорукана табылган жок");
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getId() == id) {
                        return patient;
                    } else {
                        throw new RuntimeException("Айдиси " + id + " болгон оорулуу катталган эмес");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, Patient> getPatientByAge(int age) {

        for (Hospital hospital : Database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getAge() == age){
                    return Map.of(age,patient);
                }
            }
        }
        return Map.of();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    hospital.getPatients().sort(Comparator.comparing(Patient::getAge));
                    if (ascOrDesc.equalsIgnoreCase("asc")) {
                        return hospital.getPatients();
                    } else if (ascOrDesc.equalsIgnoreCase("desc")) {
                        return hospital.getPatients().reversed();
                    } else {
                        throw new RuntimeException("asc же descтен башканы тандоого болбойт!");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }
}
