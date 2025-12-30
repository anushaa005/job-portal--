import React from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

export default function Login() {
    const [form, setForm] = useState({ email: '', password: '' });
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await api.post('/users/login', form);
            // res.data.data contains the JWT token string
            localStorage.setItem('token', res.data.data);
            alert("Login Successful!");
            navigate('/jobs');
        } catch (err) {
            alert(err.response?.data?.message || "Login Failed");
        }
    };

    return (
        <div className="flex min-h-screen items-center justify-center bg-gray-100">
            <form onSubmit={handleSubmit} className="p-8 bg-white shadow-md rounded-lg w-96">
                <h2 className="text-2xl font-bold mb-6 text-center">Job Portal Login</h2>
                <input
                    type="email" placeholder="Email" required
                    className="w-full p-2 mb-4 border rounded"
                    onChange={(e) => setForm({...form, email: e.target.value})}
                />
                <input
                    type="password" placeholder="Password" required
                    className="w-full p-2 mb-6 border rounded"
                    onChange={(e) => setForm({...form, password: e.target.value})}
                />
                <button className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700 transition">
                    Login
                </button>
            </form>
        </div>
    );
}