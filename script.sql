USE [J3LP0007]
GO
/****** Object:  Table [dbo].[tblAccount]    Script Date: 5/30/2020 3:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAccount](
	[email] [varchar](50) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[role] [varchar](50) NOT NULL,
	[status] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tblAccount] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblAccountStatus]    Script Date: 5/30/2020 3:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAccountStatus](
	[status] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tblAccountStatus] PRIMARY KEY CLUSTERED 
(
	[status] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblAnswer]    Script Date: 5/30/2020 3:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAnswer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[questionId] [int] NULL,
	[content] [varchar](100) NULL,
	[isCorrect] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblHistory]    Script Date: 5/30/2020 3:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblHistory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NULL,
	[subjectId] [varchar](50) NULL,
	[score] [float] NULL,
	[dateQuiz] [datetime] NULL,
	[numberOfCorrect] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblQuestion]    Script Date: 5/30/2020 3:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblQuestion](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content] [varchar](50) NULL,
	[createDate] [datetime] NULL,
	[lastUpdateDate] [datetime] NULL,
	[subjectId] [varchar](50) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 5/30/2020 3:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[role] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSubject]    Script Date: 5/30/2020 3:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSubject](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](50) NULL,
	[noOfQuestion] [int] NULL,
	[timer] [int] NULL,
 CONSTRAINT [PK_tblSubject] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblAccount] ([email], [name], [password], [role], [status]) VALUES (N'admin', N'Admin', N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', N'admin', N'activated')
INSERT [dbo].[tblAccount] ([email], [name], [password], [role], [status]) VALUES (N'new@new.new', N'newnew', N'9b21ec9dbe3dec76be9c39f673c8c8971f023be79e02aa3ad1f717aa00afb41c', N'student', N'activated')
INSERT [dbo].[tblAccount] ([email], [name], [password], [role], [status]) VALUES (N'user@user.com', N'user', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'student', N'new')
INSERT [dbo].[tblAccountStatus] ([status]) VALUES (N'activated')
INSERT [dbo].[tblAccountStatus] ([status]) VALUES (N'deactivated')
INSERT [dbo].[tblAccountStatus] ([status]) VALUES (N'new')
SET IDENTITY_INSERT [dbo].[tblAnswer] ON 

INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (1, 2, N'1', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (2, 2, N'2', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (3, 2, N'3', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (4, 2, N'5', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (5, 3, N'1', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (6, 3, N'2', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (7, 3, N'3', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (8, 3, N'4', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (9, 7, N'5', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (10, 7, N'6', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (11, 7, N'7', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (12, 7, N'8', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (13, 8, N'10', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (14, 8, N'100', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (15, 8, N'20', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (16, 8, N'55', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (17, 10, N'46', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (18, 10, N'64', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (19, 10, N'10', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (20, 10, N'1', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (21, 11, N'2', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (22, 11, N'6', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (23, 11, N'7', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (24, 11, N'8', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (25, 12, N'7', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (26, 12, N'8', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (27, 12, N'9', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (28, 12, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (29, 13, N'7', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (30, 13, N'8', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (31, 13, N'12', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (32, 13, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (33, 14, N'14', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (34, 14, N'8', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (35, 14, N'12', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (36, 14, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (37, 15, N'14', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (38, 15, N'16', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (39, 15, N'12', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (40, 15, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (41, 16, N'14', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (42, 16, N'18', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (43, 16, N'12', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (44, 16, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (45, 17, N'3', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (46, 17, N'18', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (47, 17, N'12', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (48, 17, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (49, 18, N'3', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (50, 18, N'18', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (51, 18, N'7', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (52, 18, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (53, 19, N'3', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (54, 19, N'9', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (55, 19, N'7', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (56, 19, N'10', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (57, 20, N'3', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (58, 20, N'9', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (59, 20, N'7', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (60, 20, N'11', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (61, 21, N'3', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (62, 21, N'13', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (63, 21, N'7', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (64, 21, N'11', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (65, 22, N'3', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (66, 22, N'13', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (67, 22, N'15', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (68, 22, N'11', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (69, 23, N'17', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (70, 23, N'13', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (71, 23, N'15', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (72, 23, N'11', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (73, 24, N'17', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (74, 24, N'4', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (75, 24, N'15', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (76, 24, N'11', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (77, 25, N'17', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (78, 25, N'4', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (79, 25, N'5', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (80, 25, N'11', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (81, 26, N'17', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (82, 26, N'4', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (83, 26, N'5', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (84, 26, N'6', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (85, 27, N'15', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (86, 27, N'11', 1)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (87, 27, N'13', 0)
INSERT [dbo].[tblAnswer] ([id], [questionId], [content], [isCorrect]) VALUES (88, 27, N'54', 0)
SET IDENTITY_INSERT [dbo].[tblAnswer] OFF
SET IDENTITY_INSERT [dbo].[tblHistory] ON 

INSERT [dbo].[tblHistory] ([id], [email], [subjectId], [score], [dateQuiz], [numberOfCorrect]) VALUES (1, N'new@new.new', N'PRJ321', 10, CAST(N'2020-05-27T20:47:33.050' AS DateTime), NULL)
INSERT [dbo].[tblHistory] ([id], [email], [subjectId], [score], [dateQuiz], [numberOfCorrect]) VALUES (2, N'new@new.new', N'PRJ311', 8.5, CAST(N'2020-05-27T20:47:54.977' AS DateTime), NULL)
INSERT [dbo].[tblHistory] ([id], [email], [subjectId], [score], [dateQuiz], [numberOfCorrect]) VALUES (3, N'new@new.new', N'PRJ311', 0, CAST(N'2020-05-30T12:59:29.473' AS DateTime), 0)
INSERT [dbo].[tblHistory] ([id], [email], [subjectId], [score], [dateQuiz], [numberOfCorrect]) VALUES (4, N'new@new.new', N'PRJ311', 1, CAST(N'2020-05-30T13:20:05.800' AS DateTime), 1)
INSERT [dbo].[tblHistory] ([id], [email], [subjectId], [score], [dateQuiz], [numberOfCorrect]) VALUES (5, N'new@new.new', N'PRJ311', 3, CAST(N'2020-05-30T13:34:34.813' AS DateTime), 3)
INSERT [dbo].[tblHistory] ([id], [email], [subjectId], [score], [dateQuiz], [numberOfCorrect]) VALUES (6, N'new@new.new', N'PRJ321', 1, CAST(N'2020-05-30T15:50:40.497' AS DateTime), 1)
INSERT [dbo].[tblHistory] ([id], [email], [subjectId], [score], [dateQuiz], [numberOfCorrect]) VALUES (7, N'new@new.new', N'PRJ311', 3, CAST(N'2020-05-30T15:53:33.620' AS DateTime), 3)
SET IDENTITY_INSERT [dbo].[tblHistory] OFF
SET IDENTITY_INSERT [dbo].[tblQuestion] ON 

INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (2, N'1+1=?', CAST(N'2020-05-22T09:41:14.210' AS DateTime), CAST(N'2020-05-26T11:30:20.270' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (3, N'2+2=?', CAST(N'2020-05-22T09:41:14.210' AS DateTime), CAST(N'2020-05-22T09:41:14.210' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (7, N'2+3=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (8, N'5+5=?', CAST(N'2020-05-27T08:19:35.913' AS DateTime), CAST(N'2020-05-27T08:19:35.913' AS DateTime), N'PRJ311', 0)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (10, N'4+6=?', CAST(N'2020-05-27T08:23:15.257' AS DateTime), CAST(N'2020-05-27T08:23:15.257' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (11, N'3+3=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (12, N'4+4=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (13, N'6+6=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (14, N'7+7=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (15, N'8+8=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (16, N'9+9=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (17, N'1+2=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 0)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (18, N'3+4=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (19, N'4+5=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (20, N'5+6=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (21, N'6+7=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (22, N'7+8=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (23, N'8+9=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (24, N'1+3=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (25, N'1+4=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (26, N'1+5=?', CAST(N'2020-05-27T08:16:14.930' AS DateTime), CAST(N'2020-05-27T08:16:14.930' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[tblQuestion] ([id], [content], [createDate], [lastUpdateDate], [subjectId], [status]) VALUES (27, N'4+7=?', CAST(N'2020-05-27T21:52:41.993' AS DateTime), CAST(N'2020-05-27T21:52:41.993' AS DateTime), N'PRJ311', 1)
SET IDENTITY_INSERT [dbo].[tblQuestion] OFF
INSERT [dbo].[tblRole] ([role]) VALUES (N'admin')
INSERT [dbo].[tblRole] ([role]) VALUES (N'student')
INSERT [dbo].[tblSubject] ([id], [name], [noOfQuestion], [timer]) VALUES (N'PRJ311', N'Java Desktop', 40, 60)
INSERT [dbo].[tblSubject] ([id], [name], [noOfQuestion], [timer]) VALUES (N'PRJ321', N'Java Web', 50, 80)
ALTER TABLE [dbo].[tblAccount]  WITH CHECK ADD  CONSTRAINT [FK_tblAccount_tblAccountStatus] FOREIGN KEY([status])
REFERENCES [dbo].[tblAccountStatus] ([status])
GO
ALTER TABLE [dbo].[tblAccount] CHECK CONSTRAINT [FK_tblAccount_tblAccountStatus]
GO
ALTER TABLE [dbo].[tblAccount]  WITH CHECK ADD  CONSTRAINT [FK_tblAccount_tblRole] FOREIGN KEY([role])
REFERENCES [dbo].[tblRole] ([role])
GO
ALTER TABLE [dbo].[tblAccount] CHECK CONSTRAINT [FK_tblAccount_tblRole]
GO
ALTER TABLE [dbo].[tblAnswer]  WITH CHECK ADD  CONSTRAINT [FK_tblAnswer_tblQuestion] FOREIGN KEY([questionId])
REFERENCES [dbo].[tblQuestion] ([id])
GO
ALTER TABLE [dbo].[tblAnswer] CHECK CONSTRAINT [FK_tblAnswer_tblQuestion]
GO
ALTER TABLE [dbo].[tblHistory]  WITH CHECK ADD  CONSTRAINT [FK_tblHistory_tblAccount] FOREIGN KEY([email])
REFERENCES [dbo].[tblAccount] ([email])
GO
ALTER TABLE [dbo].[tblHistory] CHECK CONSTRAINT [FK_tblHistory_tblAccount]
GO
ALTER TABLE [dbo].[tblHistory]  WITH CHECK ADD  CONSTRAINT [FK_tblHistory_tblSubject] FOREIGN KEY([subjectId])
REFERENCES [dbo].[tblSubject] ([id])
GO
ALTER TABLE [dbo].[tblHistory] CHECK CONSTRAINT [FK_tblHistory_tblSubject]
GO
ALTER TABLE [dbo].[tblQuestion]  WITH CHECK ADD  CONSTRAINT [FK_tblQuestion_tblSubject] FOREIGN KEY([subjectId])
REFERENCES [dbo].[tblSubject] ([id])
GO
ALTER TABLE [dbo].[tblQuestion] CHECK CONSTRAINT [FK_tblQuestion_tblSubject]
GO
