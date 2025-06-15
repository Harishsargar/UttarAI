document.getElementById("logout").onclick = () => {
    chrome.storage.local.set({ signed_in: false }, () => {
        // Dynamically update popup
        chrome.action.setPopup({ popup: 'popup_sign_in.html' }, () => {
            // Close popup so user clicks again and sees updated view
            window.close();
        });
    });
}