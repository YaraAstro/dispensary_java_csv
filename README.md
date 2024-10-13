# Dispensary Managment System in Java & CSV

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Database (CSV FILES)

### patients.csv

where patient data stored.

```csv
patient_id,patient_name,patient_birthday,patient_address,patient_NIC,patient_contact_no,patient_blood_type,patient_allergies,patient_diseases,patient_weight,patient_height
```

### doctors.csv

```csv
doctor_id,doctor_name,doctor_birthday,doctor_address,doctor_contact_no,doctor_qualification,doctor_specialization,doctor_recruited_date
```

### appointments.csv

```csv
appointment_id,patient_id,doctor_id,appointment_date,appointment_time,status,symptoms,description,prescription_id
```

### prescriptions.csv

### medicines.csv

### payments.csv
