import React, { useState } from 'react';
import $ from 'jquery';

const RegisterPage = () => {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        passwordConfirm: '',
        role: ''
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Check if passwords match
        if (formData.password !== formData.passwordConfirm) {
            alert("Passwords do not match!");
            return;
        }

        // Check if all fields are filled
        if (!formData.username || !formData.email || !formData.password || !formData.role)
        {
            alert("Please fill in all fields");
            return;
        }

        const url = 'http://localhost:9090/api/register';

        const dataToSend = JSON.stringify(
  {
            username: formData.username,
            email: formData.email,
            password: formData.password,
            role: formData.role
        });

        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: dataToSend,
            success: function(data) {
                alert("Registration successful");
                console.log('Form submitted:', dataToSend);
                window.location.href = '/login';
            },
            error: function(xhr, status, error) {
                const errorMessage = xhr.status === 400 ? "Problem with the registration data" : "An error occurred during registration";
                alert(errorMessage);

                console.log("Failed to submit form with data:", dataToSend);

                console.log("Status:", status);
                console.log("Error:", error);
                console.log("Response:", xhr.responseText);
            }
        });
    };



    return (
        <div className="container mt-5">
            <h1 className="mb-3">Register</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" className="form-control" required onChange={handleChange}/>
                </div>
                <div className="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" className="form-control" required onChange={handleChange}/>
                </div>
                <div className="form-group">
                    <label>Role:</label>
                    <select name="role" className="form-control" required onChange={handleChange}>
                        <option value="">Select a role</option>
                        <option value="analyst">Analyst</option>
                        <option value="developer">Developer</option>
                        <option value="reviewer">Reviewer</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" className="form-control" required minLength="8"
                           onChange={handleChange}/>
                </div>
                <div className="form-group">
                    <label>Confirm Password:</label>
                    <input type="password" name="passwordConfirm" className="form-control" required minLength="8"
                           onChange={handleChange}/>
                </div>
                <button type="submit" className="btn btn-primary">Register</button>
            </form>
        </div>
    );
};

export default RegisterPage;
