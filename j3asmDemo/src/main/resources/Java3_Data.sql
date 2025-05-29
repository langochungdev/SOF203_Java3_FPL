-- CREATE DATABASE IF NOT EXISTS java3_asm;
USE java3_asm;

DROP TABLE IF EXISTS CATEGORIES;
CREATE TABLE CATEGORIES (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL,
    Active BIT default 1
);

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Email NVARCHAR(255) UNIQUE NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    Fullname NVARCHAR(255),
    Username NVARCHAR(255) UNIQUE,
    Birthday DATE,
    Gender BIT default 1,
    Mobile NVARCHAR(50) UNIQUE,
    Role BIT default 0, -- true là quản trị, false là phóng viên
    Active BIT default 1
);

DROP TABLE IF EXISTS NEWS;
CREATE TABLE NEWS (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
    Content TEXT,
    Image NVARCHAR(255),
    PostedDate DATE,
    Author INT,
    ViewCount INT DEFAULT 0,
    CategoryId INT,
    Home BIT default 1,
    Active BIT default 1,
    FOREIGN KEY (Author) REFERENCES USERS(Id),
    FOREIGN KEY (CategoryId) REFERENCES CATEGORIES(Id)
);

DROP TABLE IF EXISTS NEWSLETTERS;
CREATE TABLE NEWSLETTERS (
    Email NVARCHAR(255) PRIMARY KEY,
    Enabled BIT default 1 -- true là còn hiệu lực
);

-- INSERT DATA --
-----------------
INSERT INTO CATEGORIES (Name) VALUES
(N'Văn hoá'),
(N'Pháp luật'),
(N'Thể thao'),
(N'Du lịch'),
(N'Công nghệ');

INSERT INTO USERS (Email, Password, Fullname, Username, Birthday, Gender, Mobile, Role) 
VALUES
('ndhl@example.com', 'M2Egks4ZEJsZUvlTsdQ2bg==:cWQB3ODHHrGjIV3oML7l1MyJqGo96SfmYxdpIcHGlnY=', N'Nguyễn Đàm Hoàng Linh', 'user001', '1985-05-15', 1, '0905123456', 1),
('nplh@example.com', 'M2Egks4ZEJsZUvlTsdQ2bg==:cWQB3ODHHrGjIV3oML7l1MyJqGo96SfmYxdpIcHGlnY=', N'Nguyễn Phan Lâm Hùng', 'user002', '1986-04-16', 1, '0906543210', 1),
('ltn@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Lê Thị Nhàng', 'user003', '1990-07-10', 0, '0906789123', 0),
('tvc@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Trần Văn Công', 'user004', '1992-03-22', 1, '0908765432', 0),
('pmd@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Phạm Minh Dương', 'user005', '1988-11-30', 1, '0909876543', 0),
('vvt@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Võ Văn Toàn', 'user006', '1995-09-12', 1, '0905432123', 0),
('dtga@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Đỗ Thị Giang Anh', 'user007', '1991-06-05', 0, '0902123456', 0),
('lvh@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Lý Văn Hiệp', 'user008', '1987-01-21', 1, '0909876123', 1),
('nttn@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Ngô Thị Thu Nhi', 'user009', '1993-02-18', 0, '0908761234', 0),
('bvq@example.com', 'ci/Y2vs2KqB4DWVImDT3hw==:+SitK/fuNdKab2pzBeCxskH4F2Xm4xqDY5LLcrp8eNU=', N'Bùi Văn Quyết', 'user010', '1994-12-03', 1, '0906549876', 0);

