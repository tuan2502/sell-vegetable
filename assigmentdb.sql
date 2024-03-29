USE [master]
GO
/****** Object:  Database [AssignmentDB]    Script Date: 8/7/2022 5:42:02 PM ******/
CREATE DATABASE [AssignmentDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AssignmentDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\AssignmentDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AssignmentDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\AssignmentDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [AssignmentDB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AssignmentDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AssignmentDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AssignmentDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AssignmentDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AssignmentDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AssignmentDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [AssignmentDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AssignmentDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AssignmentDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AssignmentDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AssignmentDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AssignmentDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AssignmentDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AssignmentDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AssignmentDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AssignmentDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AssignmentDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AssignmentDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AssignmentDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AssignmentDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AssignmentDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AssignmentDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AssignmentDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AssignmentDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [AssignmentDB] SET  MULTI_USER 
GO
ALTER DATABASE [AssignmentDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AssignmentDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AssignmentDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AssignmentDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AssignmentDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AssignmentDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [AssignmentDB] SET QUERY_STORE = OFF
GO
USE [AssignmentDB]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 8/7/2022 5:42:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [varchar](50) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 8/7/2022 5:42:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[detailID] [int] NOT NULL,
	[orderID] [int] NULL,
	[productID] [varchar](50) NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_tblOrderDetails] PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 8/7/2022 5:42:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [int] NOT NULL,
	[userID] [varchar](30) NULL,
	[date] [date] NULL,
	[total] [int] NULL,
 CONSTRAINT [PK_tblOrders] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProducts]    Script Date: 8/7/2022 5:42:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProducts](
	[productID] [varchar](50) NOT NULL,
	[productName] [nvarchar](50) NULL,
	[image] [varchar](max) NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[categoryID] [varchar](50) NULL,
 CONSTRAINT [PK_tblProducts] PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 8/7/2022 5:42:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [varchar](50) NOT NULL,
	[rolename] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 8/7/2022 5:42:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](30) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [varchar](11) NULL,
	[password] [varchar](32) NULL,
	[roleID] [varchar](50) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'1', N'Rau Củ')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'2', N'Hoa Quả')
GO
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-125', N'Hành Tây', N'https://organicfood.vn/image/cache/catalog/Rau-cu-qua-huu-co/cu-qua-huu-co/hanh-tay-huu-co-500x500.jpg', 25000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-14d', N'Bí đỏ', N'https://product.hstatic.net/200000271661/product/575f891a8bc64c10b61fc8142fb0d936_c5cdce3bcada406d8b877ff3d87f9d23_master.jpg', 15000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-1a4', N'Cà rốt', N'https://organicfood.vn/image/cache/catalog/Rau-cu-qua-huu-co/cu-qua-huu-co/ca-rot-huu-co-500x500.jpg', 12000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-232', N'Rau Muong', N'https://product.hstatic.net/200000315729/product/rau-muong-500x500_21fd8fe557a443739586ac1b59d6de02_grande.jpeg', 25000, 100, N'1')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-335', N'Táo', N'http://product.hstatic.net/1000282430/product/ambrosia-apple-500g_ea0835fcd451454989bd1fa051889d03_grande.jpg', 18000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-666', N'Chanh', N'https://organicfood.vn/image/cache/catalog/Rau-cu-qua-huu-co/cu-qua-huu-co/chanh-khong-hat-huu-co-500x500.jpg', 22000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-789', N'Khoai Tây', N'https://product.hstatic.net/200000271661/product/khoai_tay_5cdff61890b640c696e997296bc9519f_master.png', 17000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-8A5', N'Quýt', N'https://product.hstatic.net/1000282430/product/quyt_grande.jpg', 15000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-985', N'Dưa Hấu', N'https://organicfood.vn/image/cache/catalog/Trai-cay-huu-co/trai-cay-Viet-Nam/dua-hau-500x500.jpg', 15000, 100, N'2')
INSERT [dbo].[tblProducts] ([productID], [productName], [image], [price], [quantity], [categoryID]) VALUES (N'P-AAA', N'Bắp Cải Tím', N'https://organicfood.vn/image/cache/catalog/Rau-cu-qua-huu-co/rau-huu-co/bap-cai-tim-huu-co-500x500.jpg', 20000, 100, N'1')
GO
INSERT [dbo].[tblRoles] ([roleID], [rolename]) VALUES (N'AD', N'ADMIN')
INSERT [dbo].[tblRoles] ([roleID], [rolename]) VALUES (N'US', N'USER')
GO
INSERT [dbo].[tblUsers] ([userID], [fullName], [address], [phone], [password], [roleID]) VALUES (N'admin', N'Admin', N'asdawdawd', N'12356894', N'123', N'AD')
INSERT [dbo].[tblUsers] ([userID], [fullName], [address], [phone], [password], [roleID]) VALUES (N'tuan2502', N'Nguyen Tuan', N'Adwddddd', N'0865644162', N'123', N'US')
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblOrders] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblOrders]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblProducts] FOREIGN KEY([productID])
REFERENCES [dbo].[tblProducts] ([productID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblProducts]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK_tblOrders_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK_tblOrders_tblUsers]
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD  CONSTRAINT [FK_tblProducts_tblCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblProducts] CHECK CONSTRAINT [FK_tblProducts_tblCategory]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblRoles] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblRoles]
GO
USE [master]
GO
ALTER DATABASE [AssignmentDB] SET  READ_WRITE 
GO
