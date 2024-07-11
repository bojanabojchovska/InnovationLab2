import React, { useState } from 'react';
import $ from 'jquery';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await loginUser(username, password);
        } catch (error) {
            setError(error);
        }
    };

    const loginUser = (username, password) =>
    {
        // Hardcoded
        const hardcodedUser = {
            username: 'admin',
            password: '1234'
        };

        if (username === hardcodedUser.username && password === hardcodedUser.password) {
            localStorage.setItem('username', username);
            alert("Login erfolgreich");
            window.location.href = '/';
        } else {
            const url = 'http://localhost:9090/api/login';
            $.ajax({
                url: url,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ username, password }),
                success: function(data) {
                    localStorage.setItem('username', username);
                    alert("Login erfolgreich");
                    window.location.href = '/';
                },
                error: function(xhr, status, error)
                {
                    console.log("Status:", status);
                    console.log("Error:", error);
                    console.log("Response:", xhr.responseText);
                    console.log("Failed to submit form with data:", JSON.stringify({ username, password }));
                    const errorMessage = xhr.status === 401 ? "Falscher Benutzername oder Passwort" : "An error occurred";
                    alert(errorMessage);
                }
            });
        }
    };


    return (
        <div className="container mt-5">
            <h1 className="mb-3">Login</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" className="form-control" value={username} onChange={handleUsernameChange} />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" className="form-control" value={password} onChange={handlePasswordChange} />
                </div>
                <div className="mb-10">
                    <button type="submit" className="btn btn-primary">Login</button>
                </div>
                {error && <div className="alert alert-danger mt-3">{error}</div>}
            </form>
        </div>
    );
};

export default LoginPage;
