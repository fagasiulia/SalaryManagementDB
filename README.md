# SalaryManagementDB

This program will let the user manage a salary database.

The SalaryManagement.db contains the following tables:

1. Employee(Id, First_Name, Last_Name, Designation, Experince)
2. Salary(Employee_Id, Current_Salary)
3. Designation_Group(Designation, Salary_Group)
4. Salary_Range(Salary_Group, Experience, Range)

Using this program the perform the following operations:

1. Search for an employee based on his last_name.
2. Search for an employee's salary based on employee's id
3. Search for the salary group based on designation
4. Search for the salary range based on salarygroup
5. Add a new employee to the system and enter his salary (Employee and Salary Table).
6. Update employee's information such us: Last_Name, Designation, Experience (Employee), Curren_Salary(Salary)
7. Delete an employee from the system (Employee, Salary)
8. Using Designation_Group we can find out based on someone's profession which Salary_Group the profession has. There are 3 salary groups
which are presented in Salary_Range. Here the user can check what's the salary range of a specific group based on experience. Using this table the user can make informed decisions when it comes to raising or decreasing a salary. The salary should always be withing a specific range. 
9. An employee can ask for a file that contains all the information regarding himself or his profession. 
