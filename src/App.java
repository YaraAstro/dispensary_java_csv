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
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║     Dispensary Management System       ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1. Patient Management                 ║");
            System.out.println("║  2. Doctor Management                  ║");
            System.out.println("║  3. Medicine Management                ║");
            System.out.println("║  4. Prescription Management            ║");
            System.out.println("║  5. Payment Management                 ║");
            System.out.println("║  6. Exit                               ║");
            System.out.println("╚════════════════════════════════════════╝");
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
                    patientManagement();
                    break;
                case 5:
                    patientManagement();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the Dispensary Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void medicineManagement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'medicineManagement'");
    }

    private static void doctorManagement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doctorManagement'");
    }

    private static void patientManagement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patientManagement'");
    }
}
