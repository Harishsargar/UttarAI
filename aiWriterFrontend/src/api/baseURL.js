// Toggle this value manually based on the environment
const isProd = true; // Set to false for local development

// Base URLs
const PROD_URL = "https://uttarai-kdfm.onrender.com/api";
const DEV_URL = "http://localhost:8080/api";

// Export the correct base URL based on the environment
export const BASE_URL = isProd ? PROD_URL : DEV_URL;
