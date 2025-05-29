-- Tạo bảng Phòng ban
CREATE TABLE Departments (
    Id CHAR(3) NOT NULL,             -- Mã phòng ban
    Name NVARCHAR(50) NOT NULL,     -- Tên phòng ban
    Description NVARCHAR(255) NULL, -- Mô tả phòng ban
    PRIMARY KEY (Id)
);

-- Tạo bảng Nhân viên
CREATE TABLE Employees (
    Id VARCHAR(20) NOT NULL,           -- Mã nhân viên
    Password NVARCHAR(50) NOT NULL,    -- Mật khẩu
    Fullname NVARCHAR(50) NOT NULL,    -- Họ tên
    Photo NVARCHAR(50) NOT NULL,       -- Ảnh đại diện
    Gender BIT NOT NULL,               -- Giới tính (1: Nam, 0: Nữ)
    Birthday DATE NOT NULL,            -- Ngày sinh
    Salary FLOAT NOT NULL,             -- Lương cơ bản
    DepartmentId CHAR(3) NOT NULL,     -- Mã phòng ban
    PRIMARY KEY (Id),
    FOREIGN KEY (DepartmentId) REFERENCES Departments(Id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
go 

-- dữ liệu mẫu 
-- Thêm dữ liệu vào bảng Departments
INSERT INTO Departments (Id, Name, Description) VALUES
('D01', N'Phòng Kế Toán', N'Chịu trách nhiệm tài chính công ty'),
('D02', N'Phòng Nhân Sự', N'Quản lý nhân viên và chế độ đãi ngộ'),
('D03', N'Phòng Kinh Doanh', N'Chịu trách nhiệm về bán hàng và marketing'),
('D04', N'Phòng Công Nghệ Thông Tin', N'Phát triển và bảo trì hệ thống IT'),
('D05', N'Phòng Pháp Lý', N'Xử lý các vấn đề pháp lý của công ty');
-- Thêm dữ liệu vào bảng Employees
INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId) VALUES
('E001', N'123456', N'Nguyễn Văn A', 'avatar1.jpg', 1, '1990-01-15', 15000000, 'D01'),
('E002', N'654321', N'Nguyễn Thị B', 'avatar2.jpg', 0, '1992-03-22', 13000000, 'D02'),
('E003', N'abc123', N'Phan Văn C', 'avatar3.jpg', 1, '1988-07-10', 18000000, 'D03'),
('E004', N'password', N'Trần Thị D', 'avatar4.jpg', 0, '1995-12-05', 20000000, 'D04'),
('E005', N'qwerty', N'Lê Văn E', 'avatar5.jpg', 1, '1993-06-25', 17000000, 'D05'),
('E006', N'789012', N'Hoàng Thị F', 'avatar6.jpg', 0, '1991-09-12', 16000000, 'D01'),
('E007', N'admin123', N'Ngô Văn G', 'avatar7.jpg', 1, '1990-02-18', 19000000, 'D02'),
('E008', N'hello123', N'Vũ Thị H', 'avatar8.jpg', 0, '1994-11-08', 14000000, 'D03'),
('E009', N'newpass', N'Nguyễn Thị I', 'avatar9.jpg', 0, '1992-08-30', 17500000, 'D04'),
('E010', N'pass1234', N'Phạm Văn J', 'avatar10.jpg', 1, '1991-01-10', 21000000, 'D05');
go 


-- callableStattement 
--Thêm mới: {CALL spInsert(?, ?, ?)}-- 
CREATE PROCEDURE spInsert( 
@Id VARCHAR(20),  
@Name NVARCHAR(50),  
@Description NVARCHAR(100) 
) AS BEGIN 
INSERT INTO Departments(Id, Name, Description) 
VALUES(@Id, @Name, @Description) 
END  
go 

--Cập nhật: {CALL spUpdate(?, ?, ?)}-- 
CREATE PROCEDURE spUpdate( 
@Id VARCHAR(20),  
@Name NVARCHAR(50),  
@Description NVARCHAR(100) 
) AS BEGIN 
UPDATE Departments  
SET Name=@Name, Description=@Description  
WHERE Id=@Id 
END  
go

--Xóa theo khóa chính: {CALL spDeleteById(?)}-- 
CREATE PROCEDURE spDeleteById( 
@Id VARCHAR(20) 
) AS BEGIN 
DELETE FROM Departments WHERE Id=@Id 
END  
go

--Truy vấn tất cả: {CALL spSelectAll()}-- 
CREATE PROCEDURE spSelectAll  
AS BEGIN 
SELECT * FROM Departments 
END  
go

--Truy vấn theo khóa chính: {CALL spSelectById(?)}-- 
CREATE PROCEDURE spSelectById( 
 @Id VARCHAR(20) 
) AS BEGIN 
 SELECT * FROM Departments WHERE Id=@Id 
END 
go 


-- ================================
-- BẢNG: Departments
-- ================================

-- Truy vấn tất cả phòng ban
SELECT * FROM Departments;

-- Truy vấn phòng ban theo mã (khóa chính)
SELECT * FROM Departments WHERE Id = 'D01';

-- Thêm mới phòng ban
INSERT INTO Departments (Id, Name, Description)
VALUES ('D01', N'Phòng Kế Toán', N'Chịu trách nhiệm tài chính công ty');

-- Cập nhật thông tin phòng ban theo mã
UPDATE Departments
SET Name = N'Phòng Tài Chính', Description = N'Quản lý tài chính và kế toán'
WHERE Id = 'D01';

-- Xóa phòng ban theo mã
DELETE FROM Departments WHERE Id = 'D01';

-- ================================
-- BẢNG: Employees
-- ================================

-- Truy vấn tất cả nhân viên
SELECT * FROM Employees;

-- Truy vấn nhân viên theo mã (khóa chính)
SELECT * FROM Employees WHERE Id = 'E001';

-- Thêm mới nhân viên
INSERT INTO Employees (
    Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId
)
VALUES (
    'E001', N'123456', N'Nguyễn Văn A', 'avatar1.jpg', 1, '1995-05-01', 12000000, 'D01'
);

-- Cập nhật thông tin nhân viên theo mã
UPDATE Employees
SET Fullname = N'Nguyễn Văn Anh', Salary = 13000000
WHERE Id = 'E001';

-- Xóa nhân viên theo mã
DELETE FROM Employees WHERE Id = 'E001';





