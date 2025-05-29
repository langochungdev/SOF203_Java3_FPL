CREATE DATABASE j3asm
GO
USE j3asm
GO

-- Tạo bảng
CREATE TABLE CATEGORIES (
    Id NVARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL
)

CREATE TABLE USERS (
    Id NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(100) NOT NULL,
    Fullname NVARCHAR(100) NOT NULL,
    Birthday DATE,
    Gender BIT,
    Mobile NVARCHAR(20),
    Email NVARCHAR(100),
    Role BIT
)

CREATE TABLE NEWS (
    Id NVARCHAR(50) PRIMARY KEY,
    Title NVARCHAR(200) NOT NULL,
    Content NTEXT NOT NULL,
    Image NVARCHAR(200),
    PostedDate DATE,
    Author NVARCHAR(50),
    ViewCount INT,
    CategoryId NVARCHAR(50),
    Home BIT,
    FOREIGN KEY (Author) REFERENCES USERS(Id),
    FOREIGN KEY (CategoryId) REFERENCES CATEGORIES(Id)
)

CREATE TABLE NEWSLETTERS (
    Email NVARCHAR(100) PRIMARY KEY,
    Enabled BIT
)

-- Dữ liệu mẫu: CATEGORIES
INSERT INTO CATEGORIES (Id, Name) VALUES
('CAT01', N'Thời sự'),
('CAT02', N'Kinh tế'),
('CAT03', N'Xã hội'),
('CAT04', N'Văn hóa'),
('CAT05', N'Thể thao'),
('CAT06', N'Công nghệ'),
('CAT07', N'Giáo dục'),
('CAT08', N'Giải trí')

-- Dữ liệu mẫu: USERS
INSERT INTO USERS (Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES
('USER01', N'pass123', N'Nguyễn Văn A', '1990-01-01', 1, '0912345678', 'nva@gmail.com', 1),
('USER03', N'pass789', N'Lê Văn C', '1988-03-03', 1, '0934567890', 'lvc@gmail.com', 0),
('USER05', N'pass112', N'Hoàng Văn E', '1993-05-05', 1, '0956789012', 'hve@gmail.com', 0),
('USER07', N'pass415', N'Đỗ Văn G', '1989-07-07', 1, '0978901234', 'dvg@gmail.com', 1)

-- Dữ liệu mẫu: NEWS (đã sửa lỗi AUTHOR)
INSERT INTO NEWS (Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) VALUES 
('news01', N'5 cách tăng năng suất làm việc mỗi ngày', N'Nội dung chi tiết về 5 cách...', 'link-anh1.jpg', '2025-05-01', 'USER01', 120, 'CAT02', 1),
('news02', N'Tại sao nên học lập trình từ sớm?', N'Nội dung bài viết về lợi ích học lập trình...', 'link-anh2.jpg', '2025-05-02', 'USER01', 98, 'CAT02', 1),
('news03', N'Hướng dẫn sử dụng ChatGPT trong công việc', N'Hướng dẫn chi tiết về cách áp dụng AI...', 'link-anh3.jpg', '2025-05-03', 'USER01', 150, 'CAT02', 1),
('news04', N'Thực đơn Eat Clean cho người tăng cân', N'Mẫu thực đơn 7 ngày chi tiết...', 'link-anh4.jpg', '2025-05-04', 'USER01', 210, 'CAT02', 0),
('news05', N'Khám phá 5 cuốn sách thay đổi tư duy', N'Giới thiệu 5 cuốn sách nên đọc...', 'link-anh5.jpg', '2025-05-05', 'USER01', 75, 'CAT02', 1),
('news06', N'Cách xây dựng ứng dụng Java Swing', N'Hướng dẫn từ A-Z lập trình Swing...', 'link-anh6.jpg', '2025-05-06', 'USER01', 130, 'CAT02', 1),
('news07', N'Top 3 framework web phổ biến 2025', N'So sánh và đánh giá React, Angular, Vue...', 'link-anh7.jpg', '2025-05-07', 'USER01', 180, 'CAT02', 1),
('news08', N'Học SQL cơ bản trong 1 tuần', N'Giới thiệu các câu lệnh SQL cơ bản...', 'link-anh8.jpg', '2025-05-08', 'USER01', 90, 'CAT02', 0),
('news09', N'Cập nhật công nghệ mới 2025', N'Tổng hợp xu hướng công nghệ mới nhất...', 'link-anh9.jpg', '2025-05-09', 'USER01', 210, 'CAT02', 1),
('news10', N'Cách viết CV thu hút nhà tuyển dụng', N'Chi tiết các phần cần có trong CV...', 'link-anh10.jpg', '2025-05-10', 'USER01', 65, 'CAT02', 0)

-- Dữ liệu mẫu: NEWSLETTERS
INSERT INTO NEWSLETTERS (Email, Enabled) VALUES
('reader1@gmail.com', 1),
('reader3@gmail.com', 0),
('reader5@gmail.com', 1),
('reader7@gmail.com', 1)
