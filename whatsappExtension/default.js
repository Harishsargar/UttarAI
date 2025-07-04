document.addEventListener('DOMContentLoaded', async () => {
  const btn = document.getElementById('authBtn');
  const details = document.getElementById('details');

  // Check if user is logged in
  const { token } = await chrome.storage.local.get('token');
  if (token) {
    details.textContent = "You are logged In.."
    btn.textContent = 'Logout';
    btn.onclick = async () => {
      await chrome.storage.local.remove('token');
      btn.textContent = 'Login';
      details.textContent = "Please login for using UttarAI"
    };
  } else {
    details.textContent = "Please login for using UttarAI"
    btn.textContent = 'Login';
    btn.onclick = () => {
      chrome.tabs.create({ url: chrome.runtime.getURL('login.html') });
    };
  }
});
