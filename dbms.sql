-- Create a database
CREATE DATABASE College;

-- Use the database
USE College;

-- Create a table
CREATE TABLE Students (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(50),
    Age INT,
    Department VARCHAR(50)
);

-- Insert data into the table
INSERT INTO Students (StudentID, Name, Age, Department)
VALUES
(1, 'Aarav', 20, 'Computer Science'),
(2, 'Diya', 19, 'Electronics'),
(3, 'Rohan', 21, 'Mechanical');

-- Retrieve data from the table
SELECT * FROM Students;