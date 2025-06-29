document.getElementById('loginbtn').addEventListener("click", async (event) => {
    event.preventDefault();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const messageE = document.getElementById("msgError");
    messageE.textContent = "";
    const messageS = document.getElementById("msgSuccess");
    messageS.textContent = "";
    try {

        if (email && password) {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email,
                    password
                })
            });
            if (!response.ok) {
                const err = await response.json();
                messageE.textContent = "Invalid Credentials"
                // alert('invalid credentials')
                // throw new Error(err.message || 'Login failed');
                return;
            }

            const data = await response.json();
            const token = data.token; // assuming the JWT is in `data.token`

            // Save token in chrome.storage
            await chrome.storage.local.set({ token });
            messageS.textContent = "✅ Login successful!";
            setTimeout(() => {
                window.close();
            }, 500);
        }
    } catch (error) {
        console.error('Login error:', error);
        alert('❌ Login failed: ' + error.message);
    }
})