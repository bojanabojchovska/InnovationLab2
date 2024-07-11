import React from 'react';

const HomePage = () => {
    const username = localStorage.getItem('username');

    return (
        <div className="container mt-5">
            <h1 className="display-1 text-center">Welcome on the User Portal</h1>
            <p className="lead text-center">
                {username ? <span>Logged in as: {username}</span> : <span>You are not logged in</span>}
            </p>
        </div>
    );
};

export default HomePage;