INSERT INTO NEWS (Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) VALUES
(N'Làn sóng sa thải Gen Z Mỹ', N'Báo cáo tháng 8 của hãng nghiên cứu thị trường lao động Intelligent.com cho thấy các công ty đang cắt giảm nhân sự Gen Z "nhiều đến mức đáng lo ngại" sau vài tháng tuyển dụng.', N'culture1.jpg', '2024-09-01', 1, 120, 1, 1),
(N'Vì sao một số người mãi độc thân?', N'Thuật ngữ "chronically single" mô tả tình trạng một người độc thân trong nhiều năm đang nhận được sự quan tâm với hơn 10 triệu lượt xem trên mạng xã hội.', N'culture2.jpg', '2024-09-02', 2, 300, 1, 1),
(N'5 thói quen tiết kiệm "bất di bất dịch" của triệu phú tự thân', N'Dù sở hữu tài sản hơn một triệu USD nhưng Jonathan Sanchez vẫn duy trì những thói quen tiết kiệm mà ông khẳng định không bao giờ từ bỏ.', N'culture3.jpg', '2024-09-03', 3, 500, 1, 1),
(N'TP HCM tiêu huỷ 1,3 tấn ma túy bằng lò thiêu', N'Cục Thi hành án dân sự TP HCM phối hợp công an, VKS cùng các ban ngành tiêu huỷ 1,3 tấn ma túy - vật chứng của 126 vụ án hình sự, bằng lò thiêu.', N'law1.jpg', '2024-09-04', 4, 80, 2, 0),
(N'Bà Trương Mỹ Lan phủ nhận sử dụng 445.748 tỷ đồng của SCB', N'Chủ tịch Vạn Thịnh Phát nhiều lần nhắc lại quan điểm "tôn trọng cáo trạng truy tố", song cho rằng không sử dụng số tiền 445.748 tỷ đồng của SCB cho cá nhân hay tập đoàn.', N'law2.jpg', '2024-09-05', 5, 230, 2, 1),
(N'Lời khai của tài xế bà Trương Mỹ Lan về việc chở 108.000 tỷ đồng tiền mặt', N'Bùi Văn Dũng, 60 tuổi, khai mỗi buổi chiều bà Trương Mỹ Lan kêu "có tiền, qua ngân hàng nhận" là lái ôtô qua tầng hầm trụ sở SCB, chở các thùng tiền đã được đóng sẵn.', N'law3.jpg', '2024-09-06', 6, 150, 2, 0),
(N'Vinicius bị miệt thị là "cặn bã"', N'Theo cựu chủ tịch của Valencia Paco Roig, màu da không phải là lý do khiến Vinicius bị tẩy chay ở Tây Ban Nha.', N'sport1.jpg', '2024-09-07', 7, 400, 3, 1),
(N'Lê Tuấn Minh - người hùng thầm lặng của Việt Nam ở Olympiad cờ vua', N'Đại kiện tướng Lê Tuấn Minh lần đầu dự Olympiad cờ vua ở tuổi 28, nhưng đoạt HC đồng và nhiều lần giành điểm quyết định cho Việt Nam.', N'sport2.jpg', '2024-09-08', 8, 90, 3, 0),
(N'Pháp, Iran bị tố "không muốn thắng" để gặp Thái Lan ở Futsal World Cup', N'Pháp và Iran bị chỉ trích về thái độ thi đấu, khi gặp nhau ở lượt cuối bảng F FIFA futsal World Cup 2024 ở Uzbekistan tối 22/9.', N'sport3.jpg', '2024-09-09', 9, 270, 3, 1),
(N'Tân binh Mazraoui: "Man Utd danh tiếng hơn Bayern"', N' Mazraoui khởi nghiệp tại Ajax, trước khi chuyển sang Bayern năm 2022, nơi anh chơi 55 trận trên mọi đấu trường, đoạt một Bundesliga và một Siêu Cup Đức.

                    Hè này, hậu vệ Morroco được Man Utd tuyển mộ với mức phí 17 triệu USD cùng 5,5 triệu USD tùy thành tích, với hợp đồng đến năm 2028.

                    Trong buổi họp báo trước trận gặp Twente ở lượt đầu vòng bảng Europa League, khi được hỏi về quyết định gia nhập Man Utd, Mazraoui đáp: "Nếu so sánh Man Utd với Bayern, bạn không thể nói CLB nào lớn hơn. Có thể nói trên bình diện quốc tế, Man Utd danh tiếng hơn Bayern nên đó chắc chắn là một trong những lý do tôi thay đổi".

                    Hậu vệ 26 tuổi cho biết anh hạnh phúc tại Munich, nhưng muốn thay đổi sau hai năm để tìm kiếm thử thách mới. "Hè này, tôi cảm thấy cần và muốn có một bước tiến khác dựa trên nhiều lý do", Mazraoui nói tiếp. "Tôi cảm thấy ổn, nhưng có cảm giác rằng tôi có thể cảm thấy tốt và thoải mái hơn ở một nơi khác. Đó là lý do tôi thay đổi và nhận được đề nghị từ Man Utd".

                    Mazraoui từng thi đấu thành công dưới trướng HLV Erik ten Hag tại Ajax, ba lần vô địch giải Hà Lan, đoạt hai Cup Hà Lan và một Siêu Cup Hà Lan. Anh xem việc hiểu lối chơi, những yêu cầu của HLV Hà Lan là yếu tố quan trọng khi đàm phán với Man Utd.

                    "Khi chuyển sang CLB mới, bạn phải quyết định xem HLV và cách ông ấy nhìn nhận bóng đá có phù hợp hay không", hậu vệ Morroco nói thêm. "Từng làm việc với Ten Hag giúp mọi thứ dễ dàng hơn. Tôi biết ông ấy mong đợi gì từ tôi, và ngược lại. Nhưng đây không phải là yếu tố duy nhất khiến tôi đến CLB lớn này".


                    Ngoài Mazraoui, Man Utd còn tuyển mộ Mathijs de Ligt với mức phí 49 triệu USD từ Bayern. Mazraoui xem đây là yếu tố quan trọng khác. "Thật khó khăn khi đến CLB mới, với môi trường, những cầu thủ mới, nhưng việc cảm thấy thoải mái sẽ giúp bạn thi đấu tốt hơn", hậu vệ 26 tuổi bày tỏ. "Tôi biết nhiều cầu thủ Man Utd và đến đây cùng De Ligt nên mọi thứ trở nên dễ dàng hơn".

                    Mazraoui đã thi đấu sáu trận cho Man Utd, gồm năm tại Ngoại hạng Anh và một ở Cup Liên đoàn, có một kiến tạo cho Amad Diallo trong trận thua 1-2 trên sân Brighton ở Ngoại hạng Anh hôm 24/8.
                ', N'sport4.jpg', '2024-09-10', 10, 180, 3, 0),
(N'Quyết Chiến, Phương Vinh ra quân World Championship', N'Các cơ thủ hàng đầu Việt Nam sẽ ra quân ở giải billiards carom 3 băng vô địch thế giới - World Championship 2024 tại Phan Thiết, hôm nay.', N'sport5.jpg', '2024-09-11', 3, 620, 3, 1),
(N'Thưởng thức món Thái Michelin trên xe buýt ở Bangkok', N'Tour xe buýt ở Bangkok thu hút khách quốc tế nhờ kết hợp vừa tham quan vừa thưởng thức những món Thái nổi tiếng, được Michelin tuyển chọn.', N'travel1.jpg', '2024-09-12', 4, 50, 4, 0),
(N'Đi máy bay riêng đắt thế nào', N'Chuyến bay riêng tư được nhiều du khách cân nhắc vì các lợi ích như tránh đông đúc, thuận tiện nhưng chi phí lên tới 15.000 USD mỗi giờ.', N'travel2.jpg', '2024-09-13', 5, 240, 4, 1),
(N'Quê Hồ Xuân Hương được công nhận là điểm du lịch', N'Làng Quỳnh Đôi - quê hương "bà chúa thơ Nôm" Hồ Xuân Hương, được công nhận là điểm du lịch nhằm phát huy giá trị điểm đến, phục vụ khách tốt hơn.', N'travel3.jpg', '2024-09-14', 6, 110, 4, 0),
(N'Heineken phát triển nhân tài công nghệ Việt Nam', N'Để hiện thực hóa mục tiêu "công ty bia kết nối tốt nhất", Heineken đẩy mạnh phát triển nhiều trung tâm công nghệ tại Việt Nam, tạo thêm cơ hội cho nhân tài công nghệ Việt. Hai trung tâm công nghệ cấp khu vực. Theo công bố của Heineken Việt Nam (HVN), Việt Nam hiện là nơi đặt trụ sở của hai trung tâm công nghệ quy mô khu vực của công ty. 

Hai trung tâm công nghệ này bao gồm: Trung tâm dữ liệu & phân tích khu vực châu Á Thái Bình Dương (APAC Data & Analytics Hub) và Trung tâm phát triển nhà máy kết nối khu vực châu Á Thái Bình Dương (APAC Connected Brewery Lighthouse Hub). Tại Heineken, dữ liệu được xem là tài sản quan trọng để tạo ra lợi thế cạnh tranh. Heineken đã xây dựng 3 trung tâm Data & Analytics trên toàn cầu, trong đó có một trung tâm đặt tại Việt Nam để thúc đẩy chiến lược dữ liệu của công ty tại khu vực APAC.Heineken, một trong những tập đoàn sản xuất bia lớn nhất thế giới, đang đẩy mạnh phát triển các trung tâm công nghệ tại Việt Nam. Đây là một phần trong chiến lược tổng thể của công ty nhằm hiện thực hóa mục tiêu trở thành "công ty bia kết nối tốt nhất". Các trung tâm này không chỉ phục vụ các nhu cầu của thị trường nội địa mà còn đóng vai trò quan trọng trong mạng lưới toàn cầu của Heineken.

Những trung tâm công nghệ này sẽ tạo ra rất nhiều cơ hội cho nhân tài công nghệ tại Việt Nam, mở ra các vị trí mới cho các kỹ sư phần mềm, chuyên gia dữ liệu, và những người có kiến thức về công nghệ thông tin. Heineken đặc biệt chú trọng đến việc phát triển nhân tài nội địa và hợp tác với các trường đại học và viện nghiên cứu hàng đầu tại Việt Nam.

Ngoài ra, Heineken cũng cam kết mang lại môi trường làm việc hiện đại với các cơ sở hạ tầng công nghệ tiên tiến, giúp các chuyên gia có cơ hội học hỏi và phát triển kỹ năng của mình. Trong vài năm tới, Heineken sẽ đầu tư mạnh mẽ hơn nữa vào việc nâng cấp các trung tâm công nghệ và đưa Việt Nam trở thành một trong những trung tâm công nghệ quan trọng nhất của tập đoàn tại khu vực Đông Nam Á.

Heineken không chỉ đóng góp vào sự phát triển của ngành công nghệ tại Việt Nam, mà còn đang từng bước khẳng định vai trò của mình như một nhà tuyển dụng hàng đầu trong lĩnh vực này. Điều này không chỉ giúp tăng cường sức cạnh tranh cho Heineken mà còn góp phần phát triển kinh tế và công nghệ của đất nước.', N'tech1.jpg', '2024-10-03', 1, 0, 5, 1),

(N'Thiết bị Wi-Fi 7 "made in Vietnam" đầu tiên có tốc độ 10 Gbps', N'Thiết bị Wi-Fi 7 XGSPON, do VNPT sản xuất, cho tốc độ lên đến 10 Gb/giây, độ trễ giảm 75% so với công nghệ Wi-Fi 6. Tại lễ ra mắt sáng 3/10, ông Nguyễn Nam Long, Phó tổng giám đốc VNPT, cho biết thiết bị sử dụng nền tảng công nghệ XGSPON Wi-Fi 7 của Qualcomm và được VNPT Technology sản xuất tại Việt Nam. XGSPON (XGigabit-capable Passive Optical Network) là tiêu chuẩn truyền dẫn quang học được phát triển dựa trên nền tảng của công nghệ PON, cho phép nâng tốc độ truyền dẫn dữ liệu tối đa 10 Gb/giây cả tải lên và xuống trên một đường cáp quang.', N'tech2.jpg', '2024-10-03', 2, 0, 5, 1),
(N'Ứng dụng AI tại trung tâm logistis ICD Vĩnh Phúc', N'Công nghệ AI được Liên doanh T&T Group và Tập đoàn YCH (Singapore) ứng dụng tại cảng ICD Vĩnh Phúc - Việt Nam SuperPort giúp gia tăng năng lực cho ngành logistics. Tại sự kiện Ngày hội Đổi mới sáng tạo Việt Nam 2024 và lễ kỷ niệm 5 năm thành lập Trung tâm Đổi mới sáng tạo Quốc gia (NIC), Trung tâm logistics ICD Vĩnh Phúc - Việt Nam SuperPort do Liên doanh T&T Group và Tập đoàn YCH (Singapore) mang đến những trải nghiệm công nghệ thực tế ảo Apple Vision Pro cho người tham quan.', N'tech3.jpg', '2024-10-03', 3, 0, 5, 1),
(N'Bill Gates chia sẻ ba mối lo ngại lớn nhất về AI', N'Tỷ phú Bill Gates tự nhận là người lạc quan về tương lai của AI, nhưng vẫn có một vài lo ngại về công nghệ này. Trong podcast On with Kara Swisher phát trên Apple Podcast ngày 30/9, Bill Gates cho biết mối lo ngại đầu tiên về AI là việc kẻ xấu có thể sử dụng công nghệ này để thực hiện hành vi tội phạm trên Internet, khủng bố sinh học và chiến tranh.', N'tech4.jpg', '2024-10-03', 4, 0, 5, 1),
(N'Apple gặp khó vì cháy nhà máy linh kiện iPhone ở Ấn Độ', N'Nhà máy sản xuất linh kiện iPhone của Tata ngừng sản xuất sau một vụ cháy, khiến Apple có thể phải nhập hàng từ Trung Quốc đến Ấn Độ.', N'tech5.jpg', '2024-10-03', 5, 0, 5, 0),
(N'Thị trường AI, bán dẫn có tốc độ phát triển siêu nhanh', N'Chuyên gia của Qualcomm, Samsung, FPT... đánh giá lĩnh vực AI và bán dẫn có khả năng tiến tới quy mô hàng nghìn tỷ đôla rất sớm.', N'tech6.jpg', '2024-10-03', 6, 0, 5, 1);

INSERT INTO NEWSLETTERS (Email, Enabled) VALUES
('linhndhps38327@fpt.edu.vn', 1),
('hungnplps39152@fpt.edu.vn', 1),
('email3@example.com', 0),
('email4@example.com', 1),
('email5@example.com', 1),
('email6@example.com', 0),
('email7@example.com', 1),
('email8@example.com', 1),
('email9@example.com', 0),
('email10@example.com', 1),
('email11@example.com', 1),
('email12@example.com', 0),
('email13@example.com', 1),
('email14@example.com', 1),
('email15@example.com', 0),
('email16@example.com', 1),
('email17@example.com', 1),
('email18@example.com', 0),
('email19@example.com', 1),
('email20@example.com', 1);