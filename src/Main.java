import dao.DepartmentDao;
import dao.DoctorDao;
import dao.daoImpl.DepartmentDaoImpl;
import dao.daoImpl.DoctorDaoImpl;
import database.Database;
import database.GeneriteId;
import enums.Gender;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import services.DoctorService;
import services.serviceImpl.DepartmentServiceImpl;
import services.serviceImpl.DoctorServiceImpl;
import services.serviceImpl.HospitalServiceImpl;
import services.HospitalService;
import services.serviceImpl.PatientServiceImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) throws RuntimeException {


        Scanner scannerForInt = new Scanner(System.in);
        Scanner scannerForLong = new Scanner(System.in);
        Scanner scannerForString = new Scanner(System.in);

        HospitalService hospitalService = new HospitalServiceImpl();
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        Department department = new Department();
        PatientServiceImpl patientService = new PatientServiceImpl();

        while (true) {
                System.out.println("""
                        1. Add hospital
                        2. Find hospital by id
                        3. get all hospitals
                        4. Get all patient from hospital
                        5. Delete hospital by id
                        6. Get all hospital by address
                        7. Add doctor
                        8. Remove doctor
                        9. Update doctor
                        10.Find Doctor by id
                        11.Get all doctors by hospitals id
                        12.Add department
                        13.Find department by name
                        14.Get all department by hospital
                        15.Update department
                        16.Assign doctor to department
                        17.Remove department
                        18.Save patient
                        19.Get patient by id
                        20.Sort patient by age
                        21.Update patient by id
                        22 Add patients to hospital
                        23.Get patient by age   ?
                        24.Remove patient
                        """);
            try {
                int choice = scannerForInt.nextInt();
                if (choice<=0){
                    throw new RuntimeException("Мындай вариант жок");
                }
                scannerForInt.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Оорукананын атын жазыныз: ");
                        String hospitalName = scannerForString.nextLine();

                        System.out.print("Оорукананын адресин жазыныз: ");
                        String hospitalAddress = scannerForString.nextLine();

                        System.out.println(hospitalService.addHospital(new Hospital(GeneriteId.getHospitalId(), hospitalName, hospitalAddress)));
                    }
                    case 2 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long hospitalId = scannerForLong.nextLong();
                        scannerForLong.nextLine();

                        System.out.println(hospitalService.findHospitalById(hospitalId));
                    }
                    case 3 -> {
                        System.out.println("All hospitals:");
                        System.out.println(hospitalService.getAllHospital());
                    }
                    case 4 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long hospitalId = scannerForLong.nextLong();
                        scannerForLong.nextLine();

                        System.out.println(hospitalService.getAllPatientFromHospital(hospitalId));
                    }
                    case 5 -> {
                        System.out.println("Оорукананын id'син жазыныз: ");
                        Long hospitalId = scannerForLong.nextLong();
                        scannerForLong.nextLine();

                        System.out.println(hospitalService.deleteHospitalById(hospitalId));
                    }
                    case 6 -> {
                        System.out.print("Оорукананын дарегин жазыныз: ");
                        String hospitalAddress = scannerForString.nextLine();

                        System.out.println(hospitalService.getAllHospitalByAddress(hospitalAddress));
                    }
                    case 7 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long hospitalId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.print("Даарыгердин атын жазыныз: ");
                        String doctorName = scannerForString.nextLine();
                        System.out.print("Даарыгердин фамилиясын жазыныз: ");
                        String doctorLastName = scannerForString.nextLine();
                        System.out.print("Даарыгердин жынысын жазыныз: ");
                        String gender = scannerForString.nextLine();
                        System.out.print("Даарыгердин жумуштагы опытын жазыныз: ");
                        scannerForInt.nextLine();
                        int experience = scannerForInt.nextInt();
                        if (gender.equalsIgnoreCase("Male")) {
                            System.out.println(doctorService.add(hospitalId, new Doctor(GeneriteId.getDoctorId(), doctorName, doctorLastName, Gender.MALE, experience)));
                        } else if (gender.equalsIgnoreCase("Female")) {
                            System.out.println(doctorService.add(hospitalId, new Doctor(GeneriteId.getDoctorId(), doctorName, doctorLastName, Gender.FEMALE, experience)));
                        } else {
                            throw new RuntimeException("Жынысын туура эмес жазып жатасыз!");
                        }
                    }
                    case 8 -> {
                        System.out.print("Даарыгердин id'син жазыныз: ");
                        Long doctorId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        doctorService.removeById(doctorId);
                    }
                    case 9 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long hospitalId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.print("Даарыгердин атын жазыныз: ");
                        String doctorName = scannerForString.nextLine();
                        System.out.print("Даарыгердин фамилиясын жазыныз: ");
                        String doctorLastName = scannerForString.nextLine();
                        System.out.print("Даарыгердин иш тажрыйбасын жазыныз: ");
                        int experience = scannerForInt.nextInt();
                        scannerForInt.nextLine();

                        System.out.println(doctorService.updateById(hospitalId, new Doctor(GeneriteId.getDoctorId(), doctorName, doctorLastName, Gender.MALE, experience)));
                    }
                    case 10 -> {
                        System.out.print("Даарыгердин id'син жазыныз: ");
                        Long doctorId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.println(doctorService.findDoctorById(doctorId));
                    }
                    case 11 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long hospitalId = scannerForLong.nextLong();
                        scannerForString.nextLine();
                        System.out.println(doctorService.getAllDoctorsByHospitalId(hospitalId));
                    }
                    case 12 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long departmentId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.print("Болумдун атын жазыныз: ");
                        String departmentName = scannerForString.nextLine();

                        System.out.println(departmentService.add(departmentId, new Department(GeneriteId.getDepartmentId(), departmentName, department.getDoctors())));
                    }
                    case 13 -> {
                        System.out.print("Болумдун атын жазыныз: ");
                        String departmentName = scannerForString.nextLine();
                        System.out.println(departmentService.findDepartmentByName(departmentName));
                    }
                    case 14 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long departmentId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.println(departmentService.getAllDepartmentByHospital(departmentId));
                    }
                    case 15 -> {
                        System.out.print("Болумдун id'син жазыныз: ");
                        Long departmentId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.print("Болумго жаны ат жазыныз: ");
                        String departmentName = scannerForString.nextLine();
                        System.out.println(departmentService.updateById(departmentId, new Department(GeneriteId.getDepartmentId(), departmentName, department.getDoctors())));
                    }
                    case 16 -> {
                        System.out.print("Болумдун id'син жазыныз: ");
                        Long departmentId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.print("Doctor  ids  жазыныз: ");
                        List<Long> doctorIds = new ArrayList<>(List.of(scannerForLong.nextLong()));
                        for (int i = 0; i < 3; i++) {

                        }
                        System.out.println(doctorService.assignDoctorToDepartment(departmentId, doctorIds));
                        System.out.println();
                    }
                    case 17 -> {
                        System.out.println("Болумдун id'син жазыныз: ");
                        Long departmentId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        departmentService.removeById(departmentId);
                    }
                    case 18 -> {
                        System.out.print("Оорукананын id'син жазыныз: ");
                        Long hospitalId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.print("Оорулунун атын жазыныз: ");
                        String patientName = scannerForString.nextLine();

                        System.out.print("Фамилиясын жазыныз: ");
                        String patientLastName = scannerForString.nextLine();

                        System.out.print("Жашын жазыныз: ");
                        int patientAge = scannerForInt.nextInt();
                        scannerForInt.nextLine();

                        System.out.print("Жынысын жазыныз: ");
                        String gender = scannerForString.nextLine();

                        if (gender.equalsIgnoreCase("male")) {
                            System.out.println(patientService.add(hospitalId, new Patient(GeneriteId.getPatientId(), patientName, patientLastName, patientAge, Gender.MALE)));
                        } else if (gender.equalsIgnoreCase("female")) {
                            System.out.println(patientService.add(hospitalId, new Patient(GeneriteId.getPatientId(), patientName, patientLastName, patientAge, Gender.FEMALE)));
                        } else {
                            throw new RuntimeException("Жынысын туура эмес жазып жатасыз! ");
                        }
                    }
                    case 19 -> {

                        System.out.print("Оорулуунун id'cин жазыныз: ");
                        Long patientId = scannerForLong.nextLong();
                        scannerForString.nextLine();

                        System.out.println(patientService.getPatientById(patientId));
                    }
                    case 20 -> {

                        System.out.print("Оорулууларды сорттош учун 'asc' же 'desc' создорун жазыныз: ");
                        String ascOrDesc = scannerForString.nextLine();
                        if (ascOrDesc.equalsIgnoreCase("asc")) {
                            System.out.println(patientService.sortPatientsByAge(ascOrDesc));
                        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
                            System.out.println(patientService.sortPatientsByAge(ascOrDesc));
                        } else {
                            throw new RuntimeException("Туура эмес жазып жатасыз!");
                        }
                    }
                    case 21 -> {
                        System.out.print("Оорулуунун id'син жазыныз: ");
                        Long patientId = scannerForLong.nextLong();
                        scannerForLong.nextLine();
                        System.out.print("Оорулуунун атын кайсы атка озгорткунуз келсе,ошол атты жазыныз: ");
                        String patientName = scannerForString.nextLine();
                        System.out.print("Оорулуунун фамилиясын кайсыга озгорткунуз келсе,ошол фамилияны жазыныз: ");
                        String patientLastName = scannerForString.nextLine();
                        System.out.print("Оорулуунун жашын жазыныз: ");
                        int patientAge = scannerForInt.nextInt();
                        scannerForInt.nextLine();
                        System.out.println("Жынысын жазыныз: ");
                        String gender = scannerForString.nextLine();
                        if (gender.equalsIgnoreCase("male")) {
                            System.out.println(patientService.updateById(patientId, new Patient(GeneriteId.getPatientId(), patientName, patientLastName, patientAge, Gender.MALE)));
                        } else if (gender.equalsIgnoreCase("female")) {
                            System.out.println(patientService.updateById(patientId, new Patient(GeneriteId.getPatientId(), patientName, patientLastName, patientAge, Gender.FEMALE)));
                        } else {
                            throw new RuntimeException("Жынысын туура бериниз: ");
                        }
                    }
                    case 22 -> {

                        System.out.print("Оорукананын id'си: ");
                        Long hospitalId = scannerForLong.nextLong();

                        System.out.print("Оорулуунун атын: ");
                        String patientName1 = scannerForString.nextLine();
                        System.out.print("Оорулуунун фамилиясы: ");
                        String patientLastName1 = scannerForString.nextLine();
                        System.out.print("Оорулуунун жашы: ");
                        int patientAge1 = scannerForInt.nextInt();
                        System.out.print("Жынысы: ");
                        String gender1 = scannerForString.nextLine();

                        System.out.print("Оорулуунун атын: ");
                        String patientName2 = scannerForString.nextLine();
                        System.out.print("Оорулуунун фамилиясы: ");
                        String patientLastName2 = scannerForString.nextLine();
                        System.out.print("Оорулуунун жашы: ");
                        int patientAge2 = scannerForInt.nextInt();
                        System.out.print("Жынысы: ");
                        String gender2 = scannerForString.nextLine();

                        System.out.print("Оорулуунун атын: ");
                        String patientName3 = scannerForString.nextLine();
                        System.out.print("Оорулуунун фамилиясы: ");
                        String patientLastName3 = scannerForString.nextLine();
                        System.out.print("Оорулуунун жашы: ");
                        int patientAge3 = scannerForInt.nextInt();
                        System.out.print("Жынысы: ");
                        String gender3 = scannerForString.nextLine();

                        if (gender1.equalsIgnoreCase("male") && gender2.equalsIgnoreCase("male") && gender3.equalsIgnoreCase("male")) {
                            patientService.addPatientsToHospital(hospitalId, new ArrayList<>(Arrays.asList(
                                    new Patient(GeneriteId.getPatientId(), patientName1, patientLastName1, patientAge1, Gender.MALE),
                                    new Patient(GeneriteId.getPatientId(), patientName2, patientLastName2, patientAge2, Gender.MALE),
                                    new Patient(GeneriteId.getPatientId(), patientName3, patientLastName3, patientAge3, Gender.MALE))));
                        } else if (gender1.equalsIgnoreCase("female") && gender2.equalsIgnoreCase("female") && gender3.equalsIgnoreCase("female")) {
                            patientService.addPatientsToHospital(hospitalId, new ArrayList<>(Arrays.asList(
                                    new Patient(GeneriteId.getPatientId(), patientName1, patientLastName1, patientAge1, Gender.FEMALE),
                                    new Patient(GeneriteId.getPatientId(), patientName2, patientLastName2, patientAge2, Gender.FEMALE),
                                    new Patient(GeneriteId.getPatientId(), patientName3, patientLastName3, patientAge3, Gender.FEMALE))));
                        } else if (gender1.equalsIgnoreCase("male") && gender2.equalsIgnoreCase("male") && gender3.equalsIgnoreCase("female")) {
                            patientService.addPatientsToHospital(hospitalId, new ArrayList<>(Arrays.asList(
                                    new Patient(GeneriteId.getPatientId(), patientName1, patientLastName1, patientAge1, Gender.MALE),
                                    new Patient(GeneriteId.getPatientId(), patientName2, patientLastName2, patientAge2, Gender.MALE),
                                    new Patient(GeneriteId.getPatientId(), patientName3, patientLastName3, patientAge3, Gender.FEMALE))));
                        } else if (gender1.equalsIgnoreCase("male") && gender2.equalsIgnoreCase("female") && gender3.equalsIgnoreCase("female")) {
                            patientService.addPatientsToHospital(hospitalId, new ArrayList<>(Arrays.asList(
                                    new Patient(GeneriteId.getPatientId(), patientName1, patientLastName1, patientAge1, Gender.MALE),
                                    new Patient(GeneriteId.getPatientId(), patientName2, patientLastName2, patientAge2, Gender.FEMALE),
                                    new Patient(GeneriteId.getPatientId(), patientName3, patientLastName3, patientAge3, Gender.FEMALE))));
                        } else if (gender1.equalsIgnoreCase("female") && gender2.equalsIgnoreCase("female") && gender3.equalsIgnoreCase("male")) {
                            patientService.addPatientsToHospital(hospitalId, new ArrayList<>(Arrays.asList(
                                    new Patient(GeneriteId.getPatientId(), patientName1, patientLastName1, patientAge1, Gender.FEMALE),
                                    new Patient(GeneriteId.getPatientId(), patientName2, patientLastName2, patientAge2, Gender.FEMALE),
                                    new Patient(GeneriteId.getPatientId(), patientName3, patientLastName3, patientAge3, Gender.MALE))));
                        } else if (gender1.equalsIgnoreCase("female") && gender2.equalsIgnoreCase("male") && gender3.equalsIgnoreCase("male")) {
                            patientService.addPatientsToHospital(hospitalId, new ArrayList<>(Arrays.asList(
                                    new Patient(GeneriteId.getPatientId(), patientName1, patientLastName1, patientAge1, Gender.FEMALE),
                                    new Patient(GeneriteId.getPatientId(), patientName2, patientLastName2, patientAge2, Gender.MALE),
                                    new Patient(GeneriteId.getPatientId(), patientName3, patientLastName3, patientAge3, Gender.MALE))));
                        } else {
                            throw new RuntimeException("Жынысын туура эмес берип жатасыз!");
                        }
                    }
                    case 23 -> {
                        System.out.println(patientService.getPatientByAge(21));
                    }
                    case 24 -> {
                        System.out.print("Оорулуунун id'си: ");
                        Long patientId = scannerForLong.nextLong();
                        patientService.removeById(patientId);
                    }
                }
            }catch (InputMismatchException e){
                System.out.print("Вариант беруу учун сан жазыныз!\n ");
                scannerForInt.nextLine();

            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}