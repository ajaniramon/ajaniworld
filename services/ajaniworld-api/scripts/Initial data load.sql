USE [ajani]
GO
INSERT [dbo].[countries] ([id], [name]) VALUES (N'3a668047-4b3e-42d4-a7e2-cde44ec01c08', N'España')
INSERT [dbo].[states] ([id], [name], [country_id]) VALUES (N'1136fed6-7047-4562-92d9-45a09621d698', N'Valencia', N'3a668047-4b3e-42d4-a7e2-cde44ec01c08')
INSERT [dbo].[roles] ([id], [name]) VALUES (N'240baa65-e2b9-45d1-8c85-7983559a7519', N'ADMIN')
INSERT [dbo].[users] ([id], [code], [email], [name], [password], [phone], [surnames]) VALUES (N'dc1c0ba2-8fcc-4017-9374-6064c4161bf1', N'ajani', N'ra1996@gmail.com', N'Ramón', N'$2a$12$LtbYtEzOBdnsiD/E9Wtj2ONFkKdNVtSW5OZVUI3dzZaWDhSRDfpYC', N'+34625275130', N'Martínez Tarín')
INSERT [dbo].[users_roles] ([users_id], [roles_id]) VALUES (N'dc1c0ba2-8fcc-4017-9374-6064c4161bf1', N'240baa65-e2b9-45d1-8c85-7983559a7519')
INSERT [dbo].[spaces] ([id], [address], [city], [name], [zip_code]) VALUES (N'450cd6e7-7aed-4173-819a-644535109bac', N'Calle Gabriela Mistral 19B', N'Torrent', N'Piso de Ramón y Dani', N'46900')
