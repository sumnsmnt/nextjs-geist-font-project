INSERT INTO users (username, email, password) 
VALUES ('testuser', 'test@example.com', 'testpass') 
ON CONFLICT (username) DO NOTHING;
