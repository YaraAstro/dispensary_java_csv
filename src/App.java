import java.util.ArrayList;

public class App {

    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Medicine> medicines = new ArrayList<>();
    private static ArrayList<Payment> payments = new ArrayList<>();
    private static ArrayList<Prescription> prescriptions = new ArrayList<>();

    // load patients from csv file
    private static void loadPatients(String file_path) {
        patients = (ArrayList<Patient>) Patient.loadPatients(file_path);
        System.out.println(patients == null ? "Failed to load patients" : "Patients loaded successfully");
    }

    // load doctors from csv file
    private static void loadDoctors(String file_path) {
        doctors = (ArrayList<Doctor>) Doctor.loadDoctors(file_path);
        System.out.println(doctors == null ? "Failed to load doctors" : "Doctors loaded successfully");
    }

    // load medicines from csv file
    private static void loadMedicines(String file_path) {
        medicines = (ArrayList<Medicine>) Medicine.loadMedicines(file_path);
        System.out.println(medicines == null ? "Failed to load medicines" : "Medicines loaded successfully");
    }

    // load payments from csv file
    private static void loadPayments(String file_path) {
        payments = (ArrayList<Payment>) Payment.loadPayments(file_path);
        System.out.println(payments == null ? "Failed to load payments" : "Payments loaded successfully");
    }

    // load prescriptions from csv file
    private static void loadPrescriptions(String file_path) {
        prescriptions = (ArrayList<Prescription>) Prescription.loadPrescriptions(file_path);
        System.out.println(prescriptions == null ? "Failed to load prescriptions" : "Prescriptions loaded successfully");
    }

    public static void main(String[] args) throws Exception {
        loadPatients("data/patients.csv");
        loadDoctors("data/doctors.csv");
        loadMedicines("data/medicines.csv");
        loadPayments("data/payments.csv");
        loadPrescriptions("data/prescriptions.csv");

        mainMenu();
    }

    private static void mainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println(getMainMenuString());
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    patientManagement();
                    break;
                case 2:
                    doctorManagement();
                    break;
                case 3:
                    medicineManagement();
                    break;
                case 4:
                    prescriptionManagement();
                    break;
                case 5:
                    paymentManagement();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the Dispensary Management System. \nGoodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
    private static void medicineManagement() {
        System.out.println(getMedicineManagementString());
        // TODO: Implement medicine management logic
    }

    private static void doctorManagement() {
        System.out.println(getDoctorManagementString());
        // TODO: Implement doctor management logic
    }

    private static void patientManagement() {
        System.out.println(getPatientManagementString());
        // TODO: Implement patient management logic
    }

    private static void prescriptionManagement() {
        System.out.println(getPrescriptionManagementString());
        // TODO: Implement prescription management logic
    }

    private static void paymentManagement() {
        System.out.println(getPaymentManagementString());
        // TODO: Implement payment management logic
    }
    
    private static String getMainMenuString() {
        return "\n╔════════════════════════════════════════╗\n" +
               "║     Dispensary Management System       ║\n" +
               "╠════════════════════════════════════════╣\n" +
               "║  1. Patient Management                 ║\n" +
               "║  2. Doctor Management                  ║\n" +
               "║  3. Medicine Management                ║\n" +
               "║  4. Prescription Management            ║\n" +
               "║  5. Payment Management                 ║\n" +
               "║  6. Exit                               ║\n" +
               "╚════════════════════════════════════════╝";
    }
    
    private static String getMedicineManagementString() {
        return "\n╔════════════════════════════════════════╗\n" +
               "║        Medicine Management             ║\n" +
               "╠════════════════════════════════════════╣\n" +
               "║  1. Add New Medicine                   ║\n" +
               "║  2. Update Medicine                    ║\n" +
               "║  3. Delete Medicine                    ║\n" +
               "║  4. View All Medicines                 ║\n" +
               "║  5. Search Medicine                    ║\n" +
               "║  6. Back to Main Menu                  ║\n" +
               "╚════════════════════════════════════════╝";
    }

    private static String getDoctorManagementString() {
        return "\n╔════════════════════════════════════════╗\n" +
               "║         Doctor Management              ║\n" +
               "╠════════════════════════════════════════╣\n" +
               "║  1. Add New Doctor                     ║\n" +
               "║  2. Update Doctor                      ║\n" +
               "║  3. Delete Doctor                      ║\n" +
               "║  4. View All Doctors                   ║\n" +
               "║  5. Search Doctor                      ║\n" +
               "║  6. Back to Main Menu                  ║\n" +
               "╚════════════════════════════════════════╝";
    }

    private static String getPatientManagementString() {
        return "\n╔════════════════════════════════════════╗\n" +
               "║         Patient Management             ║\n" +
               "╠════════════════════════════════════════╣\n" +
               "║  1. Add New Patient                    ║\n" +
               "║  2. Update Patient                     ║\n" +
               "║  3. Delete Patient                     ║\n" +
               "║  4. View All Patients                  ║\n" +
               "║  5. Search Patient                     ║\n" +
               "║  6. Back to Main Menu                  ║\n" +
               "╚════════════════════════════════════════╝";
    }

    private static String getPrescriptionManagementString() {
        return "\n╔════════════════════════════════════════╗\n" +
               "║      Prescription Management           ║\n" +
               "╠════════════════════════════════════════╣\n" +
               "║  1. Create New Prescription            ║\n" +
               "║  2. Update Prescription                ║\n" +
               "║  3. Delete Prescription                ║\n" +
               "║  4. View All Prescriptions             ║\n" +
               "║  5. Search Prescription                ║\n" +
               "║  6. Back to Main Menu                  ║\n" +
               "╚════════════════════════════════════════╝";
    }

    private static String getPaymentManagementString() {
        return "\n╔════════════════════════════════════════╗\n" +
               "║         Payment Management             ║\n" +
               "╠════════════════════════════════════════╣\n" +
               "║  1. Record New Payment                 ║\n" +
               "║  2. Update Payment                     ║\n" +
               "║  3. Delete Payment                     ║\n" +
               "║  4. View All Payments                  ║\n" +
               "║  5. Search Payment                     ║\n" +
               "║  6. Back to Main Menu                  ║\n" +
               "╚════════════════════════════════════════╝";
    }
}
