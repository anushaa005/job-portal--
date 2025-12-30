import React from 'react';
import { useEffect, useState } from 'react';
import api from '../api/axios';

export default function Jobs() {
    const [jobs, setJobs] = useState([]);

    useEffect(() => {
        // Calls the getAllJobs endpoint in JobController
        api.get('/jobs')
            .then(res => setJobs(res.data.data))
            .catch(err => console.error("Error fetching jobs:", err));
    }, []);

    return (
        <div className="p-10 bg-gray-50 min-h-screen">
            <h1 className="text-3xl font-bold mb-8 text-gray-800 border-b pb-4">Available Opportunities</h1>
            <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                {jobs.map(job => (
                    <div key={job.id} className="p-6 bg-white shadow-sm rounded-xl border border-gray-200 hover:shadow-md transition">
                        <h2 className="text-xl font-bold text-blue-700">{job.title}</h2>
                        <p className="text-gray-500 font-medium">{job.companyName}</p>
                        <p className="text-gray-600 mt-1 italic">{job.location}</p>
                        <div className="mt-4 flex justify-between items-center">
                            <span className="text-green-600 font-bold">{job.salary}</span>
                            <span className="text-xs bg-gray-100 px-2 py-1 rounded uppercase">{job.jobType}</span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}