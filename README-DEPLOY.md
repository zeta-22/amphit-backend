# Amphit Backend Deployment Guide

This guide provides instructions for deploying the Amphit Backend to Railway.app.

## Prerequisites

- A Railway.app account (https://railway.app/)
- Git installed on your computer

## Deployment Steps

### 1. Prepare Your Project

The following files have been prepared for deployment:
- `application-prod.properties`: Production configuration
- `Procfile`: Instructions for running the application
- `system.properties`: Java version specification

### 2. Build the Application

Run the following command in the backend directory:
```
mvn clean package -DskipTests
```

### 3. Deploy to Railway.app

1. Sign up for Railway.app if you haven't already
2. Install the Railway CLI:
   ```
   npm i -g @railway/cli
   ```

3. Login to Railway:
   ```
   railway login
   ```

4. Create a new project:
   ```
   railway init
   ```
   
5. Add a PostgreSQL database to your project:
   ```
   railway add
   ```
   Choose PostgreSQL from the options.

6. Link your local project to the Railway project:
   ```
   railway link
   ```

7. Deploy your application:
   ```
   railway up
   ```

### 4. Set Environment Variables

In the Railway dashboard, set the following environment variables:
- `SPRING_PROFILES_ACTIVE=prod`
- `JWT_SECRET=your_secure_jwt_secret_here`
- Railway will automatically set up the PostgreSQL connection variables

### 5. Connect Your Frontend

Update your frontend's API URL to point to your Railway app URL:
1. In your Netlify dashboard for amphit-app
2. Go to Site settings > Build & deploy > Environment
3. Add an environment variable:
   - Key: `REACT_APP_API_URL`
   - Value: `https://your-railway-app-url.up.railway.app/api`
4. Trigger a new deploy of your frontend

## Troubleshooting

- If you encounter build issues, check the Railway logs
- For database issues, you can connect to the PostgreSQL database using the credentials provided in the Railway dashboard
- For API connectivity issues, check the CORS configuration in `application-prod.properties`
