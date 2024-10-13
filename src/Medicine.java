import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Medicine {
    private String medicineId;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String manufacturer;
    private LocalDate expiryDate;
    private double unitPrice;
    private static final String CSV_PATH = "data/medicines.csv";

    public Medicine(String medicineId, String name, String description, double price, int stock, String manufacturer, LocalDate expiryDate, double unitPrice) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.unitPrice = unitPrice;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
        updateCSV();
    }

    public void setDescription(String description) {
        this.description = description;
        updateCSV();
    }

    public void setPrice(double price) {
        this.price = price;
        updateCSV();
    }

    public void setStock(int stock) {
        this.stock = stock;
        updateCSV();
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        updateCSV();
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        updateCSV();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        updateCSV();
    }

    private void updateCSV() {
        ArrayList<Medicine> medicines = loadMedicines(CSV_PATH);
        for (int i = 0; i < medicines.size(); i++) {
            if (medicines.get(i).medicineId.equals(this.medicineId)) {
                medicines.set(i, this);
                break;
            }
        }
        saveMedicines(medicines, CSV_PATH);
    }

    private static void saveMedicines(ArrayList<Medicine> medicines, String file_path) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write("medicine_id,name,description,price,stock,manufacturer,expiry_date,unit_price\n");
            for (Medicine medicine : medicines) {
                writer.write(String.format("%s,%s,\"%s\",%.2f,%d,%s,%s,%.2f\n",
                    medicine.medicineId, medicine.name, medicine.description,
                    medicine.price, medicine.stock, medicine.manufacturer,
                    medicine.expiryDate, medicine.unitPrice));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Medicine> loadMedicines(String file_path) {
        ArrayList<Medicine> medicines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Medicine medicine = new Medicine(
                    data[0], // medicineId
                    data[1], // name
                    data[2], // description
                    Double.parseDouble(data[3]), // price
                    Integer.parseInt(data[4]), // stock
                    data[5], // manufacturer
                    LocalDate.parse(data[6]), // expiryDate
                    Double.parseDouble(data[7]) // unitPrice
                );
                medicines.add(medicine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return medicines;
    }
}
